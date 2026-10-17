package icu.nullptr.polyglot.translate

import com.google.gson.JsonElement
import com.google.gson.JsonParser

/**
 * Parses the response of a batch translation request.
 *
 * Accepts either:
 * - `{"translations":[{"id":1,"text":"..."}]}`
 * - `[{"id":1,"text":"..."}, ...]`
 * - `["translated line 1", "translated line 2"]`
 *
 * Missing or malformed entries are returned as empty strings so the caller can
 * fall back to individual requests.
 */
object BatchResponseParser {
    private val THINK_BLOCK = Regex("(?is)<think\\b[^>]*>.*?</think\\s*>")
    private val CODE_FENCE = Regex("(?s)```(?:json)?\\s*(.*?)```")
    private val NUMBERED_LINE = Regex("(?m)^\\s*(\\d{1,3})\\s*[.、):：]\\s*(.+?)\\s*$")
    private val TEXT_KEYS = listOf("text", "translation", "content", "translated", "value")

    fun parse(content: String, expectedCount: Int): List<String> {
        if (expectedCount <= 0) return emptyList()

        val cleaned = clean(content)
        val json = extractJson(cleaned)
        if (json != null) {
            val parsed = runCatching { parseJson(json, expectedCount) }.getOrNull()
            if (parsed != null && parsed.any { it.isNotBlank() }) return parsed
        }
        return parseNumberedLines(cleaned, expectedCount)
    }

    fun clean(content: String): String {
        val withoutThink = THINK_BLOCK.replace(content, " ")
        val fenced = CODE_FENCE.find(withoutThink)
        return (fenced?.groupValues?.getOrNull(1) ?: withoutThink).trim()
    }

    private fun extractJson(text: String): String? {
        val start = text.indexOfFirst { it == '{' || it == '[' }
        if (start < 0) return null

        val opener = text[start]
        val closer = if (opener == '{') '}' else ']'
        var depth = 0
        var inString = false
        var escaped = false

        for (index in start until text.length) {
            val char = text[index]
            if (inString) {
                when {
                    escaped -> escaped = false
                    char == '\\' -> escaped = true
                    char == '"' -> inString = false
                }
                continue
            }

            when (char) {
                '"' -> inString = true
                opener -> depth++
                closer -> {
                    depth--
                    if (depth == 0) return text.substring(start, index + 1)
                }
            }
        }
        return null
    }

    private fun parseJson(json: String, expectedCount: Int): List<String> {
        val element = JsonParser.parseString(json)
        val entries = when {
            element.isJsonObject -> element.asJsonObject.get("translations")
                ?.takeIf { it.isJsonArray }
                ?.asJsonArray

            element.isJsonArray -> element.asJsonArray
            else -> null
        } ?: return emptyTranslations(expectedCount)

        val result = MutableList(expectedCount) { "" }
        var cursor = 0

        for (entry in entries) {
            val (id, rawText) = readEntry(entry) ?: continue
            val text = sanitize(rawText)
            if (text.isEmpty()) continue

            val index = if (id != null && id in 1..expectedCount) {
                id - 1
            } else {
                nextEmptyIndex(result, cursor)
            }
            if (index !in result.indices) continue

            if (result[index].isEmpty()) {
                result[index] = text
                cursor = index + 1
            }
        }
        return result
    }

    private fun readEntry(element: JsonElement): Pair<Int?, String>? {
        if (element.isJsonPrimitive) {
            val primitive = element.asJsonPrimitive
            return if (primitive.isString) null to primitive.asString else null
        }
        if (!element.isJsonObject) return null

        val entry = element.asJsonObject
        val id = entry.get("id")?.let { value ->
            if (value.isJsonPrimitive) value.asString.toIntOrNull() else null
        }
        val text = TEXT_KEYS.firstNotNullOfOrNull { key ->
            entry.get(key)?.takeIf { it.isJsonPrimitive }?.asString
        } ?: return null

        return id to text
    }

    private fun nextEmptyIndex(result: List<String>, from: Int): Int {
        for (index in from until result.size) {
            if (result[index].isEmpty()) return index
        }
        return result.indexOfFirst { it.isEmpty() }
    }

    private fun sanitize(text: String): String =
        text.trim().removeSurrounding("\"").trim()

    private fun parseNumberedLines(content: String, expectedCount: Int): List<String> {
        val result = MutableList(expectedCount) { "" }
        var found = false
        for (match in NUMBERED_LINE.findAll(content)) {
            val id = match.groupValues[1].toIntOrNull() ?: continue
            if (id !in 1..expectedCount) continue

            val text = sanitize(match.groupValues[2])
            if (text.isEmpty()) continue

            result[id - 1] = text
            found = true
        }
        return if (found) result else emptyTranslations(expectedCount)
    }

    private fun emptyTranslations(count: Int): List<String> = MutableList(count) { "" }
}
