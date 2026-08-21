package icu.nullptr.polyglot.captions;

import icu.nullptr.polyglot.util.LoggerKt;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: CaptionLanguageState.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001cB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\f\u001a\u00020\u0005J\u0018\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0010\u001a\u00020\u0005J\u0012\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012*\u00020\u0001H\u0002J\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u0005*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0001H\u0002J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J\u0016\u0010\u0018\u001a\u00020\u00052\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012H\u0002J\u0010\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Licu/nullptr/polyglot/captions/CaptionLanguageState;", "", "<init>", "()V", "TAG", "", "AUTO_LANGUAGE", CaptionLanguageState.DISABLE_CAPTIONS_OPTION, CaptionLanguageState.AUTO_TRANSLATE_CAPTIONS_OPTION, "languagePattern", "Lkotlin/text/Regex;", "sourceLanguage", "currentSourceLanguage", "updateFromCaptionTrack", "", "track", "source", "instanceStringValues", "", "getStringOrNull", "Ljava/lang/reflect/Field;", "instance", "normalizeLanguageCode", "value", "normalizeChinese", "parts", "normalizeLanguageSubtag", "languageFromVssId", "CaptionTrackInfo", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class CaptionLanguageState {
    private static final String AUTO_TRANSLATE_CAPTIONS_OPTION = "AUTO_TRANSLATE_CAPTIONS_OPTION";
    private static final String DISABLE_CAPTIONS_OPTION = "DISABLE_CAPTIONS_OPTION";
    private static final String TAG = "CaptionLanguageState";
    public static final CaptionLanguageState INSTANCE = new CaptionLanguageState();
    private static final Regex languagePattern = new Regex("^[A-Za-z]{2,3}([_-][A-Za-z0-9]{2,8}){0,3}$");
    private static final String AUTO_LANGUAGE = "auto";
    private static volatile String sourceLanguage = AUTO_LANGUAGE;

    private CaptionLanguageState() {
    }

    public final String currentSourceLanguage() {
        String str = sourceLanguage;
        if (StringsKt.isBlank(str)) {
            str = AUTO_LANGUAGE;
        }
        return str;
    }

    public final boolean updateFromCaptionTrack(Object track, String source) {
        String language;
        Intrinsics.checkNotNullParameter(source, "source");
        CaptionTrackInfo info = CaptionTrackInfo.INSTANCE.from(track);
        if (info == null || info.isPseudoOption() || (language = info.getLanguageCode()) == null) {
            return false;
        }
        String previous = sourceLanguage;
        if (Intrinsics.areEqual(previous, language)) {
            return false;
        }
        sourceLanguage = language;
        LoggerKt.logD$default(TAG, "Detected YouTube caption language: " + previous + " -> " + language + " from " + source, null, 4, null);
        return true;
    }

    /* compiled from: CaptionLanguageState.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\b\u0082\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u0014"}, d2 = {"Licu/nullptr/polyglot/captions/CaptionLanguageState$CaptionTrackInfo;", "", "languageCode", "", "isPseudoOption", "", "<init>", "(Ljava/lang/String;Z)V", "getLanguageCode", "()Ljava/lang/String;", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class CaptionTrackInfo {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final boolean isPseudoOption;
        private final String languageCode;

        public static /* synthetic */ CaptionTrackInfo copy$default(CaptionTrackInfo captionTrackInfo, String str, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = captionTrackInfo.languageCode;
            }
            if ((i & 2) != 0) {
                z = captionTrackInfo.isPseudoOption;
            }
            return captionTrackInfo.copy(str, z);
        }

        /* renamed from: component1, reason: from getter */
        public final String getLanguageCode() {
            return this.languageCode;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getIsPseudoOption() {
            return this.isPseudoOption;
        }

        public final CaptionTrackInfo copy(String languageCode, boolean isPseudoOption) {
            return new CaptionTrackInfo(languageCode, isPseudoOption);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CaptionTrackInfo)) {
                return false;
            }
            CaptionTrackInfo captionTrackInfo = (CaptionTrackInfo) other;
            return Intrinsics.areEqual(this.languageCode, captionTrackInfo.languageCode) && this.isPseudoOption == captionTrackInfo.isPseudoOption;
        }

        public int hashCode() {
            return ((this.languageCode == null ? 0 : this.languageCode.hashCode()) * 31) + Boolean.hashCode(this.isPseudoOption);
        }

        public String toString() {
            return "CaptionTrackInfo(languageCode=" + this.languageCode + ", isPseudoOption=" + this.isPseudoOption + ")";
        }

        public CaptionTrackInfo(String languageCode, boolean isPseudoOption) {
            this.languageCode = languageCode;
            this.isPseudoOption = isPseudoOption;
        }

        public final String getLanguageCode() {
            return this.languageCode;
        }

        public final boolean isPseudoOption() {
            return this.isPseudoOption;
        }

        /* compiled from: CaptionLanguageState.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001¨\u0006\u0007"}, d2 = {"Licu/nullptr/polyglot/captions/CaptionLanguageState$CaptionTrackInfo$Companion;", "", "<init>", "()V", "from", "Licu/nullptr/polyglot/captions/CaptionLanguageState$CaptionTrackInfo;", "track", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final CaptionTrackInfo from(Object track) {
                String directLanguage;
                String vssLanguage = null;
                if (track != null) {
                    List strings = CaptionLanguageState.INSTANCE.instanceStringValues(track);
                    if (strings.isEmpty()) {
                        return null;
                    }
                    List list = strings;
                    boolean isPseudoOption = false;
                    if (!(list instanceof Collection) || !list.isEmpty()) {
                        Iterator it = list.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            String str = (String) it.next();
                            if (((Intrinsics.areEqual(str, CaptionLanguageState.DISABLE_CAPTIONS_OPTION) || Intrinsics.areEqual(str, CaptionLanguageState.AUTO_TRANSLATE_CAPTIONS_OPTION)) ? 1 : null) != null) {
                                isPseudoOption = true;
                                break;
                            }
                        }
                    }
                    Iterator it2 = strings.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            directLanguage = CaptionLanguageState.INSTANCE.normalizeLanguageCode((String) it2.next());
                            if (directLanguage != null) {
                                break;
                            }
                        } else {
                            directLanguage = null;
                            break;
                        }
                    }
                    Iterator it3 = strings.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            break;
                        }
                        String languageFromVssId = CaptionLanguageState.INSTANCE.languageFromVssId((String) it3.next());
                        if (languageFromVssId != null) {
                            vssLanguage = languageFromVssId;
                            break;
                        }
                    }
                    return new CaptionTrackInfo(directLanguage == null ? vssLanguage : directLanguage, isPseudoOption);
                }
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<String> instanceStringValues(Object $this$instanceStringValues) {
        ArrayList values = new ArrayList();
        for (Class clazz = $this$instanceStringValues.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            Iterator it = ArrayIteratorKt.iterator(clazz.getDeclaredFields());
            while (it.hasNext()) {
                Field field = (Field) it.next();
                if (!Modifier.isStatic(field.getModifiers()) && Intrinsics.areEqual(field.getType(), String.class)) {
                    Intrinsics.checkNotNull(field);
                    String stringOrNull = getStringOrNull(field, $this$instanceStringValues);
                    String value = stringOrNull != null ? StringsKt.trim((CharSequence) stringOrNull).toString() : null;
                    String str = value;
                    if (!(str == null || str.length() == 0)) {
                        values.add(value);
                    }
                }
            }
        }
        return values;
    }

    private final String getStringOrNull(Field $this$getStringOrNull, Object instance) {
        Object m10constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            $this$getStringOrNull.setAccessible(true);
            Object obj = $this$getStringOrNull.get(instance);
            m10constructorimpl = Result.m10constructorimpl(obj instanceof String ? (String) obj : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
        }
        return (String) (Result.m16isFailureimpl(m10constructorimpl) ? null : m10constructorimpl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final String normalizeLanguageCode(String value) {
        String primary;
        String trimmed = StringsKt.trim((CharSequence) value).toString();
        if (Intrinsics.areEqual(trimmed, DISABLE_CAPTIONS_OPTION) || Intrinsics.areEqual(trimmed, AUTO_TRANSLATE_CAPTIONS_OPTION) || trimmed.length() > 24 || !languagePattern.matches(trimmed)) {
            return null;
        }
        Iterable split$default = StringsKt.split$default((CharSequence) StringsKt.replace$default(trimmed, '_', '-', false, 4, (Object) null), new char[]{'-'}, false, 0, 6, (Object) null);
        Collection arrayList = new ArrayList();
        for (Object obj : split$default) {
            if (!StringsKt.isBlank((String) obj)) {
                arrayList.add(obj);
            }
        }
        List parts = (List) arrayList;
        if (parts.isEmpty()) {
            return null;
        }
        String str = (String) CollectionsKt.first(parts);
        Locale ROOT = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
        String lowerCase = str.toLowerCase(ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        switch (lowerCase.hashCode()) {
            case 3365:
                if (lowerCase.equals("in")) {
                    primary = "id";
                    break;
                }
                String str2 = (String) CollectionsKt.first(parts);
                Locale ROOT2 = Locale.ROOT;
                Intrinsics.checkNotNullExpressionValue(ROOT2, "ROOT");
                primary = str2.toLowerCase(ROOT2);
                Intrinsics.checkNotNullExpressionValue(primary, "toLowerCase(...)");
                break;
            case 3374:
                if (lowerCase.equals("iw")) {
                    primary = "he";
                    break;
                }
                String str22 = (String) CollectionsKt.first(parts);
                Locale ROOT22 = Locale.ROOT;
                Intrinsics.checkNotNullExpressionValue(ROOT22, "ROOT");
                primary = str22.toLowerCase(ROOT22);
                Intrinsics.checkNotNullExpressionValue(primary, "toLowerCase(...)");
                break;
            case 3405:
                if (lowerCase.equals("jw")) {
                    primary = "jv";
                    break;
                }
                String str222 = (String) CollectionsKt.first(parts);
                Locale ROOT222 = Locale.ROOT;
                Intrinsics.checkNotNullExpressionValue(ROOT222, "ROOT");
                primary = str222.toLowerCase(ROOT222);
                Intrinsics.checkNotNullExpressionValue(primary, "toLowerCase(...)");
                break;
            default:
                String str2222 = (String) CollectionsKt.first(parts);
                Locale ROOT2222 = Locale.ROOT;
                Intrinsics.checkNotNullExpressionValue(ROOT2222, "ROOT");
                primary = str2222.toLowerCase(ROOT2222);
                Intrinsics.checkNotNullExpressionValue(primary, "toLowerCase(...)");
                break;
        }
        if (Intrinsics.areEqual(primary, "und") || Intrinsics.areEqual(primary, "asr")) {
            return null;
        }
        if (Intrinsics.areEqual(primary, "zh")) {
            return normalizeChinese(parts);
        }
        List createListBuilder = CollectionsKt.createListBuilder();
        createListBuilder.add(primary);
        Iterator it = CollectionsKt.drop(parts, 1).iterator();
        while (it.hasNext()) {
            createListBuilder.add(INSTANCE.normalizeLanguageSubtag((String) it.next()));
        }
        List normalized = CollectionsKt.build(createListBuilder);
        return CollectionsKt.joinToString$default(normalized, "-", null, null, 0, null, null, 62, null);
    }

    private final String normalizeChinese(List<String> parts) {
        String joinToString$default = CollectionsKt.joinToString$default(parts, "-", null, null, 0, null, null, 62, null);
        Locale ROOT = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
        String lowered = joinToString$default.toLowerCase(ROOT);
        Intrinsics.checkNotNullExpressionValue(lowered, "toLowerCase(...)");
        if (StringsKt.contains$default((CharSequence) lowered, (CharSequence) "hant", false, 2, (Object) null) || StringsKt.endsWith$default(lowered, "-tw", false, 2, (Object) null) || StringsKt.endsWith$default(lowered, "-hk", false, 2, (Object) null) || StringsKt.endsWith$default(lowered, "-mo", false, 2, (Object) null)) {
            return "zh-Hant";
        }
        if (StringsKt.contains$default((CharSequence) lowered, (CharSequence) "hans", false, 2, (Object) null) || StringsKt.endsWith$default(lowered, "-cn", false, 2, (Object) null) || StringsKt.endsWith$default(lowered, "-sg", false, 2, (Object) null)) {
            return "zh-Hans";
        }
        return "zh";
    }

    private final String normalizeLanguageSubtag(String value) {
        switch (value.length()) {
            case 2:
                Locale ROOT = Locale.ROOT;
                Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                String upperCase = value.toUpperCase(ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                return upperCase;
            case 3:
            default:
                Locale ROOT2 = Locale.ROOT;
                Intrinsics.checkNotNullExpressionValue(ROOT2, "ROOT");
                String lowerCase = value.toLowerCase(ROOT2);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                return lowerCase;
            case 4:
                Locale ROOT3 = Locale.ROOT;
                Intrinsics.checkNotNullExpressionValue(ROOT3, "ROOT");
                String lowerCase2 = value.toLowerCase(ROOT3);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                if (lowerCase2.length() > 0) {
                    char upperCase2 = Character.toUpperCase(lowerCase2.charAt(0));
                    String substring = lowerCase2.substring(1);
                    Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                    return upperCase2 + substring;
                }
                return lowerCase2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String languageFromVssId(String value) {
        String candidate;
        String trimmed = StringsKt.trim((CharSequence) value).toString();
        if ((trimmed.length() == 0) || Intrinsics.areEqual(trimmed, "-")) {
            return null;
        }
        if (StringsKt.startsWith$default(trimmed, "a.", false, 2, (Object) null)) {
            candidate = StringsKt.substringAfter$default(trimmed, "a.", (String) null, 2, (Object) null);
        } else if (StringsKt.startsWith$default(trimmed, ".", false, 2, (Object) null)) {
            candidate = StringsKt.substringAfter$default(trimmed, ".", (String) null, 2, (Object) null);
        } else if (StringsKt.startsWith$default(trimmed, "t.", false, 2, (Object) null)) {
            candidate = StringsKt.substringAfterLast$default(trimmed, ".", (String) null, 2, (Object) null);
        } else {
            if (!StringsKt.startsWith$default(trimmed, "t", false, 2, (Object) null) || !StringsKt.contains$default((CharSequence) trimmed, (CharSequence) ".", false, 2, (Object) null)) {
                return null;
            }
            candidate = StringsKt.substringAfterLast$default(trimmed, ".", (String) null, 2, (Object) null);
        }
        return normalizeLanguageCode(candidate);
    }
}
