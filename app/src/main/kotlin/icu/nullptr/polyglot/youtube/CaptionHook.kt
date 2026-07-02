package icu.nullptr.polyglot.youtube

import android.text.Editable
import android.util.Log
import android.util.SparseArray
import android.view.View
import icu.nullptr.polyglot.captions.BilingualFormatter
import icu.nullptr.polyglot.captions.CaptionCue
import icu.nullptr.polyglot.captions.CaptionSession
import icu.nullptr.polyglot.module
import icu.nullptr.polyglot.util.hook
import icu.nullptr.polyglot.util.toMethod
import org.luckypray.dexkit.DexKitBridge
import org.luckypray.dexkit.result.ClassData
import org.luckypray.dexkit.result.MethodData
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.Modifier

object CaptionHook : BaseHook {
    override val name = "CaptionHook"

    private val session = CaptionSession()

    override fun install(dexkit: DexKitBridge): Boolean {
        var installed = 0

        if (installTimelineBuildHook(dexkit)) {
            installed++
        }

        installed += installOverlayUpdateHooks(dexkit)

        installed += installRenderTextHooks(dexkit)

        module.log(Log.INFO, name, "Installed $installed caption hook(s)")
        return installed > 0
    }

    fun formatCaption(original: CharSequence?): CharSequence {
        val translated = session.translationFor(original?.toString())
        return BilingualFormatter.format(original, translated)
    }

    private fun installTimelineBuildHook(dexkit: DexKitBridge): Boolean {
        val method = dexkit.findCaptionTimelineBuildMethod()
            ?.toHookMethodOrNull("caption timeline builder") ?: run {
            module.log(Log.WARN, name, "Caption timeline builder not found by DexKit features")
            return false
        }

        hook(method) { chain ->
            val result = chain.proceed()
            if (module.config.enabled) {
                val newCueCount = observeTimeline(result)
                logObservedCueCount(newCueCount, source = "timeline")
            }
            result
        }

        module.log(Log.INFO, name, "Hooked caption timeline builder: ${method.shortName()}")
        return true
    }

    private fun installRenderTextHooks(dexkit: DexKitBridge): Int {
        val methods = dexkit.findCaptionRenderTextMethods()
            .mapNotNull { it.toHookMethodOrNull("caption renderer") }
            .filter { method ->
                View::class.java.isAssignableFrom(method.declaringClass) &&
                    method.declaringClass.hasInstanceFieldAssignableTo(Editable::class.java)
            }
            .distinctBy { it.stableId() }

        var installed = 0
        for (method in methods) {
            hook(method) { chain ->
                if (!module.config.enabled) {
                    return@hook chain.proceed()
                }

                val thisObject = chain.thisObject ?: return@hook chain.proceed()
                if (!thisObject.javaClass.hasInstanceFieldAssignableTo(Editable::class.java)) {
                    return@hook chain.proceed()
                }

                val original = chain.getArg(0) as? CharSequence
                    ?: return@hook chain.proceed()

                if (session.observeRenderedText(original)) {
                    val length = CaptionCue.normalize(original.toString()).length
                    module.log(Log.DEBUG, name, "Observed rendered caption text, length=$length")
                }

                val formatted = formatCaption(original)
                if (formatted === original || formatted.toString() == original.toString()) {
                    chain.proceed()
                } else {
                    chain.proceed(arrayOf(formatted))
                }
            }

            module.log(Log.INFO, name, "Hooked caption renderer: ${method.shortName()}")
            installed++
        }

        if (installed == 0) {
            module.log(Log.WARN, name, "Caption renderer not found by DexKit features")
        }
        return installed
    }

    private fun installOverlayUpdateHooks(dexkit: DexKitBridge): Int {
        val methods = dexkit.findCaptionOverlayUpdateMethods()
            .mapNotNull { it.toHookMethodOrNull("caption overlay update") }
            .filter { method ->
                View::class.java.isAssignableFrom(method.declaringClass) &&
                    method.declaringClass.hasInstanceFieldAssignableTo(SparseArray::class.java)
            }
            .distinctBy { it.stableId() }

        var installed = 0
        for (method in methods) {
            hook(method) { chain ->
                if (module.config.enabled) {
                    val newCueCount = observeCueList(chain.getArg(0))
                    logObservedCueCount(newCueCount, source = "overlay")
                }
                chain.proceed()
            }

            module.log(Log.INFO, name, "Hooked caption overlay update: ${method.shortName()}")
            installed++
        }

        if (installed == 0) {
            module.log(Log.WARN, name, "Caption overlay update methods not found by DexKit features")
        }
        return installed
    }

    private fun MethodData.toHookMethodOrNull(label: String): Method? =
        runCatching {
            toMethod(module.hostClassLoader)
        }.onFailure { e ->
            module.log(Log.WARN, name, "Unable to load $label method $this", e)
        }.getOrNull()

    private fun observeTimeline(result: Any?): Int {
        if (result == null) return 0

        val lists = result.instanceFieldValues()
            .filterIsInstance<List<*>>()
            .filter { it.isNotEmpty() }
        val texts = lists.firstOrNull { list -> list.any { it is CharSequence } } ?: return 0
        val timeLists = lists.filter { list -> list.all { it is Number } }
        if (timeLists.size < 2) return 0

        val (starts, ends) = orderedTimelineTimeLists(timeLists[0], timeLists[1])

        val size = minOf(starts.size, ends.size, texts.size)
        if (size == 0) return 0

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
        return session.observeCues(cues)
    }

    private fun observeCueList(arg: Any?): Int {
        val list = arg as? List<*> ?: return 0
        val cues = list.mapNotNull { item ->
            if (item == null) null else item.toCaptionCue()
        }
        return session.observeCues(cues)
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
            module.log(Log.DEBUG, name, "Observed $newCueCount new caption cue(s) from $source, total=$total")
        }
    }

    private fun Method.shortName(): String =
        "${declaringClass.name}#$name(${parameterTypes.joinToString { it.simpleName }})"

    private fun Method.stableId(): String =
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

    private const val NON_DECREASING_SUBTITLE_TIME_ERROR =
        "subtitles are not given in non-decreasing start time order"
    private const val EDITABLE_TYPE = "android.text.Editable"
    private const val SPARSE_ARRAY_TYPE = "android.util.SparseArray"
}
