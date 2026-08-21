package icu.nullptr.polyglot.youtube.settings;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PreferenceMethods.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003JQ\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006 "}, d2 = {"Licu/nullptr/polyglot/youtube/settings/PreferenceMethods;", "", "resourceLoad", "Ljava/lang/reflect/Method;", "click", "keySetter", "titleSetter", "summarySetter", "addPreference", "<init>", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "getResourceLoad", "()Ljava/lang/reflect/Method;", "getClick", "getKeySetter", "getTitleSetter", "getSummarySetter", "getAddPreference", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final /* data */ class PreferenceMethods {
    private final Method addPreference;
    private final Method click;
    private final Method keySetter;
    private final Method resourceLoad;
    private final Method summarySetter;
    private final Method titleSetter;

    public static /* synthetic */ PreferenceMethods copy$default(PreferenceMethods preferenceMethods, Method method, Method method2, Method method3, Method method4, Method method5, Method method6, int i, Object obj) {
        if ((i & 1) != 0) {
            method = preferenceMethods.resourceLoad;
        }
        if ((i & 2) != 0) {
            method2 = preferenceMethods.click;
        }
        if ((i & 4) != 0) {
            method3 = preferenceMethods.keySetter;
        }
        if ((i & 8) != 0) {
            method4 = preferenceMethods.titleSetter;
        }
        if ((i & 16) != 0) {
            method5 = preferenceMethods.summarySetter;
        }
        if ((i & 32) != 0) {
            method6 = preferenceMethods.addPreference;
        }
        Method method7 = method5;
        Method method8 = method6;
        return preferenceMethods.copy(method, method2, method3, method4, method7, method8);
    }

    /* renamed from: component1, reason: from getter */
    public final Method getResourceLoad() {
        return this.resourceLoad;
    }

    /* renamed from: component2, reason: from getter */
    public final Method getClick() {
        return this.click;
    }

    /* renamed from: component3, reason: from getter */
    public final Method getKeySetter() {
        return this.keySetter;
    }

    /* renamed from: component4, reason: from getter */
    public final Method getTitleSetter() {
        return this.titleSetter;
    }

    /* renamed from: component5, reason: from getter */
    public final Method getSummarySetter() {
        return this.summarySetter;
    }

    /* renamed from: component6, reason: from getter */
    public final Method getAddPreference() {
        return this.addPreference;
    }

    public final PreferenceMethods copy(Method resourceLoad, Method click, Method keySetter, Method titleSetter, Method summarySetter, Method addPreference) {
        return new PreferenceMethods(resourceLoad, click, keySetter, titleSetter, summarySetter, addPreference);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PreferenceMethods)) {
            return false;
        }
        PreferenceMethods preferenceMethods = (PreferenceMethods) other;
        return Intrinsics.areEqual(this.resourceLoad, preferenceMethods.resourceLoad) && Intrinsics.areEqual(this.click, preferenceMethods.click) && Intrinsics.areEqual(this.keySetter, preferenceMethods.keySetter) && Intrinsics.areEqual(this.titleSetter, preferenceMethods.titleSetter) && Intrinsics.areEqual(this.summarySetter, preferenceMethods.summarySetter) && Intrinsics.areEqual(this.addPreference, preferenceMethods.addPreference);
    }

    public int hashCode() {
        return ((((((((((this.resourceLoad == null ? 0 : this.resourceLoad.hashCode()) * 31) + (this.click == null ? 0 : this.click.hashCode())) * 31) + (this.keySetter == null ? 0 : this.keySetter.hashCode())) * 31) + (this.titleSetter == null ? 0 : this.titleSetter.hashCode())) * 31) + (this.summarySetter == null ? 0 : this.summarySetter.hashCode())) * 31) + (this.addPreference != null ? this.addPreference.hashCode() : 0);
    }

    public String toString() {
        return "PreferenceMethods(resourceLoad=" + this.resourceLoad + ", click=" + this.click + ", keySetter=" + this.keySetter + ", titleSetter=" + this.titleSetter + ", summarySetter=" + this.summarySetter + ", addPreference=" + this.addPreference + ")";
    }

    public PreferenceMethods(Method resourceLoad, Method click, Method keySetter, Method titleSetter, Method summarySetter, Method addPreference) {
        this.resourceLoad = resourceLoad;
        this.click = click;
        this.keySetter = keySetter;
        this.titleSetter = titleSetter;
        this.summarySetter = summarySetter;
        this.addPreference = addPreference;
    }

    public final Method getResourceLoad() {
        return this.resourceLoad;
    }

    public final Method getClick() {
        return this.click;
    }

    public final Method getKeySetter() {
        return this.keySetter;
    }

    public final Method getTitleSetter() {
        return this.titleSetter;
    }

    public final Method getSummarySetter() {
        return this.summarySetter;
    }

    public final Method getAddPreference() {
        return this.addPreference;
    }
}
