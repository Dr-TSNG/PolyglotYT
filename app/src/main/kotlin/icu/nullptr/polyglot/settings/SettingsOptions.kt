package icu.nullptr.polyglot.settings

import icu.nullptr.polyglot.R
import icu.nullptr.polyglot.core.ConfigManager
import icu.nullptr.polyglot.module

object SettingsOptions {

    val providers = listOf(
        SettingsOption(ConfigManager.PROVIDER_GOOGLE, "Google Translate"),
        SettingsOption(ConfigManager.PROVIDER_MICROSOFT, "Microsoft Translator"),
        SettingsOption(ConfigManager.PROVIDER_OPENAI, "OpenAI compatible"),
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
        SettingsOption(ConfigManager.SUBTITLE_ORIGINAL_FIRST, module.res.getString(R.string.subtitle_mode_original_first)),
        SettingsOption(ConfigManager.SUBTITLE_TRANSLATION_FIRST, module.res.getString(R.string.subtitle_mode_translation_first)),
        SettingsOption(ConfigManager.SUBTITLE_TRANSLATION_ONLY, module.res.getString(R.string.subtitle_mode_translation_only))
    )

    val translationScales = listOf(
        SettingsOption("0.6", "60%"),
        SettingsOption("0.7", "70%"),
        SettingsOption("0.75", "75%"),
        SettingsOption("0.8", "80%"),
        SettingsOption("0.9", "90%"),
        SettingsOption("1.0", "100%"),
    )

    val baseScales = listOf(
        SettingsOption("0.8", "80%"),
        SettingsOption("0.9", "90%"),
        SettingsOption("1.0", "100%"),
        SettingsOption("1.1", "110%"),
        SettingsOption("1.25", "125%"),
        SettingsOption("1.4", "140%"),
    )

    val lineWidths = listOf(
        SettingsOption("40", "Narrow"),
        SettingsOption("52", "Normal"),
        SettingsOption("60", "Wide"),
        SettingsOption("72", "Extra wide"),
    )

    val translationColors = listOf(
        SettingsOption("white", "White"),
        SettingsOption("dim", "Dim White"),
        SettingsOption("faint", "Faint White"),
    )

    val subtitleSeparators = listOf(
        SettingsOption("", "None"),
        SettingsOption("·", "· (dot)"),
        SettingsOption("—", "— (dash)"),
        SettingsOption("»", "» (arrow)"),
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

    fun translationScaleLabel(value: String): String =
        translationScales.firstOrNull { it.value == value }?.label ?: value

    fun baseScaleLabel(value: String): String =
        baseScales.firstOrNull { it.value == value }?.label ?: value

    fun lineWidthLabel(value: String): String =
        lineWidths.firstOrNull { it.value == value }?.label ?: value

    fun translationColorLabel(value: String): String =
        translationColors.firstOrNull { it.value == value }?.label ?: value

    fun subtitleSeparatorLabel(value: String): String =
        subtitleSeparators.firstOrNull { it.value == value }?.label ?: value.ifEmpty { module.res.getString(R.string.not_set) }

    fun textOrNotSet(value: String): String =
        value.ifBlank { module.res.getString(R.string.not_set) }

    fun secretSummary(value: String): String =
        if (value.isBlank()) module.res.getString(R.string.not_set) else "********"
}

data class SettingsOption(
    val value: String,
    val label: String,
)
