package icu.nullptr.polyglot.youtube.settings;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import icu.nullptr.polyglot.ModuleEntryKt;
import icu.nullptr.polyglot.util.LoggerKt;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* compiled from: HostPreferenceAdapter.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u001c\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0003\b\u0000\u0018\u0000 Y2\u00020\u0001:\u0001YB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011JF\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001eJT\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u001e2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020#0\"J(\u0010$\u001a\u0004\u0018\u00010\u00012\u0006\u0010%\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010&\u001a\u0004\u0018\u00010\u00012\u0006\u0010%\u001a\u00020\u0001J\u0010\u0010'\u001a\u0004\u0018\u00010\u00142\u0006\u0010(\u001a\u00020\u0001J\u0016\u0010)\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u00012\u0006\u0010*\u001a\u00020\u0001J\"\u0010+\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020\u00012\u0006\u0010-\u001a\u00020\u00012\n\u0010.\u001a\u0006\u0012\u0002\b\u00030\bJ \u0010/\u001a\u0004\u0018\u00010\u00012\u0006\u0010,\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\u000fJ\"\u00100\u001a\u00020#2\u0006\u0010-\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\n\u0010.\u001a\u0006\u0012\u0002\b\u00030\bJ\u0016\u00101\u001a\u00020#2\u0006\u0010-\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u0019J*\u00102\u001a\u00020#*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\n\u0010.\u001a\u0006\u0012\u0002\b\u00030\b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002J\u0016\u00103\u001a\u0004\u0018\u000104*\u00020\u001c2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0013\u00105\u001a\u0004\u0018\u000106*\u00020\u0014H\u0002¢\u0006\u0002\u00107J\u001b\u00108\u001a\u0004\u0018\u000106*\u00020\u00142\u0006\u00109\u001a\u000206H\u0002¢\u0006\u0002\u0010:J\f\u0010;\u001a\u000206*\u000206H\u0002J\u0012\u0010<\u001a\u0004\u0018\u00010\f*\u0006\u0012\u0002\b\u00030\bH\u0002J(\u0010=\u001a\u00020#*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\n\u0010.\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010>\u001a\u00020\u0017H\u0002J\u000e\u0010?\u001a\u0004\u0018\u00010\u0001*\u00020\u0001H\u0002J\u0016\u0010$\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u000e\u0010@\u001a\u0004\u0018\u00010\u0001*\u00020\u0001H\u0002J\u0014\u0010A\u001a\u00020\u001e*\u00020\u00012\u0006\u0010*\u001a\u00020\u0001H\u0002J\u000e\u0010B\u001a\u0004\u0018\u00010\u0014*\u00020\u0001H\u0002J\u0014\u0010C\u001a\u00020#*\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0014\u0010D\u001a\u00020#*\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0014\u0010E\u001a\u00020#*\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J.\u0010F\u001a\u00020#*\u00020\u00012\n\u0010.\u001a\u0006\u0012\u0002\b\u00030\b2\u0014\u0010G\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u001e0\"H\u0002J\u0014\u0010H\u001a\u00020#*\u00020\u00012\u0006\u0010I\u001a\u00020\u0017H\u0002J\u0014\u0010J\u001a\u00020#*\u00020\u00012\u0006\u0010I\u001a\u00020\u0019H\u0002J.\u0010K\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00172\n\u0010.\u001a\u0006\u0012\u0002\b\u00030\b2\n\u0010L\u001a\u0006\u0012\u0002\b\u00030\bH\u0002J\u001a\u0010M\u001a\u0004\u0018\u00010\t*\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u001a\u0010N\u001a\u0004\u0018\u00010\t*\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J(\u0010O\u001a\u00020#*\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\n\u0010P\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010 \u001a\u00020\u001eH\u0002J\u001a\u0010Q\u001a\u0004\u0018\u00010\f*\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0018\u0010R\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u001e0S*\u00020\u0001H\u0002J \u0010T\u001a\u00020\u001e*\u00020\u00012\u0006\u0010U\u001a\u00020\u00012\n\u0010.\u001a\u0006\u0012\u0002\b\u00030\bH\u0002J \u0010V\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010W*\u00020\u00012\n\u0010.\u001a\u0006\u0012\u0002\b\u00030\bH\u0002J\u0014\u0010X\u001a\u00020\u001e*\u00020\u00012\u0006\u0010I\u001a\u00020\u0017H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0004\u0012\u00020\f0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0004\u0012\u00020\f0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006Z"}, d2 = {"Licu/nullptr/polyglot/youtube/settings/HostPreferenceAdapter;", "", "methods", "Licu/nullptr/polyglot/youtube/settings/PreferenceMethods;", "<init>", "(Licu/nullptr/polyglot/youtube/settings/PreferenceMethods;)V", "preferenceOrderFields", "Ljava/util/WeakHashMap;", "Ljava/lang/Class;", "Ljava/lang/reflect/Field;", "preferenceLayoutFields", "preferenceIconSetters", "Ljava/lang/reflect/Method;", "switchCheckedSetters", "classesFor", "Licu/nullptr/polyglot/youtube/settings/HostPreferenceClasses;", "classLoader", "Ljava/lang/ClassLoader;", "createPreference", "context", "Landroid/content/Context;", "classes", "key", "", "title", "", "summary", "icon", "Licu/nullptr/polyglot/youtube/settings/SettingsIcon;", "useIconLayout", "", "createSwitchPreference", "checked", "onChanged", "Lkotlin/Function1;", "", "createPreferenceScreen", "fragment", "preferenceScreenOrNull", "contextOrNull", "preferenceScreen", "showPreferenceScreen", "screen", "addPreference", "group", HostPreferenceAdapter.PREFERENCE_DEFAULT_LAYOUT, "preferenceClass", "findPreferenceWithKey", "prepareOrderForTop", "setSummary", "setPreferenceIcon", "loadDrawable", "Landroid/graphics/drawable/Drawable;", "preferenceIconTint", "", "(Landroid/content/Context;)Ljava/lang/Integer;", "resolveThemeColor", "attribute", "(Landroid/content/Context;I)Ljava/lang/Integer;", "withOpaqueAlpha", "findIconSetter", "setPreferenceLayout", "layoutName", "readPreferenceScreenOrNull", "preferenceManagerOrNull", "switchPreferenceScreen", "readContextOrNull", "setPreferenceKey", "setPreferenceTitle", "setPreferenceSummary", "setPreferenceChangeListener", "onChange", "setFirstStringField", "value", "setFirstEmptyCharSequenceField", "findPreferenceByString", "preferenceGroupClass", "findOrderField", "findLayoutResourceField", "setSwitchChecked", "switchPreferenceClass", "findSwitchCheckedSetter", "booleanFieldValues", "", "appendPreference", "entry", "preferenceChildren", "", "hasStringFieldValue", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class HostPreferenceAdapter {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final int OPAQUE_ALPHA_MASK = -16777216;

    @Deprecated
    public static final String PREFERENCE_DEFAULT_LAYOUT = "preference";

    @Deprecated
    public static final String PREFERENCE_WITH_ICON_LAYOUT = "preference_with_icon";

    @Deprecated
    public static final String TAG = "HostPreferenceAdapter";
    private final PreferenceMethods methods;
    private final WeakHashMap<Class<?>, Method> preferenceIconSetters;
    private final WeakHashMap<Class<?>, Field> preferenceLayoutFields;
    private final WeakHashMap<Class<?>, Field> preferenceOrderFields;
    private final WeakHashMap<Class<?>, Method> switchCheckedSetters;

    public HostPreferenceAdapter(PreferenceMethods methods) {
        Intrinsics.checkNotNullParameter(methods, "methods");
        this.methods = methods;
        this.preferenceOrderFields = new WeakHashMap<>();
        this.preferenceLayoutFields = new WeakHashMap<>();
        this.preferenceIconSetters = new WeakHashMap<>();
        this.switchCheckedSetters = new WeakHashMap<>();
    }

    public final HostPreferenceClasses classesFor(ClassLoader classLoader) {
        Intrinsics.checkNotNullParameter(classLoader, "classLoader");
        Class<?> cls = Class.forName(SettingsConstantsKt.PREFERENCE_CLASS_NAME, false, classLoader);
        Intrinsics.checkNotNullExpressionValue(cls, "forName(...)");
        Class<?> cls2 = Class.forName(SettingsConstantsKt.PREFERENCE_GROUP_CLASS_NAME, false, classLoader);
        Intrinsics.checkNotNullExpressionValue(cls2, "forName(...)");
        Class<?> cls3 = Class.forName(SettingsConstantsKt.SWITCH_PREFERENCE_CLASS_NAME, false, classLoader);
        Intrinsics.checkNotNullExpressionValue(cls3, "forName(...)");
        return new HostPreferenceClasses(classLoader, cls, cls2, cls3);
    }

    public static /* synthetic */ Object createPreference$default(HostPreferenceAdapter hostPreferenceAdapter, Context context, HostPreferenceClasses hostPreferenceClasses, String str, CharSequence charSequence, CharSequence charSequence2, SettingsIcon settingsIcon, boolean z, int i, Object obj) {
        SettingsIcon settingsIcon2;
        boolean z2;
        if ((i & 32) == 0) {
            settingsIcon2 = settingsIcon;
        } else {
            settingsIcon2 = null;
        }
        if ((i & 64) == 0) {
            z2 = z;
        } else {
            z2 = false;
        }
        return hostPreferenceAdapter.createPreference(context, hostPreferenceClasses, str, charSequence, charSequence2, settingsIcon2, z2);
    }

    public final Object createPreference(Context context, HostPreferenceClasses classes, String key, CharSequence title, CharSequence summary, SettingsIcon icon, boolean useIconLayout) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(classes, "classes");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(title, "title");
        Object newInstance = classes.getPreference().getConstructor(Context.class).newInstance(context);
        Intrinsics.checkNotNull(newInstance);
        setPreferenceKey(newInstance, key);
        setPreferenceTitle(newInstance, title);
        if (summary != null) {
            setPreferenceSummary(newInstance, summary);
        }
        setPreferenceIcon(newInstance, context, classes.getPreference(), icon);
        if (useIconLayout) {
            setPreferenceLayout(newInstance, context, classes.getPreference(), PREFERENCE_WITH_ICON_LAYOUT);
        }
        Intrinsics.checkNotNullExpressionValue(newInstance, "apply(...)");
        return newInstance;
    }

    public final Object createSwitchPreference(Context context, HostPreferenceClasses classes, String key, CharSequence title, SettingsIcon icon, CharSequence summary, boolean checked, final Function1<? super Boolean, Unit> onChanged) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(classes, "classes");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(summary, "summary");
        Intrinsics.checkNotNullParameter(onChanged, "onChanged");
        Object newInstance = classes.getSwitchPreference().getConstructor(Context.class).newInstance(context);
        Intrinsics.checkNotNull(newInstance);
        setPreferenceKey(newInstance, key);
        setPreferenceTitle(newInstance, title);
        setPreferenceIcon(newInstance, context, classes.getPreference(), icon);
        setPreferenceSummary(newInstance, summary);
        setSwitchChecked(newInstance, context, classes.getSwitchPreference(), checked);
        setPreferenceChangeListener(newInstance, classes.getPreference(), new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.HostPreferenceAdapter$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(HostPreferenceAdapter.createSwitchPreference$lambda$2$lambda$1(Function1.this, obj));
            }
        });
        Intrinsics.checkNotNullExpressionValue(newInstance, "apply(...)");
        return newInstance;
    }

    static final boolean createSwitchPreference$lambda$2$lambda$1(Function1 $onChanged, Object value) {
        Boolean bool = value instanceof Boolean ? (Boolean) value : null;
        if (bool == null) {
            return false;
        }
        boolean enabled = bool.booleanValue();
        $onChanged.invoke(Boolean.valueOf(enabled));
        return true;
    }

    public final Object createPreferenceScreen(Object fragment, Context context, String key, CharSequence title) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(title, "title");
        Object createPreferenceScreen = createPreferenceScreen(fragment, context);
        if (createPreferenceScreen == null) {
            return null;
        }
        setPreferenceKey(createPreferenceScreen, key);
        setPreferenceTitle(createPreferenceScreen, title);
        return createPreferenceScreen;
    }

    public final Object preferenceScreenOrNull(Object fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        return readPreferenceScreenOrNull(fragment);
    }

    public final Context contextOrNull(Object preferenceScreen) {
        Intrinsics.checkNotNullParameter(preferenceScreen, "preferenceScreen");
        return readContextOrNull(preferenceScreen);
    }

    public final boolean showPreferenceScreen(Object fragment, Object screen) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(screen, "screen");
        return switchPreferenceScreen(fragment, screen);
    }

    public final boolean addPreference(Object group, Object preference, Class<?> preferenceClass) {
        Intrinsics.checkNotNullParameter(group, "group");
        Intrinsics.checkNotNullParameter(preference, "preference");
        Intrinsics.checkNotNullParameter(preferenceClass, "preferenceClass");
        return appendPreference(group, preference, preferenceClass);
    }

    public final Object findPreferenceWithKey(Object group, String key, HostPreferenceClasses classes) {
        Intrinsics.checkNotNullParameter(group, "group");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(classes, "classes");
        return findPreferenceByString(group, key, classes.getPreference(), classes.getPreferenceGroup());
    }

    public final void prepareOrderForTop(Object preference, Context context, Class<?> preferenceClass) {
        Intrinsics.checkNotNullParameter(preference, "preference");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(preferenceClass, "preferenceClass");
        Field orderField = this.preferenceOrderFields.get(preferenceClass);
        if (orderField == null) {
            orderField = findOrderField(preferenceClass, context);
            if (orderField != null) {
                this.preferenceOrderFields.put(preferenceClass, orderField);
            } else {
                orderField = null;
            }
            if (orderField == null) {
                return;
            }
        }
        orderField.setAccessible(true);
        orderField.setInt(preference, -1);
    }

    public final void setSummary(Object preference, CharSequence summary) {
        Intrinsics.checkNotNullParameter(preference, "preference");
        Intrinsics.checkNotNullParameter(summary, "summary");
        setPreferenceSummary(preference, summary);
    }

    private final void setPreferenceIcon(Object $this$setPreferenceIcon, Context context, Class<?> cls, SettingsIcon icon) {
        Object m10constructorimpl;
        if (icon == null) {
            return;
        }
        Drawable drawable = loadDrawable(icon, context);
        if (drawable == null) {
            LoggerKt.logW$default(TAG, "Unable to resolve settings icon for " + icon.name(), null, 4, null);
            return;
        }
        Method setter = this.preferenceIconSetters.get(cls);
        if (setter == null) {
            setter = findIconSetter(cls);
            if (setter != null) {
                this.preferenceIconSetters.put(cls, setter);
            } else {
                setter = null;
            }
        }
        if (setter == null) {
            LoggerKt.logW$default(TAG, "Unable to find preference icon setter", null, 4, null);
            return;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(setter.invoke($this$setPreferenceIcon, drawable));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m13exceptionOrNullimpl = Result.m13exceptionOrNullimpl(m10constructorimpl);
        if (m13exceptionOrNullimpl != null) {
            LoggerKt.logW(TAG, "Unable to call preference icon setter", m13exceptionOrNullimpl);
        }
    }

    private final Drawable loadDrawable(SettingsIcon $this$loadDrawable, Context context) {
        Object m10constructorimpl;
        Drawable drawable;
        try {
            Result.Companion companion = Result.INSTANCE;
            Drawable drawable2 = ModuleEntryKt.getModule().getRes().getDrawable($this$loadDrawable.getDrawableRes(), context.getTheme());
            if (drawable2 == null || (drawable = drawable2.mutate()) == null) {
                drawable = null;
            } else {
                Integer preferenceIconTint = preferenceIconTint(context);
                if (preferenceIconTint != null) {
                    drawable.setTint(withOpaqueAlpha(preferenceIconTint.intValue()));
                }
            }
            m10constructorimpl = Result.m10constructorimpl(drawable);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        return (Drawable) (Result.m16isFailureimpl(m10constructorimpl) ? null : m10constructorimpl);
    }

    private final Integer preferenceIconTint(Context $this$preferenceIconTint) {
        Integer resolveThemeColor = resolveThemeColor($this$preferenceIconTint, R.attr.textColorPrimary);
        if (resolveThemeColor != null) {
            return resolveThemeColor;
        }
        Integer resolveThemeColor2 = resolveThemeColor($this$preferenceIconTint, R.attr.colorControlNormal);
        return resolveThemeColor2 == null ? resolveThemeColor($this$preferenceIconTint, R.attr.textColorSecondary) : resolveThemeColor2;
    }

    private final Integer resolveThemeColor(Context $this$resolveThemeColor, int attribute) {
        Object m10constructorimpl;
        TypedValue value = new TypedValue();
        if (!$this$resolveThemeColor.getTheme().resolveAttribute(attribute, value, true)) {
            return null;
        }
        int i = value.type;
        if (28 <= i && i < 32) {
            return Integer.valueOf(value.data);
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(value.resourceId != 0 ? Integer.valueOf($this$resolveThemeColor.getResources().getColorStateList(value.resourceId, $this$resolveThemeColor.getTheme()).getDefaultColor()) : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        return (Integer) (Result.m16isFailureimpl(m10constructorimpl) ? null : m10constructorimpl);
    }

    private final int withOpaqueAlpha(int $this$withOpaqueAlpha) {
        return (-16777216) | $this$withOpaqueAlpha;
    }

    private final Method findIconSetter(Class<?> cls) {
        Object obj;
        Iterator<Method> it = ReflectionSupportKt.methodsInHierarchy(cls).iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            Method method = (Method) obj;
            boolean z = false;
            if (!Modifier.isStatic(method.getModifiers()) && Intrinsics.areEqual(method.getReturnType(), Void.TYPE) && Arrays.equals(method.getParameterTypes(), new Class[]{Drawable.class})) {
                z = true;
            }
        }
        Method method2 = (Method) obj;
        if (method2 == null) {
            return null;
        }
        method2.setAccessible(true);
        return method2;
    }

    private final void setPreferenceLayout(Object $this$setPreferenceLayout, Context context, Class<?> cls, String layoutName) {
        Object m10constructorimpl;
        int layoutId = ReflectionSupportKt.resourceId(context, layoutName, "layout");
        if (layoutId == 0) {
            LoggerKt.logW$default(TAG, "Unable to resolve preference layout " + layoutName, null, 4, null);
            return;
        }
        Field layoutField = this.preferenceLayoutFields.get(cls);
        if (layoutField == null) {
            layoutField = findLayoutResourceField(cls, context);
            if (layoutField != null) {
                this.preferenceLayoutFields.put(cls, layoutField);
            } else {
                layoutField = null;
            }
        }
        if (layoutField == null) {
            LoggerKt.logW$default(TAG, "Unable to find preference layout field", null, 4, null);
            return;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            layoutField.setAccessible(true);
            layoutField.setInt($this$setPreferenceLayout, layoutId);
            m10constructorimpl = Result.m10constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m13exceptionOrNullimpl = Result.m13exceptionOrNullimpl(m10constructorimpl);
        if (m13exceptionOrNullimpl != null) {
            LoggerKt.logW(TAG, "Unable to set preference layout", m13exceptionOrNullimpl);
        }
    }

    private final Object readPreferenceScreenOrNull(Object $this$readPreferenceScreenOrNull) {
        Object obj;
        Object m10constructorimpl;
        Iterator<Method> it = ReflectionSupportKt.methodsInHierarchy($this$readPreferenceScreenOrNull.getClass()).iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                Method method = (Method) obj;
                if (((method.getParameterCount() == 0 && Intrinsics.areEqual(method.getReturnType().getName(), SettingsConstantsKt.PREFERENCE_SCREEN_CLASS_NAME)) ? 1 : null) != null) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        Method method2 = (Method) obj;
        if (method2 == null) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            method2.setAccessible(true);
            m10constructorimpl = Result.m10constructorimpl(method2.invoke($this$readPreferenceScreenOrNull, new Object[0]));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m16isFailureimpl(m10constructorimpl)) {
            return null;
        }
        return m10constructorimpl;
    }

    private final Object createPreferenceScreen(Object $this$createPreferenceScreen, Context context) {
        Object obj;
        Object m10constructorimpl;
        Object manager = preferenceManagerOrNull($this$createPreferenceScreen);
        if (manager == null) {
            return null;
        }
        Iterator<Method> it = ReflectionSupportKt.methodsInHierarchy(manager.getClass()).iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                Method method = (Method) obj;
                boolean z = false;
                if (method.getParameterTypes().length == 1 && method.getParameterTypes()[0].isAssignableFrom(Context.class) && Intrinsics.areEqual(method.getReturnType().getName(), SettingsConstantsKt.PREFERENCE_SCREEN_CLASS_NAME)) {
                    z = true;
                }
            } else {
                obj = null;
                break;
            }
        }
        Method method2 = (Method) obj;
        if (method2 == null) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            method2.setAccessible(true);
            m10constructorimpl = Result.m10constructorimpl(method2.invoke(manager, context));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m13exceptionOrNullimpl = Result.m13exceptionOrNullimpl(m10constructorimpl);
        if (m13exceptionOrNullimpl != null) {
            LoggerKt.logW(TAG, "Unable to call host PreferenceScreen factory", m13exceptionOrNullimpl);
        }
        if (Result.m16isFailureimpl(m10constructorimpl)) {
            return null;
        }
        return m10constructorimpl;
    }

    private final Object preferenceManagerOrNull(Object $this$preferenceManagerOrNull) {
        Object m10constructorimpl;
        Object obj;
        for (Field field : SequencesKt.filter(ReflectionSupportKt.fieldsInHierarchy($this$preferenceManagerOrNull.getClass()), new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.HostPreferenceAdapter$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return Boolean.valueOf(HostPreferenceAdapter.preferenceManagerOrNull$lambda$22((Field) obj2));
            }
        })) {
            try {
                Result.Companion companion = Result.INSTANCE;
                int i = 1;
                field.setAccessible(true);
                Object obj2 = field.get($this$preferenceManagerOrNull);
                if (obj2 == null) {
                    obj = null;
                } else {
                    Iterator<Method> it = ReflectionSupportKt.methodsInHierarchy(obj2.getClass()).iterator();
                    while (true) {
                        boolean z = false;
                        if (it.hasNext()) {
                            Method method = (Method) it.next();
                            if (method.getParameterTypes().length == i && method.getParameterTypes()[0].isAssignableFrom(Context.class) && Intrinsics.areEqual(method.getReturnType().getName(), SettingsConstantsKt.PREFERENCE_SCREEN_CLASS_NAME)) {
                                z = true;
                            }
                            i = 1;
                            if (z) {
                                break;
                            }
                        } else {
                            i = 0;
                            break;
                        }
                    }
                    obj = i != 0 ? obj2 : null;
                }
                m10constructorimpl = Result.m10constructorimpl(obj);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
            }
            Object obj3 = Result.m16isFailureimpl(m10constructorimpl) ? null : m10constructorimpl;
            if (obj3 != null) {
                return obj3;
            }
        }
        return null;
    }

    static final boolean preferenceManagerOrNull$lambda$22(Field it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return !Modifier.isStatic(it.getModifiers());
    }

    private final boolean switchPreferenceScreen(Object $this$switchPreferenceScreen, Object screen) {
        Object obj;
        Object m10constructorimpl;
        Iterator<Method> it = ReflectionSupportKt.methodsInHierarchy($this$switchPreferenceScreen.getClass()).iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                Method method = (Method) obj;
                if (((Intrinsics.areEqual(method.getReturnType(), Void.TYPE) && method.getParameterTypes().length == 1 && Intrinsics.areEqual(method.getParameterTypes()[0].getName(), SettingsConstantsKt.PREFERENCE_SCREEN_CLASS_NAME)) ? 1 : null) != null) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        Method method2 = (Method) obj;
        if (method2 == null) {
            return false;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            method2.setAccessible(true);
            method2.invoke($this$switchPreferenceScreen, screen);
            m10constructorimpl = Result.m10constructorimpl(true);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m13exceptionOrNullimpl = Result.m13exceptionOrNullimpl(m10constructorimpl);
        if (m13exceptionOrNullimpl != null) {
            LoggerKt.logW(TAG, "Unable to switch host PreferenceScreen", m13exceptionOrNullimpl);
        }
        if (Result.m16isFailureimpl(m10constructorimpl)) {
            m10constructorimpl = false;
        }
        return ((Boolean) m10constructorimpl).booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Context readContextOrNull(Object obj) {
        Context context;
        Context context2;
        Iterator it = SequencesKt.filter(ReflectionSupportKt.fieldsInHierarchy(obj.getClass()), new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.HostPreferenceAdapter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return Boolean.valueOf(HostPreferenceAdapter.readContextOrNull$lambda$30((Field) obj2));
            }
        }).iterator();
        do {
            if (!it.hasNext()) {
                break;
            }
            Field field = (Field) it.next();
            try {
                Result.Companion companion = Result.INSTANCE;
                field.setAccessible(true);
                Object obj2 = field.get(obj);
                context2 = Result.m10constructorimpl(obj2 instanceof Context ? (Context) obj2 : null);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                context2 = Result.m10constructorimpl(ResultKt.createFailure(th));
            }
            context = Result.m16isFailureimpl(context2) ? null : context2;
        } while (context == null);
        return context;
    }

    static final boolean readContextOrNull$lambda$30(Field it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return !Modifier.isStatic(it.getModifiers()) && Context.class.isAssignableFrom(it.getType());
    }

    private final void setPreferenceKey(Object $this$setPreferenceKey, String key) {
        Method keySetter = this.methods.getKeySetter();
        if (keySetter != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                keySetter.invoke($this$setPreferenceKey, key);
                return;
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Object m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
                Throwable m13exceptionOrNullimpl = Result.m13exceptionOrNullimpl(m10constructorimpl);
                if (m13exceptionOrNullimpl != null) {
                    LoggerKt.logW(TAG, "Unable to call preference key setter", m13exceptionOrNullimpl);
                }
                Result.m9boximpl(m10constructorimpl);
            }
        }
        setFirstStringField($this$setPreferenceKey, key);
    }

    private final void setPreferenceTitle(Object $this$setPreferenceTitle, CharSequence title) {
        Method titleSetter = this.methods.getTitleSetter();
        if (titleSetter != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                titleSetter.invoke($this$setPreferenceTitle, title);
                return;
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Object m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
                Throwable m13exceptionOrNullimpl = Result.m13exceptionOrNullimpl(m10constructorimpl);
                if (m13exceptionOrNullimpl != null) {
                    LoggerKt.logW(TAG, "Unable to call preference title setter", m13exceptionOrNullimpl);
                }
                Result.m9boximpl(m10constructorimpl);
            }
        }
        setFirstEmptyCharSequenceField($this$setPreferenceTitle, title);
    }

    private final void setPreferenceSummary(Object $this$setPreferenceSummary, CharSequence summary) {
        Method summarySetter = this.methods.getSummarySetter();
        if (summarySetter != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                summarySetter.invoke($this$setPreferenceSummary, summary);
                return;
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Object m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
                Throwable m13exceptionOrNullimpl = Result.m13exceptionOrNullimpl(m10constructorimpl);
                if (m13exceptionOrNullimpl != null) {
                    LoggerKt.logW(TAG, "Unable to call preference summary setter", m13exceptionOrNullimpl);
                }
                Result.m9boximpl(m10constructorimpl);
            }
        }
        setFirstEmptyCharSequenceField($this$setPreferenceSummary, summary);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x007f A[LOOP:1: B:5:0x0038->B:16:0x007f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void setPreferenceChangeListener(java.lang.Object r18, java.lang.Class<?> r19, final kotlin.jvm.functions.Function1<java.lang.Object, java.lang.Boolean> r20) {
        /*
            r17 = this;
            java.lang.Class r0 = r18.getClass()
            kotlin.sequences.Sequence r0 = icu.nullptr.polyglot.youtube.settings.ReflectionSupportKt.fieldsInHierarchy(r0)
            icu.nullptr.polyglot.youtube.settings.HostPreferenceAdapter$$ExternalSyntheticLambda0 r1 = new icu.nullptr.polyglot.youtube.settings.HostPreferenceAdapter$$ExternalSyntheticLambda0
            r1.<init>()
            kotlin.sequences.Sequence r0 = kotlin.sequences.SequencesKt.filter(r0, r1)
            r1 = 0
            java.util.Iterator r2 = r0.iterator()
        L17:
            boolean r3 = r2.hasNext()
            r5 = 1
            if (r3 == 0) goto L8d
            java.lang.Object r3 = r2.next()
            r6 = r3
            java.lang.reflect.Field r6 = (java.lang.reflect.Field) r6
            r7 = 0
            java.lang.Class r8 = r6.getType()
            java.lang.reflect.Method[] r8 = r8.getMethods()
            java.lang.String r9 = "getMethods(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r9)
            java.lang.Object[] r8 = (java.lang.Object[]) r8
            r9 = 0
            int r10 = r8.length
            r11 = 0
        L38:
            if (r11 >= r10) goto L83
            r12 = r8[r11]
            r13 = r12
            java.lang.reflect.Method r13 = (java.lang.reflect.Method) r13
            r14 = 0
            java.lang.Class r15 = r13.getReturnType()
            r16 = 0
            java.lang.Class r4 = java.lang.Boolean.TYPE
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r15, r4)
            if (r4 == 0) goto L77
            java.lang.Class[] r4 = r13.getParameterTypes()
            int r4 = r4.length
            r15 = 2
            if (r4 != r15) goto L74
            java.lang.Class[] r4 = r13.getParameterTypes()
            r4 = r4[r16]
            r15 = r19
            boolean r4 = r4.isAssignableFrom(r15)
            if (r4 == 0) goto L79
            java.lang.Class[] r4 = r13.getParameterTypes()
            r4 = r4[r5]
            java.lang.Class<java.lang.Object> r5 = java.lang.Object.class
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
            if (r4 == 0) goto L79
            r4 = 1
            goto L7b
        L74:
            r15 = r19
            goto L79
        L77:
            r15 = r19
        L79:
            r4 = r16
        L7b:
            if (r4 == 0) goto L7f
            r4 = 1
            goto L89
        L7f:
            int r11 = r11 + 1
            r5 = 1
            goto L38
        L83:
            r15 = r19
            r16 = 0
            r4 = r16
        L89:
            if (r4 == 0) goto L17
            goto L92
        L8d:
            r15 = r19
            r16 = 0
            r3 = 0
        L92:
            java.lang.reflect.Field r3 = (java.lang.reflect.Field) r3
            if (r3 != 0) goto L97
            return
        L97:
            java.lang.Class r0 = r3.getType()
            java.lang.ClassLoader r0 = r0.getClassLoader()
            r1 = 1
            java.lang.Class[] r2 = new java.lang.Class[r1]
            java.lang.Class r4 = r3.getType()
            r2[r16] = r4
            icu.nullptr.polyglot.youtube.settings.HostPreferenceAdapter$$ExternalSyntheticLambda1 r4 = new icu.nullptr.polyglot.youtube.settings.HostPreferenceAdapter$$ExternalSyntheticLambda1
            r5 = r20
            r4.<init>()
            java.lang.Object r0 = java.lang.reflect.Proxy.newProxyInstance(r0, r2, r4)
            r3.setAccessible(r1)
            r1 = r18
            r3.set(r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.youtube.settings.HostPreferenceAdapter.setPreferenceChangeListener(java.lang.Object, java.lang.Class, kotlin.jvm.functions.Function1):void");
    }

    static final boolean setPreferenceChangeListener$lambda$42(Field it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return !Modifier.isStatic(it.getModifiers()) && it.getType().isInterface();
    }

    static final Object setPreferenceChangeListener$lambda$45(Function1 $onChange, Object proxy, Method method, Object[] args) {
        if (Intrinsics.areEqual(method.getName(), "toString") && method.getParameterCount() == 0) {
            return "PolyglotYTPreferenceChangeListener";
        }
        if (Intrinsics.areEqual(method.getName(), "hashCode") && method.getParameterCount() == 0) {
            return Integer.valueOf(System.identityHashCode(proxy));
        }
        if (Intrinsics.areEqual(method.getName(), "equals") && method.getParameterCount() == 1) {
            return Boolean.valueOf(proxy == (args != null ? ArraysKt.firstOrNull(args) : null));
        }
        if (Intrinsics.areEqual(method.getReturnType(), Boolean.TYPE) && method.getParameterTypes().length == 2) {
            return $onChange.invoke(args != null ? ArraysKt.getOrNull(args, 1) : null);
        }
        Class<?> returnType = method.getReturnType();
        Intrinsics.checkNotNullExpressionValue(returnType, "getReturnType(...)");
        return ReflectionSupportKt.defaultReturnValue(returnType);
    }

    private final void setFirstStringField(Object $this$setFirstStringField, String value) {
        Object obj;
        Object m10constructorimpl;
        Iterator<Field> it = ReflectionSupportKt.fieldsInHierarchy($this$setFirstStringField.getClass()).iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                Field field = (Field) obj;
                boolean z = false;
                if (!Modifier.isStatic(field.getModifiers()) && Intrinsics.areEqual(field.getType(), String.class)) {
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        field.setAccessible(true);
                        m10constructorimpl = Result.m10constructorimpl(Boolean.valueOf(field.get($this$setFirstStringField) == null));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m16isFailureimpl(m10constructorimpl)) {
                        m10constructorimpl = false;
                    }
                    if (((Boolean) m10constructorimpl).booleanValue()) {
                        z = true;
                    }
                }
                if (z) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        Field field2 = (Field) obj;
        if (field2 == null) {
            return;
        }
        field2.set($this$setFirstStringField, value);
    }

    private final void setFirstEmptyCharSequenceField(Object $this$setFirstEmptyCharSequenceField, CharSequence value) {
        Object obj;
        Object m10constructorimpl;
        Iterator<Field> it = ReflectionSupportKt.fieldsInHierarchy($this$setFirstEmptyCharSequenceField.getClass()).iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                Field field = (Field) obj;
                boolean z = false;
                if (!Modifier.isStatic(field.getModifiers()) && CharSequence.class.isAssignableFrom(field.getType())) {
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        field.setAccessible(true);
                        m10constructorimpl = Result.m10constructorimpl(Boolean.valueOf(field.get($this$setFirstEmptyCharSequenceField) == null));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m16isFailureimpl(m10constructorimpl)) {
                        m10constructorimpl = false;
                    }
                    if (((Boolean) m10constructorimpl).booleanValue()) {
                        z = true;
                    }
                }
                if (z) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        Field field2 = (Field) obj;
        if (field2 == null) {
            return;
        }
        field2.set($this$setFirstEmptyCharSequenceField, value);
    }

    private final Object findPreferenceByString(Object $this$findPreferenceByString, String key, Class<?> cls, Class<?> cls2) {
        if (cls.isInstance($this$findPreferenceByString) && hasStringFieldValue($this$findPreferenceByString, key)) {
            return $this$findPreferenceByString;
        }
        if (!cls2.isInstance($this$findPreferenceByString)) {
            return null;
        }
        List<Object> preferenceChildren = preferenceChildren($this$findPreferenceByString, cls);
        if (preferenceChildren == null) {
            preferenceChildren = CollectionsKt.emptyList();
        }
        for (Object child : preferenceChildren) {
            Object findPreferenceByString = findPreferenceByString(child, key, cls, cls2);
            if (findPreferenceByString != null) {
                return findPreferenceByString;
            }
        }
        return null;
    }

    private final Field findOrderField(Class<?> cls, Context context) {
        Object m10constructorimpl;
        Constructor constructor = cls.getConstructor(Context.class);
        List<Field> candidates = SequencesKt.toList(SequencesKt.filter(ReflectionSupportKt.fieldsInHierarchy(cls), new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.HostPreferenceAdapter$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(HostPreferenceAdapter.findOrderField$lambda$51((Field) obj));
            }
        }));
        for (Field field : candidates) {
            Object lower = constructor.newInstance(context);
            Object higher = constructor.newInstance(context);
            try {
                Result.Companion companion = Result.INSTANCE;
                field.setAccessible(true);
                field.setInt(lower, -1000);
                field.setInt(higher, NativeSettingsPage.MAX_CONNECTIVITY_RESULT_LENGTH);
                Intrinsics.checkNotNull(lower, "null cannot be cast to non-null type kotlin.Comparable<kotlin.Any>");
                Intrinsics.checkNotNull(higher);
                m10constructorimpl = Result.m10constructorimpl(Boolean.valueOf(((Comparable) lower).compareTo(higher) < 0));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m16isFailureimpl(m10constructorimpl)) {
                m10constructorimpl = false;
            }
            boolean matches = ((Boolean) m10constructorimpl).booleanValue();
            if (matches) {
                return field;
            }
        }
        return null;
    }

    static final boolean findOrderField$lambda$51(Field it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return !Modifier.isStatic(it.getModifiers()) && Intrinsics.areEqual(it.getType(), Integer.TYPE);
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0141 A[LOOP:2: B:30:0x00e7->B:48:0x0141, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x013f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x008f A[LOOP:0: B:2:0x0043->B:8:0x008f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0092 A[EDGE_INSN: B:9:0x0092->B:10:0x0092 BREAK  A[LOOP:0: B:2:0x0043->B:8:0x008f], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.reflect.Field findLayoutResourceField(java.lang.Class<?> r21, android.content.Context r22) {
        /*
            Method dump skipped, instructions count: 330
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.youtube.settings.HostPreferenceAdapter.findLayoutResourceField(java.lang.Class, android.content.Context):java.lang.reflect.Field");
    }

    static final boolean findLayoutResourceField$lambda$53(Field field) {
        Intrinsics.checkNotNullParameter(field, "field");
        return !Modifier.isStatic(field.getModifiers()) && Intrinsics.areEqual(field.getType(), Integer.TYPE);
    }

    private final void setSwitchChecked(Object $this$setSwitchChecked, Context context, Class<?> cls, boolean checked) {
        Method setter = this.switchCheckedSetters.get(cls);
        if (setter == null) {
            setter = findSwitchCheckedSetter(cls, context);
            if (setter != null) {
                this.switchCheckedSetters.put(cls, setter);
            } else {
                setter = null;
            }
        }
        if (setter != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                setter.invoke($this$setSwitchChecked, Boolean.valueOf(checked));
                return;
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Throwable m13exceptionOrNullimpl = Result.m13exceptionOrNullimpl(Result.m10constructorimpl(ResultKt.createFailure(th)));
                if (m13exceptionOrNullimpl != null) {
                    LoggerKt.logW(TAG, "Unable to call switch checked setter", m13exceptionOrNullimpl);
                }
            }
        }
        Field checkedField = (Field) SequencesKt.firstOrNull(SequencesKt.filter(ReflectionSupportKt.fieldsInHierarchy($this$setSwitchChecked.getClass()), new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.HostPreferenceAdapter$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(HostPreferenceAdapter.setSwitchChecked$lambda$63((Field) obj));
            }
        }));
        if (checkedField == null) {
            return;
        }
        checkedField.setAccessible(true);
        checkedField.setBoolean($this$setSwitchChecked, checked);
    }

    static final boolean setSwitchChecked$lambda$63(Field it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return !Modifier.isStatic(it.getModifiers()) && Intrinsics.areEqual(it.getType(), Boolean.TYPE);
    }

    private final Method findSwitchCheckedSetter(Class<?> cls, Context context) {
        Object m10constructorimpl;
        Set changedToTrue;
        Boolean bool;
        boolean z;
        Object m10constructorimpl2;
        boolean z2;
        HostPreferenceAdapter hostPreferenceAdapter = this;
        boolean changedToFalse = true;
        Boolean bool2 = true;
        Constructor constructor = cls.getConstructor(Context.class);
        List<Method> candidates = SequencesKt.toList(SequencesKt.filter(ReflectionSupportKt.methodsInHierarchy(cls), new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.HostPreferenceAdapter$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(HostPreferenceAdapter.findSwitchCheckedSetter$lambda$64((Method) obj));
            }
        }));
        for (Method method : candidates) {
            Object preference = constructor.newInstance(context);
            Intrinsics.checkNotNull(preference);
            Map before = hostPreferenceAdapter.booleanFieldValues(preference);
            try {
                Result.Companion companion = Result.INSTANCE;
                method.setAccessible(changedToFalse);
                method.invoke(preference, bool2);
                m10constructorimpl = Result.m10constructorimpl(hostPreferenceAdapter.booleanFieldValues(preference));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m16isFailureimpl(m10constructorimpl)) {
                m10constructorimpl = null;
            }
            Map afterTrue = (Map) m10constructorimpl;
            if (afterTrue != null) {
                Map linkedHashMap = new LinkedHashMap();
                for (Map.Entry entry : before.entrySet()) {
                    if (!entry.getValue().booleanValue() && Intrinsics.areEqual(afterTrue.get(entry.getKey()), bool2)) {
                        linkedHashMap.put(entry.getKey(), entry.getValue());
                    }
                }
                Set changedToTrue2 = linkedHashMap.keySet();
                Map map = before;
                if (!map.isEmpty()) {
                    Iterator<Map.Entry<Field, Boolean>> it = map.entrySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            changedToTrue = changedToTrue2;
                            bool = bool2;
                            z = false;
                            break;
                        }
                        Map.Entry next = it.next();
                        Map map2 = map;
                        changedToTrue = changedToTrue2;
                        bool = bool2;
                        if (next.getValue().booleanValue() && Intrinsics.areEqual(afterTrue.get(next.getKey()), (Object) false)) {
                            z = true;
                            break;
                        }
                        bool2 = bool;
                        map = map2;
                        changedToTrue2 = changedToTrue;
                    }
                } else {
                    changedToTrue = changedToTrue2;
                    bool = bool2;
                    z = false;
                }
                boolean changedToFalse2 = z;
                if (changedToTrue.isEmpty()) {
                    bool2 = bool;
                    changedToFalse = true;
                    hostPreferenceAdapter = this;
                } else if (changedToFalse2) {
                    bool2 = bool;
                    changedToFalse = true;
                } else {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        method.invoke(preference, false);
                        m10constructorimpl2 = Result.m10constructorimpl(hostPreferenceAdapter.booleanFieldValues(preference));
                    } catch (Throwable th2) {
                        Result.Companion companion4 = Result.INSTANCE;
                        m10constructorimpl2 = Result.m10constructorimpl(ResultKt.createFailure(th2));
                    }
                    Map afterFalse = (Map) (Result.m16isFailureimpl(m10constructorimpl2) ? null : m10constructorimpl2);
                    if (afterFalse == null) {
                        bool2 = bool;
                        changedToFalse = true;
                    } else {
                        Iterable iterable = changedToTrue;
                        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
                            z2 = false;
                        } else {
                            Iterator it2 = iterable.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    z2 = false;
                                    break;
                                }
                                Iterable iterable2 = iterable;
                                if (Intrinsics.areEqual(afterFalse.get((Field) it2.next()), (Object) false)) {
                                    z2 = true;
                                    break;
                                }
                                iterable = iterable2;
                            }
                        }
                        if (z2) {
                            return method;
                        }
                        bool2 = bool;
                        changedToFalse = true;
                        hostPreferenceAdapter = this;
                    }
                }
            }
        }
        return null;
    }

    static final boolean findSwitchCheckedSetter$lambda$64(Method it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return !Modifier.isStatic(it.getModifiers()) && Intrinsics.areEqual(it.getReturnType(), Void.TYPE) && Arrays.equals(it.getParameterTypes(), new Class[]{Boolean.TYPE});
    }

    private final Map<Field, Boolean> booleanFieldValues(Object $this$booleanFieldValues) {
        Sequence filter = SequencesKt.filter(ReflectionSupportKt.fieldsInHierarchy($this$booleanFieldValues.getClass()), new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.HostPreferenceAdapter$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(HostPreferenceAdapter.booleanFieldValues$lambda$70((Field) obj));
            }
        });
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : filter) {
            Field field = (Field) obj;
            field.setAccessible(true);
            linkedHashMap.put(obj, Boolean.valueOf(field.getBoolean($this$booleanFieldValues)));
        }
        return linkedHashMap;
    }

    static final boolean booleanFieldValues$lambda$70(Field it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return !Modifier.isStatic(it.getModifiers()) && Intrinsics.areEqual(it.getType(), Boolean.TYPE);
    }

    private final boolean appendPreference(Object $this$appendPreference, Object entry, final Class<?> cls) {
        Object m10constructorimpl;
        List beforeChildren = preferenceChildren($this$appendPreference, cls);
        if (beforeChildren == null) {
            beforeChildren = CollectionsKt.emptyList();
        }
        if (beforeChildren.contains(entry)) {
            return true;
        }
        Method addPreference = this.methods.getAddPreference();
        if (addPreference != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                m10constructorimpl = Result.m10constructorimpl(addPreference.invoke($this$appendPreference, entry));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
            }
            Throwable m13exceptionOrNullimpl = Result.m13exceptionOrNullimpl(m10constructorimpl);
            if (m13exceptionOrNullimpl != null) {
                LoggerKt.logW(TAG, "Unable to call preference add method", m13exceptionOrNullimpl);
            }
            List<Object> preferenceChildren = preferenceChildren($this$appendPreference, cls);
            if (preferenceChildren == null) {
                preferenceChildren = CollectionsKt.emptyList();
            }
            if (preferenceChildren.contains(entry)) {
                return true;
            }
        }
        List<Method> candidates = SequencesKt.toList(SequencesKt.filter(ReflectionSupportKt.methodsInHierarchy($this$appendPreference.getClass()), new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.HostPreferenceAdapter$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(HostPreferenceAdapter.appendPreference$lambda$75(cls, (Method) obj));
            }
        }));
        for (Method method : candidates) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                method.setAccessible(true);
                Result.m10constructorimpl(method.invoke($this$appendPreference, entry));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                Result.m10constructorimpl(ResultKt.createFailure(th2));
            }
            List<Object> preferenceChildren2 = preferenceChildren($this$appendPreference, cls);
            if (preferenceChildren2 == null) {
                preferenceChildren2 = CollectionsKt.emptyList();
            }
            if (preferenceChildren2.contains(entry)) {
                return true;
            }
        }
        return false;
    }

    static final boolean appendPreference$lambda$75(Class $preferenceClass, Method it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Intrinsics.areEqual(it.getReturnType(), Void.TYPE) && it.getParameterTypes().length == 1 && it.getParameterTypes()[0].isAssignableFrom($preferenceClass);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0097 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[LOOP:0: B:2:0x0015->B:22:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<java.lang.Object> preferenceChildren(java.lang.Object r19, java.lang.Class<?> r20) {
        /*
            r18 = this;
            java.lang.Class r0 = r19.getClass()
            kotlin.sequences.Sequence r0 = icu.nullptr.polyglot.youtube.settings.ReflectionSupportKt.fieldsInHierarchy(r0)
            icu.nullptr.polyglot.youtube.settings.HostPreferenceAdapter$$ExternalSyntheticLambda4 r1 = new icu.nullptr.polyglot.youtube.settings.HostPreferenceAdapter$$ExternalSyntheticLambda4
            r1.<init>()
            kotlin.sequences.Sequence r0 = kotlin.sequences.SequencesKt.filter(r0, r1)
            java.util.Iterator r1 = r0.iterator()
        L15:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L9a
            java.lang.Object r0 = r1.next()
            r3 = r0
            java.lang.reflect.Field r3 = (java.lang.reflect.Field) r3
            r4 = 0
            kotlin.Result$Companion r0 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L7f
            r0 = r19
            r5 = 0
            r6 = 1
            r3.setAccessible(r6)     // Catch: java.lang.Throwable -> L7f
            java.lang.Object r7 = r3.get(r0)     // Catch: java.lang.Throwable -> L7f
            boolean r8 = kotlin.jvm.internal.TypeIntrinsics.isMutableList(r7)     // Catch: java.lang.Throwable -> L7f
            if (r8 == 0) goto L39
            java.util.List r7 = (java.util.List) r7     // Catch: java.lang.Throwable -> L7f
            goto L3a
        L39:
            r7 = 0
        L3a:
            if (r7 != 0) goto L40
            r2 = r20
            r6 = 0
            goto L78
        L40:
            r8 = r7
            r9 = 0
            r10 = r8
            java.lang.Iterable r10 = (java.lang.Iterable) r10     // Catch: java.lang.Throwable -> L7f
            r11 = 0
            boolean r12 = r10 instanceof java.util.Collection     // Catch: java.lang.Throwable -> L7f
            if (r12 == 0) goto L56
            r12 = r10
            java.util.Collection r12 = (java.util.Collection) r12     // Catch: java.lang.Throwable -> L7f
            boolean r12 = r12.isEmpty()     // Catch: java.lang.Throwable -> L7f
            if (r12 == 0) goto L56
            r2 = r20
            goto L72
        L56:
            java.util.Iterator r12 = r10.iterator()     // Catch: java.lang.Throwable -> L7f
        L5a:
            boolean r13 = r12.hasNext()     // Catch: java.lang.Throwable -> L7f
            if (r13 == 0) goto L70
            java.lang.Object r13 = r12.next()     // Catch: java.lang.Throwable -> L7f
            r14 = r13
            r15 = 0
            r2 = r20
            boolean r17 = r2.isInstance(r14)     // Catch: java.lang.Throwable -> L7d
            if (r17 != 0) goto L5a
            r6 = 0
            goto L72
        L70:
            r2 = r20
        L72:
            if (r6 == 0) goto L77
            r6 = r7
            goto L78
        L77:
            r6 = 0
        L78:
            java.lang.Object r0 = kotlin.Result.m10constructorimpl(r6)     // Catch: java.lang.Throwable -> L7d
            goto L8c
        L7d:
            r0 = move-exception
            goto L82
        L7f:
            r0 = move-exception
            r2 = r20
        L82:
            kotlin.Result$Companion r5 = kotlin.Result.INSTANCE
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m10constructorimpl(r0)
        L8c:
            boolean r5 = kotlin.Result.m16isFailureimpl(r0)
            if (r5 == 0) goto L93
            r0 = 0
        L93:
            java.util.List r0 = (java.util.List) r0
            if (r0 == 0) goto L15
            r16 = r0
            goto L9e
        L9a:
            r2 = r20
            r16 = 0
        L9e:
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.youtube.settings.HostPreferenceAdapter.preferenceChildren(java.lang.Object, java.lang.Class):java.util.List");
    }

    static final boolean preferenceChildren$lambda$77(Field it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return !Modifier.isStatic(it.getModifiers()) && List.class.isAssignableFrom(it.getType());
    }

    private final boolean hasStringFieldValue(Object $this$hasStringFieldValue, String value) {
        Object m10constructorimpl;
        for (Field field : SequencesKt.filter(ReflectionSupportKt.fieldsInHierarchy($this$hasStringFieldValue.getClass()), new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.HostPreferenceAdapter$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(HostPreferenceAdapter.hasStringFieldValue$lambda$82((Field) obj));
            }
        })) {
            try {
                Result.Companion companion = Result.INSTANCE;
                field.setAccessible(true);
                m10constructorimpl = Result.m10constructorimpl(Boolean.valueOf(Intrinsics.areEqual(field.get($this$hasStringFieldValue), value)));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m16isFailureimpl(m10constructorimpl)) {
                m10constructorimpl = false;
            }
            if (((Boolean) m10constructorimpl).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    static final boolean hasStringFieldValue$lambda$82(Field it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return !Modifier.isStatic(it.getModifiers()) && Intrinsics.areEqual(it.getType(), String.class);
    }

    /* compiled from: HostPreferenceAdapter.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Licu/nullptr/polyglot/youtube/settings/HostPreferenceAdapter$Companion;", "", "<init>", "()V", "TAG", "", "PREFERENCE_DEFAULT_LAYOUT", "PREFERENCE_WITH_ICON_LAYOUT", "OPAQUE_ALPHA_MASK", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
