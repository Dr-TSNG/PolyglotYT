package icu.nullptr.polyglot.captions

import icu.nullptr.polyglot.module

object BilingualFormatter {
    fun format(original: CharSequence?, translated: String?): CharSequence {
        val originalText = CaptionCue.normalize(original?.toString().orEmpty())
        val translatedText = CaptionCue.normalize(translated.orEmpty())
        if (translatedText.isBlank() || translatedText == originalText) {
            return original ?: ""
        }
        return when (module.config.subtitleMode) {
            "original_first" -> "$originalText\n$translatedText"
            "translation_first" -> "$translatedText\n$originalText"
            else -> translatedText
        }
    }
}
