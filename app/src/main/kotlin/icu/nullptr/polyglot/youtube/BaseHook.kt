package icu.nullptr.polyglot.youtube

import org.luckypray.dexkit.DexKitBridge

interface BaseHook {
    val name: String
    val totalHooks: Int

    fun install(dexkit: DexKitBridge): Int
}
