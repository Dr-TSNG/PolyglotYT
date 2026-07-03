package icu.nullptr.polyglot.youtube.settings

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import java.lang.reflect.Field
import java.lang.reflect.Method

internal fun Context.activityOrNull(): Activity? {
    var context: Context? = this
    val visited = HashSet<Context>()
    while (context != null && visited.add(context)) {
        if (context is Activity) return context
        context = (context as? ContextWrapper)?.baseContext
    }
    return null
}

internal fun Context.resourceEntryName(resourceId: Int): String? =
    runCatching { resources.getResourceEntryName(resourceId) }.getOrNull()

internal fun Class<*>.fieldsInHierarchy(): Sequence<Field> = sequence {
    var clazz: Class<*>? = this@fieldsInHierarchy
    while (clazz != null) {
        yieldAll(clazz.declaredFields.asSequence())
        clazz = clazz.superclass
    }
}

internal fun Class<*>.methodsInHierarchy(): Sequence<Method> = sequence {
    var clazz: Class<*>? = this@methodsInHierarchy
    while (clazz != null) {
        yieldAll(clazz.declaredMethods.asSequence())
        clazz = clazz.superclass
    }
}

internal fun Method.shortName(): String =
    "${declaringClass.name}#$name(${parameterTypes.joinToString { it.simpleName }})"

internal fun defaultReturnValue(type: Class<*>): Any? =
    when (type) {
        java.lang.Boolean.TYPE -> false
        java.lang.Byte.TYPE -> 0.toByte()
        java.lang.Character.TYPE -> 0.toChar()
        java.lang.Short.TYPE -> 0.toShort()
        java.lang.Integer.TYPE -> 0
        java.lang.Long.TYPE -> 0L
        java.lang.Float.TYPE -> 0f
        java.lang.Double.TYPE -> 0.0
        else -> null
    }
