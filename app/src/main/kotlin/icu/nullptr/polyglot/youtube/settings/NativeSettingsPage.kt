package icu.nullptr.polyglot.youtube.settings

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.Log
import android.window.OnBackInvokedCallback
import icu.nullptr.polyglot.module
import java.util.ArrayDeque

internal class NativeSettingsPage(
    private val fragment: Any,
    private val rootScreen: Any,
    private val entryPreference: Any,
    private val context: Context,
    private val classes: HostPreferenceClasses,
    private val adapter: HostPreferenceAdapter,
    private val controller: SettingsPageController,
    val activity: Activity?,
) {
    private val toolbarTitle = HostToolbarTitle(activity)
    private val backStack = ArrayDeque<ScreenState>()

    private var currentScreen: ScreenState? = null
    private var systemBackCallback: OnBackInvokedCallback? = null

    fun open() {
        toolbarTitle.capture()
        backStack.clear()

        val screen = renderScreen(PolyglotSettingsTree.root()) ?: return
        currentScreen = null
        navigateTo(screen, pushCurrent = false)
    }

    fun navigateBack(): Boolean {
        val previous = if (backStack.isEmpty()) null else backStack.removeLast()
        if (previous == null) {
            return returnToRoot()
        }

        currentScreen = previous
        val shown = adapter.showPreferenceScreen(fragment, previous.screen)
        if (shown) {
            toolbarTitle.show(previous.node.title)
            refreshSummaries()
        }
        return shown
    }

    fun refreshSummaries() {
        adapter.setSummary(entryPreference, PolyglotSettingsTree.entrySummary())
        val screen = currentScreen ?: return
        for (row in screen.rows) {
            val summary = row.node.summary() ?: continue
            adapter.setSummary(row.preference, summary)
        }
    }

    private fun navigateTo(screen: ScreenState, pushCurrent: Boolean): Boolean {
        if (pushCurrent) {
            currentScreen?.let { backStack.addLast(it) }
        }

        currentScreen = screen
        activity?.let { controller.activate(it, this) }
        registerSystemBackCallback()

        val shown = adapter.showPreferenceScreen(fragment, screen.screen)
        if (shown) {
            toolbarTitle.show(screen.node.title)
            refreshSummaries()
        }
        return shown
    }

    private fun returnToRoot(): Boolean {
        val shown = adapter.showPreferenceScreen(fragment, rootScreen)
        if (shown) {
            backStack.clear()
            currentScreen = null
            unregisterSystemBackCallback()
            activity?.let { controller.deactivate(it) }
            toolbarTitle.restore()
        }
        refreshSummaries()
        return shown
    }

    private fun renderScreen(node: SettingsScreenNode): ScreenState? {
        val screen = adapter.createPreferenceScreen(
            fragment = fragment,
            context = context,
            key = node.key,
            title = node.title,
        ) ?: return null

        val rows = mutableListOf<RenderedRow>()
        for (child in node.children) {
            val preference = renderPreference(child) ?: continue
            if (adapter.addPreference(screen, preference, classes.preference)) {
                rows += RenderedRow(node = child, preference = preference)
            }
        }

        return ScreenState(node = node, screen = screen, rows = rows)
    }

    private fun renderPreference(node: SettingsNode): Any? =
        when (node) {
            is SwitchSettingsNode -> adapter.createSwitchPreference(
                context = context,
                classes = classes,
                key = node.key,
                title = node.title,
                summary = node.summary(),
                checked = node.checked(),
                onChanged = { checked ->
                    node.onChanged(checked)
                    refreshSummaries()
                },
            )
            is SelectionSettingsNode -> adapter.createPreference(
                context = context,
                classes = classes,
                key = node.key,
                title = node.title,
                summary = node.summary(),
            ).also { preference ->
                controller.registerClickHandler(preference) {
                    openSelectionScreen(node)
                    true
                }
            }
            is OptionSettingsNode -> adapter.createPreference(
                context = context,
                classes = classes,
                key = node.key,
                title = node.title,
                summary = node.summary(),
            ).also { preference ->
                controller.registerClickHandler(preference) {
                    node.onSelected()
                    navigateBack()
                    refreshSummaries()
                    true
                }
            }
            is SettingsScreenNode -> null
        }

    private fun openSelectionScreen(node: SelectionSettingsNode) {
        val selectionScreen = SettingsScreenNode(
            key = "${node.key}.screen",
            title = node.title,
            children = node.options.map { option ->
                OptionSettingsNode(
                    key = "${node.key}.${option.value}",
                    title = option.label,
                    value = option.value,
                    selected = { node.selectedValue() == option.value },
                    onSelected = { node.onSelected(option.value) },
                )
            },
        )

        val screen = renderScreen(selectionScreen) ?: return
        navigateTo(screen, pushCurrent = true)
    }

    private fun registerSystemBackCallback() {
        val hostActivity = activity
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU || hostActivity == null || systemBackCallback != null) {
            return
        }

        val callback = OnBackInvokedCallback {
            if (navigateBack()) {
                module.log(Log.INFO, TAG, "Handled PolyglotYT native settings system back")
            }
        }

        runCatching {
            hostActivity.onBackInvokedDispatcher.registerOnBackInvokedCallback(BACK_CALLBACK_PRIORITY, callback)
            systemBackCallback = callback
            module.log(Log.INFO, TAG, "Registered PolyglotYT native settings system back callback")
        }.onFailure { e ->
            module.log(Log.WARN, TAG, "Unable to register PolyglotYT native settings system back callback", e)
        }
    }

    private fun unregisterSystemBackCallback() {
        val callback = systemBackCallback ?: return
        val hostActivity = activity
        systemBackCallback = null

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU || hostActivity == null) {
            return
        }

        runCatching {
            hostActivity.onBackInvokedDispatcher.unregisterOnBackInvokedCallback(callback)
            module.log(Log.INFO, TAG, "Unregistered PolyglotYT native settings system back callback")
        }.onFailure { e ->
            module.log(Log.WARN, TAG, "Unable to unregister PolyglotYT native settings system back callback", e)
        }
    }

    private data class ScreenState(
        val node: SettingsScreenNode,
        val screen: Any,
        val rows: List<RenderedRow>,
    )

    private data class RenderedRow(
        val node: SettingsNode,
        val preference: Any,
    )

    private companion object {
        const val TAG = "NativeSettingsPage"
    }
}
