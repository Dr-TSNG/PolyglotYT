package icu.nullptr.polyglot.translate;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TranslationRequest.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0018\u001a\u00020\tHÆ\u0003JA\u0010\u0019\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\tHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Licu/nullptr/polyglot/translate/TranslationRequest;", "", "texts", "", "", "sourceLanguage", "targetLanguage", "context", "timeoutMs", "", "<init>", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getTexts", "()Ljava/util/List;", "getSourceLanguage", "()Ljava/lang/String;", "getTargetLanguage", "getContext", "getTimeoutMs", "()I", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class TranslationRequest {
    private final String context;
    private final String sourceLanguage;
    private final String targetLanguage;
    private final List<String> texts;
    private final int timeoutMs;

    public static /* synthetic */ TranslationRequest copy$default(TranslationRequest translationRequest, List list, String str, String str2, String str3, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = translationRequest.texts;
        }
        if ((i2 & 2) != 0) {
            str = translationRequest.sourceLanguage;
        }
        if ((i2 & 4) != 0) {
            str2 = translationRequest.targetLanguage;
        }
        if ((i2 & 8) != 0) {
            str3 = translationRequest.context;
        }
        if ((i2 & 16) != 0) {
            i = translationRequest.timeoutMs;
        }
        int i3 = i;
        String str4 = str2;
        return translationRequest.copy(list, str, str4, str3, i3);
    }

    public final List<String> component1() {
        return this.texts;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSourceLanguage() {
        return this.sourceLanguage;
    }

    /* renamed from: component3, reason: from getter */
    public final String getTargetLanguage() {
        return this.targetLanguage;
    }

    /* renamed from: component4, reason: from getter */
    public final String getContext() {
        return this.context;
    }

    /* renamed from: component5, reason: from getter */
    public final int getTimeoutMs() {
        return this.timeoutMs;
    }

    public final TranslationRequest copy(List<String> texts, String sourceLanguage, String targetLanguage, String context, int timeoutMs) {
        Intrinsics.checkNotNullParameter(texts, "texts");
        Intrinsics.checkNotNullParameter(sourceLanguage, "sourceLanguage");
        Intrinsics.checkNotNullParameter(targetLanguage, "targetLanguage");
        Intrinsics.checkNotNullParameter(context, "context");
        return new TranslationRequest(texts, sourceLanguage, targetLanguage, context, timeoutMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TranslationRequest)) {
            return false;
        }
        TranslationRequest translationRequest = (TranslationRequest) other;
        return Intrinsics.areEqual(this.texts, translationRequest.texts) && Intrinsics.areEqual(this.sourceLanguage, translationRequest.sourceLanguage) && Intrinsics.areEqual(this.targetLanguage, translationRequest.targetLanguage) && Intrinsics.areEqual(this.context, translationRequest.context) && this.timeoutMs == translationRequest.timeoutMs;
    }

    public int hashCode() {
        return (((((((this.texts.hashCode() * 31) + this.sourceLanguage.hashCode()) * 31) + this.targetLanguage.hashCode()) * 31) + this.context.hashCode()) * 31) + Integer.hashCode(this.timeoutMs);
    }

    public String toString() {
        return "TranslationRequest(texts=" + this.texts + ", sourceLanguage=" + this.sourceLanguage + ", targetLanguage=" + this.targetLanguage + ", context=" + this.context + ", timeoutMs=" + this.timeoutMs + ")";
    }

    public TranslationRequest(List<String> texts, String sourceLanguage, String targetLanguage, String context, int timeoutMs) {
        Intrinsics.checkNotNullParameter(texts, "texts");
        Intrinsics.checkNotNullParameter(sourceLanguage, "sourceLanguage");
        Intrinsics.checkNotNullParameter(targetLanguage, "targetLanguage");
        Intrinsics.checkNotNullParameter(context, "context");
        this.texts = texts;
        this.sourceLanguage = sourceLanguage;
        this.targetLanguage = targetLanguage;
        this.context = context;
        this.timeoutMs = timeoutMs;
    }

    public final List<String> getTexts() {
        return this.texts;
    }

    public final String getSourceLanguage() {
        return this.sourceLanguage;
    }

    public final String getTargetLanguage() {
        return this.targetLanguage;
    }

    public final String getContext() {
        return this.context;
    }

    public final int getTimeoutMs() {
        return this.timeoutMs;
    }
}
