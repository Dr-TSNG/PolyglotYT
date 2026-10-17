package icu.nullptr.polyglot.translate.providers

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import icu.nullptr.polyglot.module
import icu.nullptr.polyglot.translate.BatchResponseParser
import icu.nullptr.polyglot.translate.LanguageNames
import icu.nullptr.polyglot.translate.TranslationHttpException
import icu.nullptr.polyglot.translate.TranslationRequest
import icu.nullptr.polyglot.translate.TranslationResult
import icu.nullptr.polyglot.translate.Translator
import java.net.HttpURLConnection
import java.net.URL

object OpenAICompatibleTranslator : Translator {
    const val DEFAULT_ENDPOINT = "https://api.openai.com/v1/chat/completions"
    const val DEFAULT_MODEL = "gpt-4o-mini"

    private val WHITESPACE = Regex("\\s+")

    override fun translate(request: TranslationRequest): TranslationResult {
        if (request.texts.all { it.isBlank() }) {
            return TranslationResult(request.texts)
        }

        val template = module.config.openAiUserPrompt
        val legacyPrompt = template.contains("{{origin}}") && !template.contains("{{lines}}")
        if (legacyPrompt) {
            return TranslationResult(
                request.texts.map { text ->
                    if (text.isBlank()) text else translateLegacy(text, request, template)
                },
            )
        }

        val indexes = request.texts.indices.filter { request.texts[it].isNotBlank() }
        val texts = indexes.map { request.texts[it] }
        val content = postChat(buildRequestBody(request, texts), request)
        var translations = BatchResponseParser.parse(content, texts.size)

        // Some compatible endpoints ignore the JSON instruction. For a single
        // line the raw response is still a valid translation.
        if (texts.size == 1 && translations.firstOrNull().isNullOrBlank()) {
            translations = listOf(BatchResponseParser.clean(content))
        }

        val output = request.texts.toMutableList()
        indexes.forEachIndexed { index, originalIndex ->
            output[originalIndex] = translations.getOrElse(index) { "" }
        }
        return TranslationResult(output)
    }

    private fun translateLegacy(
        text: String,
        request: TranslationRequest,
        template: String,
    ): String {
        val content = postChat(buildRequestBody(request, listOf(text), template), request)
        return BatchResponseParser.clean(content)
    }

    private fun buildRequestBody(
        request: TranslationRequest,
        texts: List<String>,
        template: String = module.config.openAiUserPrompt,
    ): String {
        val systemPrompt = module.config.openAiSystemPrompt
        val userPrompt = renderUserPrompt(template, request, texts)

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
            addProperty("model", module.config.openAiModel.ifBlank { DEFAULT_MODEL })
            addProperty("temperature", 1.0)
            add("messages", messages)
        }.toString()
    }

    private fun renderUserPrompt(
        template: String,
        request: TranslationRequest,
        texts: List<String>,
    ): String {
        val title = request.videoTitle.ifBlank { "unknown" }
        val lines = texts.mapIndexed { index, text ->
            "${index + 1}. ${text.replace(WHITESPACE, " ").trim()}"
        }.joinToString("\n")

        return template
            .replace("{{title}}", title)
            .replace("{{from}}", LanguageNames.displayName(request.sourceLanguage))
            .replace("{{to}}", LanguageNames.displayName(request.targetLanguage))
            .replace("{{context}}", request.context)
            .replace("{{origin}}", texts.singleOrNull().orEmpty())
            .replace("{{lines}}", lines)
    }

    private fun postChat(body: String, request: TranslationRequest): String {
        val apiKey = module.config.openAiApiKey
        require(apiKey.isNotBlank()) { "OpenAI-compatible API key is not configured" }

        val connection = URL(module.config.openAiEndpoint.ifBlank { DEFAULT_ENDPOINT })
            .openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.doOutput = true
        connection.connectTimeout = request.timeoutMs
        connection.readTimeout = request.timeoutMs
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("Content-Type", "application/json")
        connection.setRequestProperty("Authorization", "Bearer $apiKey")

        connection.outputStream.use { it.write(body.toByteArray(Charsets.UTF_8)) }

        return connection.use {
            val response = it.readBodyOrThrow()
            parseContent(response)
        }
    }

    private fun parseContent(body: String): String {
        val message = JsonParser.parseString(body)
            .asJsonObject["choices"]
            .asJsonArray[0]
            .asJsonObject["message"]
            .asJsonObject

        return message.get("content")
            ?.takeUnless { it.isJsonNull }
            ?.asString
            ?: throw IllegalStateException("OpenAI-compatible translate returned no content")
    }

    private fun HttpURLConnection.readBodyOrThrow(): String {
        if (responseCode in 200..299) {
            return inputStream.bufferedReader(Charsets.UTF_8).use { it.readText() }
        }

        val errorBody = errorStream?.bufferedReader(Charsets.UTF_8)?.use { it.readText() }.orEmpty()
        throw TranslationHttpException(
            responseCode,
            "OpenAI-compatible translate failed: HTTP $responseCode $responseMessage $errorBody",
        )
    }

    private inline fun <T> HttpURLConnection.use(block: (HttpURLConnection) -> T): T =
        try {
            block(this)
        } finally {
            disconnect()
        }
}
