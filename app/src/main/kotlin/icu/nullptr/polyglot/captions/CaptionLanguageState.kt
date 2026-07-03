package icu.nullptr.polyglot.captions

import android.util.Log
import icu.nullptr.polyglot.module
import java.lang.reflect.Field
import java.lang.reflect.Modifier
import java.util.Locale

object CaptionLanguageState {
    private const val TAG = "CaptionLanguageState"
    private const val AUTO_LANGUAGE = "auto"
    private const val DISABLE_CAPTIONS_OPTION = "DISABLE_CAPTIONS_OPTION"
    private const val AUTO_TRANSLATE_CAPTIONS_OPTION = "AUTO_TRANSLATE_CAPTIONS_OPTION"

    private val languagePattern = Regex("^[A-Za-z]{2,3}([_-][A-Za-z0-9]{2,8}){0,3}$")

    @Volatile
    private var sourceLanguage = AUTO_LANGUAGE

    fun currentSourceLanguage(): String =
        sourceLanguage.ifBlank { AUTO_LANGUAGE }

    fun updateFromCaptionTrack(track: Any?, source: String): Boolean {
        val info = CaptionTrackInfo.from(track) ?: return false
        if (info.isPseudoOption) return false

        val language = info.languageCode ?: return false
        val previous = sourceLanguage
        if (previous == language) return false

        sourceLanguage = language
        module.log(
            Log.INFO,
            TAG,
            "Detected YouTube caption language: $previous -> $language from $source",
        )
        return true
    }

    private data class CaptionTrackInfo(
        val languageCode: String?,
        val isPseudoOption: Boolean,
    ) {
        companion object {
            fun from(track: Any?): CaptionTrackInfo? {
                if (track == null) return null

                val strings = track.instanceStringValues()
                if (strings.isEmpty()) return null

                val isPseudoOption = strings.any {
                    it == DISABLE_CAPTIONS_OPTION || it == AUTO_TRANSLATE_CAPTIONS_OPTION
                }
                val directLanguage = strings.firstNotNullOfOrNull { normalizeLanguageCode(it) }
                val vssLanguage = strings.firstNotNullOfOrNull { languageFromVssId(it) }

                return CaptionTrackInfo(
                    languageCode = directLanguage ?: vssLanguage,
                    isPseudoOption = isPseudoOption,
                )
            }
        }
    }

    private fun Any.instanceStringValues(): List<String> {
        val values = ArrayList<String>()
        var clazz: Class<*>? = javaClass
        while (clazz != null) {
            for (field in clazz.declaredFields) {
                if (Modifier.isStatic(field.modifiers) || field.type != String::class.java) {
                    continue
                }
                val value = field.getStringOrNull(this)?.trim()
                if (!value.isNullOrEmpty()) {
                    values += value
                }
            }
            clazz = clazz.superclass
        }
        return values
    }

    private fun Field.getStringOrNull(instance: Any): String? =
        runCatching {
            isAccessible = true
            get(instance) as? String
        }.getOrNull()

    private fun normalizeLanguageCode(value: String): String? {
        val trimmed = value.trim()
        if (trimmed == DISABLE_CAPTIONS_OPTION || trimmed == AUTO_TRANSLATE_CAPTIONS_OPTION) {
            return null
        }
        if (trimmed.length > 24 || !languagePattern.matches(trimmed)) {
            return null
        }

        val parts = trimmed.replace('_', '-')
            .split('-')
            .filter { it.isNotBlank() }
        if (parts.isEmpty()) return null

        val primary = when (parts.first().lowercase(Locale.ROOT)) {
            "iw" -> "he"
            "in" -> "id"
            "jw" -> "jv"
            else -> parts.first().lowercase(Locale.ROOT)
        }
        if (primary == "und" || primary == "asr") return null

        if (primary == "zh") {
            return normalizeChinese(parts)
        }

        val normalized = buildList {
            add(primary)
            parts.drop(1).forEach { add(normalizeLanguageSubtag(it)) }
        }
        return normalized.joinToString("-")
    }

    private fun normalizeChinese(parts: List<String>): String {
        val lowered = parts.joinToString("-").lowercase(Locale.ROOT)
        return when {
            lowered.contains("hant") || lowered.endsWith("-tw") ||
                lowered.endsWith("-hk") || lowered.endsWith("-mo") -> "zh-Hant"

            lowered.contains("hans") || lowered.endsWith("-cn") ||
                lowered.endsWith("-sg") -> "zh-Hans"

            else -> "zh"
        }
    }

    private fun normalizeLanguageSubtag(value: String): String =
        when (value.length) {
            2 -> value.uppercase(Locale.ROOT)
            4 -> value.lowercase(Locale.ROOT).replaceFirstChar { it.uppercaseChar() }
            else -> value.lowercase(Locale.ROOT)
        }

    private fun languageFromVssId(value: String): String? {
        val trimmed = value.trim()
        if (trimmed.isEmpty() || trimmed == "-") return null

        val candidate = when {
            trimmed.startsWith("a.") -> trimmed.substringAfter("a.")
            trimmed.startsWith(".") -> trimmed.substringAfter(".")
            trimmed.startsWith("t.") -> trimmed.substringAfterLast(".")
            trimmed.startsWith("t") && "." in trimmed -> trimmed.substringAfterLast(".")
            else -> return null
        }
        return normalizeLanguageCode(candidate)
    }
}
