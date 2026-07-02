package icu.nullptr.polyglot.captions

data class CaptionCue(
    val videoId: String,
    val startMs: Long,
    val endMs: Long,
    val text: String,
) {
    fun cacheKey(): String = "${videoId}|${startMs}|${endMs}|${text.trim()}"
}
