package icu.nullptr.polyglot.translate.providers

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import icu.nullptr.polyglot.core.ConfigManager
import icu.nullptr.polyglot.translate.TranslationRequest
import icu.nullptr.polyglot.translate.TranslationResult
import icu.nullptr.polyglot.translate.Translator
import java.net.HttpURLConnection
import java.net.URL

class OpenAICompatibleTranslator(
    private val prefs: ConfigManager,
) : Translator {
    override fun translate(request: TranslationRequest): TranslationResult =
        TranslationResult(
            texts = request.texts.map { text ->
                if (text.isBlank()) text else translateOne(text, request)
            },
        )

    private fun translateOne(text: String, request: TranslationRequest): String {
        val apiKey = prefs.openAiApiKey
        require(apiKey.isNotBlank()) { "OpenAI-compatible API key is not configured" }

        val connection = URL(prefs.openAiEndpoint.ifBlank { DEFAULT_ENDPOINT })
            .openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.doOutput = true
        connection.connectTimeout = request.timeoutMs
        connection.readTimeout = request.timeoutMs
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("Content-Type", "application/json")
        connection.setRequestProperty("Authorization", "Bearer $apiKey")

        val body = buildRequestBody(text, request)
        connection.outputStream.use { it.write(body.toByteArray(Charsets.UTF_8)) }

        return connection.use {
            val response = it.readBodyOrThrow()
            parseTranslation(response)
        }
    }

    private fun buildRequestBody(text: String, request: TranslationRequest): String {
        val systemPrompt = prefs.openAiSystemPrompt
        val userPrompt = prefs.openAiUserPrompt
            .replace("{{to}}", request.targetLanguage)
            .replace("{{origin}}", text)

        val messages = JsonArray().apply {
            add(
                JsonObject().apply {
                    addProperty("role", "system")
                    addProperty("content", systemPrompt)
                },
            )
            add(
                JsonObject().apply {
                    addProperty("role", "user")
                    addProperty("content", userPrompt)
                },
            )
        }

        return JsonObject().apply {
            addProperty("model", prefs.openAiModel.ifBlank { DEFAULT_MODEL })
            addProperty("temperature", 1.0)
            add("messages", messages)
        }.toString()
    }

    private fun parseTranslation(body: String): String {
        val content = JsonParser.parseString(body)
            .asJsonObject["choices"]
            .asJsonArray[0]
            .asJsonObject["message"]
            .asJsonObject["content"]
            .asString
        return content.replace(THINK_BLOCK, "").trim()
    }

    private fun HttpURLConnection.readBodyOrThrow(): String {
        if (responseCode in 200..299) {
            return inputStream.bufferedReader(Charsets.UTF_8).use { it.readText() }
        }

        val errorBody = errorStream?.bufferedReader(Charsets.UTF_8)?.use { it.readText() }.orEmpty()
        throw IllegalStateException("OpenAI-compatible translate failed: HTTP $responseCode $responseMessage $errorBody")
    }

    private inline fun <T> HttpURLConnection.use(block: (HttpURLConnection) -> T): T =
        try {
            block(this)
        } finally {
            disconnect()
        }

    private companion object {
        const val DEFAULT_ENDPOINT = "https://api.openai.com/v1/chat/completions"
        const val DEFAULT_MODEL = "gpt-4o-mini"
        val THINK_BLOCK = Regex("^<think>[\\s\\S]*?</think>")
    }
}
