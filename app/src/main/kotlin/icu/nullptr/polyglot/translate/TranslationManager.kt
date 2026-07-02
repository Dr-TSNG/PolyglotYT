package icu.nullptr.polyglot.translate

import icu.nullptr.polyglot.core.ConfigManager
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class TranslationManager(prefs: ConfigManager) : AutoCloseable {
    private val executor: ExecutorService =
        Executors.newFixedThreadPool(prefs.maxConcurrency.coerceAtLeast(1))

    override fun close() {
        executor.shutdownNow()
    }
}
