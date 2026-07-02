package icu.nullptr.polyglot.util

import org.luckypray.dexkit.DexKitBridge
import org.luckypray.dexkit.result.MethodData
import java.lang.reflect.Method

object DexKitRuntime {
    private var libraryLoaded = false

    @Synchronized
    fun loadLibrary() {
        if (libraryLoaded) return

        System.loadLibrary("dexkit")
        libraryLoaded = true
    }

    fun <T> use(apkPath: String, block: (DexKitBridge) -> T): T {
        loadLibrary()
        return DexKitBridge.create(apkPath).use(block)
    }
}

fun MethodData.toMethod(classLoader: ClassLoader): Method =
    getMethodInstance(classLoader).apply { isAccessible = true }
