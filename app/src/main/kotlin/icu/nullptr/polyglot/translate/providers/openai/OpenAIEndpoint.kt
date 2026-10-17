package icu.nullptr.polyglot.translate.providers.openai

/**
 * Normalizes the user-provided API endpoint so that it always points at the
 * chat completions resource. Accepts a full endpoint or a base URL such as
 * "/v1"; it never adds a version segment on its own.
 */
internal object OpenAIEndpoint {
    private const val SCHEME_SEPARATOR = "://"
    private const val COMPLETIONS_PATH = "chat/completions"

    fun normalize(raw: String): String {
        val value = raw.trim()
        if (value.isEmpty()) return OpenAICompatibleTranslator.DEFAULT_ENDPOINT

        val withScheme = if (value.contains(SCHEME_SEPARATOR)) value else "https://$value"

        val suffixIndex = withScheme.indexOfFirst { it == '?' || it == '#' }
        val base = if (suffixIndex >= 0) withScheme.substring(0, suffixIndex) else withScheme
        val suffix = if (suffixIndex >= 0) withScheme.substring(suffixIndex) else ""

        val trimmedBase = base.trimEnd('/')
        val normalized = if (trimmedBase.lowercase().endsWith("/$COMPLETIONS_PATH")) {
            trimmedBase
        } else {
            "$trimmedBase/$COMPLETIONS_PATH"
        }
        return normalized + suffix
    }
}
