package icu.nullptr.polyglot.youtube.settings

import icu.nullptr.polyglot.R
import icu.nullptr.polyglot.module
import icu.nullptr.polyglot.settings.SettingsOption
import icu.nullptr.polyglot.settings.SettingsOptions

internal object PolyglotSettingsTree {
    fun root(): SettingsScreenNode =
        SettingsScreenNode(
            key = "$ENTRY_KEY.screen",
            title = ENTRY_TITLE,
            children = listOf(
                SwitchSettingsNode(
                    key = "$ENTRY_KEY.enabled",
                    title = module.res.getString(R.string.enable_module),
                    checked = { module.config.enabled },
                    summary = { SettingsOptions.enabledSummary(module.config.enabled) },
                    onChanged = { module.config.enabled = it },
                ),
                SelectionSettingsNode(
                    key = "$ENTRY_KEY.provider",
                    title = module.res.getString(R.string.translation_service),
                    options = SettingsOptions.providers,
                    selectedValue = { module.config.provider },
                    selectedLabel = { SettingsOptions.providerLabel(module.config.provider) },
                    onSelected = { module.config.provider = it },
                ),
                SelectionSettingsNode(
                    key = "$ENTRY_KEY.target_language",
                    title = module.res.getString(R.string.target_language),
                    options = SettingsOptions.targetLanguages,
                    selectedValue = { module.config.targetLanguage },
                    selectedLabel = { SettingsOptions.languageLabel(module.config.targetLanguage) },
                    onSelected = { module.config.targetLanguage = it },
                ),
                SelectionSettingsNode(
                    key = "$ENTRY_KEY.subtitle_mode",
                    title = module.res.getString(R.string.subtitle_mode),
                    options = SettingsOptions.subtitleMode,
                    selectedValue = { module.config.subtitleMode },
                    selectedLabel = { SettingsOptions.subtitleModeLabel(module.config.subtitleMode) },
                    onSelected = { module.config.subtitleMode = it },
                ),
            ),
        )

    fun entrySummary(): String =
        SettingsOptions.entrySummary()
}

internal sealed interface SettingsNode {
    val key: String
    val title: CharSequence
}

internal data class SettingsScreenNode(
    override val key: String,
    override val title: CharSequence,
    val children: List<SettingsNode>,
) : SettingsNode

internal data class SwitchSettingsNode(
    override val key: String,
    override val title: CharSequence,
    val checked: () -> Boolean,
    val summary: () -> CharSequence,
    val onChanged: (Boolean) -> Unit,
) : SettingsNode

internal data class SelectionSettingsNode(
    override val key: String,
    override val title: CharSequence,
    val options: List<SettingsOption>,
    val selectedValue: () -> String,
    val selectedLabel: () -> CharSequence,
    val onSelected: (String) -> Unit,
) : SettingsNode

internal data class OptionSettingsNode(
    override val key: String,
    override val title: CharSequence,
    val value: String,
    val selected: () -> Boolean,
    val onSelected: () -> Unit,
) : SettingsNode

internal fun SettingsNode.summary(): CharSequence? =
    when (this) {
        is SwitchSettingsNode -> summary()
        is SelectionSettingsNode -> selectedLabel()
        is OptionSettingsNode -> if (selected()) "Selected" else ""
        is SettingsScreenNode -> null
    }
