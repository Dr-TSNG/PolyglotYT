package icu.nullptr.polyglot.youtube

import org.luckypray.dexkit.DexKitBridge

interface BaseHook {
    val name: String

    fun install(dexkit: DexKitBridge): Boolean
}
