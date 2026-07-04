package icu.nullptr.polyglot.translate.providers

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonParser
import com.google.gson.JsonPrimitive
import icu.nullptr.polyglot.translate.TranslationRequest
import icu.nullptr.polyglot.translate.TranslationResult
import icu.nullptr.polyglot.translate.Translator
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.Locale

object GoogleTranslator : Translator {
    const val RPC_ID = "MkEWBc"
    const val ENDPOINT = "https://translate.google.com/_/TranslateWebserverUi/data/batchexecute"

    override fun translate(request: TranslationRequest): TranslationResult =
        TranslationResult(
            texts = request.texts.map { text ->
                if (text.isBlank()) text else translateOne(text, request)
            },
        )

    private fun translateOne(text: String, request: TranslationRequest): String {
        val sourceLanguage = googleLanguage(request.sourceLanguage)
        val targetLanguage = googleLanguage(request.targetLanguage)
        val query = buildString {
            append("rpcids=").append(RPC_ID)
            append("&source-path=%2F")
            append("&hl=en")
            append("&soc-app=1")
            append("&soc-platform=1")
            append("&soc-device=1")
            append("&rt=c")
        }
        val body = buildRequestBody(text, sourceLanguage, targetLanguage)

        val connection = URL("$ENDPOINT?$query").openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.doOutput = true
        connection.connectTimeout = request.timeoutMs
        connection.readTimeout = request.timeoutMs
        connection.setRequestProperty("Accept", "*/*")
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
        connection.setRequestProperty("Origin", "https://translate.google.com")
        connection.setRequestProperty("Referer", "https://translate.google.com/")
        connection.setRequestProperty("User-Agent", USER_AGENT)

        connection.outputStream.use { stream ->
            stream.write(body.toByteArray(Charsets.UTF_8))
        }

        return connection.use {
            val response = it.readBodyOrThrow()
            parseTranslation(response)
        }
    }

    private fun buildRequestBody(text: String, sourceLanguage: String, targetLanguage: String): String {
        val payload = jsonArray(
            jsonArray(text, sourceLanguage, targetLanguage, true),
            jsonArray(null),
        ).toString()
        val request = jsonArray(
            jsonArray(
                jsonArray(RPC_ID, payload, null, "generic"),
            ),
        ).toString()
        return "f.req=${urlEncode(request)}"
    }

    private fun parseTranslation(body: String): String {
        val trimmed = body.trimStart()
        if (trimmed.startsWith("<")) {
            throw IllegalStateException("Google translate returned ${summarizeBody(trimmed)}")
        }

        val payload = body.lineSequence()
            .map { it.trim() }
            .filter { it.startsWith("[[") }
            .firstNotNullOfOrNull(::parseRpcPayload)
            ?: throw IllegalStateException("Google translate response did not contain $RPC_ID payload")

        val units = payload
            .arrayOrNull(1)
            ?.arrayOrNull(0)
            ?.arrayOrNull(0)
            ?.arrayOrNull(5)
            ?: throw IllegalStateException("Google translate response did not contain translation units")

        return buildString {
            for (unit in units) {
                val translated = unit.asArrayOrNull()?.stringOrNull(0)
                if (!translated.isNullOrBlank()) {
                    append(translated)
                }
            }
        }
    }

    private fun parseRpcPayload(line: String): JsonArray? =
        runCatching {
            val envelope = JsonParser.parseString(line).asJsonArray
            var result: JsonArray? = null
            for (entry in envelope) {
                val row = entry.asArrayOrNull()
                if (row != null && row.stringOrNull(0) == "wrb.fr" && row.stringOrNull(1) == RPC_ID) {
                    val payload = row.stringOrNull(2)
                    if (payload == null) {
                        continue
                    }
                    result = JsonParser.parseString(payload).asJsonArray
                    break
                }
            }
            result
        }.getOrNull()

    private fun jsonArray(vararg values: Any?): JsonArray =
        JsonArray().apply {
            for (value in values) {
                add(value.toJsonElement())
            }
        }

    private fun Any?.toJsonElement(): JsonElement =
        when (this) {
            null -> JsonNull.INSTANCE
            is JsonElement -> this
            is String -> JsonPrimitive(this)
            is Boolean -> JsonPrimitive(this)
            is Number -> JsonPrimitive(this)
            else -> throw IllegalArgumentException("Unsupported JSON value: ${this::class.java.name}")
        }

    private fun JsonElement.asArrayOrNull(): JsonArray? =
        if (isJsonArray) asJsonArray else null

    private fun JsonArray.arrayOrNull(index: Int): JsonArray? =
        getOrNull(index)?.asArrayOrNull()

    private fun JsonArray.stringOrNull(index: Int): String? =
        getOrNull(index)
            ?.takeUnless { it.isJsonNull }
            ?.takeIf { it.isJsonPrimitive }
            ?.asString

    private fun JsonArray.getOrNull(index: Int): JsonElement? =
        if (index in 0 until size()) get(index) else null

    private fun summarizeBody(body: String): String =
        when {
            body.contains("captcha-form", ignoreCase = true) ||
                body.contains("unusual traffic", ignoreCase = true) ||
                body.contains("automatically detects requests", ignoreCase = true) ->
                "an anti-abuse challenge"
            body.startsWith("<") -> "an HTML response"
            else -> body.replace(Regex("\\s+"), " ").take(ERROR_BODY_PREVIEW_LENGTH)
        }

    private fun HttpURLConnection.readBodyOrThrow(): String {
        if (responseCode in 200..299) {
            return inputStream.bufferedReader(Charsets.UTF_8).use { it.readText() }
        }

        val errorBody = errorStream?.bufferedReader(Charsets.UTF_8)?.use { it.readText() }.orEmpty()
        throw IllegalStateException(
            "Google translate failed: HTTP $responseCode $responseMessage, ${summarizeBody(errorBody)}",
        )
    }

    private inline fun <T> HttpURLConnection.use(block: (HttpURLConnection) -> T): T =
        try {
            block(this)
        } finally {
            disconnect()
        }

    private fun googleLanguage(language: String): String =
        when (language.lowercase(Locale.ROOT)) {
            "auto" -> "auto"
            "zh", "zh-cn", "zh-hans" -> "zh-CN"
            "zh-tw", "zh-hk", "zh-hant" -> "zh-TW"
            else -> language
        }

    private fun urlEncode(value: String): String =
        URLEncoder.encode(value, Charsets.UTF_8.name())

    private const val USER_AGENT =
        "Mozilla/5.0 (Linux; Android 10) AppleWebKit/537.36 Chrome/120 Mobile Safari/537.36"
    private const val ERROR_BODY_PREVIEW_LENGTH = 200
}
