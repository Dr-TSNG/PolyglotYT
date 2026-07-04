package icu.nullptr.polyglot.settings

import icu.nullptr.polyglot.R
import icu.nullptr.polyglot.module

object SettingsOptions {
    const val PROVIDER_OPENAI = "openai"

    val providers = listOf(
        SettingsOption("microsoft", "Microsoft Translator"),
        SettingsOption("google", "Google Translate"),
        SettingsOption(PROVIDER_OPENAI, "OpenAI compatible"),
    )

    val targetLanguages = listOf(
        SettingsOption("en", "English"),
        SettingsOption("zh-Hans", "Chinese (Simplified)"),
        SettingsOption("zh-Hant", "Chinese (Traditional)"),
        SettingsOption("ja", "Japanese"),
        SettingsOption("ko", "Korean"),
        SettingsOption("es", "Spanish"),
        SettingsOption("fr", "French"),
        SettingsOption("de", "German"),
        SettingsOption("ru", "Russian"),
    )

    val subtitleMode = listOf(
        SettingsOption("original_first", module.res.getString(R.string.subtitle_mode_original_first)),
        SettingsOption("translation_first", module.res.getString(R.string.subtitle_mode_translation_first)),
        SettingsOption("translation_only", module.res.getString(R.string.subtitle_mode_translation_only))
    )

    fun enabledSummary(enabled: Boolean): String =
        if (enabled) module.res.getString(R.string.enabled)
        else module.res.getString(R.string.disabled)

    fun providerLabel(value: String): String =
        providers.firstOrNull { it.value == value }?.label ?: value

    fun languageLabel(value: String): String =
        targetLanguages.firstOrNull { it.value == value }?.label ?: value

    fun subtitleModeLabel(value: String): String =
        subtitleMode.firstOrNull { it.value == value }?.label ?: value

    fun textOrNotSet(value: String): String =
        value.ifBlank { module.res.getString(R.string.not_set) }

    fun secretSummary(value: String): String =
        if (value.isBlank()) module.res.getString(R.string.not_set) else "********"
}

data class SettingsOption(
    val value: String,
    val label: String,
)
