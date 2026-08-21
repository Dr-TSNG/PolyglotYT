package icu.nullptr.polyglot.captions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: CaptionCue.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u0000 !2\u00020\u0001:\u0001!B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0006\u0010\u0014\u001a\u00020\u0003J\u0006\u0010\u0015\u001a\u00020\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\tHÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\tHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\""}, d2 = {"Licu/nullptr/polyglot/captions/CaptionCue;", "", "videoId", "", "startMs", "", "endMs", "text", "windowId", "", "<init>", "(Ljava/lang/String;JJLjava/lang/String;I)V", "getVideoId", "()Ljava/lang/String;", "getStartMs", "()J", "getEndMs", "getText", "getWindowId", "()I", "cacheKey", "normalizedText", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final /* data */ class CaptionCue {
    public static final long UNKNOWN_TIME_MS = -1;
    public static final int UNKNOWN_WINDOW_ID = -1;
    private final long endMs;
    private final long startMs;
    private final String text;
    private final String videoId;
    private final int windowId;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Regex whitespace = new Regex("\\s+");

    public static /* synthetic */ CaptionCue copy$default(CaptionCue captionCue, String str, long j, long j2, String str2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = captionCue.videoId;
        }
        if ((i2 & 2) != 0) {
            j = captionCue.startMs;
        }
        if ((i2 & 4) != 0) {
            j2 = captionCue.endMs;
        }
        if ((i2 & 8) != 0) {
            str2 = captionCue.text;
        }
        if ((i2 & 16) != 0) {
            i = captionCue.windowId;
        }
        long j3 = j2;
        return captionCue.copy(str, j, j3, str2, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getVideoId() {
        return this.videoId;
    }

    /* renamed from: component2, reason: from getter */
    public final long getStartMs() {
        return this.startMs;
    }

    /* renamed from: component3, reason: from getter */
    public final long getEndMs() {
        return this.endMs;
    }

    /* renamed from: component4, reason: from getter */
    public final String getText() {
        return this.text;
    }

    /* renamed from: component5, reason: from getter */
    public final int getWindowId() {
        return this.windowId;
    }

    public final CaptionCue copy(String videoId, long startMs, long endMs, String text, int windowId) {
        Intrinsics.checkNotNullParameter(videoId, "videoId");
        Intrinsics.checkNotNullParameter(text, "text");
        return new CaptionCue(videoId, startMs, endMs, text, windowId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CaptionCue)) {
            return false;
        }
        CaptionCue captionCue = (CaptionCue) other;
        return Intrinsics.areEqual(this.videoId, captionCue.videoId) && this.startMs == captionCue.startMs && this.endMs == captionCue.endMs && Intrinsics.areEqual(this.text, captionCue.text) && this.windowId == captionCue.windowId;
    }

    public int hashCode() {
        return (((((((this.videoId.hashCode() * 31) + Long.hashCode(this.startMs)) * 31) + Long.hashCode(this.endMs)) * 31) + this.text.hashCode()) * 31) + Integer.hashCode(this.windowId);
    }

    public String toString() {
        return "CaptionCue(videoId=" + this.videoId + ", startMs=" + this.startMs + ", endMs=" + this.endMs + ", text=" + this.text + ", windowId=" + this.windowId + ")";
    }

    public CaptionCue(String videoId, long startMs, long endMs, String text, int windowId) {
        Intrinsics.checkNotNullParameter(videoId, "videoId");
        Intrinsics.checkNotNullParameter(text, "text");
        this.videoId = videoId;
        this.startMs = startMs;
        this.endMs = endMs;
        this.text = text;
        this.windowId = windowId;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ CaptionCue(java.lang.String r10, long r11, long r13, java.lang.String r15, int r16, int r17, kotlin.jvm.internal.DefaultConstructorMarker r18) {
        /*
            r9 = this;
            r0 = r17 & 16
            if (r0 == 0) goto L7
            r0 = -1
            r8 = r0
            goto L9
        L7:
            r8 = r16
        L9:
            r1 = r9
            r2 = r10
            r3 = r11
            r5 = r13
            r7 = r15
            r1.<init>(r2, r3, r5, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.captions.CaptionCue.<init>(java.lang.String, long, long, java.lang.String, int, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getVideoId() {
        return this.videoId;
    }

    public final long getStartMs() {
        return this.startMs;
    }

    public final long getEndMs() {
        return this.endMs;
    }

    public final String getText() {
        return this.text;
    }

    public final int getWindowId() {
        return this.windowId;
    }

    public final String cacheKey() {
        return this.videoId + "|" + this.windowId + "|" + this.startMs + "|" + this.endMs + "|" + normalizedText();
    }

    public final String normalizedText() {
        return INSTANCE.normalize(this.text);
    }

    /* compiled from: CaptionCue.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Licu/nullptr/polyglot/captions/CaptionCue$Companion;", "", "<init>", "()V", "UNKNOWN_TIME_MS", "", "UNKNOWN_WINDOW_ID", "", "whitespace", "Lkotlin/text/Regex;", "normalize", "", "text", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String normalize(String text) {
            Intrinsics.checkNotNullParameter(text, "text");
            return StringsKt.trim((CharSequence) CaptionCue.whitespace.replace(text, " ")).toString();
        }
    }
}
