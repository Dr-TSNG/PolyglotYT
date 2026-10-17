package icu.nullptr.polyglot.translate

import icu.nullptr.polyglot.captions.CaptionCue
import icu.nullptr.polyglot.module
import icu.nullptr.polyglot.translate.providers.MicrosoftTranslator
import icu.nullptr.polyglot.translate.providers.OpenAICompatibleTranslator
import icu.nullptr.polyglot.translate.providers.YouTubeCommentTranslator
import icu.nullptr.polyglot.util.logD
import icu.nullptr.polyglot.util.logW
import java.util.ArrayDeque
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import java.util.concurrent.RejectedExecutionException
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.ThreadFactory
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

object TranslationManager {
    const val TAG = "TranslationManager"
    const val MIN_TIMEOUT_MS = 5_000
    const val BASE_RETRY_DELAY_MS = 750L

    private const val BATCH_MAX_ITEMS = 8
    private const val BATCH_MAX_CHARS = 1_600
    private const val BATCH_DEBOUNCE_MS = 300L
    private const val BATCH_TIMEOUT_CAP_MS = 30_000
    private const val BATCH_ATTEMPTS = 2
    private const val FAILURE_COOLDOWN_MS = 30_000L

    private val inFlight = ConcurrentHashMap.newKeySet<String>()
    private val failureUntil = ConcurrentHashMap<String, Long>()

    private val executor = Executors.newFixedThreadPool(
        4,
        DaemonThreadFactory("PolyglotYT-Translator"),
    )
    private val batchExecutor = Executors.newFixedThreadPool(
        2,
        DaemonThreadFactory("PolyglotYT-Batch"),
    )
    private val scheduler = Executors.newSingleThreadScheduledExecutor(
        DaemonThreadFactory("PolyglotYT-BatchScheduler"),
    )

    private val lock = Any()
    private val pending = ArrayDeque<PendingCaption>()
    private var scheduledFlush: ScheduledFuture<*>? = null
    private var currentLane: BatchLane? = null

    @Volatile
    private var generation = 0L

    /**
     * Queues a caption for translation. Several captions that arrive close to each
     * other are merged into one provider request when batch translation is enabled.
     */
    fun enqueueCaption(
        text: String,
        context: String,
        sourceLanguage: String,
        onTranslated: (original: String, translated: String) -> Unit,
    ): Boolean {
        val original = CaptionCue.normalize(text)
        if (original.isEmpty()) return false

        if (!module.config.batchTranslationEnabled || !supportsBatch()) {
            return translateAsync(original, context, sourceLanguage, onTranslated)
        }

        val key = requestKey(original, sourceLanguage)
        if (isCoolingDown(key)) return false
        if (!inFlight.add(key)) return false

        val lane = currentLane(sourceLanguage)
        val flushNow: Boolean
        synchronized(lock) {
            if (currentLane != lane) {
                generation++
                dropPendingLocked()
                currentLane = lane
                failureUntil.clear()
            }

            pending.addLast(
                PendingCaption(
                    text = original,
                    context = context,
                    lane = lane,
                    generation = generation,
                    key = key,
                    onTranslated = onTranslated,
                ),
            )

            val pendingChars = pending.sumOf { it.text.length }
            flushNow = pending.size >= BATCH_MAX_ITEMS || pendingChars >= BATCH_MAX_CHARS
            if (flushNow) {
                scheduledFlush?.cancel(false)
                scheduledFlush = null
            } else if (scheduledFlush == null) {
                scheduledFlush = scheduler.schedule(
                    { flush() },
                    BATCH_DEBOUNCE_MS,
                    TimeUnit.MILLISECONDS,
                )
            }
        }

        if (flushNow) {
            flush()
        }
        return true
    }

    /**
     * Drops queued work and invalidates translations that are still in flight.
     * Call this when the caption language, provider or video changes.
     */
    fun reset() {
        synchronized(lock) {
            generation++
            dropPendingLocked()
            currentLane = null
        }
        failureUntil.clear()
    }

    fun translateAsync(
        text: String,
        context: String,
        sourceLanguage: String,
        onTranslated: (original: String, translated: String) -> Unit,
    ): Boolean {
        val original = CaptionCue.normalize(text)
        if (original.isEmpty()) {
            return false
        }

        val requestKey = requestKey(original, sourceLanguage)
        if (!inFlight.add(requestKey)) {
            return false
        }

        return try {
            executor.execute {
                try {
                    val translated = translateWithRetry(original, context, sourceLanguage)
                    if (translated.isNotBlank()) {
                        onTranslated(original, translated)
                    }
                } catch (e: Throwable) {
                    logW(TAG, "Caption translation failed, length=${original.length}", e)
                } finally {
                    inFlight.remove(requestKey)
                }
            }
            true
        } catch (_: RejectedExecutionException) {
            inFlight.remove(requestKey)
            false
        }
    }

    fun translateForConnectivityTest(
        text: String,
        context: String,
        sourceLanguage: String,
        timeoutMs: Int,
    ): String =
        translate(
            TranslationRequest(
                texts = listOf(CaptionCue.normalize(text)),
                sourceLanguage = normalizedSourceLanguage(sourceLanguage),
                targetLanguage = module.config.targetLanguage,
                context = context,
                timeoutMs = timeoutMs.coerceAtLeast(MIN_TIMEOUT_MS),
                videoTitle = "",
            ),
        ).firstOrNull().orEmpty()

    private fun flush() {
        while (true) {
            val batch = synchronized(lock) {
                scheduledFlush = null
                if (pending.isEmpty()) return
                takeBatchLocked()
            }
            if (batch.isEmpty()) return
            batchExecutor.execute { runBatch(batch) }
        }
    }

    private fun takeBatchLocked(): List<PendingCaption> {
        val batch = ArrayList<PendingCaption>(BATCH_MAX_ITEMS)
        var chars = 0
        val iterator = pending.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (batch.isNotEmpty() &&
                (batch.size >= BATCH_MAX_ITEMS || chars + item.text.length > BATCH_MAX_CHARS)
            ) {
                break
            }
            batch += item
            iterator.remove()
            chars += item.text.length
        }
        return batch
    }

    private fun runBatch(batch: List<PendingCaption>) {
        if (batch.isEmpty()) return

        val batchGeneration = batch.first().generation
        if (batchGeneration != generation) {
            release(batch)
            return
        }

        val request = buildRequest(batch)
        if (batch.size > 1) {
            logD(
                TAG,
                "Translating batch: size=${batch.size}, chars=${batch.sumOf { it.text.length }}",
            )
        }
        val translations = try {
            translateBatchWithRetry(request)
        } catch (error: Throwable) {
            if (isFatal(error)) {
                batch.forEach { finishFailure(it, error, log = false) }
                logW(TAG, "Batch translation rejected by provider, size=${batch.size}", error)
                return
            }

            if (batch.size > 1) {
                logD(TAG, "Splitting failed batch of ${batch.size}")
                val middle = batch.size / 2
                runBatch(batch.subList(0, middle))
                runBatch(batch.subList(middle, batch.size))
            } else {
                finishFailure(batch.first(), error)
            }
            return
        }

        if (batchGeneration != generation) {
            release(batch)
            return
        }

        for ((index, item) in batch.withIndex()) {
            val translated = translations.getOrNull(index).orEmpty()
            if (translated.isNotBlank()) {
                deliver(item, translated)
                inFlight.remove(item.key)
            } else {
                retrySingle(item)
            }
        }
    }

    private fun retrySingle(item: PendingCaption) {
        if (item.generation != generation) {
            inFlight.remove(item.key)
            return
        }

        try {
            val translated = translateWithRetry(item.text, item.context, item.lane.sourceLanguage)
            if (translated.isNotBlank()) {
                deliver(item, translated)
                inFlight.remove(item.key)
            } else {
                finishFailure(item, IllegalStateException("Translation returned an empty result"))
            }
        } catch (error: Throwable) {
            finishFailure(item, error)
        }
    }

    private fun deliver(item: PendingCaption, translated: String) {
        if (item.generation != generation) return

        failureUntil.remove(item.key)
        runCatching { item.onTranslated(item.text, translated) }
            .onFailure { logW(TAG, "Caption translation callback failed", it) }
    }

    private fun finishFailure(item: PendingCaption, error: Throwable, log: Boolean = true) {
        failureUntil[item.key] = System.currentTimeMillis() + FAILURE_COOLDOWN_MS
        inFlight.remove(item.key)
        if (log) {
            logW(TAG, "Caption translation failed, length=${item.text.length}", error)
        }
    }

    private fun release(batch: List<PendingCaption>) {
        batch.forEach { inFlight.remove(it.key) }
    }

    private fun buildRequest(batch: List<PendingCaption>): TranslationRequest {
        val lane = batch.first().lane
        return TranslationRequest(
            texts = batch.map { it.text },
            sourceLanguage = normalizedSourceLanguage(lane.sourceLanguage),
            targetLanguage = lane.targetLanguage,
            context = batch.first().context,
            timeoutMs = batchTimeoutMs(),
            videoTitle = promptTitle(),
        )
    }

    private fun translateBatchWithRetry(request: TranslationRequest): List<String> {
        var lastError: Throwable? = null
        for (attempt in 0 until BATCH_ATTEMPTS) {
            try {
                return translate(request)
            } catch (error: Throwable) {
                lastError = error
                if (attempt >= BATCH_ATTEMPTS - 1 || !isRetryable(error)) break
                Thread.sleep(retryDelayMs(attempt))
            }
        }
        throw lastError ?: IllegalStateException("Translation cancelled")
    }

    private fun translateWithRetry(text: String, context: String, sourceLanguage: String): String {
        var attempt = 0
        var lastError: Throwable? = null
        val maxRetries = module.config.maxRetries.coerceAtLeast(0)

        while (attempt <= maxRetries) {
            try {
                return translate(
                    TranslationRequest(
                        texts = listOf(text),
                        sourceLanguage = normalizedSourceLanguage(sourceLanguage),
                        targetLanguage = module.config.targetLanguage,
                        context = context,
                        timeoutMs = requestTimeoutMs(),
                        videoTitle = promptTitle(),
                    ),
                ).firstOrNull().orEmpty()
            } catch (error: Throwable) {
                lastError = error
                if (attempt >= maxRetries || !isRetryable(error)) break
                Thread.sleep(retryDelayMs(attempt))
            }
            attempt++
        }

        throw lastError ?: IllegalStateException("Translation cancelled")
    }

    private fun translate(request: TranslationRequest): List<String> =
        translator().translate(request).texts

    private fun supportsBatch(): Boolean =
        when (module.config.provider.lowercase(Locale.ROOT)) {
            "openai", "openai-compatible", "custom" -> !isLegacyOpenAiPrompt()
            else -> false
        }

    private fun isLegacyOpenAiPrompt(): Boolean {
        val template = module.config.openAiUserPrompt
        return template.contains("{{origin}}") && !template.contains("{{lines}}")
    }

    private fun translator(): Translator =
        when (module.config.provider.lowercase(Locale.ROOT)) {
            "openai", "openai-compatible", "custom" -> OpenAICompatibleTranslator
            "microsoft" -> MicrosoftTranslator
            else -> YouTubeCommentTranslator
        }

    private fun currentLane(sourceLanguage: String): BatchLane =
        BatchLane(
            provider = module.config.provider.lowercase(Locale.ROOT),
            sourceLanguage = normalizedSourceLanguage(sourceLanguage),
            targetLanguage = module.config.targetLanguage,
        )

    private fun promptTitle(): String =
        if (module.config.includeVideoTitle) VideoContext.promptTitle() else "unknown"

    private fun requestTimeoutMs(): Int =
        module.config.requestTimeoutMs.coerceAtLeast(MIN_TIMEOUT_MS)

    private fun batchTimeoutMs(): Int =
        requestTimeoutMs().coerceAtMost(BATCH_TIMEOUT_CAP_MS)

    private fun retryDelayMs(attempt: Int): Long =
        BASE_RETRY_DELAY_MS * (attempt + 1)

    private fun isRetryable(error: Throwable): Boolean =
        !(error is TranslationHttpException && !error.isRetryable)

    private fun isFatal(error: Throwable): Boolean =
        error is TranslationHttpException && error.isFatal

    private fun isCoolingDown(key: String): Boolean {
        val until = failureUntil[key] ?: return false
        if (until > System.currentTimeMillis()) return true
        failureUntil.remove(key)
        return false
    }

    private fun dropPendingLocked() {
        while (pending.isNotEmpty()) {
            inFlight.remove(pending.removeFirst().key)
        }
        scheduledFlush?.cancel(false)
        scheduledFlush = null
    }

    private fun requestKey(text: String, sourceLanguage: String): String =
        listOf(
            module.config.provider.lowercase(Locale.ROOT),
            normalizedSourceLanguage(sourceLanguage),
            module.config.targetLanguage,
            text,
        ).joinToString(separator = "\n")

    private fun normalizedSourceLanguage(language: String): String =
        language.trim().ifEmpty { "auto" }

    private data class BatchLane(
        val provider: String,
        val sourceLanguage: String,
        val targetLanguage: String,
    )

    private data class PendingCaption(
        val text: String,
        val context: String,
        val lane: BatchLane,
        val generation: Long,
        val key: String,
        val onTranslated: (original: String, translated: String) -> Unit,
    )

    private class DaemonThreadFactory(private val prefix: String) : ThreadFactory {
        private val counter = AtomicInteger(0)

        override fun newThread(runnable: Runnable): Thread =
            Thread(runnable, "$prefix-${counter.incrementAndGet()}").apply {
                isDaemon = true
            }
    }
}
