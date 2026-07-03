package icu.nullptr.polyglot.captions

import icu.nullptr.polyglot.module

object BilingualFormatter {
    fun format(original: CharSequence?, translated: String?): CharSequence {
        val originalText = CaptionCue.normalize(original?.toString().orEmpty())
        val translatedText = CaptionCue.normalize(translated.orEmpty())
        if (translatedText.isBlank() || translatedText == originalText) {
            return original ?: ""
        }
        return if (module.config.bilingualOrder == "translation_first") {
            "$translatedText\n$originalText"
        } else {
            "$originalText\n$translatedText"
        }
    }
}
