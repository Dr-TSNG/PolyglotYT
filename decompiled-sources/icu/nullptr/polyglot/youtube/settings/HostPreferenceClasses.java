package icu.nullptr.polyglot.youtube.settings;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HostPreferenceAdapter.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005\u0012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0005\u0012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\r\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0005HÆ\u0003J\r\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0005HÆ\u0003J\r\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0005HÆ\u0003J=\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\b\u0002\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\f\b\u0002\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00052\f\b\u0002\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0015\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u001c"}, d2 = {"Licu/nullptr/polyglot/youtube/settings/HostPreferenceClasses;", "", "classLoader", "Ljava/lang/ClassLoader;", HostPreferenceAdapter.PREFERENCE_DEFAULT_LAYOUT, "Ljava/lang/Class;", "preferenceGroup", "switchPreference", "<init>", "(Ljava/lang/ClassLoader;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;)V", "getClassLoader", "()Ljava/lang/ClassLoader;", "getPreference", "()Ljava/lang/Class;", "getPreferenceGroup", "getSwitchPreference", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final /* data */ class HostPreferenceClasses {
    private final ClassLoader classLoader;
    private final Class<?> preference;
    private final Class<?> preferenceGroup;
    private final Class<?> switchPreference;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HostPreferenceClasses copy$default(HostPreferenceClasses hostPreferenceClasses, ClassLoader classLoader, Class cls, Class cls2, Class cls3, int i, Object obj) {
        if ((i & 1) != 0) {
            classLoader = hostPreferenceClasses.classLoader;
        }
        if ((i & 2) != 0) {
            cls = hostPreferenceClasses.preference;
        }
        if ((i & 4) != 0) {
            cls2 = hostPreferenceClasses.preferenceGroup;
        }
        if ((i & 8) != 0) {
            cls3 = hostPreferenceClasses.switchPreference;
        }
        return hostPreferenceClasses.copy(classLoader, cls, cls2, cls3);
    }

    /* renamed from: component1, reason: from getter */
    public final ClassLoader getClassLoader() {
        return this.classLoader;
    }

    public final Class<?> component2() {
        return this.preference;
    }

    public final Class<?> component3() {
        return this.preferenceGroup;
    }

    public final Class<?> component4() {
        return this.switchPreference;
    }

    public final HostPreferenceClasses copy(ClassLoader classLoader, Class<?> preference, Class<?> preferenceGroup, Class<?> switchPreference) {
        Intrinsics.checkNotNullParameter(classLoader, "classLoader");
        Intrinsics.checkNotNullParameter(preference, "preference");
        Intrinsics.checkNotNullParameter(preferenceGroup, "preferenceGroup");
        Intrinsics.checkNotNullParameter(switchPreference, "switchPreference");
        return new HostPreferenceClasses(classLoader, preference, preferenceGroup, switchPreference);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HostPreferenceClasses)) {
            return false;
        }
        HostPreferenceClasses hostPreferenceClasses = (HostPreferenceClasses) other;
        return Intrinsics.areEqual(this.classLoader, hostPreferenceClasses.classLoader) && Intrinsics.areEqual(this.preference, hostPreferenceClasses.preference) && Intrinsics.areEqual(this.preferenceGroup, hostPreferenceClasses.preferenceGroup) && Intrinsics.areEqual(this.switchPreference, hostPreferenceClasses.switchPreference);
    }

    public int hashCode() {
        return (((((this.classLoader.hashCode() * 31) + this.preference.hashCode()) * 31) + this.preferenceGroup.hashCode()) * 31) + this.switchPreference.hashCode();
    }

    public String toString() {
        return "HostPreferenceClasses(classLoader=" + this.classLoader + ", preference=" + this.preference + ", preferenceGroup=" + this.preferenceGroup + ", switchPreference=" + this.switchPreference + ")";
    }

    public HostPreferenceClasses(ClassLoader classLoader, Class<?> preference, Class<?> preferenceGroup, Class<?> switchPreference) {
        Intrinsics.checkNotNullParameter(classLoader, "classLoader");
        Intrinsics.checkNotNullParameter(preference, "preference");
        Intrinsics.checkNotNullParameter(preferenceGroup, "preferenceGroup");
        Intrinsics.checkNotNullParameter(switchPreference, "switchPreference");
        this.classLoader = classLoader;
        this.preference = preference;
        this.preferenceGroup = preferenceGroup;
        this.switchPreference = switchPreference;
    }

    public final ClassLoader getClassLoader() {
        return this.classLoader;
    }

    public final Class<?> getPreference() {
        return this.preference;
    }

    public final Class<?> getPreferenceGroup() {
        return this.preferenceGroup;
    }

    public final Class<?> getSwitchPreference() {
        return this.switchPreference;
    }
}
