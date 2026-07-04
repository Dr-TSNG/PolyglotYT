package icu.nullptr.polyglot.youtube.settings

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import android.window.OnBackInvokedCallback
import icu.nullptr.polyglot.R
import icu.nullptr.polyglot.module
import icu.nullptr.polyglot.translate.ConnectivityTestResult
import icu.nullptr.polyglot.translate.ConnectivityTester
import java.util.ArrayDeque
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.roundToInt

internal class NativeSettingsPage(
    private val fragment: Any,
    private val rootScreen: Any,
    private val context: Context,
    private val classes: HostPreferenceClasses,
    private val adapter: HostPreferenceAdapter,
    private val controller: SettingsPageController,
    val activity: Activity?,
) {
    private val toolbarTitle = HostToolbarTitle(activity, context.hostSettingsTitle())
    private val backStack = ArrayDeque<ScreenState>()
    private val mainHandler = Handler(Looper.getMainLooper())
    private val connectivityTestRunning = AtomicBoolean(false)

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
        val screen = currentScreen ?: return
        for (row in screen.rows) {
            val summary = row.node.summary() ?: continue
            adapter.setSummary(row.preference, summary)
        }
    }

    fun detachFromHostRoot() {
        clearActiveState()
        toolbarTitle.restore()
    }

    private fun clearActiveState() {
        backStack.clear()
        currentScreen = null
        unregisterSystemBackCallback()
        activity?.let { controller.deactivate(it) }
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
        clearActiveState()
        toolbarTitle.restore()
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
            if (!child.visible()) continue
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
                icon = node.icon,
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
                icon = node.icon,
                summary = node.summary(),
            ).also { preference ->
                controller.registerClickHandler(preference) {
                    openSelectionDialog(node)
                    true
                }
            }
            is TextSettingsNode -> adapter.createPreference(
                context = context,
                classes = classes,
                key = node.key,
                title = node.title,
                icon = node.icon,
                summary = node.summary(),
            ).also { preference ->
                controller.registerClickHandler(preference) {
                    openTextInputDialog(node)
                    true
                }
            }
            is ActionSettingsNode -> adapter.createPreference(
                context = context,
                classes = classes,
                key = node.key,
                title = node.title,
                icon = node.icon,
                summary = node.summary(),
            ).also { preference ->
                controller.registerClickHandler(preference) {
                    handleSettingsAction(node.action)
                    true
                }
            }
            is SettingsScreenNode -> null
        }

    private fun openSelectionDialog(node: SelectionSettingsNode) {
        val labels = node.options.map { it.label as CharSequence }.toTypedArray()
        val checkedIndex = node.options.indexOfFirst { it.value == node.selectedValue() }

        AlertDialog.Builder(dialogContext())
            .setTitle(node.title)
            .setSingleChoiceItems(labels, checkedIndex) { dialog, which ->
                val option = node.options.getOrNull(which) ?: return@setSingleChoiceItems
                if (option.value != node.selectedValue()) {
                    node.onSelected(option.value)
                    rebuildRootScreen()
                } else {
                    refreshSummaries()
                }
                dialog.dismiss()
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun openTextInputDialog(node: TextSettingsNode) {
        val dialogContext = dialogContext()
        val input = EditText(dialogContext).apply {
            inputType = node.inputType
            setSingleLine(true)
            setText(node.value())
            setSelection(text?.length ?: 0)
        }
        val container = FrameLayout(dialogContext).apply {
            val horizontalPadding = dialogContext.dp(24)
            val topPadding = dialogContext.dp(8)
            val bottomPadding = dialogContext.dp(4)
            setPadding(horizontalPadding, topPadding, horizontalPadding, bottomPadding)
            addView(
                input,
                FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                ),
            )
        }

        val dialog = AlertDialog.Builder(dialogContext)
            .setTitle(node.title)
            .setView(container)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                node.onSubmitted(input.text?.toString().orEmpty())
                rebuildRootScreen()
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()

        input.requestFocus()
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    private fun handleSettingsAction(action: SettingsAction) {
        when (action) {
            SettingsAction.TestConnectivity -> testConnectivity()
        }
    }

    private fun testConnectivity() {
        if (!connectivityTestRunning.compareAndSet(false, true)) {
            Toast.makeText(
                dialogContext(),
                module.res.getString(R.string.connectivity_test_running),
                Toast.LENGTH_SHORT,
            ).show()
            return
        }

        Toast.makeText(
            dialogContext(),
            module.res.getString(R.string.connectivity_test_running),
            Toast.LENGTH_SHORT,
        ).show()

        Thread(
            {
                val result = ConnectivityTester.testCurrentProvider()
                mainHandler.post {
                    connectivityTestRunning.set(false)
                    showConnectivityTestResult(result)
                }
            },
            CONNECTIVITY_TEST_THREAD_NAME,
        ).apply { isDaemon = true }.start()
    }

    private fun showConnectivityTestResult(result: ConnectivityTestResult) {
        val (title, message) = when (result) {
            is ConnectivityTestResult.Success -> {
                val response = result.response.compactForDialog().ifBlank { "OK" }
                module.res.getString(R.string.connectivity_test_success_title) to
                    module.res.getString(R.string.connectivity_test_success_message, response)
            }
            is ConnectivityTestResult.Failure ->
                module.res.getString(R.string.connectivity_test_failed_title) to result.message.compactForDialog()
        }

        AlertDialog.Builder(dialogContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }

    private fun String.compactForDialog(): String =
        replace(Regex("\\s+"), " ")
            .trim()
            .let { text ->
                if (text.length <= MAX_CONNECTIVITY_RESULT_LENGTH) text
                else text.take(MAX_CONNECTIVITY_RESULT_LENGTH) + "..."
            }

    private fun rebuildRootScreen() {
        val screen = renderScreen(PolyglotSettingsTree.root()) ?: return
        currentScreen = screen
        backStack.clear()

        if (adapter.showPreferenceScreen(fragment, screen.screen)) {
            toolbarTitle.show(screen.node.title)
            refreshSummaries()
        }
    }

    private fun dialogContext(): Context =
        activity ?: context

    private fun Context.dp(value: Int): Int =
        (value * resources.displayMetrics.density).roundToInt()

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
        const val CONNECTIVITY_TEST_THREAD_NAME = "PolyglotYT-ConnectivityTest"
        const val MAX_CONNECTIVITY_RESULT_LENGTH = 1000
    }
}
