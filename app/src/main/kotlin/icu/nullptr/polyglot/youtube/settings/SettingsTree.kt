package icu.nullptr.polyglot.youtube.settings

import android.text.InputType
import icu.nullptr.polyglot.R
import icu.nullptr.polyglot.core.ConfigManager
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
                    icon = SettingsIcon.Enable,
                    checked = { module.config.enabled },
                    summary = { SettingsOptions.enabledSummary(module.config.enabled) },
                    onChanged = { module.config.enabled = it },
                ),
                ActionSettingsNode(
                    key = "$ENTRY_KEY.test_connectivity",
                    title = module.res.getString(R.string.test_connectivity),
                    icon = SettingsIcon.NetworkCheck,
                    summary = { module.res.getString(R.string.test_connectivity_summary) },
                    action = SettingsAction.TestConnectivity,
                ),
                SelectionSettingsNode(
                    key = "$ENTRY_KEY.provider",
                    title = module.res.getString(R.string.translation_service),
                    icon = SettingsIcon.Service,
                    options = SettingsOptions.providers,
                    selectedValue = { module.config.provider },
                    selectedLabel = { SettingsOptions.providerLabel(module.config.provider) },
                    onSelected = { module.config.provider = it },
                ),
                TextSettingsNode(
                    key = "$ENTRY_KEY.openai_endpoint",
                    title = module.res.getString(R.string.openai_endpoint),
                    icon = SettingsIcon.Endpoint,
                    visible = { module.config.provider == ConfigManager.PROVIDER_OPENAI },
                    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_URI,
                    value = { module.config.openAiEndpoint },
                    summary = { SettingsOptions.textOrNotSet(module.config.openAiEndpoint) },
                    onSubmitted = { module.config.openAiEndpoint = it.trim() },
                ),
                TextSettingsNode(
                    key = "$ENTRY_KEY.openai_api_key",
                    title = module.res.getString(R.string.openai_api_key),
                    icon = SettingsIcon.ApiKey,
                    visible = { module.config.provider == ConfigManager.PROVIDER_OPENAI },
                    inputType = InputType.TYPE_CLASS_TEXT or
                        InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD or
                        InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS,
                    value = { module.config.openAiApiKey },
                    summary = { SettingsOptions.secretSummary(module.config.openAiApiKey) },
                    onSubmitted = { module.config.openAiApiKey = it.trim() },
                ),
                TextSettingsNode(
                    key = "$ENTRY_KEY.openai_model",
                    title = module.res.getString(R.string.openai_model),
                    icon = SettingsIcon.Model,
                    visible = { module.config.provider == ConfigManager.PROVIDER_OPENAI },
                    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS,
                    value = { module.config.openAiModel },
                    summary = { SettingsOptions.textOrNotSet(module.config.openAiModel) },
                    onSubmitted = { module.config.openAiModel = it.trim() },
                ),
                SelectionSettingsNode(
                    key = "$ENTRY_KEY.target_language",
                    title = module.res.getString(R.string.target_language),
                    icon = SettingsIcon.Language,
                    options = SettingsOptions.targetLanguages,
                    selectedValue = { module.config.targetLanguage },
                    selectedLabel = { SettingsOptions.languageLabel(module.config.targetLanguage) },
                    onSelected = { module.config.targetLanguage = it },
                ),
                SelectionSettingsNode(
                    key = "$ENTRY_KEY.subtitle_mode",
                    title = module.res.getString(R.string.subtitle_mode),
                    icon = SettingsIcon.Subtitle,
                    options = SettingsOptions.subtitleMode,
                    selectedValue = { module.config.subtitleMode },
                    selectedLabel = { SettingsOptions.subtitleModeLabel(module.config.subtitleMode) },
                    onSelected = { module.config.subtitleMode = it },
                ),
            ),
        )
}

internal sealed interface SettingsNode {
    val key: String
    val title: CharSequence
    val icon: SettingsIcon?
    val visible: () -> Boolean
}

internal data class SettingsScreenNode(
    override val key: String,
    override val title: CharSequence,
    override val icon: SettingsIcon? = null,
    override val visible: () -> Boolean = { true },
    val children: List<SettingsNode>,
) : SettingsNode

internal data class SwitchSettingsNode(
    override val key: String,
    override val title: CharSequence,
    override val icon: SettingsIcon? = null,
    override val visible: () -> Boolean = { true },
    val checked: () -> Boolean,
    val summary: () -> CharSequence,
    val onChanged: (Boolean) -> Unit,
) : SettingsNode

internal data class SelectionSettingsNode(
    override val key: String,
    override val title: CharSequence,
    override val icon: SettingsIcon? = null,
    override val visible: () -> Boolean = { true },
    val options: List<SettingsOption>,
    val selectedValue: () -> String,
    val selectedLabel: () -> CharSequence,
    val onSelected: (String) -> Unit,
) : SettingsNode

internal data class TextSettingsNode(
    override val key: String,
    override val title: CharSequence,
    override val icon: SettingsIcon? = null,
    override val visible: () -> Boolean = { true },
    val inputType: Int,
    val value: () -> String,
    val summary: () -> CharSequence,
    val onSubmitted: (String) -> Unit,
) : SettingsNode

internal data class ActionSettingsNode(
    override val key: String,
    override val title: CharSequence,
    override val icon: SettingsIcon? = null,
    override val visible: () -> Boolean = { true },
    val summary: () -> CharSequence,
    val action: SettingsAction,
) : SettingsNode

internal enum class SettingsAction {
    TestConnectivity,
}

internal fun SettingsNode.summary(): CharSequence? =
    when (this) {
        is SwitchSettingsNode -> summary()
        is SelectionSettingsNode -> selectedLabel()
        is TextSettingsNode -> summary()
        is ActionSettingsNode -> summary()
        is SettingsScreenNode -> null
    }

internal enum class SettingsIcon(val drawableRes: Int) {
    Entry(R.drawable.outline_translate_24),
    Enable(R.drawable.outline_check_circle_24),
    Service(R.drawable.outline_linked_services_24),
    Endpoint(R.drawable.outline_data_object_24),
    ApiKey(R.drawable.outline_key_24),
    Model(R.drawable.outline_deployed_code_24),
    NetworkCheck(R.drawable.outline_network_check_24),
    Language(R.drawable.outline_language_24),
    Subtitle(R.drawable.outline_closed_caption_24),
}
