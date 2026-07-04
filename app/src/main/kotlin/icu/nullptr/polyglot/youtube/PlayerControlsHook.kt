package icu.nullptr.polyglot.youtube

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import icu.nullptr.polyglot.R
import icu.nullptr.polyglot.module
import icu.nullptr.polyglot.util.findAndHookAfter
import icu.nullptr.polyglot.util.logD
import icu.nullptr.polyglot.util.logW
import org.luckypray.dexkit.DexKitBridge
import java.util.WeakHashMap

object PlayerControlsHook : BaseHook {
    override val name = "PlayerControlsHook"
    override val totalHooks = 1

    private val translateButtons = WeakHashMap<View, View>()
    private val boundSubtitleButtons = WeakHashMap<View, Boolean>()

    override fun install(dexkit: DexKitBridge): Int {
        findAndHookAfter(
            clazz = ViewGroup::class.java,
            methodName = "addView",
            View::class.java,
            Integer.TYPE,
            ViewGroup.LayoutParams::class.java,
        ) { chain, _ ->
            val parent = chain.thisObject as? ViewGroup ?: return@findAndHookAfter
            val child = chain.getArg(0) as? View ?: return@findAndHookAfter
            if (child.isPlayerSubtitleButton()) {
                parent.post { parent.ensureTranslateButtonAfter(child) }
            }
        }

        logD(name, "Hooked player controls addView")
        return 1
    }

    private fun ViewGroup.ensureTranslateButtonAfter(subtitleButton: View) {
        runCatching {
            if (indexOfChild(subtitleButton) < 0) return

            val existingButtons = findTranslateButtons()
            val translateButton = translateButtons[subtitleButton]?.takeIf { indexOfChild(it) >= 0 }
                ?: existingButtons.firstOrNull()
                ?: createTranslateButton(subtitleButton)?.also { _ ->
                    logD(
                        name,
                        "Inserted player translate button: parent=${javaClass.name} " +
                                "subtitleParams=${subtitleButton.layoutParams?.javaClass?.name}",
                    )
                }
                ?: return

            translateButton.tag = TRANSLATE_BUTTON_TAG
            translateButtons[subtitleButton] = translateButton
            existingButtons.filter { it !== translateButton }.forEach(::removeView)
            moveTranslateButtonAfter(subtitleButton, translateButton)

            applyTranslateButtonLayout(subtitleButton, translateButton)
            syncTranslateButtonState(subtitleButton, translateButton)
            bindTranslateButton(subtitleButton, translateButton)
        }.onFailure { e ->
            logW(name, "Unable to insert player translate button", e)
        }
    }

    private fun ViewGroup.createTranslateButton(subtitleButton: View): ImageView? {
        val layoutId = context.resourceId("youtube_controls_overlay_subtitle_button", "layout")
        val button = if (layoutId != 0) {
            LayoutInflater.from(context).inflate(layoutId, this, false) as? ImageView
        } else {
            runCatching {
                subtitleButton.javaClass
                    .getConstructor(Context::class.java)
                    .newInstance(context) as? ImageView
            }.getOrNull()
        } ?: return null

        button.id = View.generateViewId()
        button.tag = TRANSLATE_BUTTON_TAG
        button.contentDescription = translateContentDescription()
        button.isClickable = true
        button.isFocusable = true
        button.scaleType = ImageView.ScaleType.CENTER_INSIDE

        copyButtonVisuals(subtitleButton, button)
        button.setImageDrawable(module.res.getDrawable(R.drawable.outline_translate_24, null))
        button.setOnClickListener {
            module.config.enabled = !module.config.enabled
            syncTranslateButtonState(subtitleButton, button)
            Toast.makeText(context, translateContentDescription(), Toast.LENGTH_SHORT).show()
            logD(name, "Player translate button changed: enabled=${module.config.enabled}")
        }
        return button
    }

    private fun ViewGroup.moveTranslateButtonAfter(subtitleButton: View, translateButton: View) {
        if (indexOfChild(translateButton) == indexOfChild(subtitleButton) + 1) return
        if (indexOfChild(translateButton) >= 0) {
            removeView(translateButton)
        }

        val insertIndex = (indexOfChild(subtitleButton) + 1).coerceIn(0, childCount)
        addView(translateButton, insertIndex)
    }

    private fun ViewGroup.applyTranslateButtonLayout(subtitleButton: View, translateButton: View) {
        val subtitleParams = subtitleButton.layoutParams ?: return
        translateButton.layoutParams = subtitleParams.copyForParent()

        if (this !is RelativeLayout) {
            return
        }

        val sourceParams = subtitleButton.layoutParams as? RelativeLayout.LayoutParams ?: return
        val targetId = sourceParams.firstRelativeTarget()
            ?: findViewIdByEntryName("player_overflow_button")
            ?: findViewIdByEntryName("player_overflow_button_container")
            ?: findViewIdByEntryName("new_player_overflow_button")
            ?: return

        val subtitleRelativeParams = RelativeLayout.LayoutParams(sourceParams).apply {
            replaceHorizontalTarget(translateButton.id)
        }
        val translateRelativeParams = RelativeLayout.LayoutParams(sourceParams).apply {
            replaceHorizontalTarget(targetId)
        }

        subtitleButton.layoutParams = subtitleRelativeParams
        translateButton.layoutParams = translateRelativeParams
    }

    private fun bindTranslateButton(subtitleButton: View, translateButton: View) {
        if (boundSubtitleButtons[subtitleButton] == true) return
        boundSubtitleButtons[subtitleButton] = true

        subtitleButton.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            (subtitleButton.parent as? ViewGroup)?.let { parent ->
                parent.applyTranslateButtonLayout(subtitleButton, translateButton)
                syncTranslateButtonState(subtitleButton, translateButton)
            }
        }

        subtitleButton.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(view: View) {
                (view.parent as? ViewGroup)?.post {
                    (view.parent as? ViewGroup)?.ensureTranslateButtonAfter(view)
                }
            }

            override fun onViewDetachedFromWindow(view: View) {
                val button = translateButtons.remove(view) ?: return
                boundSubtitleButtons.remove(view)
                val parent = button.parent as? ViewGroup ?: return
                if (!parent.hasPlayerSubtitleButton()) {
                    parent.removeView(button)
                }
            }
        })
    }

    private fun syncTranslateButtonState(subtitleButton: View, translateButton: View) {
        translateButton.visibility = subtitleButton.visibility
        translateButton.isEnabled = subtitleButton.isEnabled
        translateButton.alpha = subtitleButton.alpha
        translateButton.contentDescription = translateContentDescription()
        (translateButton as? ImageView)?.imageAlpha = if (module.config.enabled) 255 else 120
    }

    private fun copyButtonVisuals(source: View, target: ImageView) {
        target.minimumWidth = source.minimumWidth
        target.minimumHeight = source.minimumHeight
        target.setPaddingRelative(
            source.paddingStart,
            source.paddingTop,
            source.paddingEnd,
            source.paddingBottom,
        )
        target.background = source.background?.constantState?.newDrawable()?.mutate()

        val sourceImage = source as? ImageView
        target.imageTintList = sourceImage?.imageTintList ?: ColorStateList.valueOf(Color.WHITE)
    }

    private fun View.isPlayerSubtitleButton(): Boolean =
        id != View.NO_ID && runCatching {
            resources.getResourceEntryName(id) == "player_subtitle_button"
        }.getOrDefault(false)

    private fun View.isTranslateButton(): Boolean =
        tag == TRANSLATE_BUTTON_TAG || tag == LEGACY_TRANSLATE_BUTTON_TAG_BOUND

    private fun ViewGroup.findTranslateButtons(): List<View> =
        (0 until childCount).mapNotNull { index ->
            getChildAt(index).takeIf { it.isTranslateButton() }
        }

    private fun ViewGroup.hasPlayerSubtitleButton(): Boolean =
        (0 until childCount).any { index ->
            getChildAt(index).isPlayerSubtitleButton()
        }

    private fun ViewGroup.findViewIdByEntryName(entryName: String): Int? {
        val directId = context.resourceId(entryName, "id").takeIf { it != 0 }
        if (directId != null && findViewById<View>(directId) != null) return directId

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childEntryName = runCatching { child.resources.getResourceEntryName(child.id) }.getOrNull()
            if (childEntryName == entryName) return child.id
        }
        return null
    }

    private fun Context.resourceId(name: String, type: String): Int =
        resources.getIdentifier(name, type, packageName)

    private fun ViewGroup.LayoutParams.copyForParent(): ViewGroup.LayoutParams =
        runCatching {
            javaClass.getConstructor(javaClass).newInstance(this) as ViewGroup.LayoutParams
        }.getOrNull() ?: when (this) {
            is RelativeLayout.LayoutParams -> RelativeLayout.LayoutParams(this)
            is ViewGroup.MarginLayoutParams -> ViewGroup.MarginLayoutParams(this)
            else -> ViewGroup.LayoutParams(this)
        }

    private fun RelativeLayout.LayoutParams.firstRelativeTarget(): Int? =
        listOf(RelativeLayout.START_OF, RelativeLayout.LEFT_OF).firstNotNullOfOrNull { rule ->
            getRule(rule).takeIf { it > 0 }
        }

    private fun RelativeLayout.LayoutParams.replaceHorizontalTarget(targetId: Int) {
        removeRule(RelativeLayout.START_OF)
        removeRule(RelativeLayout.LEFT_OF)
        addRule(RelativeLayout.START_OF, targetId)
    }

    private fun translateContentDescription(): String {
        val state = module.res.getString(if (module.config.enabled) R.string.enabled else R.string.disabled)
        return "${module.res.getString(R.string.subtitle_quick_switch_title)} $state"
    }

    private const val TRANSLATE_BUTTON_TAG = "icu.nullptr.polyglot.player_translate_button"
    private const val LEGACY_TRANSLATE_BUTTON_TAG_BOUND = "icu.nullptr.polyglot.player_translate_button_bound"
}
