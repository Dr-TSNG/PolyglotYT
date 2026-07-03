package icu.nullptr.polyglot.settings

import icu.nullptr.polyglot.R
import icu.nullptr.polyglot.core.ConfigManager
import icu.nullptr.polyglot.module

object SettingsOptions {
    val providers = listOf(
        SettingsOption("microsoft", "Microsoft Translator"),
        SettingsOption("google", "Google Translate"),
        SettingsOption("openai", "OpenAI compatible"),
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

    fun entrySummary(config: ConfigManager = module.config): String {
        if (!config.enabled) return module.res.getString(R.string.disabled)
        return "${providerLabel(config.provider)} -> ${languageLabel(config.targetLanguage)}"
    }

    fun enabledSummary(enabled: Boolean): String =
        if (enabled) module.res.getString(R.string.enabled)
        else module.res.getString(R.string.disabled)

    fun providerLabel(value: String): String =
        providers.firstOrNull { it.value == value }?.label ?: value

    fun languageLabel(value: String): String =
        targetLanguages.firstOrNull { it.value == value }?.label ?: value

    fun subtitleModeLabel(value: String): String =
        subtitleMode.firstOrNull { it.value == value }?.label ?: value
}

data class SettingsOption(
    val value: String,
    val label: String,
)
