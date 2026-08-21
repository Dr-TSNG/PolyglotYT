package icu.nullptr.polyglot;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.widget.Toast;
import icu.nullptr.polyglot.core.ConfigManager;
import icu.nullptr.polyglot.core.FileManager;
import icu.nullptr.polyglot.util.DexKitRuntime;
import icu.nullptr.polyglot.util.HookHelpersKt;
import icu.nullptr.polyglot.util.LoggerKt;
import icu.nullptr.polyglot.youtube.BaseHook;
import icu.nullptr.polyglot.youtube.CaptionHook;
import icu.nullptr.polyglot.youtube.PlayerControlsHook;
import icu.nullptr.polyglot.youtube.SettingsHook;
import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;
import io.github.libxposed.api.XposedModuleInterface;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.luckypray.dexkit.DexKitBridge;

/* compiled from: ModuleEntry.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020!2\u0006\u0010\"\u001a\u00020%H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Licu/nullptr/polyglot/ModuleEntry;", "Lio/github/libxposed/api/XposedModule;", "<init>", "()V", "fileManager", "Licu/nullptr/polyglot/core/FileManager;", "hostVersionName", "", "getHostVersionName", "()Ljava/lang/String;", "setHostVersionName", "(Ljava/lang/String;)V", "hostClassLoader", "Ljava/lang/ClassLoader;", "getHostClassLoader", "()Ljava/lang/ClassLoader;", "setHostClassLoader", "(Ljava/lang/ClassLoader;)V", "config", "Licu/nullptr/polyglot/core/ConfigManager;", "getConfig", "()Licu/nullptr/polyglot/core/ConfigManager;", "setConfig", "(Licu/nullptr/polyglot/core/ConfigManager;)V", "res", "Landroid/content/res/Resources;", "getRes", "()Landroid/content/res/Resources;", "setRes", "(Landroid/content/res/Resources;)V", "hookInstalled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "onModuleLoaded", "", "param", "Lio/github/libxposed/api/XposedModuleInterface$ModuleLoadedParam;", "onPackageReady", "Lio/github/libxposed/api/XposedModuleInterface$PackageReadyParam;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class ModuleEntry extends XposedModule {
    public ConfigManager config;
    private FileManager fileManager;
    private final AtomicBoolean hookInstalled = new AtomicBoolean(false);
    public ClassLoader hostClassLoader;
    public String hostVersionName;
    public Resources res;

    public final String getHostVersionName() {
        String str = this.hostVersionName;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("hostVersionName");
        return null;
    }

    public final void setHostVersionName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.hostVersionName = str;
    }

    public final ClassLoader getHostClassLoader() {
        ClassLoader classLoader = this.hostClassLoader;
        if (classLoader != null) {
            return classLoader;
        }
        Intrinsics.throwUninitializedPropertyAccessException("hostClassLoader");
        return null;
    }

    public final void setHostClassLoader(ClassLoader classLoader) {
        Intrinsics.checkNotNullParameter(classLoader, "<set-?>");
        this.hostClassLoader = classLoader;
    }

    public final ConfigManager getConfig() {
        ConfigManager configManager = this.config;
        if (configManager != null) {
            return configManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("config");
        return null;
    }

    public final void setConfig(ConfigManager configManager) {
        Intrinsics.checkNotNullParameter(configManager, "<set-?>");
        this.config = configManager;
    }

    public final Resources getRes() {
        Resources resources = this.res;
        if (resources != null) {
            return resources;
        }
        Intrinsics.throwUninitializedPropertyAccessException("res");
        return null;
    }

    public final void setRes(Resources resources) {
        Intrinsics.checkNotNullParameter(resources, "<set-?>");
        this.res = resources;
    }

    public void onModuleLoaded(XposedModuleInterface.ModuleLoadedParam param) {
        Intrinsics.checkNotNullParameter(param, "param");
        if (!Intrinsics.areEqual(param.getProcessName(), "com.google.android.youtube")) {
            if (getApiVersion() >= 102) {
                detach();
            }
        } else {
            ModuleEntryKt.setModule(this);
            LoggerKt.logI$default("ModuleEntry", "Loaded in framework " + getFrameworkName() + " API " + getApiVersion(), null, 4, null);
        }
    }

    public void onPackageReady(final XposedModuleInterface.PackageReadyParam param) {
        Intrinsics.checkNotNullParameter(param, "param");
        if (!Intrinsics.areEqual(param.getPackageName(), "com.google.android.youtube") || !param.isFirstPackage()) {
            if (getApiVersion() >= 102) {
                detach();
            }
        } else {
            ClassLoader classLoader = param.getClassLoader();
            Intrinsics.checkNotNullExpressionValue(classLoader, "getClassLoader(...)");
            setHostClassLoader(classLoader);
            HookHelpersKt.findAndHookAfter(Application.class, "attach", new Class[]{Context.class}, new Function2() { // from class: icu.nullptr.polyglot.ModuleEntry$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ModuleEntry.onPackageReady$lambda$3(ModuleEntry.this, param, (XposedInterface.Chain) obj, obj2);
                }
            });
            LoggerKt.logI$default("ModuleEntry", "Application.attach hook installed", null, 4, null);
        }
    }

    static final Unit onPackageReady$lambda$3(final ModuleEntry this$0, XposedModuleInterface.PackageReadyParam $param, XposedInterface.Chain chain, Object obj) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        if (!this$0.hookInstalled.compareAndSet(false, true)) {
            return Unit.INSTANCE;
        }
        Object thisObject = chain.getThisObject();
        Intrinsics.checkNotNull(thisObject, "null cannot be cast to non-null type android.app.Application");
        Application application = (Application) thisObject;
        Object arg = chain.getArg(0);
        Intrinsics.checkNotNull(arg, "null cannot be cast to non-null type android.content.Context");
        final Context context = (Context) arg;
        this$0.fileManager = new FileManager(context);
        FileManager fileManager = this$0.fileManager;
        if (fileManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fileManager");
            fileManager = null;
        }
        this$0.setConfig(new ConfigManager(context, fileManager.getConfigDir()));
        ClassLoader classLoader = this$0.getClass().getClassLoader();
        Intrinsics.checkNotNull(classLoader);
        Class amClass = HookHelpersKt.findClass("android.content.res.AssetManager", classLoader);
        Object newInstance = HookHelpersKt.findConstructorExact(amClass, new Class[0]).newInstance(new Object[0]);
        Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type android.content.res.AssetManager");
        AssetManager am = (AssetManager) newInstance;
        HookHelpersKt.findMethodExact(amClass, "addAssetPath", String.class).invoke(am, this$0.getModuleApplicationInfo().sourceDir);
        this$0.setRes(new Resources(am, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration()));
        PackageInfo packageInfo = application.getPackageManager().getPackageInfo($param.getPackageName(), 0);
        String str = packageInfo.versionName;
        Intrinsics.checkNotNull(str);
        this$0.setHostVersionName(str);
        final String tag = $param.getPackageName() + ":" + packageInfo.getLongVersionCode();
        DexKitRuntime dexKitRuntime = DexKitRuntime.INSTANCE;
        String packageCodePath = application.getPackageCodePath();
        Intrinsics.checkNotNullExpressionValue(packageCodePath, "getPackageCodePath(...)");
        dexKitRuntime.use(packageCodePath, new Function1() { // from class: icu.nullptr.polyglot.ModuleEntry$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return ModuleEntry.onPackageReady$lambda$3$lambda$2(tag, this$0, context, (DexKitBridge) obj2);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit onPackageReady$lambda$3$lambda$2(String $tag, ModuleEntry this$0, Context $context, DexKitBridge it) {
        Object m10constructorimpl;
        Intrinsics.checkNotNullParameter(it, "it");
        LoggerKt.logI$default("ModuleEntry", "DexKit bridge ready for " + $tag, null, 4, null);
        List<BaseHook> hooks = CollectionsKt.listOf((Object[]) new BaseHook[]{SettingsHook.INSTANCE, CaptionHook.INSTANCE, PlayerControlsHook.INSTANCE});
        int successful = 0;
        int successful2 = 0;
        for (BaseHook hook : hooks) {
            int total = hook.getTotalHooks() + successful2;
            try {
                Result.Companion companion = Result.INSTANCE;
                successful += hook.install(it);
                m10constructorimpl = Result.m10constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
            }
            Throwable m13exceptionOrNullimpl = Result.m13exceptionOrNullimpl(m10constructorimpl);
            if (m13exceptionOrNullimpl != null) {
                LoggerKt.logE$default("ModuleEntry", "Error while installing hook " + hook.getName() + ": " + m13exceptionOrNullimpl.getMessage(), null, 4, null);
            }
            successful2 = total;
        }
        LoggerKt.logI$default("ModuleEntry", successful + "/" + successful2 + " hooks installed successfully", null, 4, null);
        if (successful < successful2) {
            String text = this$0.getRes().getQuantityString(R.plurals.hook_failed, successful2 - successful, Integer.valueOf(successful2 - successful));
            Intrinsics.checkNotNullExpressionValue(text, "getQuantityString(...)");
            Toast.makeText($context, text, 1).show();
        }
        return Unit.INSTANCE;
    }
}
