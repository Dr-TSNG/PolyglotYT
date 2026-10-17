package icu.nullptr.polyglot.translate.providers.openai

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import icu.nullptr.polyglot.BuildConfig
import icu.nullptr.polyglot.module
import icu.nullptr.polyglot.translate.BatchResponseParser
import icu.nullptr.polyglot.translate.BatchTranslationException
import icu.nullptr.polyglot.translate.LanguageNames
import icu.nullptr.polyglot.translate.TranslationHttpException
import icu.nullptr.polyglot.translate.TranslationRequest
import icu.nullptr.polyglot.translate.TranslationResult
import icu.nullptr.polyglot.translate.Translator
import icu.nullptr.polyglot.util.logD
import java.net.HttpURLConnection
import java.net.URL

object OpenAICompatibleTranslator : Translator {
    const val DEFAULT_ENDPOINT = "https://api.openai.com/v1/chat/completions"
    const val DEFAULT_MODEL = "gpt-4o-mini"

    private const val TAG = "OpenAICompatibleTranslator"
    private const val LOG_CHUNK_SIZE = 3_000

    private val THINKING_FIELDS = listOf("thinking", "enable_thinking", "reasoning_effort")

    private val WHITESPACE = Regex("\\s+")

    override fun translate(request: TranslationRequest): TranslationResult {
        if (request.texts.all { it.isBlank() }) {
            return TranslationResult(request.texts)
        }

        val indexes = request.texts.indices.filter { request.texts[it].isNotBlank() }
        val texts = indexes.map { request.texts[it] }
        val output = request.texts.toMutableList()

        if (texts.size == 1) {
            val userPrompt = renderSingleUserPrompt(
                template = module.config.openAiSingleUserPrompt,
                request = request,
                text = texts.single(),
            )
            val content = postChat(buildRequestBody(request, userPrompt), request)
            output[indexes.single()] = BatchResponseParser.clean(content)
            return TranslationResult(output)
        }

        val userPrompt = renderUserPrompt(
            template = module.config.openAiUserPrompt,
            request = request,
            texts = texts,
        )
        val content = postChat(buildRequestBody(request, userPrompt), request)
        val parsed = BatchResponseParser.parseBatch(content, texts.size)
        if (!parsed.isValid) {
            throw BatchTranslationException(
                "OpenAI-compatible batch returned ${parsed.receivedCount} result(s) " +
                    "for ${texts.size} texts",
            )
        }

        indexes.forEachIndexed { index, originalIndex ->
            output[originalIndex] = parsed.translations.getOrElse(index) { "" }
        }
        return TranslationResult(output)
    }

    private fun buildRequestBody(
        request: TranslationRequest,
        userPrompt: String,
    ): String {
        val systemPrompt = module.config.openAiSystemPrompt

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
            OpenAIThinkingParams.applyTo(
                body = this,
                mode = module.config.openAiThinkingMode,
                parameter = module.config.openAiThinkingParameter,
                effort = module.config.openAiReasoningEffort,
            )
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
            .replace("{{lines}}", lines)
    }

    private fun renderSingleUserPrompt(
        template: String,
        request: TranslationRequest,
        text: String,
    ): String {
        val title = request.videoTitle.ifBlank { "unknown" }
        val line = text.replace(WHITESPACE, " ").trim()

        return template
            .replace("{{title}}", title)
            .replace("{{from}}", LanguageNames.displayName(request.sourceLanguage))
            .replace("{{to}}", LanguageNames.displayName(request.targetLanguage))
            .replace("{{context}}", request.context)
            .replace("{{line}}", line)
    }

    private fun postChat(body: String, request: TranslationRequest): String {
        val apiKey = module.config.openAiApiKey
        require(apiKey.isNotBlank()) { "OpenAI-compatible API key is not configured" }

        val model = module.config.openAiModel.ifBlank { DEFAULT_MODEL }
        if (BuildConfig.DEBUG) {
            logD(
                TAG,
                "AI request: model=$model, texts=${request.texts.size}, " +
                        "timeoutMs=${request.timeoutMs}, bodyLength=${body.length}",
            )
            logD(
                TAG,
                "AI thinking: mode=${module.config.openAiThinkingMode}, " +
                        "parameter=${module.config.openAiThinkingParameter}, " +
                        "effort=${module.config.openAiReasoningEffort}",
            )
            logBody("AI request body", body)
        }

        val startedAtMs = System.currentTimeMillis()
        val connection = URL(
            OpenAIEndpoint.normalize(module.config.openAiEndpoint),
        ).openConnection() as HttpURLConnection
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
            if (BuildConfig.DEBUG) {
                logD(
                    TAG,
                    "AI response received: elapsedMs=${System.currentTimeMillis() - startedAtMs}, " +
                            "bodyLength=${response.length}",
                )
            }
            parseContent(response)
        }
    }

    private fun logBody(label: String, body: String) {
        if (body.length <= LOG_CHUNK_SIZE) {
            logD(TAG, "$label:\n$body")
            return
        }

        val chunks = body.chunked(LOG_CHUNK_SIZE)
        chunks.forEachIndexed { index, chunk ->
            logD(TAG, "$label (${index + 1}/${chunks.size}):\n$chunk")
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
        val success = responseCode in 200..299
        val body = if (success) {
            inputStream.bufferedReader(Charsets.UTF_8).use { it.readText() }
        } else {
            errorStream?.bufferedReader(Charsets.UTF_8)?.use { it.readText() }.orEmpty()
        }

        if (BuildConfig.DEBUG) {
            logD(TAG, "AI response: HTTP $responseCode, bodyLength=${body.length}")
            logBody("AI response body", body)
        }

        if (success) {
            return body
        }

        throw TranslationHttpException(
            responseCode,
            "OpenAI-compatible translate failed: HTTP $responseCode $responseMessage $body" +
                thinkingHint(body),
        )
    }

    private fun thinkingHint(body: String): String {
        val mentionsThinking = THINKING_FIELDS.any { body.contains(it, ignoreCase = true) }
        if (!mentionsThinking) return ""

        return " Hint: check the thinking mode, thinking parameter and reasoning effort " +
            "settings, or set the thinking mode back to default."
    }

    private inline fun <T> HttpURLConnection.use(block: (HttpURLConnection) -> T): T =
        try {
            block(this)
        } finally {
            disconnect()
        }
}
