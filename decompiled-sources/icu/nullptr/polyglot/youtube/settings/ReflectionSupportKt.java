package icu.nullptr.polyglot.youtube.settings;

import android.content.Context;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* compiled from: ReflectionSupport.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0000\u001a\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u001a\u001c\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0000\u001a\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n*\u0006\u0012\u0002\b\u00030\fH\u0000\u001a\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\n*\u0006\u0012\u0002\b\u00030\fH\u0000\u001a\f\u0010\u000f\u001a\u00020\u0004*\u00020\u000eH\u0000\u001a\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\fH\u0000¨\u0006\u0012"}, d2 = {"activityOrNull", "Landroid/app/Activity;", "Landroid/content/Context;", "resourceEntryName", "", "resourceId", "", "name", "type", "fieldsInHierarchy", "Lkotlin/sequences/Sequence;", "Ljava/lang/reflect/Field;", "Ljava/lang/Class;", "methodsInHierarchy", "Ljava/lang/reflect/Method;", "shortName", "defaultReturnValue", "", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class ReflectionSupportKt {
    /* JADX WARN: Code restructure failed: missing block: B:22:0x002d, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final android.app.Activity activityOrNull(android.content.Context r4) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            r0 = r4
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
        Lb:
            r2 = 0
            if (r0 == 0) goto L2d
            boolean r3 = r1.add(r0)
            if (r3 == 0) goto L2d
            boolean r3 = r0 instanceof android.app.Activity
            if (r3 == 0) goto L1c
            r2 = r0
            android.app.Activity r2 = (android.app.Activity) r2
            return r2
        L1c:
            boolean r3 = r0 instanceof android.content.ContextWrapper
            if (r3 == 0) goto L24
            r3 = r0
            android.content.ContextWrapper r3 = (android.content.ContextWrapper) r3
            goto L25
        L24:
            r3 = r2
        L25:
            if (r3 == 0) goto L2b
            android.content.Context r2 = r3.getBaseContext()
        L2b:
            r0 = r2
            goto Lb
        L2d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.youtube.settings.ReflectionSupportKt.activityOrNull(android.content.Context):android.app.Activity");
    }

    public static final String resourceEntryName(Context $this$resourceEntryName, int resourceId) {
        Object m10constructorimpl;
        Intrinsics.checkNotNullParameter($this$resourceEntryName, "<this>");
        try {
            Result.Companion companion = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl($this$resourceEntryName.getResources().getResourceEntryName(resourceId));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m16isFailureimpl(m10constructorimpl)) {
            m10constructorimpl = null;
        }
        return (String) m10constructorimpl;
    }

    public static final int resourceId(Context $this$resourceId, String name, String type) {
        Intrinsics.checkNotNullParameter($this$resourceId, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(type, "type");
        return $this$resourceId.getResources().getIdentifier(name, type, $this$resourceId.getPackageName());
    }

    public static final Sequence<Field> fieldsInHierarchy(Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        return SequencesKt.sequence(new ReflectionSupportKt$fieldsInHierarchy$1(cls, null));
    }

    public static final Sequence<Method> methodsInHierarchy(Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        return SequencesKt.sequence(new ReflectionSupportKt$methodsInHierarchy$1(cls, null));
    }

    public static final String shortName(Method $this$shortName) {
        Intrinsics.checkNotNullParameter($this$shortName, "<this>");
        String name = $this$shortName.getDeclaringClass().getName();
        String name2 = $this$shortName.getName();
        Class<?>[] parameterTypes = $this$shortName.getParameterTypes();
        Intrinsics.checkNotNullExpressionValue(parameterTypes, "getParameterTypes(...)");
        return name + "#" + name2 + "(" + ArraysKt.joinToString$default(parameterTypes, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.ReflectionSupportKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ReflectionSupportKt.shortName$lambda$1((Class) obj);
            }
        }, 31, (Object) null) + ")";
    }

    static final CharSequence shortName$lambda$1(Class it) {
        String simpleName = it.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        return simpleName;
    }

    public static final Object defaultReturnValue(Class<?> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (Intrinsics.areEqual(type, Boolean.TYPE)) {
            return false;
        }
        if (Intrinsics.areEqual(type, Byte.TYPE)) {
            return (byte) 0;
        }
        if (Intrinsics.areEqual(type, Character.TYPE)) {
            return (char) 0;
        }
        if (Intrinsics.areEqual(type, Short.TYPE)) {
            return (short) 0;
        }
        if (Intrinsics.areEqual(type, Integer.TYPE)) {
            return 0;
        }
        if (Intrinsics.areEqual(type, Long.TYPE)) {
            return 0L;
        }
        if (Intrinsics.areEqual(type, Float.TYPE)) {
            return Float.valueOf(0.0f);
        }
        if (Intrinsics.areEqual(type, Double.TYPE)) {
            return Double.valueOf(0.0d);
        }
        return null;
    }
}
