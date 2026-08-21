package icu.nullptr.polyglot.captions

import android.text.Layout
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AlignmentSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import icu.nullptr.polyglot.core.ConfigManager
import icu.nullptr.polyglot.module

/**
 * Builds the bilingual caption text shown to the user.
 *
 * - The translation line is rendered with a smaller font and a softer color
 *   (inspired by immersive-translate's subtitle styling), giving the two
 *   lines a clear visual hierarchy.
 * - Every line is forced to center alignment like YouTube's native captions.
 * - Long lines are wrapped at punctuation/word boundaries so they never
 *   overflow the screen, matching the speaker's phrasing.
 */
object BilingualFormatter {
    fun format(original: CharSequence?, translated: String?): CharSequence {
        val originalText = CaptionCue.normalize(original?.toString().orEmpty())
        val translatedText = CaptionCue.normalize(translated.orEmpty())
        if (translatedText.isBlank() || translatedText == originalText) {
            return original ?: ""
        }
        return when (module.config.subtitleMode) {
            ConfigManager.SUBTITLE_ORIGINAL_FIRST ->
                buildBilingual(originalText, translatedText, translationLineFirst = false)

            ConfigManager.SUBTITLE_TRANSLATION_FIRST ->
                buildBilingual(translatedText, originalText, translationLineFirst = true)

            ConfigManager.SUBTITLE_TRANSLATION_ONLY -> translatedText
            else -> translatedText
        }
    }

    private fun buildBilingual(
        firstLine: String,
        secondLine: String,
        translationLineFirst: Boolean,
    ): CharSequence {

        // Immersive-translate style: the original text on its own lines
        // (sentence by sentence when it is long), then the translation on
        // its own lines. No character-width wrapping: the caption view
        // handles long lines naturally, and splitting by sentence keeps the
        // phrasing readable like immersive-translate does.
        val firstLines = sentenceLines(firstLine)
        val secondLines = sentenceLines(secondLine)

        if (!module.config.subtitleStyleEnabled) {
            return centerAligned(firstLines + "\n" + secondLines)
        }

        val separator = module.config.subtitleSeparator
        val builder = SpannableStringBuilder()
        if (translationLineFirst && separator.isNotEmpty()) {
            builder.append(separator).append(' ')
        }
        builder.append(firstLines)
        val firstEnd = builder.length
        builder.append('\n')
        val secondStart = builder.length
        if (!translationLineFirst && separator.isNotEmpty()) {
            builder.append(separator).append(' ')
        }
        builder.append(secondLines)
        val secondEnd = builder.length

        // Apply the styling only to the translation line.
        val (styledStart, styledEnd) =
            if (translationLineFirst) 0 to firstEnd else secondStart to secondEnd

        val baseScale = module.config.subtitleBaseScale.coerceIn(0.5f, 1.5f)
        val translationScale = module.config.subtitleTranslationScale.coerceIn(0.5f, 1.0f)

        if (baseScale != 1.0f) {
            builder.setSpan(
                RelativeSizeSpan(baseScale),
                0,
                builder.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE,
            )
        }
        if (translationScale < 1.0f) {
            builder.setSpan(
                RelativeSizeSpan(translationScale),
                styledStart,
                styledEnd,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE,
            )
        }
        builder.setSpan(
            ForegroundColorSpan(translationColor(module.config.subtitleTranslationColor)),
            styledStart,
            styledEnd,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE,
        )
        return centerAligned(builder)
    }

    /**
     * Breaks [text] into display lines. Long text is split sentence by
     * sentence (each sentence on its own line, immersive-translate style);
     * a single very long sentence is additionally broken at word/punctuation
     * boundaries so a line never overflows the screen width.
     */
    private fun sentenceLines(text: String): String {
        if (text.isBlank()) return text

        val sentences = SentenceSplitter.split(text)
        if (sentences.size <= 1) {
            // One sentence: still bound its width so it never overflows.
            return wrapAtWidth(text, MAX_SINGLE_LINE_WIDTH)
        }

        // Multi-sentence: keep every sentence on its own line, but bound
        // each sentence width too (a long sentence should not overflow).
        return sentences.joinToString("\n") { wrapAtWidth(it, MAX_SINGLE_LINE_WIDTH) }
    }

    /**
     * Bounds a single line to [maxWidth] display units, breaking at the
     * nearest space/punctuation. Keeps YouTube's caption window from
     * clipping long lines, while preserving sentence structure.
     */
    private fun wrapAtWidth(text: String, maxWidth: Int): String {
        if (text.isEmpty()) return text
        if (displayWidth(text) <= maxWidth) return text

        val lines = ArrayList<String>()
        val current = StringBuilder()
        var currentWidth = 0
        var lastBreakIndex = -1

        for (ch in text) {
            val w = charWidth(ch)
            current.append(ch)
            currentWidth += w
            if (ch == ' ' || ch == '\t' || isPunctuation(ch)) {
                lastBreakIndex = current.length
            }
            if (currentWidth > maxWidth) {
                if (lastBreakIndex > 0 && lastBreakIndex < current.length) {
                    val keep = current.substring(0, lastBreakIndex).trimEnd()
                    val rest = current.substring(lastBreakIndex).trimStart()
                    if (keep.isNotEmpty()) lines.add(keep)
                    current.setLength(0)
                    current.append(rest)
                    currentWidth = displayWidth(rest)
                } else {
                    val trimmed = current.toString().trimEnd()
                    if (trimmed.isNotEmpty()) lines.add(trimmed)
                    current.setLength(0)
                    currentWidth = 0
                }
                lastBreakIndex = -1
            }
        }
        if (current.isNotEmpty()) {
            lines.add(current.toString().trim())
        }
        return lines.joinToString("\n")
    }

    private fun displayWidth(text: String): Int =
        text.sumOf { charWidth(it) }

    private fun charWidth(ch: Char): Int =
        if (ch.code in 0x1100..0x11FF || // Hangul Jamo
            ch.code in 0x2E80..0x303E || // CJK radicals, punctuation
            ch.code in 0x3040..0x33FF || // Hiragana/Katakana/CJK symbols
            ch.code in 0x3400..0x4DBF || // CJK ext A
            ch.code in 0x4E00..0x9FFF || // CJK unified
            ch.code in 0xAC00..0xD7A3 || // Hangul syllables
            ch.code in 0xF900..0xFAFF || // CJK compat
            ch.code in 0xFE30..0xFE4F || // CJK compat forms
            ch.code in 0xFF00..0xFF60 || // fullwidth forms
            ch.code in 0xFFE0..0xFFE6
        ) 2 else 1

    private fun isPunctuation(ch: Char): Boolean =
        ch in "，。、；：？！.,;:!?…—–·\'\"“”‘’()（）"

    /** Forces every line to center alignment, like YouTube's native captions. */
    private fun centerAligned(text: CharSequence): CharSequence {
        if (text is SpannableStringBuilder) {
            text.setSpan(
                AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
                0,
                text.length,
                Spanned.SPAN_PARAGRAPH,
            )
            return text
        }
        return SpannableStringBuilder(text).apply {
            setSpan(
                AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER),
                0,
                length,
                Spanned.SPAN_PARAGRAPH,
            )
        }
    }

    private fun translationColor(preset: String): Int =
        when (preset) {
            "white" -> 0xE6FFFFFF.toInt()
            "dim" -> 0xB3FFFFFF.toInt()
            "faint" -> 0x80FFFFFF.toInt()
            else -> 0xB3FFFFFF.toInt()
        }

    private val MAX_SINGLE_LINE_WIDTH = 110
}
