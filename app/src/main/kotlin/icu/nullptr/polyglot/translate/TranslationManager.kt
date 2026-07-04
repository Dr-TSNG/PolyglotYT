package icu.nullptr.polyglot.translate

import icu.nullptr.polyglot.captions.CaptionCue
import icu.nullptr.polyglot.module
import icu.nullptr.polyglot.translate.providers.GoogleTranslator
import icu.nullptr.polyglot.translate.providers.MicrosoftTranslator
import icu.nullptr.polyglot.translate.providers.OpenAICompatibleTranslator
import icu.nullptr.polyglot.util.logW
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

    private fun translateWithRetry(text: String, context: String, sourceLanguage: String): String {
        var attempt = 0
        var lastError: Throwable? = null
        val maxRetries = module.config.maxRetries.coerceAtLeast(0)

        while (attempt <= maxRetries) {
            runCatching {
                return translateOnce(
                    text = text,
                    context = context,
                    sourceLanguage = sourceLanguage,
                    timeoutMs = module.config.requestTimeoutMs.coerceAtLeast(MIN_TIMEOUT_MS),
                )
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
            "google" -> GoogleTranslator
            "microsoft" -> MicrosoftTranslator
            else -> MicrosoftTranslator
        }

    private fun retryDelayMs(attempt: Int): Long =
        BASE_RETRY_DELAY_MS * (attempt + 1)

    private fun requestKey(text: String, sourceLanguage: String): String =
        listOf(
            module.config.provider,
            normalizedSourceLanguage(sourceLanguage),
            module.config.targetLanguage,
            text,
        ).joinToString(separator = "\n")

    private fun normalizedSourceLanguage(language: String): String =
        language.trim().ifEmpty { "auto" }

    private object ThreadFactory : java.util.concurrent.ThreadFactory {
        private val counter = AtomicInteger(0)

        override fun newThread(runnable: Runnable): Thread =
            Thread(runnable, "PolyglotYT-Translator-${counter.incrementAndGet()}").apply {
                isDaemon = true
            }
    }
}
