package icu.nullptr.polyglot

import android.app.Application
import android.content.Context
import android.util.Log
import icu.nullptr.polyglot.core.ConfigManager
import icu.nullptr.polyglot.core.FileManager
import icu.nullptr.polyglot.dex.DexKitFileCache
import icu.nullptr.polyglot.util.findAndHookAfter
import icu.nullptr.polyglot.youtube.CaptionHook
import io.github.libxposed.api.XposedModule
import io.github.libxposed.api.XposedModuleInterface
import org.luckypray.dexkit.DexKitCacheBridge
import org.luckypray.dexkit.annotations.DexKitExperimentalApi
import java.util.concurrent.atomic.AtomicBoolean

lateinit var module: ModuleEntry

private const val TARGET_PACKAGE = "com.google.android.youtube"
private const val TAG = "ModuleEntry"

class ModuleEntry : XposedModule() {
    private lateinit var fileManager: FileManager

    lateinit var hostClassLoader: ClassLoader
    lateinit var config: ConfigManager

    private val hookInstalled = AtomicBoolean(false)

    override fun onModuleLoaded(param: XposedModuleInterface.ModuleLoadedParam) {
        if (param.processName != TARGET_PACKAGE) {
            if (apiVersion >= API_102) detach()
            return
        }

        module = this
        module.log(Log.INFO, TAG, "Loaded in framework $frameworkName API $apiVersion")
    }

    @OptIn(DexKitExperimentalApi::class)
    override fun onPackageReady(param: XposedModuleInterface.PackageReadyParam) {
        if (param.packageName != TARGET_PACKAGE || !param.isFirstPackage) {
            if (apiVersion >= API_102) detach()
            return
        }
        hostClassLoader = param.classLoader

        findAndHookAfter(
            clazz = Application::class.java,
            methodName = "attach",
            Context::class.java,
        ) { chain, _ ->
            if (!hookInstalled.compareAndSet(false, true)) {
                return@findAndHookAfter
            }
            val application = chain.thisObject as Application
            val context = chain.getArg(0) as Context

            fileManager = FileManager(context)
            config = ConfigManager(context, fileManager.configDir)

            System.loadLibrary("dexkit")
            DexKitCacheBridge.init(DexKitFileCache(fileManager.dexKitDir))

            val packageInfo = application.packageManager.getPackageInfo(param.packageName, 0)
            val tag = "${param.packageName}:${packageInfo.longVersionCode}"
            DexKitCacheBridge.create(tag, application.packageCodePath).use {
                module.log(Log.INFO, TAG, "DexKit bridge ready for $tag")

                val hooks = listOf(
                    CaptionHook,
                )

                var successful = 0
                for (hook in hooks) {
                    runCatching {
                        if (hook.install(it)) successful++
                    }.onFailure { e ->
                        module.log(Log.ERROR, TAG, "Error while installing hook ${hook.name}", e)
                    }
                }

                module.log(Log.INFO, TAG, "$successful/${hooks.size} hooks installed successfully")
            }
        }

        module.log(Log.INFO, TAG, "Application.attach hook installed")
    }
}
