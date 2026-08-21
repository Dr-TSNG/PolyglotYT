package icu.nullptr.polyglot.youtube;

import icu.nullptr.polyglot.util.HookHelpersKt;
import icu.nullptr.polyglot.util.LoggerKt;
import icu.nullptr.polyglot.youtube.settings.HostPreferenceAdapter;
import icu.nullptr.polyglot.youtube.settings.PreferenceMethods;
import icu.nullptr.polyglot.youtube.settings.PreferenceMethodsKt;
import icu.nullptr.polyglot.youtube.settings.ReflectionSupportKt;
import icu.nullptr.polyglot.youtube.settings.SettingsPageController;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.luckypray.dexkit.DexKitBridge;

/* compiled from: SettingsHook.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Licu/nullptr/polyglot/youtube/SettingsHook;", "Licu/nullptr/polyglot/youtube/BaseHook;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "totalHooks", "", "getTotalHooks", "()I", "pageController", "Licu/nullptr/polyglot/youtube/settings/SettingsPageController;", "install", "dexkit", "Lorg/luckypray/dexkit/DexKitBridge;", "installPreferenceResourceHook", "", "methods", "Licu/nullptr/polyglot/youtube/settings/PreferenceMethods;", "installPreferenceClickHook", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class SettingsHook implements BaseHook {
    private static SettingsPageController pageController;
    public static final SettingsHook INSTANCE = new SettingsHook();
    private static final String name = "SettingsHook";
    private static final int totalHooks = 2;

    private SettingsHook() {
    }

    @Override // icu.nullptr.polyglot.youtube.BaseHook
    public String getName() {
        return name;
    }

    @Override // icu.nullptr.polyglot.youtube.BaseHook
    public int getTotalHooks() {
        return totalHooks;
    }

    @Override // icu.nullptr.polyglot.youtube.BaseHook
    public int install(DexKitBridge dexkit) {
        Intrinsics.checkNotNullParameter(dexkit, "dexkit");
        PreferenceMethods methods = PreferenceMethodsKt.resolvePreferenceMethods(dexkit);
        HostPreferenceAdapter preferenceAdapter = new HostPreferenceAdapter(PreferenceMethodsKt.resolvePreferenceMethods(dexkit));
        pageController = new SettingsPageController(preferenceAdapter);
        int installed = installPreferenceResourceHook(methods) ? 0 + 1 : 0;
        if (installPreferenceClickHook(methods)) {
            installed++;
        }
        LoggerKt.logI$default(getName(), "Installed " + installed + " settings hook(s)", null, 4, null);
        return installed;
    }

    private final boolean installPreferenceResourceHook(PreferenceMethods methods) {
        Method resourceLoadMethod = methods.getResourceLoad();
        if (resourceLoadMethod == null) {
            LoggerKt.logW$default(getName(), "Preference resource loader not found", null, 4, null);
            return false;
        }
        HookHelpersKt.hook(resourceLoadMethod, new XposedInterface.Hooker() { // from class: icu.nullptr.polyglot.youtube.SettingsHook$$ExternalSyntheticLambda1
            public final Object intercept(XposedInterface.Chain chain) {
                return SettingsHook.installPreferenceResourceHook$lambda$1(chain);
            }
        });
        LoggerKt.logD$default(getName(), "Hooked preference resource loader: " + ReflectionSupportKt.shortName(resourceLoadMethod), null, 4, null);
        return true;
    }

    static final Object installPreferenceResourceHook$lambda$1(XposedInterface.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Object arg = chain.getArg(0);
        SettingsPageController settingsPageController = null;
        Integer resourceId = arg instanceof Integer ? (Integer) arg : null;
        Object result = chain.proceed();
        Object fragment = chain.getThisObject();
        if (fragment != null && resourceId != null) {
            SettingsPageController settingsPageController2 = pageController;
            if (settingsPageController2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pageController");
            } else {
                settingsPageController = settingsPageController2;
            }
            settingsPageController.injectEntry(fragment, resourceId.intValue());
        }
        return result;
    }

    private final boolean installPreferenceClickHook(PreferenceMethods methods) {
        Method clickMethod = methods.getClick();
        if (clickMethod == null) {
            LoggerKt.logW$default(getName(), "Preference click dispatcher not found", null, 4, null);
            return false;
        }
        HookHelpersKt.hook(clickMethod, new XposedInterface.Hooker() { // from class: icu.nullptr.polyglot.youtube.SettingsHook$$ExternalSyntheticLambda0
            public final Object intercept(XposedInterface.Chain chain) {
                return SettingsHook.installPreferenceClickHook$lambda$3(chain);
            }
        });
        LoggerKt.logD$default(getName(), "Hooked preference click dispatcher: " + ReflectionSupportKt.shortName(clickMethod), null, 4, null);
        return true;
    }

    static final Object installPreferenceClickHook$lambda$3(XposedInterface.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Object preference = chain.getThisObject();
        if (preference != null) {
            SettingsPageController settingsPageController = pageController;
            if (settingsPageController == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pageController");
                settingsPageController = null;
            }
            if (settingsPageController.dispatchPreferenceClick(preference)) {
                return null;
            }
        }
        return chain.proceed();
    }
}
