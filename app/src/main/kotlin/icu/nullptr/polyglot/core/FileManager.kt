package icu.nullptr.polyglot.core

import android.content.Context

class FileManager(context: Context) {
    private val filesDir = context.filesDir.resolve("polyglotyt")

    val configDir = filesDir.resolve("config")
    val dexKitDir = filesDir.resolve("dexkit")
    val logDir = filesDir.resolve("logs")

    init {
        configDir.mkdirs()
        dexKitDir.mkdirs()
        logDir.mkdirs()
    }
}
