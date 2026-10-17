package icu.nullptr.polyglot.settings

import icu.nullptr.polyglot.R
import icu.nullptr.polyglot.core.ConfigManager
import icu.nullptr.polyglot.module
import icu.nullptr.polyglot.translate.providers.openai.OpenAIThinkingParams

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

    val thinkingModes = listOf(
        SettingsOption(ConfigManager.THINKING_MODE_DEFAULT, module.res.getString(R.string.thinking_mode_default)),
        SettingsOption(ConfigManager.THINKING_MODE_ENABLED, module.res.getString(R.string.thinking_mode_enabled)),
        SettingsOption(ConfigManager.THINKING_MODE_DISABLED, module.res.getString(R.string.thinking_mode_disabled)),
    )

    val thinkingParameters = listOf(
        SettingsOption(ConfigManager.THINKING_PARAMETER_THINKING, module.res.getString(R.string.thinking_parameter_thinking)),
        SettingsOption(ConfigManager.THINKING_PARAMETER_ENABLE_THINKING, module.res.getString(R.string.thinking_parameter_enable_thinking)),
    )

    fun enabledSummary(enabled: Boolean): String =
        if (enabled) module.res.getString(R.string.enabled)
        else module.res.getString(R.string.disabled)

    fun thinkingParameterLabel(value: String): String =
        thinkingParameters.firstOrNull { it.value == value }?.label ?: value

    fun thinkingSummary(mode: String, parameter: String, effort: String): String {
        val normalizedMode = mode.trim().lowercase()
        val normalizedEffort = OpenAIThinkingParams.normalizeEffort(effort)
        val parameterLabel = thinkingParameterLabel(parameter)
        return when (normalizedMode) {
            ConfigManager.THINKING_MODE_ENABLED -> if (normalizedEffort == null) {
                module.res.getString(R.string.thinking_summary_toggle, enabledLabel(), parameterLabel)
            } else {
                module.res.getString(
                    R.string.thinking_summary_toggle_effort,
                    enabledLabel(),
                    parameterLabel,
                    normalizedEffort,
                )
            }

            ConfigManager.THINKING_MODE_DISABLED -> if (normalizedEffort == null) {
                module.res.getString(R.string.thinking_summary_toggle, disabledLabel(), parameterLabel)
            } else {
                module.res.getString(
                    R.string.thinking_summary_disabled_ignores_effort,
                    disabledLabel(),
                    parameterLabel,
                )
            }

            else -> if (normalizedEffort == null) {
                module.res.getString(R.string.thinking_summary_default)
            } else {
                module.res.getString(R.string.thinking_summary_effort_only, normalizedEffort)
            }
        }
    }

    fun reasoningEffortSummary(effort: String, mode: String): String {
        val normalizedEffort = OpenAIThinkingParams.normalizeEffort(effort)
            ?: return module.res.getString(R.string.not_set)
        return if (mode.trim().lowercase() == ConfigManager.THINKING_MODE_DISABLED) {
            module.res.getString(R.string.reasoning_effort_ignored, normalizedEffort)
        } else {
            normalizedEffort
        }
    }

    private fun enabledLabel(): String = module.res.getString(R.string.enabled)

    private fun disabledLabel(): String = module.res.getString(R.string.disabled)

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
