package icu.nullptr.polyglot

import android.app.Application
import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import android.util.Log
import android.widget.Toast
import icu.nullptr.polyglot.core.ConfigManager
import icu.nullptr.polyglot.core.FileManager
import icu.nullptr.polyglot.util.DexKitRuntime
import icu.nullptr.polyglot.util.findAndHookAfter
import icu.nullptr.polyglot.util.findClass
import icu.nullptr.polyglot.util.findConstructorExact
import icu.nullptr.polyglot.util.findMethodExact
import icu.nullptr.polyglot.youtube.CaptionHook
import icu.nullptr.polyglot.youtube.SettingsHook
import io.github.libxposed.api.XposedModule
import io.github.libxposed.api.XposedModuleInterface
import java.util.concurrent.atomic.AtomicBoolean

lateinit var module: ModuleEntry

private const val TARGET_PACKAGE = "com.google.android.youtube"
private const val TAG = "ModuleEntry"

class ModuleEntry : XposedModule() {
    private lateinit var fileManager: FileManager

    lateinit var hostClassLoader: ClassLoader
    lateinit var config: ConfigManager
    lateinit var res: Resources

    private val hookInstalled = AtomicBoolean(false)

    override fun onModuleLoaded(param: XposedModuleInterface.ModuleLoadedParam) {
        if (param.processName != TARGET_PACKAGE) {
            if (apiVersion >= API_102) detach()
            return
        }

        module = this
        module.log(Log.INFO, TAG, "Loaded in framework $frameworkName API $apiVersion")
    }

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

            val amClass = findClass("android.content.res.AssetManager", javaClass.classLoader!!)
            val am = amClass.findConstructorExact().newInstance() as AssetManager
            amClass.findMethodExact("addAssetPath", String::class.java)
                .invoke(am, moduleApplicationInfo.sourceDir)
            res = Resources(am, context.resources.displayMetrics, context.resources.configuration)

            val packageInfo = application.packageManager.getPackageInfo(param.packageName, 0)
            val tag = "${param.packageName}:${packageInfo.longVersionCode}"
            DexKitRuntime.use(application.packageCodePath) {
                module.log(Log.INFO, TAG, "DexKit bridge ready for $tag")

                val hooks = listOf(
                    SettingsHook,
                    CaptionHook,
                )

                var successful = 0
                var total = 0
                for (hook in hooks) {
                    total += hook.totalHooks
                    runCatching {
                        successful += hook.install(it)
                    }.onFailure { e ->
                        module.log(Log.ERROR, TAG, "Error while installing hook ${hook.name}", e)
                    }
                }

                module.log(Log.INFO, TAG, "$successful/$total hooks installed successfully")

                if (successful < total) {
                    val text = res.getQuantityString(
                        R.plurals.hook_failed, total - successful, total - successful
                    )
                    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
                }
            }
        }

        module.log(Log.INFO, TAG, "Application.attach hook installed")
    }
}
