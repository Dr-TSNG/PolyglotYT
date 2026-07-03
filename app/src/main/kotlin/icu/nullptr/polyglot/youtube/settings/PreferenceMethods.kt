package icu.nullptr.polyglot.youtube.settings

import icu.nullptr.polyglot.util.toMethod
import org.luckypray.dexkit.DexKitBridge
import java.lang.reflect.Method

data class PreferenceMethods(
    val resourceLoad: Method?,
    val click: Method?,

    val keySetter: Method?,
    val titleSetter: Method?,
    val summarySetter: Method?,
    val addPreference: Method?,
)

fun DexKitBridge.resolvePreferenceMethods(): PreferenceMethods {
    val resourceLoad = findMethod {
        matcher {
            returnType("void")
            usingEqStrings(PREFERENCE_RESOURCE_LOAD_ERROR)
        }
    }.singleOrNull()

    val click = findMethod {
        matcher {
            declaredClass(PREFERENCE_CLASS_NAME)
            returnType("void")
            paramTypes("android.view.View")
        }
    }.singleOrNull()

    val keySetter = findMethod {
        matcher {
            declaredClass(PREFERENCE_CLASS_NAME)
            returnType("void")
            paramTypes("java.lang.String")
            usingEqStrings(PREFERENCE_KEY_ERROR)
        }
    }.singleOrNull()

    val summarySetter = findMethod {
        matcher {
            declaredClass(PREFERENCE_CLASS_NAME)
            returnType("void")
            paramTypes("java.lang.CharSequence")
            usingEqStrings(PREFERENCE_SUMMARY_PROVIDER_ERROR)
        }
    }.singleOrNull()

    val titleSetter = findMethod {
        matcher {
            declaredClass(PREFERENCE_CLASS_NAME)
            returnType("void")
            paramTypes("java.lang.CharSequence")
        }
    }.singleOrNull { it != summarySetter }

    val addPreference = findMethod {
        matcher {
            declaredClass(PREFERENCE_GROUP_CLASS_NAME)
            returnType("void")
            paramTypes(PREFERENCE_CLASS_NAME)
            usingStrings(PREFERENCE_DUPLICATED_KEY_PREFIX)
        }
    }.singleOrNull()

    return PreferenceMethods(
        resourceLoad = resourceLoad?.toMethod(),
        click = click?.toMethod(),
        keySetter = keySetter?.toMethod(),
        titleSetter = titleSetter?.toMethod(),
        summarySetter = summarySetter?.toMethod(),
        addPreference = addPreference?.toMethod(),
    )
}
