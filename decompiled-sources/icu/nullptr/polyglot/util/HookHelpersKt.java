package icu.nullptr.polyglot.util;

import icu.nullptr.polyglot.ModuleEntryKt;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HookHelpers.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a\u001a\u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u001c\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a7\u0010\u0007\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\t\u001a\u00020\u00032\u001a\u0010\n\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00010\u000b\"\u0006\u0012\u0002\b\u00030\u0001¢\u0006\u0002\u0010\f\u001a9\u0010\r\u001a\u0004\u0018\u00010\b*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\t\u001a\u00020\u00032\u001a\u0010\n\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00010\u000b\"\u0006\u0012\u0002\b\u00030\u0001¢\u0006\u0002\u0010\f\u001a=\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00100\u000f\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u00012\u001a\u0010\n\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00010\u000b\"\u0006\u0012\u0002\b\u00030\u0001¢\u0006\u0002\u0010\u0011\u001a\u0016\u0010\u0012\u001a\u00020\u0013*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0014\u001a\u00020\u0003\u001a\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u0013*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0014\u001a\u00020\u0003\u001a\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u0013*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0014\u001a\u00020\u0003\u001a\u0014\u0010\u0017\u001a\u0004\u0018\u00010\u0018*\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u0003\u001a\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u001a*\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u0003¢\u0006\u0002\u0010\u001b\u001a\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u001d*\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u0003¢\u0006\u0002\u0010\u001e\u001a\u0014\u0010\u001f\u001a\u0004\u0018\u00010 *\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u0003\u001a\u0016\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&\u001a\"\u0010'\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020+0)\u001a9\u0010,\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2)\u0010-\u001a%\u0012\u0004\u0012\u00020*\u0012\u0015\u0012\u0013\u0018\u00010\u0018¢\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(1\u0012\u0004\u0012\u00020+0.\u001a$\u00102\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0014\u00103\u001a\u0010\u0012\u0004\u0012\u00020*\u0012\u0006\u0012\u0004\u0018\u00010\u00180)\u001aG\u00104\u001a\u00020\"2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00032\u001a\u0010\n\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00010\u000b\"\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010%\u001a\u00020&¢\u0006\u0002\u00105\u001aC\u00104\u001a\u00020\"2\n\u00106\u001a\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\t\u001a\u00020\u00032\u001a\u0010\n\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00010\u000b\"\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010%\u001a\u00020&¢\u0006\u0002\u00107\u001aO\u00108\u001a\u00020\"2\n\u00106\u001a\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\t\u001a\u00020\u00032\u001a\u0010\n\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00010\u000b\"\u0006\u0012\u0002\b\u00030\u00012\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020+0)¢\u0006\u0002\u00109\u001af\u0010:\u001a\u00020\"2\n\u00106\u001a\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\t\u001a\u00020\u00032\u001a\u0010\n\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00010\u000b\"\u0006\u0012\u0002\b\u00030\u00012)\u0010-\u001a%\u0012\u0004\u0012\u00020*\u0012\u0015\u0012\u0013\u0018\u00010\u0018¢\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(1\u0012\u0004\u0012\u00020+0.¢\u0006\u0002\u0010;\u001aQ\u0010<\u001a\u00020\"2\n\u00106\u001a\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\t\u001a\u00020\u00032\u001a\u0010\n\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00010\u000b\"\u0006\u0012\u0002\b\u00030\u00012\u0014\u00103\u001a\u0010\u0012\u0004\u0012\u00020*\u0012\u0006\u0012\u0004\u0018\u00010\u00180)¢\u0006\u0002\u00109¨\u0006="}, d2 = {"findClass", "Ljava/lang/Class;", "className", "", "classLoader", "Ljava/lang/ClassLoader;", "findClassOrNull", "findMethodExact", "Ljava/lang/reflect/Method;", "methodName", "parameterTypes", "", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", "findMethodExactOrNull", "findConstructorExact", "Ljava/lang/reflect/Constructor;", "T", "(Ljava/lang/Class;[Ljava/lang/Class;)Ljava/lang/reflect/Constructor;", "findFieldExact", "Ljava/lang/reflect/Field;", "fieldName", "findFieldExactOrNull", "findFieldInHierarchyOrNull", "getFieldValueOrNull", "", "getIntFieldOrNull", "", "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Integer;", "getLongFieldOrNull", "", "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Long;", "getCharSequenceFieldOrNull", "", "hook", "Lio/github/libxposed/api/XposedInterface$HookHandle;", "executable", "Ljava/lang/reflect/Executable;", "hooker", "Lio/github/libxposed/api/XposedInterface$Hooker;", "hookBefore", "before", "Lkotlin/Function1;", "Lio/github/libxposed/api/XposedInterface$Chain;", "", "hookAfter", "after", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "result", "hookReplace", "replacement", "findAndHook", "(Ljava/lang/String;Ljava/lang/ClassLoader;Ljava/lang/String;[Ljava/lang/Class;Lio/github/libxposed/api/XposedInterface$Hooker;)Lio/github/libxposed/api/XposedInterface$HookHandle;", "clazz", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;Lio/github/libxposed/api/XposedInterface$Hooker;)Lio/github/libxposed/api/XposedInterface$HookHandle;", "findAndHookBefore", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;Lkotlin/jvm/functions/Function1;)Lio/github/libxposed/api/XposedInterface$HookHandle;", "findAndHookAfter", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;Lkotlin/jvm/functions/Function2;)Lio/github/libxposed/api/XposedInterface$HookHandle;", "findAndHookReplace", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class HookHelpersKt {
    public static final Class<?> findClass(String className, ClassLoader classLoader) {
        Intrinsics.checkNotNullParameter(className, "className");
        Intrinsics.checkNotNullParameter(classLoader, "classLoader");
        Class<?> cls = Class.forName(className, false, classLoader);
        Intrinsics.checkNotNullExpressionValue(cls, "forName(...)");
        return cls;
    }

    public static final Class<?> findClassOrNull(String className, ClassLoader classLoader) {
        Object m10constructorimpl;
        Intrinsics.checkNotNullParameter(className, "className");
        Intrinsics.checkNotNullParameter(classLoader, "classLoader");
        try {
            Result.Companion companion = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(findClass(className, classLoader));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m16isFailureimpl(m10constructorimpl)) {
            m10constructorimpl = null;
        }
        return (Class) m10constructorimpl;
    }

    public static final Method findMethodExact(Class<?> cls, String methodName, Class<?>... parameterTypes) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(parameterTypes, "parameterTypes");
        Method declaredMethod = cls.getDeclaredMethod(methodName, (Class[]) Arrays.copyOf(parameterTypes, parameterTypes.length));
        declaredMethod.setAccessible(true);
        Intrinsics.checkNotNullExpressionValue(declaredMethod, "apply(...)");
        return declaredMethod;
    }

    public static final Method findMethodExactOrNull(Class<?> cls, String methodName, Class<?>... parameterTypes) {
        Object m10constructorimpl;
        Intrinsics.checkNotNullParameter(cls, "<this>");
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(parameterTypes, "parameterTypes");
        try {
            Result.Companion companion = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(findMethodExact(cls, methodName, (Class[]) Arrays.copyOf(parameterTypes, parameterTypes.length)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m16isFailureimpl(m10constructorimpl)) {
            m10constructorimpl = null;
        }
        return (Method) m10constructorimpl;
    }

    public static final <T> Constructor<T> findConstructorExact(Class<T> cls, Class<?>... parameterTypes) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        Intrinsics.checkNotNullParameter(parameterTypes, "parameterTypes");
        Constructor declaredConstructor = cls.getDeclaredConstructor((Class[]) Arrays.copyOf(parameterTypes, parameterTypes.length));
        declaredConstructor.setAccessible(true);
        Intrinsics.checkNotNullExpressionValue(declaredConstructor, "apply(...)");
        return declaredConstructor;
    }

    public static final Field findFieldExact(Class<?> cls, String fieldName) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        Intrinsics.checkNotNullParameter(fieldName, "fieldName");
        Field declaredField = cls.getDeclaredField(fieldName);
        declaredField.setAccessible(true);
        Intrinsics.checkNotNullExpressionValue(declaredField, "apply(...)");
        return declaredField;
    }

    public static final Field findFieldExactOrNull(Class<?> cls, String fieldName) {
        Object m10constructorimpl;
        Intrinsics.checkNotNullParameter(cls, "<this>");
        Intrinsics.checkNotNullParameter(fieldName, "fieldName");
        try {
            Result.Companion companion = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(findFieldExact(cls, fieldName));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m16isFailureimpl(m10constructorimpl)) {
            m10constructorimpl = null;
        }
        return (Field) m10constructorimpl;
    }

    public static final Field findFieldInHierarchyOrNull(Class<?> cls, String fieldName) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        Intrinsics.checkNotNullParameter(fieldName, "fieldName");
        for (Class clazz = cls; clazz != null; clazz = clazz.getSuperclass()) {
            Field findFieldExactOrNull = findFieldExactOrNull(clazz, fieldName);
            if (findFieldExactOrNull != null) {
                return findFieldExactOrNull;
            }
        }
        return null;
    }

    public static final Object getFieldValueOrNull(Object $this$getFieldValueOrNull, String fieldName) {
        Object m10constructorimpl;
        Intrinsics.checkNotNullParameter($this$getFieldValueOrNull, "<this>");
        Intrinsics.checkNotNullParameter(fieldName, "fieldName");
        Field findFieldInHierarchyOrNull = findFieldInHierarchyOrNull($this$getFieldValueOrNull.getClass(), fieldName);
        if (findFieldInHierarchyOrNull == null) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(findFieldInHierarchyOrNull.get($this$getFieldValueOrNull));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m16isFailureimpl(m10constructorimpl)) {
            return null;
        }
        return m10constructorimpl;
    }

    public static final Integer getIntFieldOrNull(Object $this$getIntFieldOrNull, String fieldName) {
        Intrinsics.checkNotNullParameter($this$getIntFieldOrNull, "<this>");
        Intrinsics.checkNotNullParameter(fieldName, "fieldName");
        Object fieldValueOrNull = getFieldValueOrNull($this$getIntFieldOrNull, fieldName);
        Number number = fieldValueOrNull instanceof Number ? (Number) fieldValueOrNull : null;
        if (number != null) {
            return Integer.valueOf(number.intValue());
        }
        return null;
    }

    public static final Long getLongFieldOrNull(Object $this$getLongFieldOrNull, String fieldName) {
        Intrinsics.checkNotNullParameter($this$getLongFieldOrNull, "<this>");
        Intrinsics.checkNotNullParameter(fieldName, "fieldName");
        Object fieldValueOrNull = getFieldValueOrNull($this$getLongFieldOrNull, fieldName);
        Number number = fieldValueOrNull instanceof Number ? (Number) fieldValueOrNull : null;
        if (number != null) {
            return Long.valueOf(number.longValue());
        }
        return null;
    }

    public static final CharSequence getCharSequenceFieldOrNull(Object $this$getCharSequenceFieldOrNull, String fieldName) {
        Intrinsics.checkNotNullParameter($this$getCharSequenceFieldOrNull, "<this>");
        Intrinsics.checkNotNullParameter(fieldName, "fieldName");
        Object fieldValueOrNull = getFieldValueOrNull($this$getCharSequenceFieldOrNull, fieldName);
        if (fieldValueOrNull instanceof CharSequence) {
            return (CharSequence) fieldValueOrNull;
        }
        return null;
    }

    public static final XposedInterface.HookHandle hook(Executable executable, XposedInterface.Hooker hooker) {
        Intrinsics.checkNotNullParameter(executable, "executable");
        Intrinsics.checkNotNullParameter(hooker, "hooker");
        XposedInterface.HookHandle intercept = ModuleEntryKt.getModule().hook(executable).intercept(hooker);
        Intrinsics.checkNotNullExpressionValue(intercept, "intercept(...)");
        return intercept;
    }

    public static final XposedInterface.HookHandle hookBefore(Executable executable, final Function1<? super XposedInterface.Chain, Unit> before) {
        Intrinsics.checkNotNullParameter(executable, "executable");
        Intrinsics.checkNotNullParameter(before, "before");
        return hook(executable, new XposedInterface.Hooker() { // from class: icu.nullptr.polyglot.util.HookHelpersKt$$ExternalSyntheticLambda2
            public final Object intercept(XposedInterface.Chain chain) {
                return HookHelpersKt.hookBefore$lambda$9(Function1.this, chain);
            }
        });
    }

    static final Object hookBefore$lambda$9(Function1 $before, XposedInterface.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        $before.invoke(chain);
        return chain.proceed();
    }

    public static final XposedInterface.HookHandle hookAfter(Executable executable, final Function2<? super XposedInterface.Chain, Object, Unit> after) {
        Intrinsics.checkNotNullParameter(executable, "executable");
        Intrinsics.checkNotNullParameter(after, "after");
        return hook(executable, new XposedInterface.Hooker() { // from class: icu.nullptr.polyglot.util.HookHelpersKt$$ExternalSyntheticLambda0
            public final Object intercept(XposedInterface.Chain chain) {
                return HookHelpersKt.hookAfter$lambda$10(Function2.this, chain);
            }
        });
    }

    static final Object hookAfter$lambda$10(Function2 $after, XposedInterface.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Object result = chain.proceed();
        $after.invoke(chain, result);
        return result;
    }

    public static final XposedInterface.HookHandle hookReplace(Executable executable, final Function1<? super XposedInterface.Chain, ? extends Object> replacement) {
        Intrinsics.checkNotNullParameter(executable, "executable");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        return hook(executable, new XposedInterface.Hooker() { // from class: icu.nullptr.polyglot.util.HookHelpersKt$$ExternalSyntheticLambda1
            public final Object intercept(XposedInterface.Chain chain) {
                return HookHelpersKt.hookReplace$lambda$11(Function1.this, chain);
            }
        });
    }

    static final Object hookReplace$lambda$11(Function1 $replacement, XposedInterface.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        return $replacement.invoke(chain);
    }

    public static final XposedInterface.HookHandle findAndHook(String className, ClassLoader classLoader, String methodName, Class<?>[] parameterTypes, XposedInterface.Hooker hooker) {
        Intrinsics.checkNotNullParameter(className, "className");
        Intrinsics.checkNotNullParameter(classLoader, "classLoader");
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(parameterTypes, "parameterTypes");
        Intrinsics.checkNotNullParameter(hooker, "hooker");
        return findAndHook(findClass(className, classLoader), methodName, (Class[]) Arrays.copyOf(parameterTypes, parameterTypes.length), hooker);
    }

    public static final XposedInterface.HookHandle findAndHook(Class<?> clazz, String methodName, Class<?>[] parameterTypes, XposedInterface.Hooker hooker) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(parameterTypes, "parameterTypes");
        Intrinsics.checkNotNullParameter(hooker, "hooker");
        return hook(findMethodExact(clazz, methodName, (Class[]) Arrays.copyOf(parameterTypes, parameterTypes.length)), hooker);
    }

    public static final XposedInterface.HookHandle findAndHookBefore(Class<?> clazz, String methodName, Class<?>[] parameterTypes, Function1<? super XposedInterface.Chain, Unit> before) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(parameterTypes, "parameterTypes");
        Intrinsics.checkNotNullParameter(before, "before");
        return hookBefore(findMethodExact(clazz, methodName, (Class[]) Arrays.copyOf(parameterTypes, parameterTypes.length)), before);
    }

    public static final XposedInterface.HookHandle findAndHookAfter(Class<?> clazz, String methodName, Class<?>[] parameterTypes, Function2<? super XposedInterface.Chain, Object, Unit> after) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(parameterTypes, "parameterTypes");
        Intrinsics.checkNotNullParameter(after, "after");
        return hookAfter(findMethodExact(clazz, methodName, (Class[]) Arrays.copyOf(parameterTypes, parameterTypes.length)), after);
    }

    public static final XposedInterface.HookHandle findAndHookReplace(Class<?> clazz, String methodName, Class<?>[] parameterTypes, Function1<? super XposedInterface.Chain, ? extends Object> replacement) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(parameterTypes, "parameterTypes");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        return hookReplace(findMethodExact(clazz, methodName, (Class[]) Arrays.copyOf(parameterTypes, parameterTypes.length)), replacement);
    }
}
