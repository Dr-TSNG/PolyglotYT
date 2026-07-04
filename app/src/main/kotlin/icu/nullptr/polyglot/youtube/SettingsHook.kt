package icu.nullptr.polyglot.youtube

import icu.nullptr.polyglot.util.hook
import icu.nullptr.polyglot.util.logD
import icu.nullptr.polyglot.util.logI
import icu.nullptr.polyglot.util.logW
import icu.nullptr.polyglot.youtube.settings.HostPreferenceAdapter
import icu.nullptr.polyglot.youtube.settings.PreferenceMethods
import icu.nullptr.polyglot.youtube.settings.SettingsPageController
import icu.nullptr.polyglot.youtube.settings.resolvePreferenceMethods
import icu.nullptr.polyglot.youtube.settings.shortName
import org.luckypray.dexkit.DexKitBridge

object SettingsHook : BaseHook {
    override val name = "SettingsHook"
    override val totalHooks = 2

    private lateinit var pageController: SettingsPageController

    override fun install(dexkit: DexKitBridge): Int {
        val methods = dexkit.resolvePreferenceMethods()


        val preferenceAdapter = HostPreferenceAdapter(dexkit.resolvePreferenceMethods())
        pageController = SettingsPageController(preferenceAdapter)

        var installed = 0
        if (installPreferenceResourceHook(methods)) installed++
        if (installPreferenceClickHook(methods)) installed++

        logI(name, "Installed $installed settings hook(s)")
        return installed
    }

    private fun installPreferenceResourceHook(methods: PreferenceMethods): Boolean {
        val resourceLoadMethod = methods.resourceLoad ?: run {
            logW(name, "Preference resource loader not found")
            return false
        }

        hook(resourceLoadMethod) { chain ->
            val resourceId = chain.getArg(0) as? Int
            val result = chain.proceed()
            val fragment = chain.thisObject
            if (fragment != null && resourceId != null) {
                pageController.injectEntry(fragment, resourceId)
            }
            result
        }

        logD(name, "Hooked preference resource loader: ${resourceLoadMethod.shortName()}")
        return true
    }

    private fun installPreferenceClickHook(methods: PreferenceMethods): Boolean {
        val clickMethod = methods.click ?: run {
            logW(name, "Preference click dispatcher not found")
            return false
        }

        hook(clickMethod) { chain ->
            val preference = chain.thisObject
            if (preference != null && pageController.dispatchPreferenceClick(preference)) {
                return@hook null
            }
            chain.proceed()
        }

        logD(name, "Hooked preference click dispatcher: ${clickMethod.shortName()}")
        return true
    }
}
