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
                ActionSettingsNode(
                    key = "$ENTRY_KEY.export_log",
                    title = module.res.getString(R.string.export_log),
                    icon = SettingsIcon.NetworkCheck,
                    summary = { module.res.getString(R.string.export_log_summary) },
                    action = SettingsAction.ExportLog,
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
                    key = "$ENTRY_KEY.microsoft_endpoint",
                    title = module.res.getString(R.string.microsoft_endpoint),
                    icon = SettingsIcon.Endpoint,
                    visible = { module.config.provider == ConfigManager.PROVIDER_MICROSOFT },
                    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_URI,
                    value = { module.config.microsoftEndpoint },
                    summary = { SettingsOptions.textOrNotSet(module.config.microsoftEndpoint) },
                    onSubmitted = { module.config.microsoftEndpoint = it.trim() },
                ),
                TextSettingsNode(
                    key = "$ENTRY_KEY.microsoft_api_key",
                    title = module.res.getString(R.string.microsoft_api_key),
                    icon = SettingsIcon.ApiKey,
                    visible = { module.config.provider == ConfigManager.PROVIDER_MICROSOFT },
                    inputType = InputType.TYPE_CLASS_TEXT or
                        InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD or
                        InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS,
                    value = { module.config.microsoftApiKey },
                    summary = { SettingsOptions.secretSummary(module.config.microsoftApiKey) },
                    onSubmitted = { module.config.microsoftApiKey = it.trim() },
                ),
                TextSettingsNode(
                    key = "$ENTRY_KEY.microsoft_region",
                    title = module.res.getString(R.string.microsoft_region),
                    icon = SettingsIcon.Language,
                    visible = { module.config.provider == ConfigManager.PROVIDER_MICROSOFT },
                    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS,
                    value = { module.config.microsoftRegion },
                    summary = { SettingsOptions.textOrNotSet(module.config.microsoftRegion) },
                    onSubmitted = { module.config.microsoftRegion = it.trim() },
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
                SettingsScreenNode(
                    key = "$ENTRY_KEY.style_screen",
                    title = module.res.getString(R.string.subtitle_style),
                    icon = SettingsIcon.Subtitle,
                    children = listOf(
                        SwitchSettingsNode(
                            key = "$ENTRY_KEY.style_enabled",
                            title = module.res.getString(R.string.subtitle_style_enabled),
                            icon = SettingsIcon.Subtitle,
                            checked = { module.config.subtitleStyleEnabled },
                            summary = { module.res.getString(R.string.subtitle_style_enabled_summary) },
                            onChanged = { module.config.subtitleStyleEnabled = it },
                        ),
                        SwitchSettingsNode(
                            key = "$ENTRY_KEY.sentence_break",
                            title = module.res.getString(R.string.subtitle_sentence_break),
                            icon = SettingsIcon.Subtitle,
                            checked = { module.config.subtitleSentenceBreak },
                            summary = { module.res.getString(R.string.subtitle_sentence_break_summary) },
                            onChanged = { module.config.subtitleSentenceBreak = it },
                        ),
                        SelectionSettingsNode(
                            key = "$ENTRY_KEY.base_scale",
                            title = module.res.getString(R.string.subtitle_base_scale),
                            icon = SettingsIcon.Subtitle,
                            options = SettingsOptions.baseScales,
                            selectedValue = { module.config.subtitleBaseScale.toString() },
                            selectedLabel = { SettingsOptions.baseScaleLabel(module.config.subtitleBaseScale.toString()) },
                            onSelected = { module.config.subtitleBaseScale = it.toFloat() },
                        ),
                        SelectionSettingsNode(
                            key = "$ENTRY_KEY.translation_scale",
                            title = module.res.getString(R.string.translation_scale),
                            icon = SettingsIcon.Subtitle,
                            options = SettingsOptions.translationScales,
                            selectedValue = { module.config.subtitleTranslationScale.toString() },
                            selectedLabel = { SettingsOptions.translationScaleLabel(module.config.subtitleTranslationScale.toString()) },
                            onSelected = { module.config.subtitleTranslationScale = it.toFloat() },
                        ),
                        SelectionSettingsNode(
                            key = "$ENTRY_KEY.line_width",
                            title = module.res.getString(R.string.subtitle_line_width),
                            icon = SettingsIcon.Subtitle,
                            options = SettingsOptions.lineWidths,
                            selectedValue = { module.config.subtitleLineWidth.toString() },
                            selectedLabel = { SettingsOptions.lineWidthLabel(module.config.subtitleLineWidth.toString()) },
                            onSelected = { module.config.subtitleLineWidth = it.toInt() },
                        ),
                        SelectionSettingsNode(
                            key = "$ENTRY_KEY.translation_color",
                            title = module.res.getString(R.string.translation_color),
                            icon = SettingsIcon.Subtitle,
                            options = SettingsOptions.translationColors,
                            selectedValue = { module.config.subtitleTranslationColor },
                            selectedLabel = { SettingsOptions.translationColorLabel(module.config.subtitleTranslationColor) },
                            onSelected = { module.config.subtitleTranslationColor = it },
                        ),
                        SelectionSettingsNode(
                            key = "$ENTRY_KEY.subtitle_separator",
                            title = module.res.getString(R.string.subtitle_separator),
                            icon = SettingsIcon.Subtitle,
                            options = SettingsOptions.subtitleSeparators,
                            selectedValue = { module.config.subtitleSeparator },
                            selectedLabel = { SettingsOptions.subtitleSeparatorLabel(module.config.subtitleSeparator) },
                            onSelected = { module.config.subtitleSeparator = it },
                        ),
                    ),
                ),
                SettingsScreenNode(
                    key = "$ENTRY_KEY.performance_screen",
                    title = module.res.getString(R.string.translation_performance),
                    icon = SettingsIcon.Model,
                    children = listOf(
                        TextSettingsNode(
                            key = "$ENTRY_KEY.batch_size",
                            title = module.res.getString(R.string.translation_batch_size),
                            icon = SettingsIcon.Model,
                            inputType = InputType.TYPE_CLASS_NUMBER,
                            value = { module.config.translationBatchSize.toString() },
                            summary = { module.res.getString(R.string.translation_batch_size_summary, module.config.translationBatchSize) },
                            onSubmitted = { module.config.translationBatchSize = it.toIntOrNull()?.coerceIn(1, 64) ?: 8 },
                        ),
                        TextSettingsNode(
                            key = "$ENTRY_KEY.batch_window",
                            title = module.res.getString(R.string.translation_batch_window),
                            icon = SettingsIcon.Model,
                            inputType = InputType.TYPE_CLASS_NUMBER,
                            value = { module.config.translationBatchWindowMs.toString() },
                            summary = { module.res.getString(R.string.translation_batch_window_summary, module.config.translationBatchWindowMs) },
                            onSubmitted = { module.config.translationBatchWindowMs = it.toIntOrNull()?.coerceIn(0, 2000) ?: 150 },
                        ),
                    ),
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
    ExportLog,
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
