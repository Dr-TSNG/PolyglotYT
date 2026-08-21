package icu.nullptr.polyglot.youtube.settings;

import android.app.Activity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HostToolbarTitle.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0005J\u0006\u0010\r\u001a\u00020\nR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Licu/nullptr/polyglot/youtube/settings/HostToolbarTitle;", "", "activity", "Landroid/app/Activity;", "fallbackTitle", "", "<init>", "(Landroid/app/Activity;Ljava/lang/CharSequence;)V", "originalTitle", "capture", "", "show", "title", "restore", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class HostToolbarTitle {
    private final Activity activity;
    private final CharSequence fallbackTitle;
    private CharSequence originalTitle;

    public HostToolbarTitle(Activity activity, CharSequence fallbackTitle) {
        this.activity = activity;
        this.fallbackTitle = fallbackTitle;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001e, code lost:
    
        if (r0 != null) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void capture() {
        /*
            r4 = this;
            java.lang.CharSequence r0 = r4.originalTitle
            if (r0 != 0) goto L3b
            android.app.Activity r0 = r4.activity
            if (r0 != 0) goto L9
            goto L3b
        L9:
            android.app.Activity r0 = r4.activity
            java.lang.CharSequence r0 = icu.nullptr.polyglot.youtube.settings.HostToolbarTitleKt.access$findToolbarTitleText(r0)
            r1 = 0
            if (r0 == 0) goto L21
        L14:
            r2 = r0
            r3 = 0
            boolean r2 = icu.nullptr.polyglot.youtube.settings.HostToolbarTitleKt.access$isPolyglotTitle(r2)
            if (r2 != 0) goto L1d
            goto L1e
        L1d:
            r0 = r1
        L1e:
            if (r0 == 0) goto L21
            goto L38
        L21:
            android.app.Activity r0 = r4.activity
            java.lang.CharSequence r0 = r0.getTitle()
            if (r0 == 0) goto L32
            r2 = r0
            r3 = 0
            boolean r2 = icu.nullptr.polyglot.youtube.settings.HostToolbarTitleKt.access$isPolyglotTitle(r2)
            if (r2 != 0) goto L32
            r1 = r0
        L32:
            if (r1 != 0) goto L37
            java.lang.CharSequence r0 = r4.fallbackTitle
            goto L38
        L37:
            r0 = r1
        L38:
            r4.originalTitle = r0
            return
        L3b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.youtube.settings.HostToolbarTitle.capture():void");
    }

    public final void show(CharSequence title) {
        Intrinsics.checkNotNullParameter(title, "title");
        Activity activity = this.activity;
        if (activity != null) {
            HostToolbarTitleKt.setSettingsToolbarTitle(activity, title);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0010, code lost:
    
        if (r0 != null) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void restore() {
        /*
            r4 = this;
            java.lang.CharSequence r0 = r4.originalTitle
            r1 = 0
            if (r0 == 0) goto L13
        L6:
            r2 = r0
            r3 = 0
            boolean r2 = icu.nullptr.polyglot.youtube.settings.HostToolbarTitleKt.access$isPolyglotTitle(r2)
            if (r2 != 0) goto Lf
            goto L10
        Lf:
            r0 = r1
        L10:
            if (r0 == 0) goto L13
            goto L18
        L13:
            java.lang.CharSequence r0 = r4.fallbackTitle
            if (r0 != 0) goto L18
            return
        L18:
            r4.show(r0)
            r4.originalTitle = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.youtube.settings.HostToolbarTitle.restore():void");
    }
}
