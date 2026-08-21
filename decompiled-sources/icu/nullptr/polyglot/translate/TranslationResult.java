package icu.nullptr.polyglot.translate;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TranslationResult.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Licu/nullptr/polyglot/translate/TranslationResult;", "", "texts", "", "", "<init>", "(Ljava/util/List;)V", "getTexts", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class TranslationResult {
    private final List<String> texts;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TranslationResult copy$default(TranslationResult translationResult, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = translationResult.texts;
        }
        return translationResult.copy(list);
    }

    public final List<String> component1() {
        return this.texts;
    }

    public final TranslationResult copy(List<String> texts) {
        Intrinsics.checkNotNullParameter(texts, "texts");
        return new TranslationResult(texts);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof TranslationResult) && Intrinsics.areEqual(this.texts, ((TranslationResult) other).texts);
    }

    public int hashCode() {
        return this.texts.hashCode();
    }

    public String toString() {
        return "TranslationResult(texts=" + this.texts + ")";
    }

    public TranslationResult(List<String> texts) {
        Intrinsics.checkNotNullParameter(texts, "texts");
        this.texts = texts;
    }

    public final List<String> getTexts() {
        return this.texts;
    }
}
