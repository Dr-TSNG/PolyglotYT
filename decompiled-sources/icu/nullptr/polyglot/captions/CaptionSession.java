package icu.nullptr.polyglot.captions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: CaptionSession.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\u0018\u0000 12\u00020\u0001:\u000212B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0006J\u001a\u0010\u0017\u001a\u00020\u00182\b\u0010\u0013\u001a\u0004\u0018\u00010\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u001a\u001a\u00020\u00182\b\u0010\u0013\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u001b\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u001c\u001a\u00020\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u001d\u001a\u00020\u00182\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fJ\u0006\u0010 \u001a\u00020\u0018J\u000e\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\bJ\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020\b0$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\b0&J\u0010\u0010'\u001a\u00020\u000f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fJ\u0006\u0010(\u001a\u00020)J\u0017\u0010*\u001a\u0004\u0018\u00010\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010+J\u001c\u0010,\u001a\b\u0012\u0004\u0012\u00020\b0$2\u0006\u0010-\u001a\u00020\n2\u0006\u0010.\u001a\u00020\nJ\b\u0010/\u001a\u00020\u0018H\u0002J\u0010\u00100\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0006H\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000RN\u0010\f\u001aB\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u00060\u0006\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u000f0\u000f \u000e* \u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u00060\u0006\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\u000f0\u000f\u0018\u00010\r0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Licu/nullptr/polyglot/captions/CaptionSession;", "", "<init>", "()V", "translations", "Ljava/util/concurrent/ConcurrentHashMap;", "", "observedCues", "Licu/nullptr/polyglot/captions/CaptionCue;", "renderedTexts", "", "failedAt", "formattedTexts", "Ljava/util/concurrent/ConcurrentHashMap$KeySetView;", "kotlin.jvm.PlatformType", "", "observedCueCounter", "Ljava/util/concurrent/atomic/AtomicInteger;", "translationFor", "original", "translatedCueContaining", "Licu/nullptr/polyglot/captions/CaptionSession$CaptionTranslation;", "fragment", "putTranslation", "", "translated", "putFailure", "isRecentlyFailed", "isFormattedRenderedText", "rememberFormattedText", "text", "", "clear", "observeCue", "cue", "observeNewCues", "", "cues", "", "observeRenderedText", "observedCueCount", "", "cueStartMsFor", "(Ljava/lang/String;)Ljava/lang/Long;", "cuesInWindow", "fromMs", "windowMs", "trimToCapacity", "normalize", "Companion", "CaptionTranslation", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class CaptionSession {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final long FAILURE_RETRY_TTL_MS = 3000;

    @Deprecated
    public static final int MAX_FORMATTED_TEXTS = 500;

    @Deprecated
    public static final int MAX_TRANSLATIONS = 2000;

    @Deprecated
    public static final long RENDERED_TEXT_LOG_INTERVAL_MS = 30000;
    private final ConcurrentHashMap<String, String> translations = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, CaptionCue> observedCues = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Long> renderedTexts = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Long> failedAt = new ConcurrentHashMap<>();
    private final ConcurrentHashMap.KeySetView<String, Boolean> formattedTexts = ConcurrentHashMap.newKeySet();
    private final AtomicInteger observedCueCounter = new AtomicInteger(0);

    public final String translationFor(String original) {
        if (original == null) {
            return null;
        }
        return this.translations.get(normalize(original));
    }

    public final CaptionTranslation translatedCueContaining(String fragment) {
        String translated;
        String normalized = normalize(fragment == null ? "" : fragment);
        if (normalized.length() == 0) {
            return null;
        }
        for (CaptionCue captionCue : this.observedCues.values()) {
            Intrinsics.checkNotNullExpressionValue(captionCue, "next(...)");
            CaptionCue cue = captionCue;
            String cueText = cue.normalizedText();
            if (!Intrinsics.areEqual(cueText, normalized) && StringsKt.contains$default((CharSequence) cueText, (CharSequence) normalized, false, 2, (Object) null) && (translated = this.translations.get(cueText)) != null) {
                return new CaptionTranslation(cueText, translated);
            }
        }
        return null;
    }

    public final void putTranslation(String original, String translated) {
        if (original == null || translated == null) {
            return;
        }
        this.translations.put(normalize(original), translated);
        trimToCapacity();
    }

    public final void putFailure(String original) {
        if (original == null) {
            return;
        }
        this.failedAt.put(normalize(original), Long.valueOf(System.currentTimeMillis()));
    }

    public final boolean isRecentlyFailed(String original) {
        Long l;
        if (original == null || (l = this.failedAt.get(normalize(original))) == null) {
            return false;
        }
        long failed = l.longValue();
        return System.currentTimeMillis() - failed < FAILURE_RETRY_TTL_MS;
    }

    public final boolean isFormattedRenderedText(String fragment) {
        String normalized = normalize(fragment == null ? "" : fragment);
        if (normalized.length() == 0) {
            return false;
        }
        return this.formattedTexts.contains(normalized);
    }

    public final void rememberFormattedText(CharSequence text) {
        String obj = text != null ? text.toString() : null;
        if (obj == null) {
            obj = "";
        }
        String normalized = normalize(obj);
        if (normalized.length() > 0) {
            this.formattedTexts.add(normalized);
            if (this.formattedTexts.size() > 500) {
                int excess = this.formattedTexts.size() - MAX_FORMATTED_TEXTS;
                Iterator iterator = this.formattedTexts.iterator();
                Intrinsics.checkNotNullExpressionValue(iterator, "iterator(...)");
                for (int removed = 0; iterator.hasNext() && removed < excess; removed++) {
                    iterator.next();
                    iterator.remove();
                }
            }
        }
    }

    public final void clear() {
        this.translations.clear();
        this.observedCues.clear();
        this.renderedTexts.clear();
        this.failedAt.clear();
        this.formattedTexts.clear();
        this.observedCueCounter.set(0);
    }

    public final boolean observeCue(CaptionCue cue) {
        Intrinsics.checkNotNullParameter(cue, "cue");
        if (cue.normalizedText().length() == 0) {
            return false;
        }
        CaptionCue previous = this.observedCues.putIfAbsent(cue.cacheKey(), cue);
        if (previous != null) {
            return false;
        }
        this.observedCueCounter.incrementAndGet();
        return true;
    }

    public final List<CaptionCue> observeNewCues(Iterable<CaptionCue> cues) {
        Intrinsics.checkNotNullParameter(cues, "cues");
        ArrayList newCues = new ArrayList();
        for (CaptionCue cue : cues) {
            if (observeCue(cue)) {
                newCues.add(cue);
            }
        }
        return newCues;
    }

    public final boolean observeRenderedText(CharSequence text) {
        String obj = text != null ? text.toString() : null;
        if (obj == null) {
            obj = "";
        }
        String normalized = normalize(obj);
        if (normalized.length() == 0) {
            return false;
        }
        long now = System.currentTimeMillis();
        Long previous = this.renderedTexts.put(normalized, Long.valueOf(now));
        return previous == null || now - previous.longValue() > RENDERED_TEXT_LOG_INTERVAL_MS;
    }

    public final int observedCueCount() {
        return this.observedCueCounter.get();
    }

    public final Long cueStartMsFor(String text) {
        Object obj;
        String normalized = normalize(text == null ? "" : text);
        if (normalized.length() == 0) {
            return null;
        }
        Iterable values = this.observedCues.values();
        Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
        Iterator it = values.iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                if (Intrinsics.areEqual(((CaptionCue) obj).normalizedText(), normalized)) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        CaptionCue captionCue = (CaptionCue) obj;
        if (captionCue != null) {
            return Long.valueOf(captionCue.getStartMs());
        }
        return null;
    }

    public final List<CaptionCue> cuesInWindow(long fromMs, long windowMs) {
        Iterable values = this.observedCues.values();
        Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
        Collection arrayList = new ArrayList();
        for (Object obj : values) {
            CaptionCue captionCue = (CaptionCue) obj;
            if (captionCue.getStartMs() > fromMs && captionCue.getStartMs() <= fromMs + windowMs) {
                arrayList.add(obj);
            }
        }
        return (List) arrayList;
    }

    private final void trimToCapacity() {
        if (this.translations.size() <= 2000) {
            return;
        }
        int excess = this.translations.size() - MAX_TRANSLATIONS;
        Iterator iterator = this.translations.entrySet().iterator();
        for (int removed = 0; iterator.hasNext() && removed < excess; removed++) {
            iterator.next();
            iterator.remove();
        }
    }

    private final String normalize(String text) {
        return CaptionCue.INSTANCE.normalize(text);
    }

    /* compiled from: CaptionSession.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Licu/nullptr/polyglot/captions/CaptionSession$Companion;", "", "<init>", "()V", "RENDERED_TEXT_LOG_INTERVAL_MS", "", "FAILURE_RETRY_TTL_MS", "MAX_TRANSLATIONS", "", "MAX_FORMATTED_TEXTS", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* compiled from: CaptionSession.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Licu/nullptr/polyglot/captions/CaptionSession$CaptionTranslation;", "", "original", "", "translated", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getOriginal", "()Ljava/lang/String;", "getTranslated", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class CaptionTranslation {
        private final String original;
        private final String translated;

        public static /* synthetic */ CaptionTranslation copy$default(CaptionTranslation captionTranslation, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = captionTranslation.original;
            }
            if ((i & 2) != 0) {
                str2 = captionTranslation.translated;
            }
            return captionTranslation.copy(str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getOriginal() {
            return this.original;
        }

        /* renamed from: component2, reason: from getter */
        public final String getTranslated() {
            return this.translated;
        }

        public final CaptionTranslation copy(String original, String translated) {
            Intrinsics.checkNotNullParameter(original, "original");
            Intrinsics.checkNotNullParameter(translated, "translated");
            return new CaptionTranslation(original, translated);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CaptionTranslation)) {
                return false;
            }
            CaptionTranslation captionTranslation = (CaptionTranslation) other;
            return Intrinsics.areEqual(this.original, captionTranslation.original) && Intrinsics.areEqual(this.translated, captionTranslation.translated);
        }

        public int hashCode() {
            return (this.original.hashCode() * 31) + this.translated.hashCode();
        }

        public String toString() {
            return "CaptionTranslation(original=" + this.original + ", translated=" + this.translated + ")";
        }

        public CaptionTranslation(String original, String translated) {
            Intrinsics.checkNotNullParameter(original, "original");
            Intrinsics.checkNotNullParameter(translated, "translated");
            this.original = original;
            this.translated = translated;
        }

        public final String getOriginal() {
            return this.original;
        }

        public final String getTranslated() {
            return this.translated;
        }
    }
}
