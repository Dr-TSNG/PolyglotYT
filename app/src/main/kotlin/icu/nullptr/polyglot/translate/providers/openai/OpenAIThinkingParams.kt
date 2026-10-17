package icu.nullptr.polyglot.translate.providers.openai

import com.google.gson.JsonObject
import icu.nullptr.polyglot.core.ConfigManager

/**
 * Maps the user-selected thinking controls onto an OpenAI-compatible request
 * body. The app does not try to detect provider capabilities: the user picks
 * the parameter shape and the effort value, and the server decides whether the
 * combination is supported.
 */
internal object OpenAIThinkingParams {
    private const val THINKING_KEY = "thinking"
    private const val MAX_EFFORT_LENGTH = 64
    private const val EFFORT_KEY = "reasoning_effort"

    fun applyTo(
        body: JsonObject,
        mode: String,
        parameter: String,
        effort: String,
    ) {
        val normalizedMode = mode.trim().lowercase()
        when (normalizedMode) {
            ConfigManager.THINKING_MODE_ENABLED ->
                applyToggle(body, parameter, enabled = true)

            ConfigManager.THINKING_MODE_DISABLED ->
                applyToggle(body, parameter, enabled = false)
        }

        // A disabled thinking mode takes precedence over the effort value, so
        // the request never contains contradictory controls.
        if (normalizedMode == ConfigManager.THINKING_MODE_DISABLED) return

        val normalizedEffort = normalizeEffort(effort) ?: return
        body.addProperty(EFFORT_KEY, normalizedEffort)
    }

    fun normalizeEffort(effort: String): String? {
        val trimmed = effort.trim()
        if (trimmed.isEmpty() || trimmed.length > MAX_EFFORT_LENGTH) return null
        if (trimmed.any { it.isWhitespace() }) return null
        return trimmed
    }

    private fun applyToggle(body: JsonObject, parameter: String, enabled: Boolean) {
        when (parameter.trim().lowercase()) {
            ConfigManager.THINKING_PARAMETER_ENABLE_THINKING ->
                body.addProperty("enable_thinking", enabled)

            else -> {
                val resolvedType = if (enabled) "enabled" else "disabled"
                body.add(
                    THINKING_KEY,
                    JsonObject().apply { addProperty("type", resolvedType) },
                )
            }
        }
    }
}
