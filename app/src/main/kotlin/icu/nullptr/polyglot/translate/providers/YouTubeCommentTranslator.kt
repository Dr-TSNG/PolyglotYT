package icu.nullptr.polyglot.translate.providers

import android.os.Build
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import icu.nullptr.polyglot.module
import icu.nullptr.polyglot.translate.TranslationRequest
import icu.nullptr.polyglot.translate.TranslationResult
import icu.nullptr.polyglot.translate.Translator
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.Base64
import java.util.Locale

/** Uses the same Innertube action as YouTube's "Translate comment" button. */
object YouTubeCommentTranslator : Translator {
    const val ENDPOINT =
        "https://youtubei.googleapis.com/youtubei/v1/comment/perform_comment_action?prettyPrint=false"
    const val TRANSLATE_ACTION_TYPE = 22

    override fun translate(request: TranslationRequest): TranslationResult =
        TranslationResult(
            texts = request.texts.map { text ->
                if (text.isBlank()) text else translateOne(text, request)
            },
        )

    private fun translateOne(text: String, request: TranslationRequest): String {
        val clientVersion = module.hostVersionName
        val connection = URL(ENDPOINT).openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.doOutput = true
        connection.connectTimeout = request.timeoutMs
        connection.readTimeout = request.timeoutMs
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("Content-Type", "application/json")
        connection.setRequestProperty("X-YouTube-Client-Name", ANDROID_CLIENT_ID)
        connection.setRequestProperty("X-YouTube-Client-Version", clientVersion)
        connection.setRequestProperty(
            "User-Agent",
            "com.google.android.youtube/$clientVersion " +
                "(Linux; U; Android ${Build.VERSION.RELEASE}) gzip",
        )

        connection.outputStream.use { stream ->
            stream.write(buildRequestBody(text, request.targetLanguage).toByteArray(Charsets.UTF_8))
        }

        return connection.use {
            val body = it.readBodyOrThrow()
            parseTranslation(body)
        }
    }

    private fun buildRequestBody(text: String, targetLanguage: String): String {
        val client = JsonObject().apply {
            addProperty("clientName", "ANDROID")
            addProperty("clientVersion", module.hostVersionName)
            addProperty("androidSdkVersion", Build.VERSION.SDK_INT)
            addProperty("hl", Locale.getDefault().toLanguageTag())
        }
        return JsonObject().apply {
            add(
                "context",
                JsonObject().apply {
                    add("client", client)
                },
            )
            add(
                "actions",
                JsonArray().apply {
                    add(encodeTranslateAction(text, targetLanguage))
                },
            )
        }.toString()
    }

    private fun encodeTranslateAction(text: String, targetLanguage: String): String {
        val comment = protobuf {
            string(fieldNumber = 1, value = text)
        }
        val params = protobuf {
            message(fieldNumber = 1, value = comment)
        }
        val translateParams = protobuf {
            string(fieldNumber = 2, value = PLACEHOLDER_ID)
            message(fieldNumber = 3, value = params)
            string(fieldNumber = 4, value = targetLanguage)
        }
        val action = protobuf {
            int32(fieldNumber = 1, value = TRANSLATE_ACTION_TYPE)
            int32(fieldNumber = 2, value = 2)
            string(fieldNumber = 3, value = PLACEHOLDER_ID)
            string(fieldNumber = 5, value = PLACEHOLDER_ID)
            string(fieldNumber = 23, value = PLACEHOLDER_ID)
            message(fieldNumber = 31, value = translateParams)
        }
        return URLEncoder.encode(
            Base64.getEncoder().encodeToString(action),
            Charsets.UTF_8.name(),
        )
    }

    private fun parseTranslation(body: String): String {
        val mutations = JsonParser.parseString(body)
            .asJsonObject["frameworkUpdates"]
            ?.asJsonObject
            ?.get("entityBatchUpdate")
            ?.asJsonObject
            ?.get("mutations")
            ?.asJsonArray
            ?: throw IllegalStateException("YouTube comment translation response contained no mutations")

        for (mutation in mutations) {
            val content = mutation.asJsonObject["payload"]
                ?.asJsonObject
                ?.get("commentEntityPayload")
                ?.asJsonObject
                ?.get("translatedContent")
                ?.asJsonObject
                ?.get("content")
                ?.takeUnless { it.isJsonNull }
                ?.asString
            if (!content.isNullOrBlank()) {
                return content
            }
        }
        throw IllegalStateException("YouTube comment translation response contained no translated text")
    }

    private fun HttpURLConnection.readBodyOrThrow(): String {
        if (responseCode in 200..299) {
            return inputStream.bufferedReader(Charsets.UTF_8).use { it.readText() }
        }

        val errorBody = errorStream?.bufferedReader(Charsets.UTF_8)?.use { it.readText() }.orEmpty()
        throw IllegalStateException(
            "YouTube comment translation failed: HTTP $responseCode $responseMessage " +
                errorBody.replace(Regex("\\s+"), " ").take(ERROR_BODY_PREVIEW_LENGTH),
        )
    }

    private inline fun <T> HttpURLConnection.use(block: (HttpURLConnection) -> T): T =
        try {
            block(this)
        } finally {
            disconnect()
        }

    private inline fun protobuf(block: ProtoWriter.() -> Unit): ByteArray =
        ProtoWriter().apply(block).toByteArray()

    private class ProtoWriter {
        private val output = ByteArrayOutputStream()

        fun int32(fieldNumber: Int, value: Int) {
            tag(fieldNumber, WIRE_TYPE_VARINT)
            varint(value.toLong())
        }

        fun string(fieldNumber: Int, value: String) {
            message(fieldNumber, value.toByteArray(Charsets.UTF_8))
        }

        fun message(fieldNumber: Int, value: ByteArray) {
            tag(fieldNumber, WIRE_TYPE_LENGTH_DELIMITED)
            varint(value.size.toLong())
            output.write(value)
        }

        fun toByteArray(): ByteArray = output.toByteArray()

        private fun tag(fieldNumber: Int, wireType: Int) {
            varint(((fieldNumber shl 3) or wireType).toLong())
        }

        private fun varint(value: Long) {
            var remaining = value
            while (remaining and -0x80L != 0L) {
                output.write(((remaining and 0x7fL) or 0x80L).toInt())
                remaining = remaining ushr 7
            }
            output.write(remaining.toInt())
        }
    }

    private const val ANDROID_CLIENT_ID = "3"
    private const val PLACEHOLDER_ID = " "
    private const val WIRE_TYPE_VARINT = 0
    private const val WIRE_TYPE_LENGTH_DELIMITED = 2
    private const val ERROR_BODY_PREVIEW_LENGTH = 300
}
