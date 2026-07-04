package icu.nullptr.polyglot.translate

import icu.nullptr.polyglot.module

object ConnectivityTester {
    const val TEST_TEXT = "This is a test message for PolyglotYT connectivity."
    const val TEST_CONTEXT = "PolyglotYT connectivity test"
    const val TEST_SOURCE_LANGUAGE = "en"
    const val TEST_TIMEOUT_MS = 15_000

    fun testCurrentProvider(): ConnectivityTestResult =
        runCatching {
            val translated = TranslationManager.translateForConnectivityTest(
                text = TEST_TEXT,
                context = TEST_CONTEXT,
                sourceLanguage = TEST_SOURCE_LANGUAGE,
                timeoutMs = module.config.requestTimeoutMs.coerceIn(
                    TranslationManager.MIN_TIMEOUT_MS,
                    TEST_TIMEOUT_MS,
                ),
            )
            ConnectivityTestResult.Success(translated)
        }.getOrElse { error ->
            ConnectivityTestResult.Failure(error.message ?: error.javaClass.simpleName)
        }
}

sealed interface ConnectivityTestResult {
    data class Success(val response: String) : ConnectivityTestResult
    data class Failure(val message: String) : ConnectivityTestResult
}
