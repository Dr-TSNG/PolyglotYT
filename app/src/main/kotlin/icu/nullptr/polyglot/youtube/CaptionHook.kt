package icu.nullptr.polyglot.youtube

import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.util.SparseArray
import android.view.View
import icu.nullptr.polyglot.captions.BilingualFormatter
import icu.nullptr.polyglot.captions.CaptionCue
import icu.nullptr.polyglot.captions.CaptionLanguageState
import icu.nullptr.polyglot.captions.CaptionSession
import icu.nullptr.polyglot.module
import icu.nullptr.polyglot.translate.TranslationManager
import icu.nullptr.polyglot.util.hook
import icu.nullptr.polyglot.util.logD
import icu.nullptr.polyglot.util.logI
import icu.nullptr.polyglot.util.logV
import icu.nullptr.polyglot.util.logW
import icu.nullptr.polyglot.util.toMethod
import org.luckypray.dexkit.DexKitBridge
import org.luckypray.dexkit.result.ClassData
import org.luckypray.dexkit.result.MethodData
import java.lang.reflect.Executable
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.util.WeakHashMap
import java.util.concurrent.atomic.AtomicLong

object CaptionHook : BaseHook {
    override val name = "CaptionHook"
    override val totalHooks = 4

    private val session = CaptionSession()
    private val mainHandler = Handler(Looper.getMainLooper())
    private val rendererStates = WeakHashMap<Any, RendererState>()
    private val rendererSequence = AtomicLong(0L)
    private val applyingTranslatedText = ThreadLocal.withInitial { false }

    override fun install(dexkit: DexKitBridge): Int {
        var installed = 0

        if (installTimelineBuildHook(dexkit)) {
            installed++
        }

        if (installOverlayUpdateHooks(dexkit)) {
            installed++
        }

        if (installRenderTextHooks(dexkit)) {
            installed++
        }

        if (installCaptionLanguageHooks(dexkit)) {
            installed++
        }

        logI(name, "Installed $installed caption hook(s)")
        return installed
    }

    fun formatCaption(original: CharSequence?): CharSequence {
        val translated = session.translationFor(original?.toString())
        return BilingualFormatter.format(original, translated)
    }

    private fun installTimelineBuildHook(dexkit: DexKitBridge): Boolean {
        val method = dexkit.findCaptionTimelineBuildMethod()
            ?.toMethod() ?: run {
            logW(name, "Caption timeline builder not found")
            return false
        }

        hook(method) { chain ->
            val result = chain.proceed()
            if (module.config.enabled) {
                val newCues = observeTimeline(result)
                requestTranslations(newCues, source = "timeline", priority = PRIORITY_TIMELINE)
                logObservedCueCount(newCues.size, source = "timeline")
            }
            result
        }

        logD(name, "Hooked caption timeline builder: ${method.shortName()}")
        return true
    }

    private fun installRenderTextHooks(dexkit: DexKitBridge): Boolean {
        val methods = dexkit.findCaptionRenderTextMethods()
            .map { it.toMethod() }
            .filter { method ->
                View::class.java.isAssignableFrom(method.declaringClass) &&
                        method.declaringClass.hasInstanceFieldAssignableTo(Editable::class.java)
            }
            .distinctBy { it.stableId() }

        var installed = 0
        for (method in methods) {
            hook(method) { chain ->
                if (applyingTranslatedText.get() == true) {
                    return@hook chain.proceed()
                }

                if (!module.config.enabled) {
                    return@hook chain.proceed()
                }

                val thisObject = chain.thisObject ?: return@hook chain.proceed()
                if (!thisObject.javaClass.hasInstanceFieldAssignableTo(Editable::class.java)) {
                    return@hook chain.proceed()
                }

                val original = chain.getArg(0) as? CharSequence
                    ?: return@hook chain.proceed()
                val normalizedOriginal = CaptionCue.normalize(original.toString())
                if (normalizedOriginal.isEmpty()) {
                    return@hook chain.proceed()
                }

                rememberRendererState(thisObject, method, normalizedOriginal)
                logRenderCall(thisObject, normalizedOriginal)

                // Request a translation for whatever is being rendered (the
                // fragment or the full cue); prefetch upcoming cues so the
                // next caption is usually ready when it shows.
                if (!session.isFormattedRenderedText(normalizedOriginal)) {
                    requestTranslation(normalizedOriginal, source = "render", priority = PRIORITY_VISIBLE)
                    prefetchNearbyCues(normalizedOriginal)
                }

                // If this text is exactly a bilingual block we injected
                // earlier (YouTube re-renders our setText), pass it through
                // untouched. This stops the refresh loop: inject ->
                // re-render -> re-inject. Only the exact recorded block is
                // matched; fragments that are prefixes of a cue must still
                // be processed normally.
                if (session.isExactlyFormattedText(normalizedOriginal)) {
                    return@hook chain.proceed()
                }

                // Map the rendered fragment to its full cue and translation.
                // YouTube renders one cue through several fragment calls; the
                // full cue text is what carries the translation in the cache.
                var matching = session.translatedCueContaining(normalizedOriginal)

                // Fallback: if the fragment itself has a translation in the
                // cache (the render path requested it directly), use it so
                // the caption never shows untranslated text while waiting.
                var replacementText: CharSequence? = null
                if (matching != null) {
                    replacementText = replacementForTranslatedCue(
                        matching.original, normalizedOriginal, matching.translated,
                    )
                } else {
                    val ownTranslation = session.translationFor(normalizedOriginal)
                    if (ownTranslation != null) {
                        logV(name, "Fragment fallback translation: " + shorten(normalizedOriginal))
                        replacementText = BilingualFormatter.format(normalizedOriginal, ownTranslation)
                    }
                }

                if (replacementText != null) {
                    session.rememberFormattedText(replacementText)
                    return@hook chain.proceed(arrayOf(replacementText))
                }

                // No matching cue yet or translation still pending: fall
                // back to the plain caption formatter.
                val formatted = formatCaption(original)
                if (formatted.toString() != original.toString()) {
                    session.rememberFormattedText(formatted)
                    return@hook chain.proceed(arrayOf(formatted))
                }
                chain.proceed()
            }

            logD(name, "Hooked caption renderer: ${method.shortName()}")
            installed++
        }

        if (installed == 0) {
            logW(name, "Caption renderer not found")
        }
        return installed > 0
    }

    private fun installCaptionLanguageHooks(dexkit: DexKitBridge): Boolean {
        val trackClassName = dexkit.findCaptionTrackClassName() ?: run {
            logW(name, "Caption track class not found")
            return false
        }

        val methods = dexkit.findCaptionTrackStateMethods(trackClassName)
            .map { it.toMethod() }
            .distinctBy { it.stableId() }

        if (methods.isEmpty()) {
            logW(name, "Caption language methods not found")
            return false
        }

        for (method in methods) {
            hook(method) { chain ->
                observeCaptionTrack(chain.getArg(0), "${method.shortName()} arg0")
                val result = chain.proceed()
                result
            }
        }

        logD(name, "Hooked caption language trackers: ${methods.size}")
        return true
    }

    private fun installOverlayUpdateHooks(dexkit: DexKitBridge): Boolean {
        val methods = dexkit.findCaptionOverlayUpdateMethods()
            .map { it.toMethod() }
            .filter { method ->
                View::class.java.isAssignableFrom(method.declaringClass) &&
                        method.declaringClass.hasInstanceFieldAssignableTo(SparseArray::class.java)
            }
            .distinctBy { it.stableId() }

        var installed = 0
        for (method in methods) {
            hook(method) { chain ->
                if (module.config.enabled) {
                    val newCues = observeCueList(chain.getArg(0))
                    requestTranslations(newCues, source = "overlay", priority = PRIORITY_UPCOMING)
                    logObservedCueCount(newCues.size, source = "overlay")
                }
                chain.proceed()
            }

            logD(name, "Hooked caption overlay update: ${method.shortName()}")
            installed++
        }

        if (installed == 0) {
            logW(name, "Caption overlay update methods not found")
        }
        return installed > 0
    }

    private fun observeTimeline(result: Any?): List<CaptionCue> {
        if (result == null) return emptyList()

        val lists = result.instanceFieldValues()
            .filterIsInstance<List<*>>()
            .filter { it.isNotEmpty() }
        val texts = lists.firstOrNull { list -> list.any { it is CharSequence } } ?: return emptyList()
        val timeLists = lists.filter { list -> list.all { it is Number } }
        if (timeLists.size < 2) return emptyList()

        val (starts, ends) = orderedTimelineTimeLists(timeLists[0], timeLists[1])

        val size = minOf(starts.size, ends.size, texts.size)
        if (size == 0) return emptyList()

        val cues = ArrayList<CaptionCue>(size)
        for (index in 0 until size) {
            val text = texts[index] as? CharSequence ?: continue
            val normalized = CaptionCue.normalize(text.toString())
            if (normalized.isEmpty()) continue

            cues.add(
                CaptionCue(
                    videoId = "",
                    startMs = (starts[index] as? Number)?.toLong() ?: CaptionCue.UNKNOWN_TIME_MS,
                    endMs = (ends[index] as? Number)?.toLong() ?: CaptionCue.UNKNOWN_TIME_MS,
                    text = normalized,
                ),
            )
        }
        return session.observeNewCues(cues)
    }

    private fun observeCueList(arg: Any?): List<CaptionCue> {
        val list = arg as? List<*> ?: return emptyList()
        val cues = list.mapNotNull { item ->
            item?.toCaptionCue()
        }
        return session.observeNewCues(cues)
    }

    private fun Any.toCaptionCue(): CaptionCue? {
        val text = instanceFieldValues()
            .filterIsInstance<CharSequence>()
            .map { CaptionCue.normalize(it.toString()) }
            .filter { it.isNotEmpty() }
            .maxByOrNull { it.length }
            ?: return null

        return CaptionCue(
            videoId = "",
            startMs = firstInstanceFieldValue<Long>() ?: CaptionCue.UNKNOWN_TIME_MS,
            endMs = CaptionCue.UNKNOWN_TIME_MS,
            text = text,
            windowId = firstInstanceFieldValue<Int>() ?: CaptionCue.UNKNOWN_WINDOW_ID,
        )
    }

    private fun orderedTimelineTimeLists(first: List<*>, second: List<*>): Pair<List<*>, List<*>> {
        val comparableSize = minOf(first.size, second.size)
        var firstBeforeSecond = 0
        var secondBeforeFirst = 0
        for (index in 0 until comparableSize) {
            val a = (first[index] as? Number)?.toLong() ?: continue
            val b = (second[index] as? Number)?.toLong() ?: continue
            if (a <= b) firstBeforeSecond++ else secondBeforeFirst++
        }
        return if (firstBeforeSecond >= secondBeforeFirst) {
            first to second
        } else {
            second to first
        }
    }

    private inline fun <reified T> Any.firstInstanceFieldValue(): T? =
        instanceFieldValues().firstNotNullOfOrNull { it as? T }

    private fun Any.instanceFieldValues(): List<Any?> {
        val values = ArrayList<Any?>()
        var clazz: Class<*>? = javaClass
        while (clazz != null) {
            for (field in clazz.declaredFields) {
                if (Modifier.isStatic(field.modifiers)) continue
                values += field.getValueOrNull(this)
            }
            clazz = clazz.superclass
        }
        return values
    }

    private fun Field.getValueOrNull(instance: Any): Any? =
        runCatching {
            isAccessible = true
            get(instance)
        }.getOrNull()

    private fun Class<*>.hasInstanceFieldAssignableTo(type: Class<*>): Boolean {
        var clazz: Class<*>? = this
        while (clazz != null) {
            for (field in clazz.declaredFields) {
                if (!Modifier.isStatic(field.modifiers) && type.isAssignableFrom(field.type)) {
                    return true
                }
            }
            clazz = clazz.superclass
        }
        return false
    }

    private fun logObservedCueCount(newCueCount: Int, source: String) {
        if (newCueCount <= 0) return

        val total = session.observedCueCount()
        if (total <= 5 || total % 25 == 0) {
            logV(name, "Observed $newCueCount new caption cue(s) from $source, total=$total")
        }
    }

    private fun requestTranslations(cues: List<CaptionCue>, source: String, priority: Int) {
        // Translate each cue individually. Batching several cues into one
        // request risks misaligned translations when the provider reorders
        // or drops lines (seen as "wrong translation" on screen), so we keep
        // the reliable per-cue path and rely on prefetch for context.
        for (cue in cues) {
            requestTranslation(cue.text, source, priority)
        }
    }

    private fun requestTranslation(text: String, source: String, priority: Int) {
        if (session.translationFor(text) != null) return
        if (session.isRecentlyFailed(text)) return

        val sourceLanguage = CaptionLanguageState.currentSourceLanguage()
        TranslationManager.enqueue(
            text = text,
            context = "YouTube subtitle $source",
            sourceLanguage = sourceLanguage,
            priority = priority,
            onTranslated = { original, translated ->
                session.putTranslation(original, translated)
                logV(name, "Translated caption from $source, sourceLanguage=$sourceLanguage, length=${original.length}")
            },
            onFailed = { original ->
                session.putFailure(original)
                logV(name, "Caption translation failed from $source, length=${original.length}")
            },
        )
    }

    private fun shorten(text: String, max: Int = 60): String =
        if (text.length <= max) text else text.take(max) + "…"

    /**
     * Prefetches the cues that will appear shortly after the currently
     * rendered one (same idea as immersive-translate translating the visible
     * area first and prefetching nearby content), so the next caption usually
     * already has its translation by the time it shows.
     */
    private fun prefetchNearbyCues(renderedText: String) {
        val anchorStartMs = session.cueStartMsFor(renderedText) ?: return
        val nearby = session.cuesInWindow(anchorStartMs, PREFETCH_WINDOW_MS)
        if (nearby.isEmpty()) return
        logV(name, "Prefetching ${nearby.size} upcoming cue(s) after $anchorStartMs ms")
        requestTranslations(nearby, "prefetch", PRIORITY_UPCOMING)
    }

    private fun observeCaptionTrack(track: Any?, source: String) {
        if (!CaptionLanguageState.updateFromCaptionTrack(track, source)) return

        session.clear()
        synchronized(rendererStates) {
            rendererStates.clear()
        }
        rendererSequence.set(0L)
    }

    /**
     * Builds the text to render for a caption render call. When the
     * rendered fragment belongs to a translated cue (it equals the cue or
     * is the tail of it), the full bilingual block is returned. When the
     * fragment is a leading piece of the cue, an empty string is returned
     * so the caption window does not show partial rows around the block.
     */
    private fun replacementForTranslatedCue(
        cueText: String,
        renderedFragment: String,
        translated: String,
    ): CharSequence {
        val normalizedCue = CaptionCue.normalize(cueText)
        val normalizedFragment = CaptionCue.normalize(renderedFragment)
        if (normalizedCue.isEmpty() || normalizedFragment.isEmpty()) {
            return renderedFragment
        }

        val belongsToCue =
            normalizedCue == normalizedFragment ||
            normalizedCue.endsWith(normalizedFragment)
        return if (belongsToCue) {
            BilingualFormatter.format(normalizedCue, translated)
        } else {
            ""
        }
    }

    /**
     * DIAGNOSTIC: log every render call with the view instance identity,
     * visibility state and fragment text, so we can tell which renderer is
     * actually visible on screen. Throttled per (instance, text) pair.
     */
    private val renderDiagLastLog = java.util.concurrent.ConcurrentHashMap<String, Long>()

    private fun logRenderCall(renderer: Any, normalizedText: String) {
        val now = System.currentTimeMillis()
        val identity = System.identityHashCode(renderer)
        val key = identity.toString() + "|" + normalizedText
        val last = renderDiagLastLog[key] ?: 0L
        if (now - last < 3000L) return
        renderDiagLastLog[key] = now
        if (renderDiagLastLog.size > 1000) renderDiagLastLog.clear()

        var visibility = "?"
        var bounds = "?"
        var shown = "?"
        if (renderer is View) {
            visibility = when (renderer.visibility) {
                View.VISIBLE -> "VISIBLE"
                View.INVISIBLE -> "INVISIBLE"
                else -> "GONE"
            }
            shown = if (renderer.isShown) "shown" else "hidden"
            bounds = "(" + renderer.left + "," + renderer.top + ")-(" + renderer.right + "," + renderer.bottom + ")"
        }
        val inCache = session.isRenderedFullCue(normalizedText)
        val hasTrans = session.translationFor(normalizedText) != null
        logV(name, "RENDER-DIAG id=" + identity + " cls=" + renderer.javaClass.name +
            " vis=" + visibility + " " + shown + " bounds=" + bounds +
            " fullCue=" + inCache + " trans=" + hasTrans +
            " text='" + shorten(normalizedText, 48) + "'")
    }

    private fun rememberRendererState(renderer: Any, method: Method, normalizedText: String) {
        if (normalizedText.isEmpty()) return

        synchronized(rendererStates) {
            rendererStates[renderer] = RendererState(
                method = method,
                normalizedText = normalizedText,
                sequence = rendererSequence.incrementAndGet(),
                updatedAtMs = System.currentTimeMillis(),
            )
        }
    }

    private fun touchRendererState(renderer: Any, state: RendererState) {
        synchronized(rendererStates) {
            val current = rendererStates[renderer]
            if (current != null && current.normalizedText == state.normalizedText) {
                rendererStates[renderer] = state.copy(updatedAtMs = System.currentTimeMillis())
            }
        }
    }

    private fun invokeRenderer(
        renderer: Any,
        method: Method,
        text: CharSequence,
        source: String,
        originalLength: Int,
    ) {
        runCatching {
            applyingTranslatedText.set(true)
            method.invoke(renderer, text)
        }.onSuccess {
            if (text.isNotEmpty()) {
                logV(name, "Refreshed visible caption from $source, length=$originalLength")
            }
        }.onFailure { e ->
            logW(name, "Unable to refresh visible caption", e)
        }.also {
            applyingTranslatedText.set(false)
        }
    }

    private fun Executable.shortName(): String =
        "${declaringClass.name}#$name(${parameterTypes.joinToString { it.simpleName }})"

    private fun Executable.stableId(): String =
        "${declaringClass.name}#$name#${parameterTypes.joinToString { it.name }}"

    private fun DexKitBridge.findCaptionTimelineBuildMethod(): MethodData? {
        val addLineMethod = findMethod {
            matcher {
                returnType("void")
                paramTypes("java.lang.CharSequence", "long", "long")
                usingEqStrings(NON_DECREASING_SUBTITLE_TIME_ERROR)
            }
        }.singleOrNull()

        val builderClass = addLineMethod?.declaredClass ?: return null
        val candidates = builderClass.methods.filter { method ->
            method.isMethod &&
                    method.paramCount == 0 &&
                    method.returnTypeName != "void" &&
                    method.returnType?.hasAtLeastListFields(count = 3) == true
        }

        return candidates.singleOrNull() ?: candidates.firstOrNull()
    }

    private fun ClassData.hasAtLeastListFields(count: Int): Boolean =
        fields.count { !Modifier.isStatic(it.modifiers) && it.typeName == "java.util.List" } >= count

    private fun ClassData.hasInstanceFieldTypeInHierarchy(typeName: String): Boolean {
        var clazz: ClassData? = this
        while (clazz != null) {
            if (clazz.fields.any { !Modifier.isStatic(it.modifiers) && it.typeName == typeName }) {
                return true
            }
            clazz = clazz.superClass
        }
        return false
    }

    private fun DexKitBridge.findCaptionRenderTextMethods(): List<MethodData> =
        findMethod {
            matcher {
                returnType("void")
                paramTypes("java.lang.CharSequence")
            }
        }.filter { method ->
            method.declaredClass?.hasInstanceFieldTypeInHierarchy(EDITABLE_TYPE) == true
        }

    private fun DexKitBridge.findCaptionOverlayUpdateMethods(): List<MethodData> =
        findMethod {
            matcher {
                returnType("void")
                paramTypes("java.util.List")
            }
        }.filter { method ->
            method.declaredClass?.hasInstanceFieldTypeInHierarchy(SPARSE_ARRAY_TYPE) == true
        }

    private fun DexKitBridge.findCaptionTrackClassName(): String? =
        findMethod {
            matcher {
                returnType("boolean")
                usingEqStrings(AUTO_TRANSLATE_CAPTIONS_OPTION)
            }
        }.singleOrNull { method -> method.isMethod && method.paramCount == 0 }
            ?.toMethod()
            ?.declaringClass
            ?.name

    private fun DexKitBridge.findCaptionTrackStateMethods(trackClassName: String): List<MethodData> =
        findMethod {
            matcher {
                returnType("void")
                paramTypes(trackClassName)
                usingEqStrings(MENU_ITEM_CAPTIONS_KEY)
            }
        }.filter { method ->
            method.isMethod &&
                    !Modifier.isAbstract(method.modifiers)
        }

    private const val NON_DECREASING_SUBTITLE_TIME_ERROR =
        "subtitles are not given in non-decreasing start time order"
    private const val AUTO_TRANSLATE_CAPTIONS_OPTION = "AUTO_TRANSLATE_CAPTIONS_OPTION"
    private const val MENU_ITEM_CAPTIONS_KEY = "menu_item_captions"
    private const val EDITABLE_TYPE = "android.text.Editable"
    private const val SPARSE_ARRAY_TYPE = "android.util.SparseArray"
    private const val RENDERER_STATE_TTL_MS = 60_000L
    private const val PREFETCH_WINDOW_MS = 10_000L
    private const val REFRESH_COOLDOWN_MS = 2_000L

    private const val PRIORITY_VISIBLE = TranslationManager.PRIORITY_VISIBLE
    private const val PRIORITY_UPCOMING = TranslationManager.PRIORITY_UPCOMING
    private const val PRIORITY_TIMELINE = TranslationManager.PRIORITY_BACKGROUND

    private data class RendererState(
        val method: Method,
        val normalizedText: String,
        val sequence: Long,
        val updatedAtMs: Long,
    )
}