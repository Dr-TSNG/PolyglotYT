package icu.nullptr.polyglot.util

import icu.nullptr.polyglot.module
import io.github.libxposed.api.XposedInterface
import java.lang.reflect.Executable
import java.lang.reflect.Method

fun findClass(className: String, classLoader: ClassLoader): Class<*> =
    Class.forName(className, false, classLoader)

fun findClassOrNull(className: String, classLoader: ClassLoader): Class<*>? =
    runCatching { findClass(className, classLoader) }.getOrNull()

fun Class<*>.findMethodExact(methodName: String, vararg parameterTypes: Class<*>): Method =
    getDeclaredMethod(methodName, *parameterTypes).apply { isAccessible = true }

fun Class<*>.findMethodExactOrNull(
    methodName: String,
    vararg parameterTypes: Class<*>,
): Method? = runCatching { findMethodExact(methodName, *parameterTypes) }.getOrNull()

fun hook(
    executable: Executable,
    hooker: XposedInterface.Hooker,
): XposedInterface.HookHandle =
    module.hook(executable)
        .intercept(hooker)

fun hookBefore(
    executable: Executable,
    before: (XposedInterface.Chain) -> Unit,
): XposedInterface.HookHandle = hook(executable) { chain ->
    before(chain)
    chain.proceed()
}

fun hookAfter(
    executable: Executable,
    after: (XposedInterface.Chain, result: Any?) -> Unit,
): XposedInterface.HookHandle = hook(executable) { chain ->
    val result = chain.proceed()
    after(chain, result)
    result
}

fun hookReplace(
    executable: Executable,
    replacement: (XposedInterface.Chain) -> Any?,
): XposedInterface.HookHandle = hook(executable) { chain ->
    replacement(chain)
}

fun findAndHook(
    className: String,
    classLoader: ClassLoader,
    methodName: String,
    vararg parameterTypes: Class<*>,
    hooker: XposedInterface.Hooker,
): XposedInterface.HookHandle =
    findAndHook(
        clazz = findClass(className, classLoader),
        methodName = methodName,
        parameterTypes = parameterTypes,
        hooker = hooker,
    )

fun findAndHook(
    clazz: Class<*>,
    methodName: String,
    vararg parameterTypes: Class<*>,
    hooker: XposedInterface.Hooker,
): XposedInterface.HookHandle =
    hook(
        executable = clazz.findMethodExact(methodName, *parameterTypes),
        hooker = hooker,
    )

fun findAndHookBefore(
    clazz: Class<*>,
    methodName: String,
    vararg parameterTypes: Class<*>,
    before: (XposedInterface.Chain) -> Unit,
): XposedInterface.HookHandle =
    hookBefore(clazz.findMethodExact(methodName, *parameterTypes), before = before)

fun findAndHookAfter(
    clazz: Class<*>,
    methodName: String,
    vararg parameterTypes: Class<*>,
    after: (XposedInterface.Chain, result: Any?) -> Unit,
): XposedInterface.HookHandle =
    hookAfter(clazz.findMethodExact(methodName, *parameterTypes), after = after)

fun findAndHookReplace(
    clazz: Class<*>,
    methodName: String,
    vararg parameterTypes: Class<*>,
    replacement: (XposedInterface.Chain) -> Any?,
): XposedInterface.HookHandle =
    hookReplace(clazz.findMethodExact(methodName, *parameterTypes), replacement = replacement)
