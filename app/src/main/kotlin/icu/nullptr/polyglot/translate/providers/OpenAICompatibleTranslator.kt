package icu.nullptr.polyglot.translate.providers

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import icu.nullptr.polyglot.module
import icu.nullptr.polyglot.translate.TranslationRequest
import icu.nullptr.polyglot.translate.TranslationResult
import icu.nullptr.polyglot.translate.Translator
import icu.nullptr.polyglot.util.logW
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

object OpenAICompatibleTranslator : Translator {
    const val DEFAULT_ENDPOINT = "https://api.openai.com/v1/chat/completions"
    const val DEFAULT_MODEL = "gpt-4o-mini"
    val THINK_BLOCK = Regex("^<think>[\\s\\S]*?</think>")

    private const val FALLBACK_CONCURRENCY = 4
    private val FALLBACK_EXECUTOR = Executors.newFixedThreadPool(FALLBACK_CONCURRENCY) { runnable ->
        Thread(runnable, "PolyglotYT-OpenAI-Fallback-${FALLBACK_THREAD_COUNTER.incrementAndGet()}").apply {
            isDaemon = true
        }
    }
    private val FALLBACK_THREAD_COUNTER = AtomicInteger(0)

    override fun translate(request: TranslationRequest): TranslationResult {
        val texts = request.texts
        if (texts.size <= 1) {
            return TranslationResult(
                texts = texts.map { text ->
                    if (text.isBlank()) text else translateOne(text, request)
                },
            )
        }

        // Batch: translate multiple lines in a single completion, with
        // numbered lines so results can be mapped back. Falls back to
        // per-line calls when the model does not preserve the numbering.
        return try {
            val translated = translateBatch(texts, request)
            if (translated.size == texts.size) {
                TranslationResult(translated)
            } else {
                logW(TAG, "OpenAI batch returned ${translated.size}/${texts.size} lines, retrying per-line")
                TranslationResult(translateFallback(texts, request))
            }
        } catch (e: Exception) {
            logW(TAG, "OpenAI batch translation failed, retrying per-line", e)
            TranslationResult(translateFallback(texts, request))
        }
    }

    /** Translates lines in parallel so a slow per-line fallback stays fast. */
    private fun translateFallback(texts: List<String>, request: TranslationRequest): List<String> {
        if (texts.size <= 1) {
            return texts.map { text ->
                if (text.isBlank()) text else translateOne(text, request)
            }
        }
        return try {
            FALLBACK_EXECUTOR.invokeAll(
                texts.map { text ->
                    java.util.concurrent.Callable {
                        if (text.isBlank()) text else translateOne(text, request)
                    }
                },
            ).map { it.get() }
        } catch (e: Exception) {
            logW(TAG, "OpenAI parallel fallback failed", e)
            texts.map { text ->
                if (text.isBlank()) text else translateOne(text, request)
            }
        }
    }

    private fun translateOne(text: String, request: TranslationRequest): String {
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

        val body = buildRequestBody(text, request)
        connection.outputStream.use { it.write(body.toByteArray(Charsets.UTF_8)) }

        return connection.use {
            val response = it.readBodyOrThrow()
            parseTranslation(response)
        }
    }

    private fun translateBatch(texts: List<String>, request: TranslationRequest): List<String> {
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

        val body = buildBatchRequestBody(texts, request)
        connection.outputStream.use { it.write(body.toByteArray(Charsets.UTF_8)) }

        val response = connection.use { it.readBodyOrThrow() }
        return parseNumberedTranslations(response, texts.size)
    }

    private fun buildRequestBody(text: String, request: TranslationRequest): String {
        val systemPrompt = module.config.openAiSystemPrompt
        val userPrompt = module.config.openAiUserPrompt
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
            addProperty("model", module.config.openAiModel.ifBlank { DEFAULT_MODEL })
            addProperty("temperature", 1.0)
            add("messages", messages)
        }.toString()
    }

    private fun buildBatchRequestBody(texts: List<String>, request: TranslationRequest): String {
        val systemPrompt = module.config.openAiSystemPrompt
        val numbered = texts.mapIndexed { index, text -> "${index + 1}. $text" }
            .joinToString("\n")
        val userPrompt = buildString {
            append(
                module.config.openAiUserPrompt
                    .replace("{{to}}", request.targetLanguage)
                    .replace("{{origin}}", numbered),
            )
            // The template may not contain {{origin}} (custom prompt); make
            // sure the numbered lines always reach the model.
            if ("{{origin}}" !in module.config.openAiUserPrompt) {
                append("\n\nTranslate each numbered subtitle line separately. ")
                append("Return exactly the same number of lines, each prefixed with its number, ")
                append("e.g. \"1. ...\", \"2. ...\". If translation is unnecessary, return the original:\n")
                append(numbered)
            }
        }

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
            addProperty("temperature", 0.3)
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

    private fun parseNumberedTranslations(body: String, expected: Int): List<String> {
        val content = JsonParser.parseString(body)
            .asJsonObject["choices"]
            .asJsonArray[0]
            .asJsonObject["message"]
            .asJsonObject["content"]
            .asString
            .replace(THINK_BLOCK, "")
            .trim()

        val result = ArrayList<String>()
        var currentNumber = 0
        for (rawLine in content.lineSequence()) {
            val line = rawLine.trim()
            if (line.isEmpty()) continue
            val match = NUMBERED_LINE_PREFIX.find(line)
            if (match != null) {
                val number = match.groupValues[1].toIntOrNull() ?: continue
                if (number == currentNumber + 1) {
                    currentNumber = number
                    result.add(match.groupValues[2].trim())
                    continue
                }
            }
            // Continuation of the previous line (multi-line translation).
            if (result.isNotEmpty()) {
                result[result.size - 1] = result.last() + " " + line
            }
        }

        if (result.size != expected) {
            throw IllegalStateException(
                "OpenAI batch returned ${result.size} lines, expected $expected",
            )
        }
        return result
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

    private const val TAG = "OpenAICompatibleTranslator"
    private val NUMBERED_LINE_PREFIX = Regex("^\\s*(\\d{1,3})[.、．:：]\\s*(.*)$")
}
