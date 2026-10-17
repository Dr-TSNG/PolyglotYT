package icu.nullptr.polyglot.translate.providers

import org.junit.Assert.assertEquals
import org.junit.Test

class OpenAIEndpointTest {

    @Test
    fun blankUsesDefaultEndpoint() {
        assertEquals(
            OpenAICompatibleTranslator.DEFAULT_ENDPOINT,
            OpenAIEndpoint.normalize("   "),
        )
    }

    @Test
    fun fullEndpointIsUnchanged() {
        assertEquals(
            "https://api.deepseek.com/v1/chat/completions",
            OpenAIEndpoint.normalize("https://api.deepseek.com/v1/chat/completions"),
        )
    }

    @Test
    fun trailingSlashIsRemoved() {
        assertEquals(
            "https://api.openai.com/v1/chat/completions",
            OpenAIEndpoint.normalize("https://api.openai.com/v1/chat/completions/"),
        )
    }

    @Test
    fun versionedBaseGetsCompletionsPath() {
        assertEquals(
            "https://api.openai.com/v1/chat/completions",
            OpenAIEndpoint.normalize("https://api.openai.com/v1"),
        )
        assertEquals(
            "https://api.deepseek.com/v1/chat/completions",
            OpenAIEndpoint.normalize("https://api.deepseek.com/v1/"),
        )
    }

    @Test
    fun bareHostGetsCompletionsPathWithoutVersion() {
        assertEquals(
            "https://api.deepseek.com/chat/completions",
            OpenAIEndpoint.normalize("https://api.deepseek.com"),
        )
        assertEquals(
            "https://api.deepseek.com/chat/completions",
            OpenAIEndpoint.normalize("https://api.deepseek.com/"),
        )
    }

    @Test
    fun customBasePathGetsCompletionsPath() {
        assertEquals(
            "https://proxy.example.com/openai/chat/completions",
            OpenAIEndpoint.normalize("https://proxy.example.com/openai"),
        )
        assertEquals(
            "https://open.bigmodel.cn/api/paas/v4/chat/completions",
            OpenAIEndpoint.normalize("https://open.bigmodel.cn/api/paas/v4"),
        )
    }

    @Test
    fun missingSchemeGetsHttps() {
        assertEquals(
            "https://api.openai.com/v1/chat/completions",
            OpenAIEndpoint.normalize("api.openai.com/v1"),
        )
    }

    @Test
    fun queryStringIsPreserved() {
        assertEquals(
            "https://proxy.example.com/v1/chat/completions?api-version=1",
            OpenAIEndpoint.normalize("https://proxy.example.com/v1?api-version=1"),
        )
    }

    @Test
    fun whitespaceIsTrimmed() {
        assertEquals(
            "https://api.openai.com/v1/chat/completions",
            OpenAIEndpoint.normalize("  https://api.openai.com/v1/chat/completions  "),
        )
    }
}
