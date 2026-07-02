package icu.nullptr.polyglot.captions

import java.util.concurrent.ConcurrentHashMap

class CaptionSession {
    private val translations = ConcurrentHashMap<String, String>()

    fun translationFor(original: String?): String? {
        if (original == null) return null
        return translations[normalize(original)]
    }

    fun putTranslation(original: String?, translated: String?) {
        if (original == null || translated == null) return
        translations[normalize(original)] = translated
    }

    private fun normalize(text: String): String =
        text.replace(Regex("\\s+"), " ").trim()
}
