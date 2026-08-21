package icu.nullptr.polyglot;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ModuleEntry.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\"\u001a\u0010\u0000\u001a\u00020\u0001X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"module", "Licu/nullptr/polyglot/ModuleEntry;", "getModule", "()Licu/nullptr/polyglot/ModuleEntry;", "setModule", "(Licu/nullptr/polyglot/ModuleEntry;)V", "TARGET_PACKAGE", "", "TAG", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class ModuleEntryKt {
    private static final String TAG = "ModuleEntry";
    private static final String TARGET_PACKAGE = "com.google.android.youtube";
    public static ModuleEntry module;

    public static final ModuleEntry getModule() {
        ModuleEntry moduleEntry = module;
        if (moduleEntry != null) {
            return moduleEntry;
        }
        Intrinsics.throwUninitializedPropertyAccessException("module");
        return null;
    }

    public static final void setModule(ModuleEntry moduleEntry) {
        Intrinsics.checkNotNullParameter(moduleEntry, "<set-?>");
        module = moduleEntry;
    }
}
