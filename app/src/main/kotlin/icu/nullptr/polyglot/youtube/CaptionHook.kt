package icu.nullptr.polyglot.youtube

import android.util.Log
import icu.nullptr.polyglot.captions.BilingualFormatter
import icu.nullptr.polyglot.captions.CaptionSession
import icu.nullptr.polyglot.module
import org.luckypray.dexkit.DexKitCacheBridge
import org.luckypray.dexkit.annotations.DexKitExperimentalApi

object CaptionHook : BaseHook {
    override val name = "CaptionHook"

    private val session = CaptionSession()

    @OptIn(DexKitExperimentalApi::class)
    override fun install(dexkit: DexKitCacheBridge.RecyclableBridge): Boolean {
        module.log(Log.INFO, name, "Caption hook scaffold installed; DexKit finder wiring is next")
        return true
    }

    fun formatCaption(original: CharSequence?): CharSequence {
        val text = original?.toString().orEmpty()
        val translated = session.translationFor(text)
        return BilingualFormatter.format(text, translated)
    }
}
