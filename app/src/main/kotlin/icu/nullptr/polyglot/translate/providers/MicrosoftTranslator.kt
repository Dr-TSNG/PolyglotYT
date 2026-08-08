package icu.nullptr.polyglot.translate.providers

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import icu.nullptr.polyglot.module
import icu.nullptr.polyglot.translate.TranslationRequest
import icu.nullptr.polyglot.translate.TranslationResult
import icu.nullptr.polyglot.translate.Translator
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL
import java.net.URLEncoder
import java.util.Locale

object MicrosoftTranslator : Translator {
    const val EDGE_ENDPOINT = "https://edge.microsoft.com/translate/translatetext"
    const val DEFAULT_AZURE_ENDPOINT = "https://api.cognitive.microsofttranslator.com/translate"

    override fun translate(request: TranslationRequest): TranslationResult =
        try {
            translateWithEdge(request)
        } catch (edgeError: Exception) {
            if (module.config.microsoftApiKey.isBlank()) {
                throw IllegalStateException("Microsoft Edge translate failed", edgeError)
            }

            try {
                translateWithAzure(request)
            } catch (azureError: Exception) {
                azureError.addSuppressed(edgeError)
                throw azureError
            }
        }

    private fun translateWithEdge(request: TranslationRequest): TranslationResult {
        val nonBlankTexts = request.texts.filter { it.isNotBlank() }
        if (nonBlankTexts.isEmpty()) {
            return TranslationResult(request.texts)
        }

        val query = buildString {
            append("from=").append(urlEncode(microsoftLanguage(request.sourceLanguage)))
            append("&to=").append(urlEncode(microsoftLanguage(request.targetLanguage)))
            append("&isEnterpriseClient=false")
        }
        val connection = URL("$EDGE_ENDPOINT?$query").openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.doOutput = true
        connection.connectTimeout = request.timeoutMs
        connection.readTimeout = request.timeoutMs
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("Content-Type", "application/json")

        connection.outputStream.use { stream ->
            stream.write(buildEdgeRequestBody(nonBlankTexts).toByteArray(Charsets.UTF_8))
        }

        val translations = connection.use {
            parseTranslations(it.readBodyOrThrow("Microsoft Edge translate"))
        }
        return mergeTranslations(request.texts, translations, "Microsoft Edge translate")
    }

    private fun translateWithAzure(request: TranslationRequest): TranslationResult {
        val nonBlankTexts = request.texts.filter { it.isNotBlank() }
        if (nonBlankTexts.isEmpty()) {
            return TranslationResult(request.texts)
        }

        val sourceLanguage = microsoftLanguage(request.sourceLanguage)
        val targetLanguage = microsoftLanguage(request.targetLanguage)
        val query = buildString {
            append("api-version=3.0")
            if (sourceLanguage.isNotEmpty()) {
                append("&from=").append(urlEncode(sourceLanguage))
            }
            append("&to=").append(urlEncode(targetLanguage))
            append("&textType=plain")
        }
        val endpoint = azureTranslateEndpoint(module.config.microsoftEndpoint)
        val separator = if ('?' in endpoint) '&' else '?'
        val connection = URL("$endpoint$separator$query").openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.doOutput = true
        connection.connectTimeout = request.timeoutMs
        connection.readTimeout = request.timeoutMs
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", module.config.microsoftApiKey)
        module.config.microsoftRegion.takeIf { it.isNotBlank() }?.let { region ->
            connection.setRequestProperty("Ocp-Apim-Subscription-Region", region)
        }

        connection.outputStream.use { stream ->
            stream.write(buildAzureRequestBody(nonBlankTexts).toByteArray(Charsets.UTF_8))
        }

        val translations = connection.use {
            parseTranslations(it.readBodyOrThrow("Microsoft Azure translate"))
        }
        return mergeTranslations(request.texts, translations, "Microsoft Azure translate")
    }

    private fun buildEdgeRequestBody(texts: List<String>): String =
        JsonArray().apply {
            texts.forEach { text -> add(text) }
        }.toString()

    private fun buildAzureRequestBody(texts: List<String>): String =
        JsonArray().apply {
            texts.forEach { text ->
                add(
                    JsonObject().apply {
                        addProperty("Text", text)
                    },
                )
            }
        }.toString()

    private fun parseTranslations(body: String): List<String> =
        JsonParser.parseString(body).asJsonArray.map { result ->
            result.asJsonObject["translations"]
                .asJsonArray[0]
                .asJsonObject["text"]
                .asString
        }

    private fun mergeTranslations(
        originals: List<String>,
        translations: List<String>,
        label: String,
    ): TranslationResult {
        val expectedCount = originals.count { it.isNotBlank() }
        check(translations.size == expectedCount) {
            "$label returned ${translations.size} results for $expectedCount texts"
        }

        var translatedIndex = 0
        return TranslationResult(
            texts = originals.map { text ->
                if (text.isBlank()) text else translations[translatedIndex++]
            },
        )
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

    private fun microsoftLanguage(language: String): String =
        when (language.lowercase(Locale.ROOT)) {
            "auto" -> ""
            "zh", "zh-cn", "zh-hans" -> "zh-Hans"
            "zh-tw", "zh-hk", "zh-hant" -> "zh-Hant"
            else -> language
        }

    private fun azureTranslateEndpoint(configuredEndpoint: String): String {
        val endpoint = configuredEndpoint.ifBlank { DEFAULT_AZURE_ENDPOINT }.trimEnd('/')
        val uri = URI(endpoint)
        val path = uri.path.trimEnd('/')
        if (path.endsWith("/translate", ignoreCase = true)) {
            return endpoint
        }

        return if (uri.host.orEmpty().endsWith("cognitiveservices.azure.com", ignoreCase = true)) {
            "$endpoint/translator/text/v3.0/translate"
        } else {
            "$endpoint/translate"
        }
    }

    private fun urlEncode(value: String): String =
        URLEncoder.encode(value, Charsets.UTF_8.name())
}
