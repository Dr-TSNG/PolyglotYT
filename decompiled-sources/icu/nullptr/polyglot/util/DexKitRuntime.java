package icu.nullptr.polyglot.util;

import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.luckypray.dexkit.DexKitBridge;

/* compiled from: DexKitHelpers.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0006\u001a\u00020\u0007J-\u0010\b\u001a\u0002H\t\"\u0004\b\u0000\u0010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002H\t0\r¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Licu/nullptr/polyglot/util/DexKitRuntime;", "", "<init>", "()V", "libraryLoaded", "", "loadLibrary", "", "use", "T", "apkPath", "", "block", "Lkotlin/Function1;", "Lorg/luckypray/dexkit/DexKitBridge;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class DexKitRuntime {
    public static final DexKitRuntime INSTANCE = new DexKitRuntime();
    private static boolean libraryLoaded;

    private DexKitRuntime() {
    }

    public final synchronized void loadLibrary() {
        if (libraryLoaded) {
            return;
        }
        System.loadLibrary("dexkit");
        libraryLoaded = true;
    }

    public final <T> T use(String apkPath, Function1<? super DexKitBridge, ? extends T> block) {
        Intrinsics.checkNotNullParameter(apkPath, "apkPath");
        Intrinsics.checkNotNullParameter(block, "block");
        loadLibrary();
        DexKitBridge create = DexKitBridge.INSTANCE.create(apkPath);
        try {
            T invoke = block.invoke(create);
            CloseableKt.closeFinally(create, null);
            return invoke;
        } finally {
        }
    }
}
