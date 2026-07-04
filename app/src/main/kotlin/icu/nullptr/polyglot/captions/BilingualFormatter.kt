package icu.nullptr.polyglot.captions

import icu.nullptr.polyglot.core.ConfigManager
import icu.nullptr.polyglot.module

object BilingualFormatter {
    fun format(original: CharSequence?, translated: String?): CharSequence {
        val originalText = CaptionCue.normalize(original?.toString().orEmpty())
        val translatedText = CaptionCue.normalize(translated.orEmpty())
        if (translatedText.isBlank() || translatedText == originalText) {
            return original ?: ""
        }
        return when (module.config.subtitleMode) {
            ConfigManager.SUBTITLE_ORIGINAL_FIRST -> "$originalText\n$translatedText"
            ConfigManager.SUBTITLE_TRANSLATION_FIRST -> "$translatedText\n$originalText"
            ConfigManager.SUBTITLE_TRANSLATION_ONLY -> translatedText
            else -> translatedText
        }
    }
}
