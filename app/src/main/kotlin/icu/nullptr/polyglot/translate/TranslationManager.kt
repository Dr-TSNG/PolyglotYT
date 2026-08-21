package icu.nullptr.polyglot.translate

import icu.nullptr.polyglot.captions.CaptionCue
import icu.nullptr.polyglot.module
import icu.nullptr.polyglot.translate.providers.MicrosoftTranslator
import icu.nullptr.polyglot.translate.providers.OpenAICompatibleTranslator
import icu.nullptr.polyglot.translate.providers.YouTubeCommentTranslator
import icu.nullptr.polyglot.util.logV
import icu.nullptr.polyglot.util.logW
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import java.util.concurrent.PriorityBlockingQueue
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong

/**
 * Batched, priority-aware translation pipeline.
 *
 * Design notes (inspired by immersive-translate's approach of translating
 * visible content first and amortizing network latency via batching):
 *
 * - All requests are queued with a priority. The cue currently on screen is
 *   enqueued with the highest priority, upcoming cues with medium priority,
 *   and the full timeline at background priority. Priorities are upgradable:
 *   re-enqueuing the same text while it is still queued replaces the entry
 *   when the new priority is higher, so the visible cue jumps the queue.
 * - A small pool of workers drains the queue; each worker coalesces up to
 *   [translationBatchSize] pending texts (or waits up to
 *   [translationBatchWindowMs]) into a single provider request. Providers
 *   that accept multiple texts per call (Microsoft, OpenAI-compatible,
 *   YouTube comment action) then translate N lines in one round trip instead
 *   of N.
 * - Deduplication: at most one queued entry per (text, language) key, and
 *   nothing is enqueued while that key is being translated. Failures are
 *   reported through an [onFailed] callback so callers can install a negative
 *   cache.
 */
object TranslationManager {
    const val TAG = "TranslationManager"
    const val MIN_TIMEOUT_MS = 5_000
    const val BASE_RETRY_DELAY_MS = 750L

    const val PRIORITY_VISIBLE = 0
    const val PRIORITY_UPCOMING = 1
    const val PRIORITY_BACKGROUND = 2

    private const val WORKER_COUNT = 4
    private const val VISIBLE_REQUEST_TIMEOUT_CAP_MS = 15_000

    private data class QueuedTranslation(
        val texts: List<String>,
        val context: String,
        val sourceLanguage: String,
        val priority: Int,
        val sequence: Long,
        val onTranslated: (original: String, translated: String) -> Unit,
        val onFailed: (original: String) -> Unit,
    ) {
        val text: String get() = texts.first()
    }

    private val pendingQueue = PriorityBlockingQueue<QueuedTranslation>(64) { a, b ->
        val byPriority = a.priority.compareTo(b.priority)
        if (byPriority != 0) byPriority else a.sequence.compareTo(b.sequence)
    }
    private val queueLock = Any()
    private val queuedByKey = HashMap<String, QueuedTranslation>()
    private val inFlight = ConcurrentHashMap.newKeySet<String>()
    private val sequenceCounter = AtomicLong(0L)
    private val executor = Executors.newFixedThreadPool(WORKER_COUNT, ThreadFactory)

    init {
        repeat(WORKER_COUNT) { index ->
            executor.execute { workerLoop(index) }
        }
    }

    /**
     * Enqueue a translation request. Returns `false` when the text is empty or
     * already being translated.
     *
     * If the same (text, language) is already queued, the entry is replaced
     * when [priority] is higher (smaller number), so a cue that just became
     * visible overtakes the background queue.
     */
    fun enqueue(
        text: String,
        context: String,
        sourceLanguage: String,
        priority: Int = PRIORITY_BACKGROUND,
        onTranslated: (original: String, translated: String) -> Unit,
        onFailed: (original: String) -> Unit = {},
    ): Boolean {
        val original = CaptionCue.normalize(text)
        if (original.isEmpty()) {
            return false
        }

        val requestKey = requestKey(original, sourceLanguage)
        if (requestKey in inFlight) {
            return false
        }

        val item = QueuedTranslation(
            texts = listOf(original),
            context = context,
            sourceLanguage = sourceLanguage,
            priority = priority,
            sequence = sequenceCounter.incrementAndGet(),
            onTranslated = onTranslated,
            onFailed = onFailed,
        )

        synchronized(queueLock) {
            val existing = queuedByKey[requestKey]
            when {
                existing == null -> {
                    queuedByKey[requestKey] = item
                    pendingQueue.offer(item)
                }
                priority < existing.priority -> {
                    queuedByKey[requestKey] = item
                    pendingQueue.remove(existing)
                    pendingQueue.offer(item)
                }
                else -> {
                    // Same or lower priority: keep the queued entry.
                }
            }
        }
        return true
    }

    /**
     * Enqueue a whole paragraph of consecutive cues as a single translation
     * request. Sending multiple consecutive subtitle lines together lets the
     * provider see the surrounding context, which produces much more coherent
     * translations (the same technique immersive-translate uses by submitting
     * the whole transcript at once). Results are mapped back line by line.
     *
     * Returns `false` when every line is already translated or the paragraph
     * key is already in flight.
     */
    fun enqueueParagraph(
        texts: List<String>,
        context: String,
        sourceLanguage: String,
        priority: Int = PRIORITY_BACKGROUND,
        onTranslated: (original: String, translated: String) -> Unit,
        onFailed: (original: String) -> Unit = {},
    ): Boolean {
        val normalized = texts.map { CaptionCue.normalize(it) }.filter { it.isNotEmpty() }
        if (normalized.isEmpty()) return false
        if (normalized.size == 1) {
            return enqueue(normalized[0], context, sourceLanguage, priority, onTranslated, onFailed)
        }

        val requestKey = paragraphKey(normalized, sourceLanguage)
        if (requestKey in inFlight) return false

        val item = QueuedTranslation(
            texts = normalized,
            context = context,
            sourceLanguage = sourceLanguage,
            priority = priority,
            sequence = sequenceCounter.incrementAndGet(),
            onTranslated = onTranslated,
            onFailed = onFailed,
        )

        synchronized(queueLock) {
            val existing = queuedByKey[requestKey]
            when {
                existing == null -> {
                    queuedByKey[requestKey] = item
                    pendingQueue.offer(item)
                }
                priority < existing.priority -> {
                    queuedByKey[requestKey] = item
                    pendingQueue.remove(existing)
                    pendingQueue.offer(item)
                }
                else -> {
                    // Same or lower priority: keep the queued entry.
                }
            }
        }
        return true
    }

    private fun workerLoop(index: Int) {
        while (true) {
            try {
                val first = pendingQueue.take()
                val batch = ArrayList<QueuedTranslation>()
                batch.add(first)

                // For LLM-style providers (OpenAI-compatible), keep batches at
                // one line and let the worker pool provide concurrency: a
                // single completion that must produce N lines takes ~N times
                // as long as one line, which is why large batches feel slow on
                // custom APIs. Immersive-translate sends small segments
                // concurrently for the same reason. Batch batching only pays
                // off for Microsoft/Google which translate arrays in one shot.
                val provider = module.config.provider.lowercase(Locale.ROOT)
                val batchSize = if (provider == "openai" || provider == "openai-compatible" || provider == "custom") {
                    1
                } else {
                    module.config.translationBatchSize.coerceIn(1, 64)
                }
                // The currently visible cue must not wait for the batch window:
                // dispatch it immediately so the user sees the translation as
                // soon as the provider answers.
                val windowMs: Long =
                    if (first.priority == PRIORITY_VISIBLE || batchSize == 1) 0L
                    else module.config.translationBatchWindowMs.coerceIn(0, 2_000).toLong()
                val deadline = System.currentTimeMillis() + windowMs

                while (batch.size < batchSize) {
                    val remaining = deadline - System.currentTimeMillis()
                    if (remaining <= 0) break
                    val next = pendingQueue.poll(remaining, TimeUnit.MILLISECONDS) ?: break
                    batch.add(next)
                }

                dispatchBatch(batch)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
                return
            } catch (e: Throwable) {
                logW(TAG, "Translation worker $index crashed", e)
            }
        }
    }

    private fun dispatchBatch(batch: List<QueuedTranslation>) {
        // Claim the queued entries: an entry that was replaced by a
        // higher-priority duplicate is stale and must not be translated. The
        // in-flight slot is only taken if no other worker already claimed the
        // same key, so the same text is never translated twice concurrently.
        val claimed = ArrayList<QueuedTranslation>(batch.size)
        synchronized(queueLock) {
            for (item in batch) {
                val key = itemKey(item)
                if (queuedByKey.remove(key, item) && inFlight.add(key)) {
                    claimed.add(item)
                }
            }
        }
        if (claimed.isEmpty()) return

        // Visible cues get a shorter timeout so a slow provider cannot keep
        // the on-screen subtitle waiting indefinitely.
        val containsVisible = claimed.any { it.priority == PRIORITY_VISIBLE }
        val timeoutMs = if (containsVisible) {
            module.config.requestTimeoutMs
                .coerceAtLeast(MIN_TIMEOUT_MS)
                .coerceAtMost(VISIBLE_REQUEST_TIMEOUT_CAP_MS)
        } else {
            module.config.requestTimeoutMs.coerceAtLeast(MIN_TIMEOUT_MS)
        }

        try {
            val translated = translateWithRetry(claimed, timeoutMs)
            // Flattened results map back to each item's texts in order.
            var index = 0
            for (item in claimed) {
                for (text in item.texts) {
                    val result = translated.getOrNull(index) ?: break
                    index++
                    if (result.isNotBlank()) {
                        logV(TAG, "Translated [${index - 1}] ${shorten(text)} -> ${shorten(result)}")
                        runCatching { item.onTranslated(text, result) }
                            .onFailure { e -> logW(TAG, "Translation callback failed", e) }
                    } else {
                        logW(TAG, "Blank translation for ${shorten(text)}")
                    }
                }
            }
        } catch (e: Throwable) {
            logW(TAG, "Caption translation failed, batchSize=${claimed.size}", e)
            for (item in claimed) {
                runCatching { item.onFailed(item.text) }
            }
        } finally {
            for (item in claimed) {
                inFlight.remove(itemKey(item))
            }
        }
    }

    private fun translateWithRetry(batch: List<QueuedTranslation>, timeoutMs: Int): List<String> {
        var attempt = 0
        var lastError: Throwable? = null
        val maxRetries = module.config.maxRetries.coerceAtLeast(0)

        while (attempt <= maxRetries) {
            runCatching {
                return translateBatch(batch = batch, timeoutMs = timeoutMs)
            }.onFailure { error ->
                lastError = error
                if (attempt < maxRetries) {
                    Thread.sleep(retryDelayMs(attempt))
                }
            }
            attempt++
        }

        throw lastError ?: IllegalStateException("Translation cancelled")
    }

    private fun translateBatch(batch: List<QueuedTranslation>, timeoutMs: Int): List<String> {
        val texts = batch.flatMap { it.texts }
        if (texts.isEmpty()) return emptyList()
        val request = TranslationRequest(
            texts = texts,
            sourceLanguage = normalizedSourceLanguage(batch.first().sourceLanguage),
            targetLanguage = module.config.targetLanguage,
            context = batch.first().context,
            timeoutMs = timeoutMs,
        )
        val result = translator().translate(request)
        if (result.texts.size != texts.size) {
            throw IllegalStateException(
                "Provider returned ${result.texts.size} results for ${texts.size} texts",
            )
        }
        return result.texts
    }

    fun translateForConnectivityTest(
        text: String,
        context: String,
        sourceLanguage: String,
        timeoutMs: Int,
    ): String =
        translateOnce(
            text = CaptionCue.normalize(text),
            context = context,
            sourceLanguage = sourceLanguage,
            timeoutMs = timeoutMs.coerceAtLeast(MIN_TIMEOUT_MS),
        )

    private fun translateOnce(
        text: String,
        context: String,
        sourceLanguage: String,
        timeoutMs: Int,
    ): String {
        if (text.isBlank()) {
            return ""
        }

        val request = TranslationRequest(
            texts = listOf(text),
            sourceLanguage = normalizedSourceLanguage(sourceLanguage),
            targetLanguage = module.config.targetLanguage,
            context = context,
            timeoutMs = timeoutMs,
        )
        return translator().translate(request).texts.firstOrNull().orEmpty()
    }

    private fun translator(): Translator =
        when (module.config.provider.lowercase(Locale.ROOT)) {
            "openai", "openai-compatible", "custom" -> OpenAICompatibleTranslator
            "microsoft" -> MicrosoftTranslator
            else -> YouTubeCommentTranslator
        }

    private fun retryDelayMs(attempt: Int): Long =
        BASE_RETRY_DELAY_MS * (attempt + 1)

    private fun itemKey(item: QueuedTranslation): String =
        if (item.texts.size == 1) requestKey(item.text, item.sourceLanguage)
        else paragraphKey(item.texts, item.sourceLanguage)

    private fun paragraphKey(texts: List<String>, sourceLanguage: String): String =
        listOf(
            module.config.provider,
            normalizedSourceLanguage(sourceLanguage),
            module.config.targetLanguage,
            "PARAGRAPH",
        ).joinToString(separator = "\n") + "\n" + texts.joinToString(separator = "\u0001")

    private fun requestKey(text: String, sourceLanguage: String): String =
        listOf(
            module.config.provider,
            normalizedSourceLanguage(sourceLanguage),
            module.config.targetLanguage,
            text,
        ).joinToString(separator = "\n")

    private fun normalizedSourceLanguage(language: String): String =
        language.trim().ifEmpty { "auto" }

    private fun shorten(text: String, max: Int = 60): String =
        if (text.length <= max) text else text.take(max) + "…"

    private object ThreadFactory : java.util.concurrent.ThreadFactory {
        private val counter = AtomicInteger(0)

        override fun newThread(runnable: Runnable): Thread =
            Thread(runnable, "PolyglotYT-Translator-${counter.incrementAndGet()}").apply {
                isDaemon = true
            }
    }
}
