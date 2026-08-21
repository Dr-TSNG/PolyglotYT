package icu.nullptr.polyglot.youtube;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.util.SparseArray;
import android.view.View;
import icu.nullptr.polyglot.ModuleEntryKt;
import icu.nullptr.polyglot.captions.BilingualFormatter;
import icu.nullptr.polyglot.captions.CaptionCue;
import icu.nullptr.polyglot.captions.CaptionLanguageState;
import icu.nullptr.polyglot.captions.CaptionSession;
import icu.nullptr.polyglot.translate.TranslationManager;
import icu.nullptr.polyglot.util.DexKitHelpersKt;
import icu.nullptr.polyglot.util.HookHelpersKt;
import icu.nullptr.polyglot.util.LoggerKt;
import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.luckypray.dexkit.DexKitBridge;
import org.luckypray.dexkit.query.FindMethod;
import org.luckypray.dexkit.query.matchers.MethodMatcher;
import org.luckypray.dexkit.result.ClassData;
import org.luckypray.dexkit.result.FieldData;
import org.luckypray.dexkit.result.MethodData;

/* compiled from: CaptionHook.kt */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001nB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001eJ\u0010\u0010 \u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010!\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\"\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010#\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0018\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\b\u0010'\u001a\u0004\u0018\u00010\u0012H\u0002J\u0018\u0010(\u001a\b\u0012\u0004\u0012\u00020&0%2\b\u0010)\u001a\u0004\u0018\u00010\u0012H\u0002J\u000e\u0010*\u001a\u0004\u0018\u00010&*\u00020\u0012H\u0002J4\u0010+\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030%\u0012\b\u0012\u0006\u0012\u0002\b\u00030%0,2\n\u0010-\u001a\u0006\u0012\u0002\b\u00030%2\n\u0010.\u001a\u0006\u0012\u0002\b\u00030%H\u0002J\u001c\u0010/\u001a\u0004\u0018\u0001H0\"\u0006\b\u0000\u00100\u0018\u0001*\u00020\u0012H\u0082\b¢\u0006\u0002\u00101J\u0014\u00102\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120%*\u00020\u0012H\u0002J\u0016\u00103\u001a\u0004\u0018\u00010\u0012*\u0002042\u0006\u00105\u001a\u00020\u0012H\u0002J\u001c\u00106\u001a\u00020\u0018*\u0006\u0012\u0002\b\u0003072\n\u00108\u001a\u0006\u0012\u0002\b\u000307H\u0002J\u0018\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\t2\u0006\u0010<\u001a\u00020\u0005H\u0002J&\u0010=\u001a\u00020:2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020&0%2\u0006\u0010<\u001a\u00020\u00052\u0006\u0010?\u001a\u00020\tH\u0002J \u0010@\u001a\u00020:2\u0006\u0010A\u001a\u00020\u00052\u0006\u0010<\u001a\u00020\u00052\u0006\u0010?\u001a\u00020\tH\u0002J\u0010\u0010B\u001a\u00020:2\u0006\u0010C\u001a\u00020\u0005H\u0002J\u001a\u0010D\u001a\u00020:2\b\u0010E\u001a\u0004\u0018\u00010\u00122\u0006\u0010<\u001a\u00020\u0005H\u0002J \u0010F\u001a\u00020:2\u0006\u0010G\u001a\u00020\u00122\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020\u0005H\u0002J \u0010K\u001a\u00020\u001e2\u0006\u0010L\u001a\u00020\u00052\u0006\u0010M\u001a\u00020\u00052\u0006\u0010N\u001a\u00020\u0005H\u0002J\u0018\u0010O\u001a\u00020:2\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010<\u001a\u00020\u0005H\u0002J\u0018\u0010P\u001a\u00020:2\u0006\u0010G\u001a\u00020\u00122\u0006\u0010Q\u001a\u00020\u0013H\u0002J0\u0010R\u001a\u00020:2\u0006\u0010G\u001a\u00020\u00122\u0006\u0010H\u001a\u00020I2\u0006\u0010A\u001a\u00020\u001e2\u0006\u0010<\u001a\u00020\u00052\u0006\u0010S\u001a\u00020\tH\u0002J\f\u0010T\u001a\u00020\u0005*\u00020UH\u0002J\f\u0010V\u001a\u00020\u0005*\u00020UH\u0002J\u000e\u0010W\u001a\u0004\u0018\u00010X*\u00020\u001cH\u0002J\u0014\u0010Y\u001a\u00020\u0018*\u00020Z2\u0006\u0010[\u001a\u00020\tH\u0002J\u0014\u0010\\\u001a\u00020\u0018*\u00020Z2\u0006\u0010]\u001a\u00020\u0005H\u0002J\u0012\u0010^\u001a\b\u0012\u0004\u0012\u00020X0%*\u00020\u001cH\u0002J\u0012\u0010_\u001a\b\u0012\u0004\u0012\u00020X0%*\u00020\u001cH\u0002J\u000e\u0010`\u001a\u0004\u0018\u00010\u0005*\u00020\u001cH\u0002J\u001a\u0010a\u001a\b\u0012\u0004\u0012\u00020X0%*\u00020\u001c2\u0006\u0010b\u001a\u00020\u0005H\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\u0016\u001a&\u0012\f\u0012\n \u0019*\u0004\u0018\u00010\u00180\u0018 \u0019*\u0012\u0012\f\u0012\n \u0019*\u0004\u0018\u00010\u00180\u0018\u0018\u00010\u00170\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010c\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010d\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010g\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010h\u001a\u00020iX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010j\u001a\u00020iX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010k\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010l\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010m\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006o"}, d2 = {"Licu/nullptr/polyglot/youtube/CaptionHook;", "Licu/nullptr/polyglot/youtube/BaseHook;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "totalHooks", "", "getTotalHooks", "()I", "session", "Licu/nullptr/polyglot/captions/CaptionSession;", "mainHandler", "Landroid/os/Handler;", "rendererStates", "Ljava/util/WeakHashMap;", "", "Licu/nullptr/polyglot/youtube/CaptionHook$RendererState;", "rendererSequence", "Ljava/util/concurrent/atomic/AtomicLong;", "applyingTranslatedText", "Ljava/lang/ThreadLocal;", "", "kotlin.jvm.PlatformType", "install", "dexkit", "Lorg/luckypray/dexkit/DexKitBridge;", "formatCaption", "", "original", "installTimelineBuildHook", "installRenderTextHooks", "installCaptionLanguageHooks", "installOverlayUpdateHooks", "observeTimeline", "", "Licu/nullptr/polyglot/captions/CaptionCue;", "result", "observeCueList", "arg", "toCaptionCue", "orderedTimelineTimeLists", "Lkotlin/Pair;", "first", "second", "firstInstanceFieldValue", "T", "(Ljava/lang/Object;)Ljava/lang/Object;", "instanceFieldValues", "getValueOrNull", "Ljava/lang/reflect/Field;", "instance", "hasInstanceFieldAssignableTo", "Ljava/lang/Class;", "type", "logObservedCueCount", "", "newCueCount", "source", "requestTranslations", "cues", "priority", "requestTranslation", "text", "prefetchNearbyCues", "renderedText", "observeCaptionTrack", "track", "rememberRendererState", "renderer", "method", "Ljava/lang/reflect/Method;", "normalizedText", "replacementForTranslatedCue", "cueText", "renderedFragment", "translated", "refreshVisibleRenderers", "touchRendererState", "state", "invokeRenderer", "originalLength", "shortName", "Ljava/lang/reflect/Executable;", "stableId", "findCaptionTimelineBuildMethod", "Lorg/luckypray/dexkit/result/MethodData;", "hasAtLeastListFields", "Lorg/luckypray/dexkit/result/ClassData;", "count", "hasInstanceFieldTypeInHierarchy", "typeName", "findCaptionRenderTextMethods", "findCaptionOverlayUpdateMethods", "findCaptionTrackClassName", "findCaptionTrackStateMethods", "trackClassName", "NON_DECREASING_SUBTITLE_TIME_ERROR", CaptionHook.AUTO_TRANSLATE_CAPTIONS_OPTION, "MENU_ITEM_CAPTIONS_KEY", "EDITABLE_TYPE", "SPARSE_ARRAY_TYPE", "RENDERER_STATE_TTL_MS", "", "PREFETCH_WINDOW_MS", "PRIORITY_VISIBLE", "PRIORITY_UPCOMING", "PRIORITY_TIMELINE", "RendererState", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class CaptionHook implements BaseHook {
    private static final String AUTO_TRANSLATE_CAPTIONS_OPTION = "AUTO_TRANSLATE_CAPTIONS_OPTION";
    private static final String EDITABLE_TYPE = "android.text.Editable";
    private static final String MENU_ITEM_CAPTIONS_KEY = "menu_item_captions";
    private static final String NON_DECREASING_SUBTITLE_TIME_ERROR = "subtitles are not given in non-decreasing start time order";
    private static final long PREFETCH_WINDOW_MS = 10000;
    private static final int PRIORITY_TIMELINE = 2;
    private static final int PRIORITY_UPCOMING = 1;
    private static final int PRIORITY_VISIBLE = 0;
    private static final long RENDERER_STATE_TTL_MS = 60000;
    private static final String SPARSE_ARRAY_TYPE = "android.util.SparseArray";
    public static final CaptionHook INSTANCE = new CaptionHook();
    private static final String name = "CaptionHook";
    private static final int totalHooks = 4;
    private static final CaptionSession session = new CaptionSession();
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    private static final WeakHashMap<Object, RendererState> rendererStates = new WeakHashMap<>();
    private static final AtomicLong rendererSequence = new AtomicLong(0);
    private static final ThreadLocal<Boolean> applyingTranslatedText = ThreadLocal.withInitial(new Supplier() { // from class: icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda19
        @Override // java.util.function.Supplier
        public final Object get() {
            return CaptionHook.applyingTranslatedText$lambda$0();
        }
    });

    private CaptionHook() {
    }

    @Override // icu.nullptr.polyglot.youtube.BaseHook
    public String getName() {
        return name;
    }

    @Override // icu.nullptr.polyglot.youtube.BaseHook
    public int getTotalHooks() {
        return totalHooks;
    }

    static final Boolean applyingTranslatedText$lambda$0() {
        return false;
    }

    @Override // icu.nullptr.polyglot.youtube.BaseHook
    public int install(DexKitBridge dexkit) {
        Intrinsics.checkNotNullParameter(dexkit, "dexkit");
        int installed = 0;
        if (installTimelineBuildHook(dexkit)) {
            installed = 0 + 1;
        }
        if (installOverlayUpdateHooks(dexkit)) {
            installed++;
        }
        if (installRenderTextHooks(dexkit)) {
            installed++;
        }
        if (installCaptionLanguageHooks(dexkit)) {
            installed++;
        }
        LoggerKt.logI$default(getName(), "Installed " + installed + " caption hook(s)", null, 4, null);
        return installed;
    }

    public final CharSequence formatCaption(CharSequence original) {
        String translated = session.translationFor(original != null ? original.toString() : null);
        return BilingualFormatter.INSTANCE.format(original, translated);
    }

    private final boolean installTimelineBuildHook(DexKitBridge dexkit) {
        Method method;
        MethodData findCaptionTimelineBuildMethod = findCaptionTimelineBuildMethod(dexkit);
        if (findCaptionTimelineBuildMethod == null || (method = DexKitHelpersKt.toMethod(findCaptionTimelineBuildMethod)) == null) {
            LoggerKt.logW$default(getName(), "Caption timeline builder not found", null, 4, null);
            return false;
        }
        HookHelpersKt.hook(method, new XposedInterface.Hooker() { // from class: icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda14
            public final Object intercept(XposedInterface.Chain chain) {
                return CaptionHook.installTimelineBuildHook$lambda$2(chain);
            }
        });
        LoggerKt.logD$default(getName(), "Hooked caption timeline builder: " + shortName(method), null, 4, null);
        return true;
    }

    static final Object installTimelineBuildHook$lambda$2(XposedInterface.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Object result = chain.proceed();
        if (ModuleEntryKt.getModule().getConfig().getEnabled()) {
            List newCues = INSTANCE.observeTimeline(result);
            INSTANCE.requestTranslations(newCues, "timeline", 2);
            INSTANCE.logObservedCueCount(newCues.size(), "timeline");
        }
        return result;
    }

    private final boolean installRenderTextHooks(DexKitBridge dexkit) {
        Iterable findCaptionRenderTextMethods = findCaptionRenderTextMethods(dexkit);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(findCaptionRenderTextMethods, 10));
        Iterator it = findCaptionRenderTextMethods.iterator();
        while (it.hasNext()) {
            arrayList.add(DexKitHelpersKt.toMethod((MethodData) it.next()));
        }
        Collection arrayList2 = new ArrayList();
        Iterator it2 = ((List) arrayList).iterator();
        while (true) {
            boolean z = false;
            if (!it2.hasNext()) {
                break;
            }
            Object next = it2.next();
            Method method = (Method) next;
            if (View.class.isAssignableFrom(method.getDeclaringClass())) {
                CaptionHook captionHook = INSTANCE;
                Class<?> declaringClass = method.getDeclaringClass();
                Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
                if (captionHook.hasInstanceFieldAssignableTo(declaringClass, Editable.class)) {
                    z = true;
                }
            }
            if (z) {
                arrayList2.add(next);
            }
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : (List) arrayList2) {
            if (hashSet.add(INSTANCE.stableId((Method) obj))) {
                arrayList3.add(obj);
            }
        }
        ArrayList<Method> methods = arrayList3;
        int installed = 0;
        for (final Method method2 : methods) {
            HookHelpersKt.hook(method2, new XposedInterface.Hooker() { // from class: icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda7
                public final Object intercept(XposedInterface.Chain chain) {
                    return CaptionHook.installRenderTextHooks$lambda$7(method2, chain);
                }
            });
            LoggerKt.logD$default(getName(), "Hooked caption renderer: " + shortName(method2), null, 4, null);
            installed++;
        }
        if (installed == 0) {
            LoggerKt.logW$default(getName(), "Caption renderer not found", null, 4, null);
        }
        return installed > 0;
    }

    static final Object installRenderTextHooks$lambda$7(Method $method, XposedInterface.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        if (Intrinsics.areEqual((Object) applyingTranslatedText.get(), (Object) true)) {
            return chain.proceed();
        }
        if (!ModuleEntryKt.getModule().getConfig().getEnabled()) {
            return chain.proceed();
        }
        Object thisObject = chain.getThisObject();
        if (thisObject == null) {
            return chain.proceed();
        }
        if (!INSTANCE.hasInstanceFieldAssignableTo(thisObject.getClass(), Editable.class)) {
            return chain.proceed();
        }
        Object arg = chain.getArg(0);
        CharSequence original = arg instanceof CharSequence ? (CharSequence) arg : null;
        if (original == null) {
            return chain.proceed();
        }
        String normalizedOriginal = CaptionCue.INSTANCE.normalize(original.toString());
        INSTANCE.rememberRendererState(thisObject, $method, normalizedOriginal);
        if (session.observeRenderedText(original)) {
            int length = normalizedOriginal.length();
            LoggerKt.logV$default(INSTANCE.getName(), "Observed rendered caption text, length=" + length, null, 4, null);
        }
        if (!session.isFormattedRenderedText(normalizedOriginal)) {
            INSTANCE.requestTranslation(normalizedOriginal, "render", 0);
            INSTANCE.prefetchNearbyCues(normalizedOriginal);
        }
        CaptionSession.CaptionTranslation translatedCueContaining = session.translatedCueContaining(normalizedOriginal);
        if (translatedCueContaining != null) {
            CharSequence replacementForTranslatedCue = INSTANCE.replacementForTranslatedCue(translatedCueContaining.getOriginal(), normalizedOriginal, translatedCueContaining.getTranslated());
            session.rememberFormattedText(replacementForTranslatedCue);
            return chain.proceed(new CharSequence[]{replacementForTranslatedCue});
        }
        CharSequence formatted = INSTANCE.formatCaption(original);
        if (formatted == original || Intrinsics.areEqual(formatted.toString(), original.toString())) {
            return chain.proceed();
        }
        session.rememberFormattedText(formatted);
        return chain.proceed(new CharSequence[]{formatted});
    }

    private final boolean installCaptionLanguageHooks(DexKitBridge dexkit) {
        String trackClassName = findCaptionTrackClassName(dexkit);
        if (trackClassName == null) {
            LoggerKt.logW$default(getName(), "Caption track class not found", null, 4, null);
            return false;
        }
        Iterable findCaptionTrackStateMethods = findCaptionTrackStateMethods(dexkit, trackClassName);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(findCaptionTrackStateMethods, 10));
        Iterator it = findCaptionTrackStateMethods.iterator();
        while (it.hasNext()) {
            arrayList.add(DexKitHelpersKt.toMethod((MethodData) it.next()));
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : (List) arrayList) {
            if (hashSet.add(INSTANCE.stableId((Method) obj))) {
                arrayList2.add(obj);
            }
        }
        ArrayList<Method> methods = arrayList2;
        if (methods.isEmpty()) {
            LoggerKt.logW$default(getName(), "Caption language methods not found", null, 4, null);
            return false;
        }
        for (final Method method : methods) {
            HookHelpersKt.hook(method, new XposedInterface.Hooker() { // from class: icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda15
                public final Object intercept(XposedInterface.Chain chain) {
                    return CaptionHook.installCaptionLanguageHooks$lambda$11(method, chain);
                }
            });
        }
        LoggerKt.logD$default(getName(), "Hooked caption language trackers: " + methods.size(), null, 4, null);
        return true;
    }

    static final Object installCaptionLanguageHooks$lambda$11(Method $method, XposedInterface.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        INSTANCE.observeCaptionTrack(chain.getArg(0), INSTANCE.shortName($method) + " arg0");
        Object result = chain.proceed();
        return result;
    }

    private final boolean installOverlayUpdateHooks(DexKitBridge dexkit) {
        Iterable findCaptionOverlayUpdateMethods = findCaptionOverlayUpdateMethods(dexkit);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(findCaptionOverlayUpdateMethods, 10));
        Iterator it = findCaptionOverlayUpdateMethods.iterator();
        while (it.hasNext()) {
            arrayList.add(DexKitHelpersKt.toMethod((MethodData) it.next()));
        }
        Collection arrayList2 = new ArrayList();
        Iterator it2 = ((List) arrayList).iterator();
        while (true) {
            boolean z = false;
            if (!it2.hasNext()) {
                break;
            }
            Object next = it2.next();
            Method method = (Method) next;
            if (View.class.isAssignableFrom(method.getDeclaringClass())) {
                CaptionHook captionHook = INSTANCE;
                Class<?> declaringClass = method.getDeclaringClass();
                Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
                if (captionHook.hasInstanceFieldAssignableTo(declaringClass, SparseArray.class)) {
                    z = true;
                }
            }
            if (z) {
                arrayList2.add(next);
            }
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : (List) arrayList2) {
            if (hashSet.add(INSTANCE.stableId((Method) obj))) {
                arrayList3.add(obj);
            }
        }
        ArrayList<Method> methods = arrayList3;
        int installed = 0;
        for (Method method2 : methods) {
            HookHelpersKt.hook(method2, new XposedInterface.Hooker() { // from class: icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda18
                public final Object intercept(XposedInterface.Chain chain) {
                    return CaptionHook.installOverlayUpdateHooks$lambda$15(chain);
                }
            });
            LoggerKt.logD$default(getName(), "Hooked caption overlay update: " + shortName(method2), null, 4, null);
            installed++;
        }
        if (installed == 0) {
            LoggerKt.logW$default(getName(), "Caption overlay update methods not found", null, 4, null);
        }
        return installed > 0;
    }

    static final Object installOverlayUpdateHooks$lambda$15(XposedInterface.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        if (ModuleEntryKt.getModule().getConfig().getEnabled()) {
            List newCues = INSTANCE.observeCueList(chain.getArg(0));
            INSTANCE.requestTranslations(newCues, "overlay", 1);
            INSTANCE.logObservedCueCount(newCues.size(), "overlay");
        }
        return chain.proceed();
    }

    private final List<CaptionCue> observeTimeline(Object result) {
        Object obj;
        boolean z;
        boolean z2;
        if (result == null) {
            return CollectionsKt.emptyList();
        }
        Iterable instanceFieldValues = instanceFieldValues(result);
        Collection arrayList = new ArrayList();
        for (Object obj2 : instanceFieldValues) {
            if (obj2 instanceof List) {
                arrayList.add(obj2);
            }
        }
        Collection arrayList2 = new ArrayList();
        for (Object obj3 : (List) arrayList) {
            if (!((List) obj3).isEmpty()) {
                arrayList2.add(obj3);
            }
        }
        Iterable lists = (List) arrayList2;
        Iterator it = lists.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            Iterable iterable = (List) obj;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                Iterator it2 = iterable.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z2 = false;
                        break;
                    }
                    if (it2.next() instanceof CharSequence) {
                        z2 = true;
                        break;
                    }
                }
            } else {
                z2 = false;
            }
            if (z2) {
                break;
            }
        }
        List texts = (List) obj;
        if (texts == null) {
            return CollectionsKt.emptyList();
        }
        Collection arrayList3 = new ArrayList();
        for (Object obj4 : lists) {
            Iterable iterable2 = (List) obj4;
            if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
                Iterator it3 = iterable2.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        z = true;
                        break;
                    }
                    if (!(it3.next() instanceof Number)) {
                        z = false;
                        break;
                    }
                }
            } else {
                z = true;
            }
            if (z) {
                arrayList3.add(obj4);
            }
        }
        List timeLists = (List) arrayList3;
        if (timeLists.size() < 2) {
            return CollectionsKt.emptyList();
        }
        Pair<List<?>, List<?>> orderedTimelineTimeLists = orderedTimelineTimeLists((List) timeLists.get(0), (List) timeLists.get(1));
        List starts = orderedTimelineTimeLists.component1();
        List ends = orderedTimelineTimeLists.component2();
        int size = Math.min(starts.size(), Math.min(ends.size(), texts.size()));
        if (size == 0) {
            return CollectionsKt.emptyList();
        }
        ArrayList cues = new ArrayList(size);
        for (int index = 0; index < size; index++) {
            Object obj5 = texts.get(index);
            CharSequence text = obj5 instanceof CharSequence ? (CharSequence) obj5 : null;
            if (text != null) {
                String normalized = CaptionCue.INSTANCE.normalize(text.toString());
                if (!(normalized.length() == 0)) {
                    Object obj6 = starts.get(index);
                    Number number = obj6 instanceof Number ? (Number) obj6 : null;
                    long longValue = number != null ? number.longValue() : -1L;
                    Object obj7 = ends.get(index);
                    Number number2 = obj7 instanceof Number ? (Number) obj7 : null;
                    cues.add(new CaptionCue("", longValue, number2 != null ? number2.longValue() : -1L, normalized, 0, 16, null));
                }
            }
        }
        return session.observeNewCues(cues);
    }

    private final List<CaptionCue> observeCueList(Object arg) {
        Iterable list = arg instanceof List ? (List) arg : null;
        if (list == null) {
            return CollectionsKt.emptyList();
        }
        Collection arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            CaptionCue captionCue = next != null ? INSTANCE.toCaptionCue(next) : null;
            if (captionCue != null) {
                arrayList.add(captionCue);
            }
        }
        List cues = (List) arrayList;
        return session.observeNewCues(cues);
    }

    private final CaptionCue toCaptionCue(Object $this$toCaptionCue) {
        Object next;
        Long l;
        Iterable instanceFieldValues = instanceFieldValues($this$toCaptionCue);
        Collection arrayList = new ArrayList();
        for (Object obj : instanceFieldValues) {
            if (obj instanceof CharSequence) {
                arrayList.add(obj);
            }
        }
        Iterable iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList2.add(CaptionCue.INSTANCE.normalize(((CharSequence) it.next()).toString()));
        }
        Collection arrayList3 = new ArrayList();
        for (Object obj2 : (List) arrayList2) {
            if (((String) obj2).length() > 0) {
                arrayList3.add(obj2);
            }
        }
        Iterator it2 = ((List) arrayList3).iterator();
        Integer num = null;
        if (it2.hasNext()) {
            next = it2.next();
            if (it2.hasNext()) {
                int length = ((String) next).length();
                do {
                    Object next2 = it2.next();
                    int length2 = ((String) next2).length();
                    if (length < length2) {
                        next = next2;
                        length = length2;
                    }
                } while (it2.hasNext());
            }
        } else {
            next = null;
        }
        String text = (String) next;
        if (text == null) {
            return null;
        }
        Iterator<T> it3 = instanceFieldValues($this$toCaptionCue).iterator();
        while (true) {
            if (!it3.hasNext()) {
                l = null;
                break;
            }
            Object next3 = it3.next();
            l = (Long) (!(next3 instanceof Long) ? null : next3);
            if (l != null) {
                break;
            }
        }
        long longValue = l != null ? l.longValue() : -1L;
        Iterator<T> it4 = instanceFieldValues($this$toCaptionCue).iterator();
        while (true) {
            if (!it4.hasNext()) {
                break;
            }
            Object next4 = it4.next();
            Integer num2 = (Integer) (!(next4 instanceof Integer) ? null : next4);
            if (num2 != null) {
                num = num2;
                break;
            }
        }
        return new CaptionCue("", longValue, -1L, text, num != null ? num.intValue() : -1);
    }

    private final Pair<List<?>, List<?>> orderedTimelineTimeLists(List<?> first, List<?> second) {
        int comparableSize = Math.min(first.size(), second.size());
        int firstBeforeSecond = 0;
        int secondBeforeFirst = 0;
        for (int index = 0; index < comparableSize; index++) {
            Object obj = first.get(index);
            Number number = obj instanceof Number ? (Number) obj : null;
            if (number != null) {
                long a = number.longValue();
                Object obj2 = second.get(index);
                Number number2 = obj2 instanceof Number ? (Number) obj2 : null;
                if (number2 != null) {
                    long b = number2.longValue();
                    if (a <= b) {
                        firstBeforeSecond++;
                    } else {
                        secondBeforeFirst++;
                    }
                }
            }
        }
        if (firstBeforeSecond >= secondBeforeFirst) {
            return TuplesKt.to(first, second);
        }
        return TuplesKt.to(second, first);
    }

    private final /* synthetic */ <T> T firstInstanceFieldValue(Object $this$firstInstanceFieldValue) {
        for (Object obj : instanceFieldValues($this$firstInstanceFieldValue)) {
            Intrinsics.reifiedOperationMarker(2, "T");
            Object obj2 = obj;
            if (obj2 != null) {
                return obj2;
            }
        }
        return null;
    }

    private final List<Object> instanceFieldValues(Object $this$instanceFieldValues) {
        ArrayList values = new ArrayList();
        for (Class clazz = $this$instanceFieldValues.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            Iterator it = ArrayIteratorKt.iterator(clazz.getDeclaredFields());
            while (it.hasNext()) {
                Field field = (Field) it.next();
                if (!Modifier.isStatic(field.getModifiers())) {
                    Intrinsics.checkNotNull(field);
                    values.add(getValueOrNull(field, $this$instanceFieldValues));
                }
            }
        }
        return values;
    }

    private final Object getValueOrNull(Field $this$getValueOrNull, Object instance) {
        Object m10constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            $this$getValueOrNull.setAccessible(true);
            m10constructorimpl = Result.m10constructorimpl($this$getValueOrNull.get(instance));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m16isFailureimpl(m10constructorimpl)) {
            return null;
        }
        return m10constructorimpl;
    }

    private final boolean hasInstanceFieldAssignableTo(Class<?> cls, Class<?> cls2) {
        for (Class clazz = cls; clazz != null; clazz = clazz.getSuperclass()) {
            Iterator it = ArrayIteratorKt.iterator(clazz.getDeclaredFields());
            while (it.hasNext()) {
                Field field = (Field) it.next();
                if (!Modifier.isStatic(field.getModifiers()) && cls2.isAssignableFrom(field.getType())) {
                    return true;
                }
            }
        }
        return false;
    }

    private final void logObservedCueCount(int newCueCount, String source) {
        if (newCueCount <= 0) {
            return;
        }
        int total = session.observedCueCount();
        if (total <= 5 || total % 25 == 0) {
            LoggerKt.logV$default(getName(), "Observed " + newCueCount + " new caption cue(s) from " + source + ", total=" + total, null, 4, null);
        }
    }

    private final void requestTranslations(List<CaptionCue> cues, String source, int priority) {
        for (CaptionCue cue : cues) {
            requestTranslation(cue.getText(), source, priority);
        }
    }

    private final void requestTranslation(String text, final String source, int priority) {
        if (session.translationFor(text) == null && !session.isRecentlyFailed(text)) {
            final String sourceLanguage = CaptionLanguageState.INSTANCE.currentSourceLanguage();
            TranslationManager.INSTANCE.enqueue(text, "YouTube subtitle " + source, sourceLanguage, priority, new Function2() { // from class: icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CaptionHook.requestTranslation$lambda$27(source, sourceLanguage, (String) obj, (String) obj2);
                }
            }, new Function1() { // from class: icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return CaptionHook.requestTranslation$lambda$28(source, (String) obj);
                }
            });
        }
    }

    static final Unit requestTranslation$lambda$27(String $source, String $sourceLanguage, String original, String translated) {
        Intrinsics.checkNotNullParameter(original, "original");
        Intrinsics.checkNotNullParameter(translated, "translated");
        session.putTranslation(original, translated);
        LoggerKt.logV$default(INSTANCE.getName(), "Translated caption from " + $source + ", sourceLanguage=" + $sourceLanguage + ", length=" + original.length(), null, 4, null);
        INSTANCE.refreshVisibleRenderers(original, $source);
        return Unit.INSTANCE;
    }

    static final Unit requestTranslation$lambda$28(String $source, String original) {
        Intrinsics.checkNotNullParameter(original, "original");
        session.putFailure(original);
        LoggerKt.logV$default(INSTANCE.getName(), "Caption translation failed from " + $source + ", length=" + original.length(), null, 4, null);
        return Unit.INSTANCE;
    }

    private final void prefetchNearbyCues(String renderedText) {
        Long cueStartMsFor = session.cueStartMsFor(renderedText);
        if (cueStartMsFor == null) {
            return;
        }
        long anchorStartMs = cueStartMsFor.longValue();
        List nearby = session.cuesInWindow(anchorStartMs, PREFETCH_WINDOW_MS);
        if (nearby.isEmpty()) {
            return;
        }
        LoggerKt.logV$default(getName(), "Prefetching " + nearby.size() + " upcoming cue(s) after " + anchorStartMs + " ms", null, 4, null);
        for (CaptionCue cue : nearby) {
            requestTranslation(cue.getText(), "prefetch", 1);
        }
    }

    private final void observeCaptionTrack(Object track, String source) {
        if (CaptionLanguageState.INSTANCE.updateFromCaptionTrack(track, source)) {
            session.clear();
            synchronized (rendererStates) {
                rendererStates.clear();
                Unit unit = Unit.INSTANCE;
            }
            rendererSequence.set(0L);
        }
    }

    private final void rememberRendererState(Object renderer, Method method, String normalizedText) {
        Throwable th;
        if (normalizedText.length() == 0) {
            return;
        }
        synchronized (rendererStates) {
            try {
                try {
                    rendererStates.put(renderer, new RendererState(method, normalizedText, rendererSequence.incrementAndGet(), System.currentTimeMillis()));
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    private final CharSequence replacementForTranslatedCue(String cueText, String renderedFragment, String translated) {
        String normalizedCue = CaptionCue.INSTANCE.normalize(cueText);
        String normalizedFragment = CaptionCue.INSTANCE.normalize(renderedFragment);
        boolean shouldDisplayBlock = true;
        if (!(normalizedCue.length() == 0)) {
            if (!(normalizedFragment.length() == 0)) {
                if (!Intrinsics.areEqual(normalizedCue, normalizedFragment) && !StringsKt.endsWith$default(normalizedCue, normalizedFragment, false, 2, (Object) null)) {
                    shouldDisplayBlock = false;
                }
                if (shouldDisplayBlock) {
                    return BilingualFormatter.INSTANCE.format(normalizedCue, translated);
                }
                return "";
            }
        }
        return renderedFragment;
    }

    private final void refreshVisibleRenderers(String original, final String source) {
        final String normalized = CaptionCue.INSTANCE.normalize(original);
        if (normalized.length() == 0) {
            return;
        }
        mainHandler.post(new Runnable() { // from class: icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CaptionHook.refreshVisibleRenderers$lambda$37(normalized, source);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x018c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final void refreshVisibleRenderers$lambda$37(java.lang.String r22, java.lang.String r23) {
        /*
            Method dump skipped, instructions count: 566
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.youtube.CaptionHook.refreshVisibleRenderers$lambda$37(java.lang.String, java.lang.String):void");
    }

    private final void touchRendererState(Object renderer, RendererState state) {
        Throwable th;
        synchronized (rendererStates) {
            try {
                RendererState rendererState = rendererStates.get(renderer);
                if (rendererState != null && Intrinsics.areEqual(rendererState.getNormalizedText(), state.getNormalizedText())) {
                    try {
                        rendererStates.put(renderer, RendererState.copy$default(state, null, null, 0L, System.currentTimeMillis(), 7, null));
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    private final void invokeRenderer(Object renderer, Method method, CharSequence text, String source, int originalLength) {
        Object m10constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            CaptionHook captionHook = this;
            applyingTranslatedText.set(true);
            m10constructorimpl = Result.m10constructorimpl(method.invoke(renderer, text));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m17isSuccessimpl(m10constructorimpl)) {
            if (text.length() > 0) {
                LoggerKt.logV$default(INSTANCE.getName(), "Refreshed visible caption from " + source + ", length=" + originalLength, null, 4, null);
            }
        }
        Throwable m13exceptionOrNullimpl = Result.m13exceptionOrNullimpl(m10constructorimpl);
        if (m13exceptionOrNullimpl != null) {
            LoggerKt.logW(INSTANCE.getName(), "Unable to refresh visible caption", m13exceptionOrNullimpl);
        }
        applyingTranslatedText.set(false);
    }

    private final String shortName(Executable $this$shortName) {
        String name2 = $this$shortName.getDeclaringClass().getName();
        String name3 = $this$shortName.getName();
        Class<?>[] parameterTypes = $this$shortName.getParameterTypes();
        Intrinsics.checkNotNullExpressionValue(parameterTypes, "getParameterTypes(...)");
        return name2 + "#" + name3 + "(" + ArraysKt.joinToString$default(parameterTypes, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CaptionHook.shortName$lambda$43((Class) obj);
            }
        }, 31, (Object) null) + ")";
    }

    static final CharSequence shortName$lambda$43(Class it) {
        String simpleName = it.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        return simpleName;
    }

    private final String stableId(Executable $this$stableId) {
        String name2 = $this$stableId.getDeclaringClass().getName();
        String name3 = $this$stableId.getName();
        Class<?>[] parameterTypes = $this$stableId.getParameterTypes();
        Intrinsics.checkNotNullExpressionValue(parameterTypes, "getParameterTypes(...)");
        return name2 + "#" + name3 + "#" + ArraysKt.joinToString$default(parameterTypes, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CaptionHook.stableId$lambda$44((Class) obj);
            }
        }, 31, (Object) null);
    }

    static final CharSequence stableId$lambda$44(Class it) {
        String name2 = it.getName();
        Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
        return name2;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0070 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0030 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final org.luckypray.dexkit.result.MethodData findCaptionTimelineBuildMethod(org.luckypray.dexkit.DexKitBridge r17) {
        /*
            r16 = this;
            icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda16 r0 = new icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda16
            r0.<init>()
            r1 = r17
            org.luckypray.dexkit.result.MethodDataList r0 = r1.findMethod(r0)
            java.lang.Object r0 = r0.singleOrNull()
            org.luckypray.dexkit.result.MethodData r0 = (org.luckypray.dexkit.result.MethodData) r0
            if (r0 == 0) goto L88
            org.luckypray.dexkit.result.ClassData r2 = r0.getDeclaredClass()
            if (r2 != 0) goto L1c
            goto L88
        L1c:
            org.luckypray.dexkit.result.MethodDataList r3 = r2.getMethods()
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            r4 = 0
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Collection r5 = (java.util.Collection) r5
            r6 = r3
            r7 = 0
            java.util.Iterator r8 = r6.iterator()
        L30:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L74
            java.lang.Object r9 = r8.next()
            r10 = r9
            org.luckypray.dexkit.result.MethodData r10 = (org.luckypray.dexkit.result.MethodData) r10
            r11 = 0
            boolean r12 = r10.isMethod()
            if (r12 == 0) goto L6d
            int r12 = r10.getParamCount()
            if (r12 != 0) goto L6d
            java.lang.String r12 = r10.getReturnTypeName()
            java.lang.String r14 = "void"
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual(r12, r14)
            if (r12 != 0) goto L6d
            org.luckypray.dexkit.result.ClassData r12 = r10.getReturnType()
            r14 = 1
            if (r12 == 0) goto L68
            icu.nullptr.polyglot.youtube.CaptionHook r15 = icu.nullptr.polyglot.youtube.CaptionHook.INSTANCE
            r13 = 3
            boolean r12 = r15.hasAtLeastListFields(r12, r13)
            if (r12 != r14) goto L68
            r12 = r14
            goto L69
        L68:
            r12 = 0
        L69:
            if (r12 == 0) goto L6d
            r13 = r14
            goto L6e
        L6d:
            r13 = 0
        L6e:
            if (r13 == 0) goto L30
            r5.add(r9)
            goto L30
        L74:
            java.util.List r5 = (java.util.List) r5
            java.lang.Object r3 = kotlin.collections.CollectionsKt.singleOrNull(r5)
            org.luckypray.dexkit.result.MethodData r3 = (org.luckypray.dexkit.result.MethodData) r3
            if (r3 != 0) goto L87
            java.lang.Object r3 = kotlin.collections.CollectionsKt.firstOrNull(r5)
            org.luckypray.dexkit.result.MethodData r3 = (org.luckypray.dexkit.result.MethodData) r3
        L87:
            return r3
        L88:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.youtube.CaptionHook.findCaptionTimelineBuildMethod(org.luckypray.dexkit.DexKitBridge):org.luckypray.dexkit.result.MethodData");
    }

    static final Unit findCaptionTimelineBuildMethod$lambda$46(FindMethod findMethod) {
        Intrinsics.checkNotNullParameter(findMethod, "$this$findMethod");
        findMethod.matcher(new Function1() { // from class: icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CaptionHook.findCaptionTimelineBuildMethod$lambda$46$lambda$45((MethodMatcher) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit findCaptionTimelineBuildMethod$lambda$46$lambda$45(MethodMatcher matcher) {
        Intrinsics.checkNotNullParameter(matcher, "$this$matcher");
        MethodMatcher.returnType$default(matcher, "void", null, false, 6, null);
        matcher.paramTypes("java.lang.CharSequence", "long", "long");
        matcher.usingEqStrings(NON_DECREASING_SUBTITLE_TIME_ERROR);
        return Unit.INSTANCE;
    }

    private final boolean hasAtLeastListFields(ClassData $this$hasAtLeastListFields, int count) {
        int i;
        Iterable<FieldData> fields = $this$hasAtLeastListFields.getFields();
        if ((fields instanceof Collection) && ((Collection) fields).isEmpty()) {
            i = 0;
        } else {
            i = 0;
            for (FieldData fieldData : fields) {
                if (((Modifier.isStatic(fieldData.getModifiers()) || !Intrinsics.areEqual(fieldData.getTypeName(), "java.util.List")) ? null : 1) != null && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        return i >= count;
    }

    private final boolean hasInstanceFieldTypeInHierarchy(ClassData $this$hasInstanceFieldTypeInHierarchy, String typeName) {
        ClassData clazz = $this$hasInstanceFieldTypeInHierarchy;
        while (true) {
            boolean z = false;
            if (clazz == null) {
                return false;
            }
            Iterable fields = clazz.getFields();
            if (!(fields instanceof Collection) || !((Collection) fields).isEmpty()) {
                Iterator<FieldData> it = fields.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    FieldData fieldData = (FieldData) it.next();
                    if (((Modifier.isStatic(fieldData.getModifiers()) || !Intrinsics.areEqual(fieldData.getTypeName(), typeName)) ? null : 1) != null) {
                        z = true;
                        break;
                    }
                }
            }
            if (z) {
                return true;
            }
            clazz = clazz.getSuperClass();
        }
    }

    private final List<MethodData> findCaptionRenderTextMethods(DexKitBridge $this$findCaptionRenderTextMethods) {
        Iterable findMethod = $this$findCaptionRenderTextMethods.findMethod(new Function1() { // from class: icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CaptionHook.findCaptionRenderTextMethods$lambda$51((FindMethod) obj);
            }
        });
        Collection arrayList = new ArrayList();
        for (MethodData methodData : findMethod) {
            ClassData declaredClass = methodData.getDeclaredClass();
            boolean z = false;
            if (declaredClass != null && INSTANCE.hasInstanceFieldTypeInHierarchy(declaredClass, EDITABLE_TYPE)) {
                z = true;
            }
            if (z) {
                arrayList.add(methodData);
            }
        }
        return (List) arrayList;
    }

    static final Unit findCaptionRenderTextMethods$lambda$51(FindMethod findMethod) {
        Intrinsics.checkNotNullParameter(findMethod, "$this$findMethod");
        findMethod.matcher(new Function1() { // from class: icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CaptionHook.findCaptionRenderTextMethods$lambda$51$lambda$50((MethodMatcher) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit findCaptionRenderTextMethods$lambda$51$lambda$50(MethodMatcher matcher) {
        Intrinsics.checkNotNullParameter(matcher, "$this$matcher");
        MethodMatcher.returnType$default(matcher, "void", null, false, 6, null);
        matcher.paramTypes("java.lang.CharSequence");
        return Unit.INSTANCE;
    }

    private final List<MethodData> findCaptionOverlayUpdateMethods(DexKitBridge $this$findCaptionOverlayUpdateMethods) {
        Iterable findMethod = $this$findCaptionOverlayUpdateMethods.findMethod(new Function1() { // from class: icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CaptionHook.findCaptionOverlayUpdateMethods$lambda$54((FindMethod) obj);
            }
        });
        Collection arrayList = new ArrayList();
        for (MethodData methodData : findMethod) {
            ClassData declaredClass = methodData.getDeclaredClass();
            boolean z = false;
            if (declaredClass != null && INSTANCE.hasInstanceFieldTypeInHierarchy(declaredClass, SPARSE_ARRAY_TYPE)) {
                z = true;
            }
            if (z) {
                arrayList.add(methodData);
            }
        }
        return (List) arrayList;
    }

    static final Unit findCaptionOverlayUpdateMethods$lambda$54(FindMethod findMethod) {
        Intrinsics.checkNotNullParameter(findMethod, "$this$findMethod");
        findMethod.matcher(new Function1() { // from class: icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CaptionHook.findCaptionOverlayUpdateMethods$lambda$54$lambda$53((MethodMatcher) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit findCaptionOverlayUpdateMethods$lambda$54$lambda$53(MethodMatcher matcher) {
        Intrinsics.checkNotNullParameter(matcher, "$this$matcher");
        MethodMatcher.returnType$default(matcher, "void", null, false, 6, null);
        matcher.paramTypes("java.util.List");
        return Unit.INSTANCE;
    }

    static final Unit findCaptionTrackClassName$lambda$57(FindMethod findMethod) {
        Intrinsics.checkNotNullParameter(findMethod, "$this$findMethod");
        findMethod.matcher(new Function1() { // from class: icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CaptionHook.findCaptionTrackClassName$lambda$57$lambda$56((MethodMatcher) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit findCaptionTrackClassName$lambda$57$lambda$56(MethodMatcher matcher) {
        Intrinsics.checkNotNullParameter(matcher, "$this$matcher");
        MethodMatcher.returnType$default(matcher, "boolean", null, false, 6, null);
        matcher.usingEqStrings(AUTO_TRANSLATE_CAPTIONS_OPTION);
        return Unit.INSTANCE;
    }

    static final boolean findCaptionTrackClassName$lambda$58(MethodData method) {
        Intrinsics.checkNotNullParameter(method, "method");
        return method.isMethod() && method.getParamCount() == 0;
    }

    private final String findCaptionTrackClassName(DexKitBridge $this$findCaptionTrackClassName) {
        Method method;
        Class<?> declaringClass;
        MethodData singleOrNull = $this$findCaptionTrackClassName.findMethod(new Function1() { // from class: icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CaptionHook.findCaptionTrackClassName$lambda$57((FindMethod) obj);
            }
        }).singleOrNull(new Function1() { // from class: icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CaptionHook.findCaptionTrackClassName$lambda$58((MethodData) obj));
            }
        });
        if (singleOrNull == null || (method = DexKitHelpersKt.toMethod(singleOrNull)) == null || (declaringClass = method.getDeclaringClass()) == null) {
            return null;
        }
        return declaringClass.getName();
    }

    private final List<MethodData> findCaptionTrackStateMethods(DexKitBridge $this$findCaptionTrackStateMethods, final String trackClassName) {
        Iterable findMethod = $this$findCaptionTrackStateMethods.findMethod(new Function1() { // from class: icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CaptionHook.findCaptionTrackStateMethods$lambda$60(trackClassName, (FindMethod) obj);
            }
        });
        Collection arrayList = new ArrayList();
        for (MethodData methodData : findMethod) {
            MethodData methodData2 = methodData;
            if (methodData2.isMethod() && !Modifier.isAbstract(methodData2.getModifiers())) {
                arrayList.add(methodData);
            }
        }
        return (List) arrayList;
    }

    static final Unit findCaptionTrackStateMethods$lambda$60(final String $trackClassName, FindMethod findMethod) {
        Intrinsics.checkNotNullParameter(findMethod, "$this$findMethod");
        findMethod.matcher(new Function1() { // from class: icu.nullptr.polyglot.youtube.CaptionHook$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CaptionHook.findCaptionTrackStateMethods$lambda$60$lambda$59($trackClassName, (MethodMatcher) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit findCaptionTrackStateMethods$lambda$60$lambda$59(String $trackClassName, MethodMatcher matcher) {
        Intrinsics.checkNotNullParameter(matcher, "$this$matcher");
        MethodMatcher.returnType$default(matcher, "void", null, false, 6, null);
        matcher.paramTypes($trackClassName);
        matcher.usingEqStrings(MENU_ITEM_CAPTIONS_KEY);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: CaptionHook.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u001d"}, d2 = {"Licu/nullptr/polyglot/youtube/CaptionHook$RendererState;", "", "method", "Ljava/lang/reflect/Method;", "normalizedText", "", "sequence", "", "updatedAtMs", "<init>", "(Ljava/lang/reflect/Method;Ljava/lang/String;JJ)V", "getMethod", "()Ljava/lang/reflect/Method;", "getNormalizedText", "()Ljava/lang/String;", "getSequence", "()J", "getUpdatedAtMs", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static final /* data */ class RendererState {
        private final Method method;
        private final String normalizedText;
        private final long sequence;
        private final long updatedAtMs;

        public static /* synthetic */ RendererState copy$default(RendererState rendererState, Method method, String str, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                method = rendererState.method;
            }
            if ((i & 2) != 0) {
                str = rendererState.normalizedText;
            }
            if ((i & 4) != 0) {
                j = rendererState.sequence;
            }
            if ((i & 8) != 0) {
                j2 = rendererState.updatedAtMs;
            }
            long j3 = j2;
            return rendererState.copy(method, str, j, j3);
        }

        /* renamed from: component1, reason: from getter */
        public final Method getMethod() {
            return this.method;
        }

        /* renamed from: component2, reason: from getter */
        public final String getNormalizedText() {
            return this.normalizedText;
        }

        /* renamed from: component3, reason: from getter */
        public final long getSequence() {
            return this.sequence;
        }

        /* renamed from: component4, reason: from getter */
        public final long getUpdatedAtMs() {
            return this.updatedAtMs;
        }

        public final RendererState copy(Method method, String normalizedText, long sequence, long updatedAtMs) {
            Intrinsics.checkNotNullParameter(method, "method");
            Intrinsics.checkNotNullParameter(normalizedText, "normalizedText");
            return new RendererState(method, normalizedText, sequence, updatedAtMs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RendererState)) {
                return false;
            }
            RendererState rendererState = (RendererState) other;
            return Intrinsics.areEqual(this.method, rendererState.method) && Intrinsics.areEqual(this.normalizedText, rendererState.normalizedText) && this.sequence == rendererState.sequence && this.updatedAtMs == rendererState.updatedAtMs;
        }

        public int hashCode() {
            return (((((this.method.hashCode() * 31) + this.normalizedText.hashCode()) * 31) + Long.hashCode(this.sequence)) * 31) + Long.hashCode(this.updatedAtMs);
        }

        public String toString() {
            return "RendererState(method=" + this.method + ", normalizedText=" + this.normalizedText + ", sequence=" + this.sequence + ", updatedAtMs=" + this.updatedAtMs + ")";
        }

        public RendererState(Method method, String normalizedText, long sequence, long updatedAtMs) {
            Intrinsics.checkNotNullParameter(method, "method");
            Intrinsics.checkNotNullParameter(normalizedText, "normalizedText");
            this.method = method;
            this.normalizedText = normalizedText;
            this.sequence = sequence;
            this.updatedAtMs = updatedAtMs;
        }

        public final Method getMethod() {
            return this.method;
        }

        public final String getNormalizedText() {
            return this.normalizedText;
        }

        public final long getSequence() {
            return this.sequence;
        }

        public final long getUpdatedAtMs() {
            return this.updatedAtMs;
        }
    }
}
