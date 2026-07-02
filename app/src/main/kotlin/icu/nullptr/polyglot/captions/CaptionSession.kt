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

    fun putTranslation(original: String?, translated: String?) {
        if (original == null || translated == null) return
        translations[normalize(original)] = translated
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

    fun observeCues(cues: Iterable<CaptionCue>): Int {
        var count = 0
        for (cue in cues) {
            if (observeCue(cue)) count++
        }
        return count
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
}
