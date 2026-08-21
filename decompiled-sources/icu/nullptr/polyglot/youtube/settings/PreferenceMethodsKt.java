package icu.nullptr.polyglot.youtube.settings;

import icu.nullptr.polyglot.util.DexKitHelpersKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.luckypray.dexkit.DexKitBridge;
import org.luckypray.dexkit.query.FindMethod;
import org.luckypray.dexkit.query.matchers.MethodMatcher;
import org.luckypray.dexkit.result.MethodData;

/* compiled from: PreferenceMethods.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"resolvePreferenceMethods", "Licu/nullptr/polyglot/youtube/settings/PreferenceMethods;", "Lorg/luckypray/dexkit/DexKitBridge;", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class PreferenceMethodsKt {
    public static final PreferenceMethods resolvePreferenceMethods(DexKitBridge $this$resolvePreferenceMethods) {
        Intrinsics.checkNotNullParameter($this$resolvePreferenceMethods, "<this>");
        MethodData resourceLoad = $this$resolvePreferenceMethods.findMethod(new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PreferenceMethodsKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreferenceMethodsKt.resolvePreferenceMethods$lambda$1((FindMethod) obj);
            }
        }).singleOrNull();
        MethodData click = $this$resolvePreferenceMethods.findMethod(new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PreferenceMethodsKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreferenceMethodsKt.resolvePreferenceMethods$lambda$3((FindMethod) obj);
            }
        }).singleOrNull();
        MethodData keySetter = $this$resolvePreferenceMethods.findMethod(new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PreferenceMethodsKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreferenceMethodsKt.resolvePreferenceMethods$lambda$5((FindMethod) obj);
            }
        }).singleOrNull();
        final MethodData summarySetter = $this$resolvePreferenceMethods.findMethod(new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PreferenceMethodsKt$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreferenceMethodsKt.resolvePreferenceMethods$lambda$7((FindMethod) obj);
            }
        }).singleOrNull();
        MethodData titleSetter = $this$resolvePreferenceMethods.findMethod(new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PreferenceMethodsKt$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreferenceMethodsKt.resolvePreferenceMethods$lambda$9((FindMethod) obj);
            }
        }).singleOrNull(new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PreferenceMethodsKt$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(PreferenceMethodsKt.resolvePreferenceMethods$lambda$10(MethodData.this, (MethodData) obj));
            }
        });
        MethodData addPreference = $this$resolvePreferenceMethods.findMethod(new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PreferenceMethodsKt$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreferenceMethodsKt.resolvePreferenceMethods$lambda$12((FindMethod) obj);
            }
        }).singleOrNull();
        return new PreferenceMethods(resourceLoad != null ? DexKitHelpersKt.toMethod(resourceLoad) : null, click != null ? DexKitHelpersKt.toMethod(click) : null, keySetter != null ? DexKitHelpersKt.toMethod(keySetter) : null, titleSetter != null ? DexKitHelpersKt.toMethod(titleSetter) : null, summarySetter != null ? DexKitHelpersKt.toMethod(summarySetter) : null, addPreference != null ? DexKitHelpersKt.toMethod(addPreference) : null);
    }

    static final Unit resolvePreferenceMethods$lambda$1(FindMethod findMethod) {
        Intrinsics.checkNotNullParameter(findMethod, "$this$findMethod");
        findMethod.matcher(new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PreferenceMethodsKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreferenceMethodsKt.resolvePreferenceMethods$lambda$1$lambda$0((MethodMatcher) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit resolvePreferenceMethods$lambda$1$lambda$0(MethodMatcher matcher) {
        Intrinsics.checkNotNullParameter(matcher, "$this$matcher");
        MethodMatcher.returnType$default(matcher, "void", null, false, 6, null);
        matcher.usingEqStrings(SettingsConstantsKt.PREFERENCE_RESOURCE_LOAD_ERROR);
        return Unit.INSTANCE;
    }

    static final Unit resolvePreferenceMethods$lambda$3(FindMethod findMethod) {
        Intrinsics.checkNotNullParameter(findMethod, "$this$findMethod");
        findMethod.matcher(new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PreferenceMethodsKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreferenceMethodsKt.resolvePreferenceMethods$lambda$3$lambda$2((MethodMatcher) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit resolvePreferenceMethods$lambda$3$lambda$2(MethodMatcher matcher) {
        Intrinsics.checkNotNullParameter(matcher, "$this$matcher");
        MethodMatcher.declaredClass$default(matcher, SettingsConstantsKt.PREFERENCE_CLASS_NAME, null, false, 6, null);
        MethodMatcher.returnType$default(matcher, "void", null, false, 6, null);
        matcher.paramTypes("android.view.View");
        return Unit.INSTANCE;
    }

    static final Unit resolvePreferenceMethods$lambda$5(FindMethod findMethod) {
        Intrinsics.checkNotNullParameter(findMethod, "$this$findMethod");
        findMethod.matcher(new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PreferenceMethodsKt$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreferenceMethodsKt.resolvePreferenceMethods$lambda$5$lambda$4((MethodMatcher) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit resolvePreferenceMethods$lambda$5$lambda$4(MethodMatcher matcher) {
        Intrinsics.checkNotNullParameter(matcher, "$this$matcher");
        MethodMatcher.declaredClass$default(matcher, SettingsConstantsKt.PREFERENCE_CLASS_NAME, null, false, 6, null);
        MethodMatcher.returnType$default(matcher, "void", null, false, 6, null);
        matcher.paramTypes("java.lang.String");
        matcher.usingEqStrings(SettingsConstantsKt.PREFERENCE_KEY_ERROR);
        return Unit.INSTANCE;
    }

    static final Unit resolvePreferenceMethods$lambda$7(FindMethod findMethod) {
        Intrinsics.checkNotNullParameter(findMethod, "$this$findMethod");
        findMethod.matcher(new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PreferenceMethodsKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreferenceMethodsKt.resolvePreferenceMethods$lambda$7$lambda$6((MethodMatcher) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit resolvePreferenceMethods$lambda$7$lambda$6(MethodMatcher matcher) {
        Intrinsics.checkNotNullParameter(matcher, "$this$matcher");
        MethodMatcher.declaredClass$default(matcher, SettingsConstantsKt.PREFERENCE_CLASS_NAME, null, false, 6, null);
        MethodMatcher.returnType$default(matcher, "void", null, false, 6, null);
        matcher.paramTypes("java.lang.CharSequence");
        matcher.usingEqStrings(SettingsConstantsKt.PREFERENCE_SUMMARY_PROVIDER_ERROR);
        return Unit.INSTANCE;
    }

    static final Unit resolvePreferenceMethods$lambda$9(FindMethod findMethod) {
        Intrinsics.checkNotNullParameter(findMethod, "$this$findMethod");
        findMethod.matcher(new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PreferenceMethodsKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreferenceMethodsKt.resolvePreferenceMethods$lambda$9$lambda$8((MethodMatcher) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit resolvePreferenceMethods$lambda$9$lambda$8(MethodMatcher matcher) {
        Intrinsics.checkNotNullParameter(matcher, "$this$matcher");
        MethodMatcher.declaredClass$default(matcher, SettingsConstantsKt.PREFERENCE_CLASS_NAME, null, false, 6, null);
        MethodMatcher.returnType$default(matcher, "void", null, false, 6, null);
        matcher.paramTypes("java.lang.CharSequence");
        return Unit.INSTANCE;
    }

    static final boolean resolvePreferenceMethods$lambda$10(MethodData $summarySetter, MethodData it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return !Intrinsics.areEqual(it, $summarySetter);
    }

    static final Unit resolvePreferenceMethods$lambda$12(FindMethod findMethod) {
        Intrinsics.checkNotNullParameter(findMethod, "$this$findMethod");
        findMethod.matcher(new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PreferenceMethodsKt$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PreferenceMethodsKt.resolvePreferenceMethods$lambda$12$lambda$11((MethodMatcher) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit resolvePreferenceMethods$lambda$12$lambda$11(MethodMatcher matcher) {
        Intrinsics.checkNotNullParameter(matcher, "$this$matcher");
        MethodMatcher.declaredClass$default(matcher, SettingsConstantsKt.PREFERENCE_GROUP_CLASS_NAME, null, false, 6, null);
        MethodMatcher.returnType$default(matcher, "void", null, false, 6, null);
        matcher.paramTypes(SettingsConstantsKt.PREFERENCE_CLASS_NAME);
        matcher.usingStrings(SettingsConstantsKt.PREFERENCE_DUPLICATED_KEY_PREFIX);
        return Unit.INSTANCE;
    }
}
