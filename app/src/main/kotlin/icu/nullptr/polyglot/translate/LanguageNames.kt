package icu.nullptr.polyglot.translate

import java.util.Locale

object LanguageNames {
    fun displayName(language: String): String {
        val trimmed = language.trim()
        if (trimmed.isEmpty() || trimmed.equals("auto", ignoreCase = true)) {
            return "auto-detect"
        }

        val displayName = runCatching {
            Locale.forLanguageTag(trimmed).getDisplayName(Locale.ENGLISH)
        }.getOrNull()

        return displayName
            ?.takeIf {
                it.isNotBlank() &&
                    !it.equals(trimmed, ignoreCase = true) &&
                    !it.contains("(unknown)", ignoreCase = true)
            }
            ?: trimmed
    }
}
