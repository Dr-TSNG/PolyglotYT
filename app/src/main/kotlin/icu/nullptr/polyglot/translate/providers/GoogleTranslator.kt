package icu.nullptr.polyglot.translate.providers

import com.google.gson.JsonParser
import icu.nullptr.polyglot.translate.TranslationRequest
import icu.nullptr.polyglot.translate.TranslationResult
import icu.nullptr.polyglot.translate.Translator
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.Locale

class GoogleTranslator : Translator {
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
            append("client=gtx")
            append("&sl=").append(urlEncode(sourceLanguage))
            append("&tl=").append(urlEncode(targetLanguage))
            append("&dt=t")
            append("&strip=1")
            append("&nonced=1")
            append("&q=").append(urlEncode(text))
        }

        val connection = URL("$ENDPOINT?$query").openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connectTimeout = request.timeoutMs
        connection.readTimeout = request.timeoutMs
        connection.setRequestProperty("Accept", "application/json")

        return connection.use {
            val body = it.readBodyOrThrow()
            parseTranslation(body)
        }
    }

    private fun parseTranslation(body: String): String {
        val trimmed = body.trimStart()
        if (trimmed.startsWith("<")) {
            throw IllegalStateException("Google translate returned an anti-abuse HTML page")
        }

        val root = JsonParser.parseString(body).asJsonArray
        val sentences = root[0].asJsonArray
        return buildString {
            for (sentence in sentences) {
                val items = sentence.asJsonArray
                if (!items[0].isJsonNull) {
                    append(items[0].asString)
                }
            }
        }
    }

    private fun HttpURLConnection.readBodyOrThrow(): String {
        if (responseCode in 200..299) {
            return inputStream.bufferedReader(Charsets.UTF_8).use { it.readText() }
        }

        val errorBody = errorStream?.bufferedReader(Charsets.UTF_8)?.use { it.readText() }.orEmpty()
        throw IllegalStateException("Google translate failed: HTTP $responseCode $responseMessage $errorBody")
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

    private companion object {
        const val ENDPOINT = "https://translate.googleapis.com/translate_a/single"
    }
}
