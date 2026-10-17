package icu.nullptr.polyglot.translate

/**
 * Holds metadata about the video that is currently being translated.
 *
 * The video title hook calls [updateTitle] whenever the player metadata changes.
 */
object VideoContext {
    private const val MAX_TITLE_LENGTH = 200

    private val whitespace = Regex("\\s+")

    @Volatile
    private var title: String = ""

    fun currentTitle(): String = title

    fun updateTitle(value: CharSequence?) {
        title = value?.toString().orEmpty()
            .replace(whitespace, " ")
            .replace("\"", "")
            .trim()
            .take(MAX_TITLE_LENGTH)
    }

    fun promptTitle(): String = title.ifBlank { "unknown" }

    fun clear() {
        title = ""
    }
}
