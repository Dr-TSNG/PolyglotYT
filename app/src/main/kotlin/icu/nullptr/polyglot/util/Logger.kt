package icu.nullptr.polyglot.util

import android.util.Log
import icu.nullptr.polyglot.module

fun logV(tag: String, message: String, tr: Throwable? = null) {
    module.log(Log.VERBOSE, tag, message, tr)
}

fun logD(tag: String, message: String, tr: Throwable? = null) {
    module.log(Log.DEBUG, tag, message, tr)
}

fun logI(tag: String, message: String, tr: Throwable? = null) {
    module.log(Log.INFO, tag, message, tr)
}

fun logW(tag: String, message: String, tr: Throwable? = null) {
    module.log(Log.WARN, tag, message, tr)
}

fun logE(tag: String, message: String, tr: Throwable? = null) {
    module.log(Log.ERROR, tag, message, tr)
}
