package icu.nullptr.polyglot.util

import android.util.Log
import icu.nullptr.polyglot.module
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * File-based logger that mirrors the module's log output into a file in the
 * module's own directory. The user can export it from the PolyglotYT settings
 * page or pull it via adb, then send it back for debugging.
 */
object ModuleLogger {
    const val TAG = "PolyglotYT"

    private const val MAX_FILE_BYTES = 4L * 1024 * 1024

    @Volatile
    private var logFile: File? = null

    private val timestampFormat = SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.US)

    fun init(directory: File) {
        directory.mkdirs()
        logFile = File(directory, "polyglotyt.log")
        info("ModuleLogger", "Logger initialized at ${directory.absolutePath}")
    }

    fun verbose(tag: String, message: String) = log(Log.VERBOSE, tag, message, null)
    fun debug(tag: String, message: String) = log(Log.DEBUG, tag, message, null)
    fun info(tag: String, message: String) = log(Log.INFO, tag, message, null)
    fun warn(tag: String, message: String, tr: Throwable? = null) = log(Log.WARN, tag, message, tr)
    fun error(tag: String, message: String, tr: Throwable? = null) = log(Log.ERROR, tag, message, tr)

    private fun log(level: Int, tag: String, message: String, tr: Throwable?) {
        val line = buildString {
            append(timestampFormat.format(Date()))
            append(' ')
            append(levelChar(level))
            append(' ')
            append(tag)
            append(": ")
            append(message)
            if (tr != null) {
                append('\n')
                append(Log.getStackTraceString(tr))
            }
        }

        val file = logFile
        if (file != null) {
            runCatching {
                val append = file.length() <= MAX_FILE_BYTES
                FileOutputStream(file, append).use { out ->
                    out.write(line.toByteArray(Charsets.UTF_8))
                    out.write('\n'.code)
                }
            }
        }
    }

    fun logFilePath(): String? = logFile?.absolutePath

    fun clear() {
        runCatching { logFile?.delete() }
    }

    private fun levelChar(level: Int): Char =
        when (level) {
            Log.VERBOSE -> 'V'
            Log.DEBUG -> 'D'
            Log.INFO -> 'I'
            Log.WARN -> 'W'
            Log.ERROR -> 'E'
            else -> '?'
        }
}

/**
 * Logs to both the module log file and LSPosed. Guards against the module
 * lateinit not being initialized yet (e.g. hooks running before onModuleLoaded).
 */
private fun moduleLog(level: Int, tag: String, message: String, tr: Throwable?) {
    try {
        module.log(level, tag, message, tr)
    } catch (_: Throwable) {
        // module not initialized; file log already captured the line
    }
}

fun logV(tag: String, message: String, tr: Throwable? = null) {
    ModuleLogger.verbose(tag, message)
    moduleLog(Log.VERBOSE, tag, message, tr)
}

fun logD(tag: String, message: String, tr: Throwable? = null) {
    ModuleLogger.debug(tag, message)
    moduleLog(Log.DEBUG, tag, message, tr)
}

fun logI(tag: String, message: String, tr: Throwable? = null) {
    ModuleLogger.info(tag, message)
    moduleLog(Log.INFO, tag, message, tr)
}

fun logW(tag: String, message: String, tr: Throwable? = null) {
    ModuleLogger.warn(tag, message, tr)
    moduleLog(Log.WARN, tag, message, tr)
}

fun logE(tag: String, message: String, tr: Throwable? = null) {
    ModuleLogger.error(tag, message, tr)
    moduleLog(Log.ERROR, tag, message, tr)
}
