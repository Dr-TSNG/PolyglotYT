package icu.nullptr.polyglot.captions

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

class CaptionSession {
    private val translations = ConcurrentHashMap<String, String>()
    private val observedCues = ConcurrentHashMap<String, CaptionCue>()
    private val renderedTexts = ConcurrentHashMap<String, Long>()
    private val observedCueCounter = AtomicInteger(0)

    fun translationFor(original: String?): String? {
        if (original == null) return null
        return translations[normalize(original)]
    }

    fun translatedCueContaining(fragment: String?): CaptionTranslation? {
        val normalized = normalize(fragment.orEmpty())
        if (normalized.isEmpty()) return null

        for (cue in observedCues.values) {
            val cueText = cue.normalizedText()
            if (cueText == normalized || !cueText.contains(normalized)) continue

            val translated = translations[cueText] ?: continue
            return CaptionTranslation(cueText, translated)
        }
        return null
    }

    fun putTranslation(original: String?, translated: String?) {
        if (original == null || translated == null) return
        translations[normalize(original)] = translated
    }

    fun clear() {
        translations.clear()
        observedCues.clear()
        renderedTexts.clear()
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

    private fun normalize(text: String): String =
        CaptionCue.normalize(text)

    private companion object {
        const val RENDERED_TEXT_LOG_INTERVAL_MS = 30_000L
    }

    data class CaptionTranslation(
        val original: String,
        val translated: String,
    )
}
