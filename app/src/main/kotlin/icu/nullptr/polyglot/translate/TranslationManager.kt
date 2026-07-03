package icu.nullptr.polyglot.translate

import android.util.Log
import icu.nullptr.polyglot.captions.CaptionCue
import icu.nullptr.polyglot.module
import icu.nullptr.polyglot.translate.providers.GoogleTranslator
import icu.nullptr.polyglot.translate.providers.MicrosoftTranslator
import icu.nullptr.polyglot.translate.providers.OpenAICompatibleTranslator
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import java.util.concurrent.RejectedExecutionException
import java.util.concurrent.atomic.AtomicInteger

object TranslationManager {
    const val TAG = "TranslationManager"
    const val MIN_TIMEOUT_MS = 5_000
    const val BASE_RETRY_DELAY_MS = 750L

    private val inFlight = ConcurrentHashMap.newKeySet<String>()
    private val executor = Executors.newFixedThreadPool(4, ThreadFactory)

    fun translateAsync(
        text: String,
        context: String,
        onTranslated: (original: String, translated: String) -> Unit,
    ): Boolean {
        val original = CaptionCue.normalize(text)
        if (original.isEmpty()) {
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
        val maxRetries = module.config.maxRetries.coerceAtLeast(0)

        while (attempt <= maxRetries) {
            runCatching {
                val request = TranslationRequest(
                    texts = listOf(text),
                    sourceLanguage = "auto",
                    targetLanguage = module.config.targetLanguage,
                    context = context,
                    timeoutMs = module.config.requestTimeoutMs.coerceAtLeast(MIN_TIMEOUT_MS),
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
        when (module.config.provider.lowercase(Locale.ROOT)) {
            "openai", "openai-compatible", "custom" -> OpenAICompatibleTranslator
            "google" -> GoogleTranslator
            "microsoft" -> MicrosoftTranslator
            else -> MicrosoftTranslator
        }

    private fun retryDelayMs(attempt: Int): Long =
        BASE_RETRY_DELAY_MS * (attempt + 1)

    private object ThreadFactory : java.util.concurrent.ThreadFactory {
        private val counter = AtomicInteger(0)

        override fun newThread(runnable: Runnable): Thread =
            Thread(runnable, "PolyglotYT-Translator-${counter.incrementAndGet()}").apply {
                isDaemon = true
            }
    }
}
