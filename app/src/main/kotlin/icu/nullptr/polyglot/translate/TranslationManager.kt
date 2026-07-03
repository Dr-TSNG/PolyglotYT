package icu.nullptr.polyglot.translate

import android.util.Log
import icu.nullptr.polyglot.captions.CaptionCue
import icu.nullptr.polyglot.core.ConfigManager
import icu.nullptr.polyglot.module
import icu.nullptr.polyglot.translate.providers.GoogleTranslator
import icu.nullptr.polyglot.translate.providers.MicrosoftTranslator
import icu.nullptr.polyglot.translate.providers.OpenAICompatibleTranslator
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.RejectedExecutionException
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

class TranslationManager(
    private val prefs: ConfigManager,
) : AutoCloseable {
    private val closed = AtomicBoolean(false)
    private val inFlight = ConcurrentHashMap.newKeySet<String>()
    private val googleTranslator = GoogleTranslator()
    private val microsoftTranslator = MicrosoftTranslator()
    private val openAICompatibleTranslator = OpenAICompatibleTranslator(prefs)

    private val executor: ExecutorService =
        Executors.newFixedThreadPool(prefs.maxConcurrency.coerceAtLeast(1), ThreadFactory)

    fun translateAsync(
        text: String,
        context: String,
        onTranslated: (original: String, translated: String) -> Unit,
    ): Boolean {
        val original = CaptionCue.normalize(text)
        if (closed.get() || original.isEmpty() || prefs.sourceLanguage == prefs.targetLanguage) {
            return false
        }

        if (!inFlight.add(original)) {
            return false
        }

        return try {
            executor.execute {
                try {
                    val translated = translateWithRetry(original, context)
                    if (translated.isNotBlank()) {
                        onTranslated(original, translated)
                    }
                } catch (e: Throwable) {
                    module.log(Log.WARN, TAG, "Caption translation failed, length=${original.length}", e)
                } finally {
                    inFlight.remove(original)
                }
            }
            true
        } catch (_: RejectedExecutionException) {
            inFlight.remove(original)
            false
        }
    }

    private fun translateWithRetry(text: String, context: String): String {
        var attempt = 0
        var lastError: Throwable? = null
        val maxRetries = prefs.maxRetries.coerceAtLeast(0)

        while (attempt <= maxRetries && !closed.get()) {
            runCatching {
                val request = TranslationRequest(
                    texts = listOf(text),
                    sourceLanguage = prefs.sourceLanguage,
                    targetLanguage = prefs.targetLanguage,
                    context = context,
                    timeoutMs = prefs.requestTimeoutMs.coerceAtLeast(MIN_TIMEOUT_MS),
                )
                return translator().translate(request).texts.firstOrNull().orEmpty()
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

    private fun translator(): Translator =
        when (prefs.provider.lowercase(Locale.ROOT)) {
            "openai", "openai-compatible", "custom" -> openAICompatibleTranslator
            "google" -> googleTranslator
            "microsoft" -> microsoftTranslator
            else -> microsoftTranslator
        }

    private fun retryDelayMs(attempt: Int): Long =
        BASE_RETRY_DELAY_MS * (attempt + 1)

    override fun close() {
        if (closed.compareAndSet(false, true)) {
            executor.shutdownNow()
            inFlight.clear()
        }
    }

    private object ThreadFactory : java.util.concurrent.ThreadFactory {
        private val counter = AtomicInteger(0)

        override fun newThread(runnable: Runnable): Thread =
            Thread(runnable, "PolyglotYT-Translator-${counter.incrementAndGet()}").apply {
                isDaemon = true
            }
    }

    private companion object {
        const val TAG = "TranslationManager"
        const val MIN_TIMEOUT_MS = 5_000
        const val BASE_RETRY_DELAY_MS = 750L
    }
}
