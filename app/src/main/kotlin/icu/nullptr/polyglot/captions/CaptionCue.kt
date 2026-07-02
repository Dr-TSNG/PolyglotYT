package icu.nullptr.polyglot.captions

data class CaptionCue(
    val videoId: String,
    val startMs: Long,
    val endMs: Long,
    val text: String,
    val windowId: Int = UNKNOWN_WINDOW_ID,
) {
    fun cacheKey(): String = "${videoId}|${windowId}|${startMs}|${endMs}|${normalizedText()}"

    fun normalizedText(): String = normalize(text)

    companion object {
        const val UNKNOWN_TIME_MS = -1L
        const val UNKNOWN_WINDOW_ID = -1

        private val whitespace = Regex("\\s+")

        fun normalize(text: String): String =
            text.replace(whitespace, " ").trim()
    }
}
