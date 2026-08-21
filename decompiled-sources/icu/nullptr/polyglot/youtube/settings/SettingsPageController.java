package icu.nullptr.polyglot.youtube.settings;

import android.app.Activity;
import android.content.Context;
import icu.nullptr.polyglot.util.HookHelpersKt;
import icu.nullptr.polyglot.util.LoggerKt;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SettingsPageController.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0000\u0018\u0000 '2\u00020\u0001:\u0001'B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u000e\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u0001J\u001c\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u00012\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u0016\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\bJ\u000e\u0010 \u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\rJ\u0010\u0010!\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\rH\u0002J\u0014\u0010\"\u001a\u00020\u00122\n\u0010#\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0002J\u0014\u0010$\u001a\u00020\u00122\n\u0010#\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0002J\u0018\u0010%\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u0019H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Licu/nullptr/polyglot/youtube/settings/SettingsPageController;", "", "adapter", "Licu/nullptr/polyglot/youtube/settings/HostPreferenceAdapter;", "<init>", "(Licu/nullptr/polyglot/youtube/settings/HostPreferenceAdapter;)V", "nativePages", "Ljava/util/WeakHashMap;", "Licu/nullptr/polyglot/youtube/settings/NativeSettingsPage;", "clickHandlers", "Lkotlin/Function0;", "", "activePagesByActivity", "Landroid/app/Activity;", "hookedBackActivityClasses", "", "Ljava/lang/Class;", "injectEntry", "", "fragment", "resourceId", "", "detachActivePageForHostRoot", "activity", "resourceEntryName", "", "dispatchPreferenceClick", HostPreferenceAdapter.PREFERENCE_DEFAULT_LAYOUT, "registerClickHandler", "handler", "activate", "page", "deactivate", "installBackHookForActivity", "hookActivityBackMethod", "activityClass", "hookActivityFinishMethod", "handleActivityBack", "source", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class SettingsPageController {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final String TAG = "SettingsPageController";
    private final WeakHashMap<Activity, NativeSettingsPage> activePagesByActivity;
    private final HostPreferenceAdapter adapter;
    private final WeakHashMap<Object, Function0<Boolean>> clickHandlers;
    private final Set<Class<?>> hookedBackActivityClasses;
    private final WeakHashMap<Object, NativeSettingsPage> nativePages;

    public SettingsPageController(HostPreferenceAdapter adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        this.adapter = adapter;
        this.nativePages = new WeakHashMap<>();
        this.clickHandlers = new WeakHashMap<>();
        this.activePagesByActivity = new WeakHashMap<>();
        this.hookedBackActivityClasses = new LinkedHashSet();
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void injectEntry(java.lang.Object r21, int r22) {
        /*
            Method dump skipped, instructions count: 337
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.youtube.settings.SettingsPageController.injectEntry(java.lang.Object, int):void");
    }

    private static final NativeSettingsPage injectEntry$lambda$3$createPage(Object $fragment, Object rootScreen, Context context, HostPreferenceClasses classes, SettingsPageController $this_runCatching, Activity activity) {
        return new NativeSettingsPage($fragment, rootScreen, context, classes, $this_runCatching.adapter, $this_runCatching, activity);
    }

    private final void detachActivePageForHostRoot(Activity activity, String resourceEntryName) {
        NativeSettingsPage page = this.activePagesByActivity.get(activity);
        if (page == null) {
            return;
        }
        page.detachFromHostRoot();
        LoggerKt.logD$default(TAG, "Detached native settings page after host root reload in " + resourceEntryName, null, 4, null);
    }

    public final boolean dispatchPreferenceClick(Object preference) {
        Intrinsics.checkNotNullParameter(preference, "preference");
        Function0 function0 = this.clickHandlers.get(preference);
        if (function0 != null) {
            return function0.invoke().booleanValue();
        }
        NativeSettingsPage nativeSettingsPage = this.nativePages.get(preference);
        if (nativeSettingsPage != null) {
            nativeSettingsPage.open();
            return true;
        }
        return false;
    }

    public final void registerClickHandler(Object preference, Function0<Boolean> handler) {
        Intrinsics.checkNotNullParameter(preference, "preference");
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.clickHandlers.put(preference, handler);
    }

    public final void activate(Activity activity, NativeSettingsPage page) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(page, "page");
        this.activePagesByActivity.put(activity, page);
    }

    public final void deactivate(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activePagesByActivity.remove(activity);
    }

    private final void installBackHookForActivity(Activity activity) {
        Class activityClass = activity.getClass();
        synchronized (this.hookedBackActivityClasses) {
            if (this.hookedBackActivityClasses.add(activityClass)) {
                Unit unit = Unit.INSTANCE;
                hookActivityBackMethod(activityClass);
                hookActivityFinishMethod(activityClass);
            }
        }
    }

    private final void hookActivityBackMethod(Class<?> activityClass) {
        Object obj;
        Iterator<Method> it = ReflectionSupportKt.methodsInHierarchy(activityClass).iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                Method method = (Method) obj;
                if (Intrinsics.areEqual(method.getName(), "onBackPressed") && method.getParameterCount() == 0 && Intrinsics.areEqual(method.getReturnType(), Void.TYPE)) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        Method method2 = (Method) obj;
        if (method2 == null) {
            LoggerKt.logW$default(TAG, "Unable to find settings activity back method", null, 4, null);
            return;
        }
        method2.setAccessible(true);
        HookHelpersKt.hook(method2, new XposedInterface.Hooker() { // from class: icu.nullptr.polyglot.youtube.settings.SettingsPageController$$ExternalSyntheticLambda0
            public final Object intercept(XposedInterface.Chain chain) {
                return SettingsPageController.hookActivityBackMethod$lambda$11(SettingsPageController.this, chain);
            }
        });
        LoggerKt.logD$default(TAG, "Hooked settings activity back method: " + ReflectionSupportKt.shortName(method2), null, 4, null);
    }

    static final Object hookActivityBackMethod$lambda$11(SettingsPageController this$0, XposedInterface.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Object thisObject = chain.getThisObject();
        Activity activity = thisObject instanceof Activity ? (Activity) thisObject : null;
        boolean handled = activity != null ? this$0.handleActivityBack(activity, "back") : false;
        if (handled) {
            return null;
        }
        return chain.proceed();
    }

    private final void hookActivityFinishMethod(Class<?> activityClass) {
        Object obj;
        Iterator<Method> it = ReflectionSupportKt.methodsInHierarchy(activityClass).iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                Method method = (Method) obj;
                if (Intrinsics.areEqual(method.getName(), "finish") && method.getParameterCount() == 0 && Intrinsics.areEqual(method.getReturnType(), Void.TYPE)) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        Method method2 = (Method) obj;
        if (method2 == null) {
            LoggerKt.logW$default(TAG, "Unable to find settings activity finish method", null, 4, null);
            return;
        }
        method2.setAccessible(true);
        HookHelpersKt.hook(method2, new XposedInterface.Hooker() { // from class: icu.nullptr.polyglot.youtube.settings.SettingsPageController$$ExternalSyntheticLambda1
            public final Object intercept(XposedInterface.Chain chain) {
                return SettingsPageController.hookActivityFinishMethod$lambda$15(SettingsPageController.this, chain);
            }
        });
        LoggerKt.logD$default(TAG, "Hooked settings activity finish method: " + ReflectionSupportKt.shortName(method2), null, 4, null);
    }

    static final Object hookActivityFinishMethod$lambda$15(SettingsPageController this$0, XposedInterface.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Object thisObject = chain.getThisObject();
        Activity activity = thisObject instanceof Activity ? (Activity) thisObject : null;
        boolean handled = activity != null ? this$0.handleActivityBack(activity, "toolbar back") : false;
        if (handled) {
            return null;
        }
        return chain.proceed();
    }

    private final boolean handleActivityBack(Activity activity, String source) {
        NativeSettingsPage page = this.activePagesByActivity.get(activity);
        if (page == null || !page.navigateBack()) {
            return false;
        }
        LoggerKt.logI$default(TAG, "Handled PolyglotYT native settings " + source, null, 4, null);
        return true;
    }

    /* compiled from: SettingsPageController.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Licu/nullptr/polyglot/youtube/settings/SettingsPageController$Companion;", "", "<init>", "()V", "TAG", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
