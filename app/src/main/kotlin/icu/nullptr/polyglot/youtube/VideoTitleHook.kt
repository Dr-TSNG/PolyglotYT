package icu.nullptr.polyglot.youtube

import android.media.MediaMetadata
import android.media.session.MediaSession
import icu.nullptr.polyglot.BuildConfig
import icu.nullptr.polyglot.translate.VideoContext
import icu.nullptr.polyglot.util.findMethodExact
import icu.nullptr.polyglot.util.hookAfter
import icu.nullptr.polyglot.util.logD
import icu.nullptr.polyglot.util.logI
import icu.nullptr.polyglot.util.logW
import org.luckypray.dexkit.DexKitBridge

object VideoTitleHook : BaseHook {
    override val name = "VideoTitleHook"
    override val totalHooks = 1

    override fun install(dexkit: DexKitBridge): Int {
        val setMetadata = runCatching {
            MediaSession::class.java.findMethodExact(
                "setMetadata",
                MediaMetadata::class.java,
            )
        }.onFailure { error ->
            logW(name, "MediaSession.setMetadata not found", error)
        }.getOrNull() ?: return 0

        hookAfter(setMetadata) { chain, _ ->
            val metadata = chain.getArg(0) as? MediaMetadata ?: return@hookAfter
            val title = runCatching {
                metadata.getString(MediaMetadata.METADATA_KEY_TITLE)
            }.getOrNull() ?: metadata.description?.title

            if (title.isNullOrBlank()) return@hookAfter

            VideoContext.updateTitle(title)

            if (BuildConfig.DEBUG) {
                val mediaId = runCatching {
                    metadata.getString(MediaMetadata.METADATA_KEY_MEDIA_ID)
                }.getOrNull() ?: metadata.description?.mediaId
                logD(
                    name,
                    "Metadata updated: mediaId=$mediaId, titleLength=${title.length}, " +
                            "title=\"${title.take(MAX_DEBUG_TITLE_LENGTH)}\"",
                )
            }
        }

        logI(name, "Hooked MediaSession.setMetadata")
        return 1
    }

    private const val MAX_DEBUG_TITLE_LENGTH = 120
}
