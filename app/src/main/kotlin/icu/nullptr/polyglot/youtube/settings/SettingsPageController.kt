package icu.nullptr.polyglot.youtube.settings

import android.app.Activity
import icu.nullptr.polyglot.module
import icu.nullptr.polyglot.util.hook
import icu.nullptr.polyglot.util.logD
import icu.nullptr.polyglot.util.logI
import icu.nullptr.polyglot.util.logW
import java.util.WeakHashMap

internal class SettingsPageController(
    private val adapter: HostPreferenceAdapter,
) {
    private val nativePages = WeakHashMap<Any, NativeSettingsPage>()
    private val clickHandlers = WeakHashMap<Any, () -> Boolean>()
    private val activePagesByActivity = WeakHashMap<Activity, NativeSettingsPage>()
    private val hookedBackActivityClasses = mutableSetOf<Class<*>>()

    fun injectEntry(fragment: Any, resourceId: Int) {
        val rootScreen = adapter.preferenceScreenOrNull(fragment) ?: return
        val context = adapter.contextOrNull(rootScreen) ?: return
        val resourceEntryName = context.resourceEntryName(resourceId) ?: return
        if (resourceEntryName !in MAIN_SETTINGS_RESOURCES) return

        runCatching {
            val classLoader = rootScreen.javaClass.classLoader ?: module.hostClassLoader
            val classes = adapter.classesFor(classLoader)
            val activity = context.activityOrNull()
            activity?.let { detachActivePageForHostRoot(it, resourceEntryName) }

            fun createPage(): NativeSettingsPage =
                NativeSettingsPage(
                    fragment = fragment,
                    rootScreen = rootScreen,
                    context = context,
                    classes = classes,
                    adapter = adapter,
                    controller = this,
                    activity = activity,
                )

            val existingEntry = adapter.findPreferenceWithKey(rootScreen, ENTRY_KEY, classes)
            if (existingEntry != null) {
                nativePages[existingEntry] = createPage()
                activity?.let { installBackHookForActivity(it) }
                logI(TAG, "Rebound native settings entry in $resourceEntryName")
                return
            }

            val entry = adapter.createPreference(
                context = context,
                classes = classes,
                key = ENTRY_KEY,
                title = ENTRY_TITLE,
                summary = null,
                icon = SettingsIcon.Entry,
                useIconLayout = true,
            )
            adapter.prepareOrderForTop(entry, context, classes.preference)

            val page = createPage()
            activity?.let { installBackHookForActivity(it) }

            if (adapter.addPreference(rootScreen, entry, classes.preference)) {
                nativePages[entry] = page
                logD(TAG, "Injected native settings entry into $resourceEntryName")
            } else {
                logW(TAG, "Unable to add native settings entry into $resourceEntryName")
            }
        }.onFailure { e ->
            logW(TAG, "Unable to inject native settings entry into $resourceEntryName", e)
        }
    }

    private fun detachActivePageForHostRoot(activity: Activity, resourceEntryName: String) {
        val page = activePagesByActivity[activity] ?: return
        page.detachFromHostRoot()
        logD(TAG, "Detached native settings page after host root reload in $resourceEntryName")
    }

    fun dispatchPreferenceClick(preference: Any): Boolean {
        clickHandlers[preference]?.let { handler ->
            return handler()
        }

        nativePages[preference]?.let { page ->
            page.open()
            return true
        }

        return false
    }

    fun registerClickHandler(preference: Any, handler: () -> Boolean) {
        clickHandlers[preference] = handler
    }

    fun activate(activity: Activity, page: NativeSettingsPage) {
        activePagesByActivity[activity] = page
    }

    fun deactivate(activity: Activity) {
        activePagesByActivity.remove(activity)
    }

    private fun installBackHookForActivity(activity: Activity) {
        val activityClass = activity.javaClass
        synchronized(hookedBackActivityClasses) {
            if (!hookedBackActivityClasses.add(activityClass)) {
                return
            }
        }

        hookActivityBackMethod(activityClass)
        hookActivityFinishMethod(activityClass)
    }

    private fun hookActivityBackMethod(activityClass: Class<*>) {
        val method = activityClass.methodsInHierarchy()
            .firstOrNull {
                it.name == "onBackPressed" &&
                    it.parameterCount == 0 &&
                    it.returnType == Void.TYPE
            } ?: run {
            logW(TAG, "Unable to find settings activity back method")
            return
        }

        method.isAccessible = true
        hook(method) { chain ->
            val handled = (chain.thisObject as? Activity)
                ?.let { handleActivityBack(it, "back") }
                ?: false
            if (handled) return@hook null
            chain.proceed()
        }

        logD(TAG, "Hooked settings activity back method: ${method.shortName()}")
    }

    private fun hookActivityFinishMethod(activityClass: Class<*>) {
        val method = activityClass.methodsInHierarchy()
            .firstOrNull {
                it.name == "finish" &&
                    it.parameterCount == 0 &&
                    it.returnType == Void.TYPE
            } ?: run {
            logW(TAG, "Unable to find settings activity finish method")
            return
        }

        method.isAccessible = true
        hook(method) { chain ->
            val handled = (chain.thisObject as? Activity)
                ?.let { handleActivityBack(it, "toolbar back") }
                ?: false
            if (handled) return@hook null
            chain.proceed()
        }

        logD(TAG, "Hooked settings activity finish method: ${method.shortName()}")
    }

    private fun handleActivityBack(activity: Activity, source: String): Boolean {
        val page = activePagesByActivity[activity] ?: return false
        if (!page.navigateBack()) return false

        logI(TAG, "Handled PolyglotYT native settings $source")
        return true
    }

    private companion object {
        const val TAG = "SettingsPageController"
    }
}
