package icu.nullptr.polyglot.captions

import icu.nullptr.polyglot.module

object BilingualFormatter {
    fun format(original: CharSequence?, translated: String?): CharSequence {
        val originalText = original?.toString().orEmpty()
        if (translated.isNullOrBlank() || CaptionCue.normalize(translated) == CaptionCue.normalize(originalText)) {
            return original ?: ""
        }
        return if (module.config.bilingualOrder == "translation_first") {
            "$translated\n$originalText"
        } else {
            "$originalText\n$translated"
        }
    }
}
