package icu.nullptr.polyglot.youtube.settings

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.util.TypedValue
import icu.nullptr.polyglot.module
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.lang.reflect.Proxy
import java.util.WeakHashMap

internal class HostPreferenceAdapter(private val methods: PreferenceMethods) {
    private val preferenceOrderFields = WeakHashMap<Class<*>, Field>()
    private val preferenceLayoutFields = WeakHashMap<Class<*>, Field>()
    private val preferenceIconSetters = WeakHashMap<Class<*>, Method>()
    private val switchCheckedSetters = WeakHashMap<Class<*>, Method>()

    fun classesFor(classLoader: ClassLoader): HostPreferenceClasses =
        HostPreferenceClasses(
            classLoader = classLoader,
            preference = Class.forName(PREFERENCE_CLASS_NAME, false, classLoader),
            preferenceGroup = Class.forName(PREFERENCE_GROUP_CLASS_NAME, false, classLoader),
            switchPreference = Class.forName(SWITCH_PREFERENCE_CLASS_NAME, false, classLoader),
        )

    fun createPreference(
        context: Context,
        classes: HostPreferenceClasses,
        key: String,
        title: CharSequence,
        summary: CharSequence?,
        icon: SettingsIcon? = null,
        useIconLayout: Boolean = false,
    ): Any =
        classes.preference.getConstructor(Context::class.java).newInstance(context).apply {
            setPreferenceKey(key)
            setPreferenceTitle(title)
            if (summary != null) setPreferenceSummary(summary)
            setPreferenceIcon(context, classes.preference, icon)
            if (useIconLayout) {
                setPreferenceLayout(context, classes.preference, PREFERENCE_WITH_ICON_LAYOUT)
            }
        }

    fun createSwitchPreference(
        context: Context,
        classes: HostPreferenceClasses,
        key: String,
        title: CharSequence,
        icon: SettingsIcon?,
        summary: CharSequence,
        checked: Boolean,
        onChanged: (Boolean) -> Unit,
    ): Any =
        classes.switchPreference.getConstructor(Context::class.java).newInstance(context).apply {
            setPreferenceKey(key)
            setPreferenceTitle(title)
            setPreferenceIcon(context, classes.preference, icon)
            setPreferenceSummary(summary)
            setSwitchChecked(context, classes.switchPreference, checked)
            setPreferenceChangeListener(classes.preference) { value ->
                val enabled = value as? Boolean ?: return@setPreferenceChangeListener false
                onChanged(enabled)
                true
            }
        }

    fun createPreferenceScreen(
        fragment: Any,
        context: Context,
        key: String,
        title: CharSequence,
    ): Any? =
        fragment.createPreferenceScreen(context)?.apply {
            setPreferenceKey(key)
            setPreferenceTitle(title)
        }

    fun preferenceScreenOrNull(fragment: Any): Any? =
        fragment.readPreferenceScreenOrNull()

    fun contextOrNull(preferenceScreen: Any): Context? =
        preferenceScreen.readContextOrNull()

    fun showPreferenceScreen(fragment: Any, screen: Any): Boolean =
        fragment.switchPreferenceScreen(screen)

    fun addPreference(group: Any, preference: Any, preferenceClass: Class<*>): Boolean =
        group.appendPreference(preference, preferenceClass)

    fun hasPreferenceWithKey(
        group: Any,
        key: String,
        classes: HostPreferenceClasses,
    ): Boolean =
        group.hasPreferenceWithString(key, classes.preference, classes.preferenceGroup)

    fun prepareOrderForTop(preference: Any, context: Context, preferenceClass: Class<*>) {
        val orderField = preferenceOrderFields[preferenceClass] ?: preferenceClass.findOrderField(context)
            ?.also { preferenceOrderFields[preferenceClass] = it }
            ?: return
        orderField.isAccessible = true
        orderField.setInt(preference, PREFERENCE_TOP_ORDER)
    }

    fun setSummary(preference: Any, summary: CharSequence) {
        preference.setPreferenceSummary(summary)
    }

    private fun Any.setPreferenceIcon(
        context: Context,
        preferenceClass: Class<*>,
        icon: SettingsIcon?,
    ) {
        if (icon == null) return

        val drawable = icon.loadDrawable(context) ?: run {
            module.log(Log.WARN, TAG, "Unable to resolve settings icon for ${icon.name}")
            return
        }
        val setter = preferenceIconSetters[preferenceClass] ?: preferenceClass.findIconSetter()
            ?.also { preferenceIconSetters[preferenceClass] = it }
        if (setter == null) {
            module.log(Log.WARN, TAG, "Unable to find preference icon setter")
            return
        }

        runCatching {
            setter.invoke(this, drawable)
        }.onFailure { e ->
            module.log(Log.WARN, TAG, "Unable to call preference icon setter", e)
        }
    }

    private fun SettingsIcon.loadDrawable(context: Context): Drawable? =
        runCatching {
            module.res.getDrawable(drawableRes, context.theme)
                ?.mutate()
                ?.apply { context.preferenceIconTint()?.let(::setTint) }
        }.getOrNull()

    private fun Context.preferenceIconTint(): Int? =
        resolveThemeColor(android.R.attr.colorControlNormal)
            ?: resolveThemeColor(android.R.attr.textColorSecondary)
            ?: resolveThemeColor(android.R.attr.textColorPrimary)

    private fun Context.resolveThemeColor(attribute: Int): Int? {
        val value = TypedValue()
        if (!theme.resolveAttribute(attribute, value, true)) return null

        if (value.type in TypedValue.TYPE_FIRST_COLOR_INT..TypedValue.TYPE_LAST_COLOR_INT) {
            return value.data
        }

        return runCatching {
            if (value.resourceId != 0) resources.getColorStateList(value.resourceId, theme).defaultColor else null
        }.getOrNull()
    }

    private fun Class<*>.findIconSetter(): Method? =
        methodsInHierarchy()
            .firstOrNull { method ->
                !Modifier.isStatic(method.modifiers) &&
                    method.returnType == Void.TYPE &&
                    method.parameterTypes.contentEquals(arrayOf(Drawable::class.java))
            }?.apply { isAccessible = true }

    private fun Any.setPreferenceLayout(
        context: Context,
        preferenceClass: Class<*>,
        layoutName: String,
    ) {
        val layoutId = context.resourceId(layoutName, "layout")
        if (layoutId == 0) {
            module.log(Log.WARN, TAG, "Unable to resolve preference layout $layoutName")
            return
        }

        val layoutField = preferenceLayoutFields[preferenceClass] ?: preferenceClass.findLayoutResourceField(context)
            ?.also { preferenceLayoutFields[preferenceClass] = it }
        if (layoutField == null) {
            module.log(Log.WARN, TAG, "Unable to find preference layout field")
            return
        }

        runCatching {
            layoutField.isAccessible = true
            layoutField.setInt(this, layoutId)
        }.onFailure { e ->
            module.log(Log.WARN, TAG, "Unable to set preference layout", e)
        }
    }

    private fun Any.readPreferenceScreenOrNull(): Any? {
        val method = javaClass.methodsInHierarchy()
            .firstOrNull {
                it.parameterCount == 0 &&
                    it.returnType.name == PREFERENCE_SCREEN_CLASS_NAME
            } ?: return null
        return runCatching {
            method.isAccessible = true
            method.invoke(this)
        }.getOrNull()
    }

    private fun Any.createPreferenceScreen(context: Context): Any? {
        val manager = preferenceManagerOrNull() ?: return null
        val method = manager.javaClass.methodsInHierarchy()
            .firstOrNull {
                it.parameterTypes.size == 1 &&
                    it.parameterTypes[0].isAssignableFrom(Context::class.java) &&
                    it.returnType.name == PREFERENCE_SCREEN_CLASS_NAME
            } ?: return null

        return runCatching {
            method.isAccessible = true
            method.invoke(manager, context)
        }.onFailure { e ->
            module.log(Log.WARN, TAG, "Unable to call host PreferenceScreen factory", e)
        }.getOrNull()
    }

    private fun Any.preferenceManagerOrNull(): Any? =
        javaClass.fieldsInHierarchy()
            .filter { !Modifier.isStatic(it.modifiers) }
            .firstNotNullOfOrNull { field ->
                runCatching {
                    field.isAccessible = true
                    val value = field.get(this) ?: return@runCatching null
                    value.takeIf { candidate ->
                        candidate.javaClass.methodsInHierarchy().any { method ->
                            method.parameterTypes.size == 1 &&
                                method.parameterTypes[0].isAssignableFrom(Context::class.java) &&
                                method.returnType.name == PREFERENCE_SCREEN_CLASS_NAME
                        }
                    }
                }.getOrNull()
            }

    private fun Any.switchPreferenceScreen(screen: Any): Boolean {
        val method = javaClass.methodsInHierarchy()
            .firstOrNull {
                it.returnType == java.lang.Void.TYPE &&
                    it.parameterTypes.size == 1 &&
                    it.parameterTypes[0].name == PREFERENCE_SCREEN_CLASS_NAME
            } ?: return false

        return runCatching {
            method.isAccessible = true
            method.invoke(this, screen)
            true
        }.onFailure { e ->
            module.log(Log.WARN, TAG, "Unable to switch host PreferenceScreen", e)
        }.getOrDefault(false)
    }

    private fun Any.readContextOrNull(): Context? =
        javaClass.fieldsInHierarchy()
            .filter { !Modifier.isStatic(it.modifiers) && Context::class.java.isAssignableFrom(it.type) }
            .firstNotNullOfOrNull { field ->
                runCatching {
                    field.isAccessible = true
                    field.get(this) as? Context
                }.getOrNull()
            }

    private fun Any.setPreferenceKey(key: String) {
        methods.keySetter?.let { method ->
            runCatching {
                method.invoke(this, key)
                return
            }.onFailure { e ->
                module.log(Log.WARN, TAG, "Unable to call preference key setter", e)
            }
        }

        setFirstStringField(value = key)
    }

    private fun Any.setPreferenceTitle(title: CharSequence) {
        methods.titleSetter?.let { method ->
            runCatching {
                method.invoke(this, title)
                return
            }.onFailure { e ->
                module.log(Log.WARN, TAG, "Unable to call preference title setter", e)
            }
        }

        setFirstEmptyCharSequenceField(title)
    }

    private fun Any.setPreferenceSummary(summary: CharSequence) {
        methods.summarySetter?.let { method ->
            runCatching {
                method.invoke(this, summary)
                return
            }.onFailure { e ->
                module.log(Log.WARN, TAG, "Unable to call preference summary setter", e)
            }
        }

        setFirstEmptyCharSequenceField(summary)
    }

    private fun Any.setPreferenceChangeListener(
        preferenceClass: Class<*>,
        onChange: (Any?) -> Boolean,
    ) {
        val listenerField = javaClass.fieldsInHierarchy()
            .filter { !Modifier.isStatic(it.modifiers) && it.type.isInterface }
            .firstOrNull { field ->
                field.type.methods.any { method ->
                    method.returnType == java.lang.Boolean.TYPE &&
                        method.parameterTypes.size == 2 &&
                        method.parameterTypes[0].isAssignableFrom(preferenceClass) &&
                        method.parameterTypes[1] == Any::class.java
                }
            } ?: return

        val listener = Proxy.newProxyInstance(
            listenerField.type.classLoader,
            arrayOf(listenerField.type),
        ) { proxy, method, args ->
            when {
                method.name == "toString" && method.parameterCount == 0 ->
                    "PolyglotYTPreferenceChangeListener"
                method.name == "hashCode" && method.parameterCount == 0 ->
                    System.identityHashCode(proxy)
                method.name == "equals" && method.parameterCount == 1 ->
                    proxy === args?.firstOrNull()
                method.returnType == java.lang.Boolean.TYPE && method.parameterTypes.size == 2 ->
                    onChange(args?.getOrNull(1))
                else -> defaultReturnValue(method.returnType)
            }
        }

        listenerField.isAccessible = true
        listenerField.set(this, listener)
    }

    private fun Any.setFirstStringField(value: String) {
        val field = javaClass.fieldsInHierarchy()
            .firstOrNull {
                !Modifier.isStatic(it.modifiers) &&
                    it.type == String::class.java &&
                    runCatching {
                        it.isAccessible = true
                        it.get(this) == null
                    }.getOrDefault(false)
            } ?: return

        field.set(this, value)
    }

    private fun Any.setFirstEmptyCharSequenceField(value: CharSequence) {
        val field = javaClass.fieldsInHierarchy()
            .firstOrNull {
                !Modifier.isStatic(it.modifiers) &&
                    CharSequence::class.java.isAssignableFrom(it.type) &&
                    runCatching {
                        it.isAccessible = true
                        it.get(this) == null
                    }.getOrDefault(false)
            } ?: return

        field.set(this, value)
    }

    private fun Any.hasPreferenceWithString(
        key: String,
        preferenceClass: Class<*>,
        preferenceGroupClass: Class<*>,
    ): Boolean =
        findPreferenceByString(
            key = key,
            preferenceClass = preferenceClass,
            preferenceGroupClass = preferenceGroupClass,
        ) != null

    private fun Any.findPreferenceByString(
        key: String,
        preferenceClass: Class<*>,
        preferenceGroupClass: Class<*>,
    ): Any? {
        if (preferenceClass.isInstance(this) && hasStringFieldValue(key)) return this
        if (!preferenceGroupClass.isInstance(this)) return null

        for (child in preferenceChildren(preferenceClass).orEmpty()) {
            child.findPreferenceByString(key, preferenceClass, preferenceGroupClass)?.let { return it }
        }
        return null
    }

    private fun Class<*>.findOrderField(context: Context): Field? {
        val constructor = getConstructor(Context::class.java)
        val candidates = fieldsInHierarchy()
            .filter { !Modifier.isStatic(it.modifiers) && it.type == Integer.TYPE }
            .toList()

        for (field in candidates) {
            val lower = constructor.newInstance(context)
            val higher = constructor.newInstance(context)
            val matches = runCatching {
                field.isAccessible = true
                field.setInt(lower, -1000)
                field.setInt(higher, 1000)
                @Suppress("UNCHECKED_CAST")
                val comparable = lower as Comparable<Any>
                comparable.compareTo(higher) < 0
            }.getOrDefault(false)
            if (matches) return field
        }
        return null
    }

    private fun Class<*>.findLayoutResourceField(context: Context): Field? {
        val defaultLayoutId = context.resourceId(PREFERENCE_DEFAULT_LAYOUT, "layout")
        val constructor = getConstructor(Context::class.java)
        val preference = constructor.newInstance(context)
        val orderField = findOrderField(context)
        val candidates = fieldsInHierarchy()
            .filter { field -> !Modifier.isStatic(field.modifiers) && field.type == Integer.TYPE }
            .toList()

        candidates.firstOrNull { field ->
            defaultLayoutId != 0 && runCatching {
                field.isAccessible = true
                field.getInt(preference) == defaultLayoutId
            }.getOrDefault(false)
        }?.let { return it }

        return candidates
            .filter { field -> orderField?.name != field.name }
            .firstOrNull { field ->
                runCatching {
                    field.isAccessible = true
                    val value = field.getInt(preference)
                    value != 0 && value != Int.MAX_VALUE
                }.getOrDefault(false)
            }
    }

    private fun Any.setSwitchChecked(
        context: Context,
        switchPreferenceClass: Class<*>,
        checked: Boolean,
    ) {
        val setter = switchCheckedSetters[switchPreferenceClass] ?: switchPreferenceClass.findSwitchCheckedSetter(context)
            ?.also { switchCheckedSetters[switchPreferenceClass] = it }
        if (setter != null) {
            runCatching {
                setter.invoke(this, checked)
                return
            }.onFailure { e ->
                module.log(Log.WARN, TAG, "Unable to call switch checked setter", e)
            }
        }

        val checkedField = javaClass.fieldsInHierarchy()
            .filter { !Modifier.isStatic(it.modifiers) && it.type == java.lang.Boolean.TYPE }
            .firstOrNull() ?: return
        checkedField.isAccessible = true
        checkedField.setBoolean(this, checked)
    }

    private fun Class<*>.findSwitchCheckedSetter(context: Context): Method? {
        val constructor = getConstructor(Context::class.java)
        val candidates = methodsInHierarchy()
            .filter {
                !Modifier.isStatic(it.modifiers) &&
                    it.returnType == java.lang.Void.TYPE &&
                    it.parameterTypes.contentEquals(arrayOf(java.lang.Boolean.TYPE))
            }
            .toList()

        for (method in candidates) {
            val preference = constructor.newInstance(context)
            val before = preference.booleanFieldValues()
            val afterTrue = runCatching {
                method.isAccessible = true
                method.invoke(preference, true)
                preference.booleanFieldValues()
            }.getOrNull() ?: continue
            val changedToTrue = before.filter { (field, value) ->
                value == false && afterTrue[field] == true
            }.keys
            val changedToFalse = before.any { (field, value) ->
                value == true && afterTrue[field] == false
            }
            if (changedToTrue.isEmpty() || changedToFalse) continue

            val afterFalse = runCatching {
                method.invoke(preference, false)
                preference.booleanFieldValues()
            }.getOrNull() ?: continue
            if (changedToTrue.any { afterFalse[it] == false }) {
                return method
            }
        }
        return null
    }

    private fun Any.booleanFieldValues(): Map<Field, Boolean> =
        javaClass.fieldsInHierarchy()
            .filter { !Modifier.isStatic(it.modifiers) && it.type == java.lang.Boolean.TYPE }
            .associateWith { field ->
                field.isAccessible = true
                field.getBoolean(this)
            }

    private fun Any.appendPreference(entry: Any, preferenceClass: Class<*>): Boolean {
        val beforeChildren = preferenceChildren(preferenceClass).orEmpty()
        if (entry in beforeChildren) return true

        methods.addPreference?.let { method ->
            runCatching {
                method.invoke(this, entry)
            }.onFailure { e ->
                module.log(Log.WARN, TAG, "Unable to call preference add method", e)
            }
            if (entry in preferenceChildren(preferenceClass).orEmpty()) {
                return true
            }
        }

        val candidates = javaClass.methodsInHierarchy()
            .filter {
                it.returnType == java.lang.Void.TYPE &&
                    it.parameterTypes.size == 1 &&
                    it.parameterTypes[0].isAssignableFrom(preferenceClass)
            }
            .toList()

        for (method in candidates) {
            runCatching {
                method.isAccessible = true
                method.invoke(this, entry)
            }
            if (entry in preferenceChildren(preferenceClass).orEmpty()) {
                return true
            }
        }
        return false
    }

    @Suppress("UNCHECKED_CAST")
    private fun Any.preferenceChildren(preferenceClass: Class<*>): MutableList<Any>? =
        javaClass.fieldsInHierarchy()
            .filter { !Modifier.isStatic(it.modifiers) && MutableList::class.java.isAssignableFrom(it.type) }
            .firstNotNullOfOrNull { field ->
                runCatching {
                    field.isAccessible = true
                    val value = field.get(this) as? MutableList<Any> ?: return@runCatching null
                    value.takeIf { list -> list.all { preferenceClass.isInstance(it) } }
                }.getOrNull()
            }

    private fun Any.hasStringFieldValue(value: String): Boolean =
        javaClass.fieldsInHierarchy()
            .filter { !Modifier.isStatic(it.modifiers) && it.type == String::class.java }
            .any { field ->
                runCatching {
                    field.isAccessible = true
                    field.get(this) == value
                }.getOrDefault(false)
            }

    private companion object {
        const val TAG = "HostPreferenceAdapter"
        const val PREFERENCE_DEFAULT_LAYOUT = "preference"
        const val PREFERENCE_WITH_ICON_LAYOUT = "preference_with_icon"
    }
}

internal data class HostPreferenceClasses(
    val classLoader: ClassLoader,
    val preference: Class<*>,
    val preferenceGroup: Class<*>,
    val switchPreference: Class<*>,
)
