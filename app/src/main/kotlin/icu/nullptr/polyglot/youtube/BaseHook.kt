package icu.nullptr.polyglot.youtube

import org.luckypray.dexkit.DexKitCacheBridge
import org.luckypray.dexkit.annotations.DexKitExperimentalApi

interface BaseHook {
    val name: String

    @OptIn(DexKitExperimentalApi::class)
    fun install(dexkit: DexKitCacheBridge.RecyclableBridge): Boolean
}
