package icu.nullptr.polyglot.translate.providers.openai

import com.google.gson.JsonObject
import icu.nullptr.polyglot.core.ConfigManager
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class OpenAIThinkingParamsTest {

    @Test
    fun defaultModeSendsNothing() {
        val body = requestBody()

        assertFalse(body.has("thinking"))
        assertFalse(body.has("enable_thinking"))
        assertFalse(body.has("reasoning_effort"))
    }

    @Test
    fun enabledModeWritesThinkingType() {
        val body = requestBody(
            mode = ConfigManager.THINKING_MODE_ENABLED,
            parameter = ConfigManager.THINKING_PARAMETER_THINKING,
        )

        assertEquals(
            "enabled",
            body.getAsJsonObject("thinking").get("type").asString,
        )
    }

    @Test
    fun disabledModeWritesThinkingType() {
        val body = requestBody(
            mode = ConfigManager.THINKING_MODE_DISABLED,
            parameter = ConfigManager.THINKING_PARAMETER_THINKING,
        )

        assertEquals(
            "disabled",
            body.getAsJsonObject("thinking").get("type").asString,
        )
    }

    @Test
    fun enabledModeWritesEnableThinkingBoolean() {
        val body = requestBody(
            mode = ConfigManager.THINKING_MODE_ENABLED,
            parameter = ConfigManager.THINKING_PARAMETER_ENABLE_THINKING,
        )

        assertTrue(body.get("enable_thinking").asBoolean)
        assertFalse(body.has("thinking"))
    }

    @Test
    fun disabledModeWritesEnableThinkingBoolean() {
        val body = requestBody(
            mode = ConfigManager.THINKING_MODE_DISABLED,
            parameter = ConfigManager.THINKING_PARAMETER_ENABLE_THINKING,
        )

        assertFalse(body.get("enable_thinking").asBoolean)
    }

    @Test
    fun effortIsSentWithoutToggle() {
        val body = requestBody(effort = "high")

        assertEquals("high", body.get("reasoning_effort").asString)
        assertFalse(body.has("thinking"))
        assertFalse(body.has("enable_thinking"))
    }

    @Test
    fun effortIsSentAlongsideEnabledToggle() {
        val body = requestBody(
            mode = ConfigManager.THINKING_MODE_ENABLED,
            effort = "medium",
        )

        assertEquals("medium", body.get("reasoning_effort").asString)
        assertEquals(
            "enabled",
            body.getAsJsonObject("thinking").get("type").asString,
        )
    }

    @Test
    fun disabledModeIgnoresEffort() {
        val body = requestBody(
            mode = ConfigManager.THINKING_MODE_DISABLED,
            effort = "low",
        )

        assertFalse(body.has("reasoning_effort"))
        assertEquals(
            "disabled",
            body.getAsJsonObject("thinking").get("type").asString,
        )
    }

    @Test
    fun blankAndMalformedEffortIsIgnored() {
        assertFalse(requestBody(effort = "   ").has("reasoning_effort"))
        assertFalse(requestBody(effort = "low effort").has("reasoning_effort"))
        assertFalse(requestBody(effort = "x".repeat(65)).has("reasoning_effort"))
    }

    @Test
    fun effortIsTrimmed() {
        val body = requestBody(effort = "  low  ")

        assertEquals("low", body.get("reasoning_effort").asString)
    }

    @Test
    fun unknownModeAndParameterFallBackToDefaults() {
        val body = requestBody(mode = "unexpected", parameter = "unexpected")

        assertFalse(body.has("thinking"))
        assertFalse(body.has("enable_thinking"))
        assertFalse(body.has("reasoning_effort"))
    }

    private fun requestBody(
        mode: String = ConfigManager.THINKING_MODE_DEFAULT,
        parameter: String = ConfigManager.THINKING_PARAMETER_THINKING,
        effort: String = "",
    ): JsonObject = JsonObject().apply {
        addProperty("model", "test-model")
        OpenAIThinkingParams.applyTo(this, mode, parameter, effort)
    }
}
