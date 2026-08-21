package icu.nullptr.polyglot.captions

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

class CaptionSession {
    private val translations = ConcurrentHashMap<String, String>()
    private val observedCues = ConcurrentHashMap<String, CaptionCue>()
    private val renderedTexts = ConcurrentHashMap<String, Long>()
    private val failedAt = ConcurrentHashMap<String, Long>()
    private val formattedTexts = ConcurrentHashMap.newKeySet<String>()
    private val observedCueCounter = AtomicInteger(0)

    fun translationFor(original: String?): String? {
        if (original == null) return null
        return translations[normalize(original)]
    }

    fun translatedCueContaining(fragment: String?): CaptionTranslation? {
        val normalized = normalize(fragment.orEmpty())
        if (normalized.isEmpty()) return null

        // Prefer an exact match first, then the longest cue that contains the
        // fragment. When several cues are similar (one being a substring of
        // another), the longest match is the most specific and avoids mapping
        // a fragment to the wrong, shorter cue's translation.
        var best: Pair<String, String>? = null
        for (cue in observedCues.values) {
            val cueText = cue.normalizedText()
            if (cueText.isEmpty()) continue
            val translated = translations[cueText] ?: continue
            if (cueText == normalized) {
                return CaptionTranslation(cueText, translated)
            }
            if (cueText.contains(normalized)) {
                if (best == null || cueText.length > best.first.length) {
                    best = cueText to translated
                }
            }
        }
        return best?.let { (cueText, translated) -> CaptionTranslation(cueText, translated) }
    }

    fun putTranslation(original: String?, translated: String?) {
        if (original == null || translated == null) return
        translations[normalize(original)] = translated
        trimToCapacity()
    }

    /** Records a failed translation so it is not re-requested for a while. */
    fun putFailure(original: String?) {
        if (original == null) return
        failedAt[normalize(original)] = System.currentTimeMillis()
    }

    /** True when a translation recently failed; skip re-requesting it. */
    fun isRecentlyFailed(original: String?): Boolean {
        if (original == null) return false
        val failed = failedAt[normalize(original)] ?: return false
        return System.currentTimeMillis() - failed < FAILURE_RETRY_TTL_MS
    }

    /**
     * True when [fragment] is the full text of an observed cue (not a partial
     * fragment of a longer cue). Used to decide whether to request a
     * translation for this render call.
     */
    fun isRenderedFullCue(fragment: String?): Boolean {
        val normalized = normalize(fragment.orEmpty())
        if (normalized.isEmpty()) return false
        for (cue in observedCues.values) {
            if (cue.normalizedText() == normalized) return true
        }
        return false
    }

    /**
     * True when [fragment] is (part of) a bilingual block we already injected.
     * YouTube re-renders our setText internally, passing the block or a
     * fragment of its first line (the original cue text) back into the render
     * method. We must pass those through untouched or the subtitle would be
     * cleared/duplicated. Match rules, in order:
     *  - exact recorded formatted text;
     *  - fragment starts with a translated cue (block = original + translation);
     *  - fragment is a PREFIX of a translated cue (partial first line of the
     *    block).
     */
    fun isFormattedRenderedText(fragment: String?): Boolean {
        val normalized = normalize(fragment.orEmpty())
        if (normalized.isEmpty()) return false
        if (formattedTexts.contains(normalized)) return true

        for (cue in observedCues.values) {
            val cueText = cue.normalizedText()
            if (cueText.isEmpty() || !translations.containsKey(cueText)) continue
            if (normalized.startsWith(cueText) && normalized.length > cueText.length) {
                return true
            }
            if (cueText.startsWith(normalized)) {
                return true
            }
        }
        return false
    }

    /**
     * True only when [fragment] equals a bilingual block we recorded via
     * [rememberFormattedText] (not a partial fragment of a cue). Used as a
     * re-entry guard so re-renders of an injected block pass through
     * untouched while real caption fragments are still processed.
     */
    fun isExactlyFormattedText(fragment: String?): Boolean {
        val normalized = normalize(fragment.orEmpty())
        return normalized.isNotEmpty() && formattedTexts.contains(normalized)
    }

    /** Records a formatted bilingual block so re-renders are recognized. */
    fun rememberFormattedText(text: CharSequence?) {
        val normalized = normalize(text?.toString().orEmpty())
        if (normalized.isNotEmpty()) {
            formattedTexts.add(normalized)
            if (formattedTexts.size > MAX_FORMATTED_TEXTS) {
                val excess = formattedTexts.size - MAX_FORMATTED_TEXTS
                val iterator = formattedTexts.iterator()
                var removed = 0
                while (iterator.hasNext() && removed < excess) {
                    iterator.next()
                    iterator.remove()
                    removed++
                }
            }
        }
    }

    fun clear() {
        translations.clear()
        observedCues.clear()
        renderedTexts.clear()
        failedAt.clear()
        formattedTexts.clear()
        observedCueCounter.set(0)
    }

    fun observeCue(cue: CaptionCue): Boolean {
        if (cue.normalizedText().isEmpty()) return false
        val previous = observedCues.putIfAbsent(cue.cacheKey(), cue)
        if (previous == null) {
            observedCueCounter.incrementAndGet()
            return true
        }
        return false
    }

    fun observeNewCues(cues: Iterable<CaptionCue>): List<CaptionCue> {
        val newCues = ArrayList<CaptionCue>()
        for (cue in cues) {
            if (observeCue(cue)) {
                newCues += cue
            }
        }
        return newCues
    }

    fun observeRenderedText(text: CharSequence?): Boolean {
        val normalized = normalize(text?.toString().orEmpty())
        if (normalized.isEmpty()) return false
        val now = System.currentTimeMillis()
        val previous = renderedTexts.put(normalized, now)
        return previous == null || now - previous > RENDERED_TEXT_LOG_INTERVAL_MS
    }

    fun observedCueCount(): Int = observedCueCounter.get()

    /** Returns the start time of the observed cue matching [text], if any. */
    fun cueStartMsFor(text: String?): Long? {
        val normalized = normalize(text.orEmpty())
        if (normalized.isEmpty()) return null
        return observedCues.values
            .firstOrNull { it.normalizedText() == normalized }
            ?.startMs
    }

    /** Observed cues whose start time falls in [fromMs, fromMs + windowMs]. */
    fun cuesInWindow(fromMs: Long, windowMs: Long): List<CaptionCue> =
        observedCues.values.filter { cue ->
            cue.startMs > fromMs && cue.startMs <= fromMs + windowMs
        }

    private fun trimToCapacity() {
        if (translations.size <= MAX_TRANSLATIONS) return
        val excess = translations.size - MAX_TRANSLATIONS
        var removed = 0
        val iterator = translations.entries.iterator()
        while (iterator.hasNext() && removed < excess) {
            iterator.next()
            iterator.remove()
            removed++
        }
    }

    private fun normalize(text: String): String =
        CaptionCue.normalize(text)

    private companion object {
        const val RENDERED_TEXT_LOG_INTERVAL_MS = 30_000L
        const val FAILURE_RETRY_TTL_MS = 10_000L
        const val MAX_TRANSLATIONS = 2_000
        const val MAX_FORMATTED_TEXTS = 500
    }

    data class CaptionTranslation(
        val original: String,
        val translated: String,
    )
}
