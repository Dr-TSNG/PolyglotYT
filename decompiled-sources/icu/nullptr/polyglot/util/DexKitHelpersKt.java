package icu.nullptr.polyglot.util;

import icu.nullptr.polyglot.ModuleEntryKt;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.luckypray.dexkit.result.MethodData;

/* compiled from: DexKitHelpers.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u000e\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0006*\u00020\u0002¨\u0006\u0007"}, d2 = {"toMethod", "Ljava/lang/reflect/Method;", "Lorg/luckypray/dexkit/result/MethodData;", "toConstructor", "Ljava/lang/reflect/Constructor;", "toExecutable", "Ljava/lang/reflect/Executable;", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class DexKitHelpersKt {
    public static final Method toMethod(MethodData $this$toMethod) {
        Intrinsics.checkNotNullParameter($this$toMethod, "<this>");
        Method methodInstance = $this$toMethod.getMethodInstance(ModuleEntryKt.getModule().getHostClassLoader());
        methodInstance.setAccessible(true);
        return methodInstance;
    }

    public static final Constructor<?> toConstructor(MethodData $this$toConstructor) {
        Intrinsics.checkNotNullParameter($this$toConstructor, "<this>");
        Constructor constructorInstance = $this$toConstructor.getConstructorInstance(ModuleEntryKt.getModule().getHostClassLoader());
        constructorInstance.setAccessible(true);
        return constructorInstance;
    }

    public static final Executable toExecutable(MethodData $this$toExecutable) {
        Intrinsics.checkNotNullParameter($this$toExecutable, "<this>");
        return $this$toExecutable.isConstructor() ? toConstructor($this$toExecutable) : toMethod($this$toExecutable);
    }
}
