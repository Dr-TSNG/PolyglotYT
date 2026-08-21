package icu.nullptr.polyglot.captions

/**
 * Splits subtitle text into sentences so each complete sentence can be shown
 * on its own line, matching the speaker's phrasing (the same idea behind
 * immersive-translate showing one cue per line).
 */
object SentenceSplitter {

    // Sentence-ending punctuation, including CJK variants and ellipsis.
    private val sentenceEnders = charArrayOf(
        '.', '!', '?', ';',
        '。', '！', '？', '；', '…',
        '‼', '⁇', '⁈', '⁉',
    )

    /**
     * Splits [text] into sentences. A new sentence starts after a sentence
     * ender followed by whitespace, a quote, or the end of the string.
     * Runs of the same ender (e.g. "!!" or "...") are kept together.
     */
    fun split(text: String): List<String> {
        if (text.isBlank()) return emptyList()

        val sentences = ArrayList<String>()
        val current = StringBuilder()
        var lastEndIndex = -1
        var i = 0
        while (i < text.length) {
            val ch = text[i]
            current.append(ch)

            if (isSentenceEnder(ch)) {
                // Consume a run of identical enders ("...", "!!", "??").
                while (i + 1 < text.length && text[i + 1] == ch) {
                    current.append(text[i + 1])
                    i++
                }
                lastEndIndex = current.length
            } else if (isSentenceBreakAfter(ch) && lastEndIndex > 0) {
                // After an ender, a quote/whitespace finishes the sentence.
                val sentence = current.toString()
                sentences.add(sentence.trim())
                current.setLength(0)
                lastEndIndex = -1
                // Trim leading whitespace of the next sentence.
                while (i + 1 < text.length && text[i + 1].isWhitespace()) {
                    i++
                }
            }
            i++
        }

        if (current.isNotEmpty()) {
            sentences.add(current.toString().trim())
        }
        return sentences.filter { it.isNotEmpty() }
    }

    private fun isSentenceEnder(ch: Char): Boolean =
        sentenceEnders.contains(ch)

    /**
     * Characters that may terminate a sentence after an ender: quotes,
     * closing brackets and whitespace.
     */
    private fun isSentenceBreakAfter(ch: Char): Boolean =
        ch == '"' || ch == '"' || ch == '\'' || ch == ')' ||
            ch == '）' || ch == ']' || ch == '】' || ch == '»' ||
            ch == '”' || ch == '’' || ch.isWhitespace()
}
