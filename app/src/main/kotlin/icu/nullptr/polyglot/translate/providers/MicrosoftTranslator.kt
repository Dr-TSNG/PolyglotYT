package icu.nullptr.polyglot.translate.providers

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import icu.nullptr.polyglot.translate.TranslationRequest
import icu.nullptr.polyglot.translate.TranslationResult
import icu.nullptr.polyglot.translate.Translator
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.Base64
import java.util.Locale

object MicrosoftTranslator : Translator {
    const val AUTH_ENDPOINT = "https://edge.microsoft.com/translate/auth"
    const val TRANSLATE_ENDPOINT = "https://api-edge.cognitive.microsofttranslator.com/translate"
    const val TOKEN_REFRESH_MARGIN_MS = 60_000L
    const val FALLBACK_TOKEN_TTL_MS = 8 * 60_000L

    @Volatile
    private var token: String = ""

    @Volatile
    private var tokenExpiresAtMs: Long = 0L

    override fun translate(request: TranslationRequest): TranslationResult =
        TranslationResult(
            texts = request.texts.map { text ->
                if (text.isBlank()) text else translateOne(text, request)
            },
        )

    private fun translateOne(text: String, request: TranslationRequest): String {
        val sourceLanguage = microsoftLanguage(request.sourceLanguage)
        val targetLanguage = microsoftLanguage(request.targetLanguage)
        val query = buildString {
            append("api-version=3.0")
            if (sourceLanguage.isNotEmpty()) {
                append("&from=").append(urlEncode(sourceLanguage))
            }
            append("&to=").append(urlEncode(targetLanguage))
            append("&includeSentenceLength=true")
            append("&textType=plain")
        }

        val connection = URL("$TRANSLATE_ENDPOINT?$query").openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.doOutput = true
        connection.connectTimeout = request.timeoutMs
        connection.readTimeout = request.timeoutMs
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("Content-Type", "application/json")
        connection.setRequestProperty("Authorization", "Bearer ${authToken(request.timeoutMs)}")

        connection.outputStream.use { stream ->
            stream.write(buildRequestBody(text).toByteArray(Charsets.UTF_8))
        }

        return connection.use {
            val body = it.readBodyOrThrow("Microsoft translate")
            parseTranslation(body)
        }
    }

    @Synchronized
    private fun authToken(timeoutMs: Int): String {
        val now = System.currentTimeMillis()
        if (token.isNotBlank() && now < tokenExpiresAtMs - TOKEN_REFRESH_MARGIN_MS) {
            return token
        }

        val connection = URL(AUTH_ENDPOINT).openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connectTimeout = timeoutMs
        connection.readTimeout = timeoutMs

        token = connection.use {
            it.readBodyOrThrow("Microsoft auth").trim()
        }
        tokenExpiresAtMs = tokenExpiryMs(token) ?: (now + FALLBACK_TOKEN_TTL_MS)
        return token
    }

    private fun buildRequestBody(text: String): String =
        JsonArray().apply {
            add(
                JsonObject().apply {
                    addProperty("Text", text)
                },
            )
        }.toString()

    private fun parseTranslation(body: String): String {
        val root = JsonParser.parseString(body).asJsonArray
        return root[0]
            .asJsonObject["translations"]
            .asJsonArray[0]
            .asJsonObject["text"]
            .asString
    }

    private fun HttpURLConnection.readBodyOrThrow(label: String): String {
        if (responseCode in 200..299) {
            return inputStream.bufferedReader(Charsets.UTF_8).use { it.readText() }
        }

        val errorBody = errorStream?.bufferedReader(Charsets.UTF_8)?.use { it.readText() }.orEmpty()
        throw IllegalStateException("$label failed: HTTP $responseCode $responseMessage $errorBody")
    }

    private inline fun <T> HttpURLConnection.use(block: (HttpURLConnection) -> T): T =
        try {
            block(this)
        } finally {
            disconnect()
        }

    private fun tokenExpiryMs(jwt: String): Long? =
        runCatching {
            val payload = jwt.split(".").getOrNull(1) ?: return null
            val json = String(Base64.getUrlDecoder().decode(payload), Charsets.UTF_8)
            JsonParser.parseString(json).asJsonObject["exp"].asLong * 1000L
        }.getOrNull()

    private fun microsoftLanguage(language: String): String =
        when (language.lowercase(Locale.ROOT)) {
            "auto" -> ""
            "zh", "zh-cn", "zh-hans" -> "zh-Hans"
            "zh-tw", "zh-hk", "zh-hant" -> "zh-Hant"
            else -> language
        }

    private fun urlEncode(value: String): String =
        URLEncoder.encode(value, Charsets.UTF_8.name())
}
