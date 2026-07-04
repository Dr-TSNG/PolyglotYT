package icu.nullptr.polyglot.youtube.settings

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

internal class HostToolbarTitle(
    private val activity: Activity?,
    private val fallbackTitle: CharSequence?,
) {
    private var originalTitle: CharSequence? = null

    fun capture() {
        if (originalTitle != null || activity == null) return
        originalTitle = activity.findToolbarTitleText()
            ?.takeUnless { it.isPolyglotTitle() }
            ?: activity.title?.takeUnless { it.isPolyglotTitle() }
            ?: fallbackTitle
    }

    fun show(title: CharSequence) {
        activity?.setSettingsToolbarTitle(title)
    }

    fun restore() {
        val title = originalTitle
            ?.takeUnless { it.isPolyglotTitle() }
            ?: fallbackTitle
            ?: return
        show(title)
        originalTitle = null
    }
}

internal fun Context.hostSettingsTitle(): CharSequence? {
    for (name in HOST_SETTINGS_TITLE_RESOURCES) {
        val id = resourceId(name, "string")
        if (id != 0) {
            return runCatching { getText(id) }.getOrNull()
        }
    }
    return null
}

private fun Activity.setSettingsToolbarTitle(title: CharSequence) {
    this.title = title
    val decor = window?.decorView ?: return
    decor.post {
        this@setSettingsToolbarTitle.title = title
        findToolbarTitleView()?.text = title
    }
}

private fun Activity.findToolbarTitleText(): CharSequence? =
    findToolbarTitleView()?.text?.takeIf { it.isNotBlank() }

private fun CharSequence.isPolyglotTitle(): Boolean =
    toString() == ENTRY_TITLE

private fun Activity.findToolbarTitleView(): TextView? {
    val decor = window?.decorView ?: return null
    val toolbar = findToolbarContainer(decor)
    if (toolbar != null) {
        toolbar.descendants(TextView::class.java)
            .firstOrNull { it.isShown && it.text.isNotBlank() }
            ?.let { return it }
    }

    val screenLocation = IntArray(2)
    return decor.descendants(TextView::class.java)
        .filter { it.isShown && it.text.isNotBlank() }
        .filter { view ->
            view.getLocationOnScreen(screenLocation)
            screenLocation[1] < decor.height / 5
        }
        .minByOrNull { view ->
            view.getLocationOnScreen(screenLocation)
            screenLocation[1]
        }
}

private fun Activity.findToolbarContainer(decor: View): View? {
    for (name in TOOLBAR_RESOURCE_NAMES) {
        val id = resources.getIdentifier(name, "id", packageName)
        if (id != 0) {
            decor.findViewById<View>(id)?.let { return it }
        }
    }
    return null
}

private fun <T : View> View.descendants(type: Class<T>): Sequence<T> = sequence {
    if (type.isInstance(this@descendants)) {
        type.cast(this@descendants)?.let { yield(it) }
    }
    if (this@descendants is ViewGroup) {
        for (index in 0 until childCount) {
            yieldAll(getChildAt(index).descendants(type))
        }
    }
}

private val TOOLBAR_RESOURCE_NAMES = arrayOf(
    "toolbar",
    "settings_toolbar_layout",
)

private val HOST_SETTINGS_TITLE_RESOURCES = arrayOf(
    "settings",
    "settings_title",
)
