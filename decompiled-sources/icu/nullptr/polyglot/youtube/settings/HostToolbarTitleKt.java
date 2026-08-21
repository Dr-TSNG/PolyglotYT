package icu.nullptr.polyglot.youtube.settings;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

/* compiled from: HostToolbarTitle.kt */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0000\u001a\u0014\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0001H\u0002\u001a\u000e\u0010\u0007\u001a\u0004\u0018\u00010\u0001*\u00020\u0005H\u0002\u001a\f\u0010\b\u001a\u00020\t*\u00020\u0001H\u0002\u001a\u000e\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\u0005H\u0002\u001a\u0016\u0010\f\u001a\u0004\u0018\u00010\r*\u00020\u00052\u0006\u0010\u000e\u001a\u00020\rH\u0002\u001a*\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\b\b\u0000\u0010\u0011*\u00020\r*\u00020\r2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0013H\u0002\"\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017\"\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017¨\u0006\u0019"}, d2 = {"hostSettingsTitle", "", "Landroid/content/Context;", "setSettingsToolbarTitle", "", "Landroid/app/Activity;", "title", "findToolbarTitleText", "isPolyglotTitle", "", "findToolbarTitleView", "Landroid/widget/TextView;", "findToolbarContainer", "Landroid/view/View;", "decor", "descendants", "Lkotlin/sequences/Sequence;", "T", "type", "Ljava/lang/Class;", "TOOLBAR_RESOURCE_NAMES", "", "", "[Ljava/lang/String;", "HOST_SETTINGS_TITLE_RESOURCES", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class HostToolbarTitleKt {
    private static final String[] TOOLBAR_RESOURCE_NAMES = {"toolbar", "settings_toolbar_layout"};
    private static final String[] HOST_SETTINGS_TITLE_RESOURCES = {"settings", "settings_title"};

    public static final CharSequence hostSettingsTitle(Context $this$hostSettingsTitle) {
        Object m10constructorimpl;
        Intrinsics.checkNotNullParameter($this$hostSettingsTitle, "<this>");
        String[] strArr = HOST_SETTINGS_TITLE_RESOURCES;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                return null;
            }
            String name = strArr[i];
            int id = ReflectionSupportKt.resourceId($this$hostSettingsTitle, name, "string");
            if (id == 0) {
                i++;
            } else {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    m10constructorimpl = Result.m10constructorimpl($this$hostSettingsTitle.getText(id));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    m10constructorimpl = Result.m10constructorimpl(ResultKt.createFailure(th));
                }
                return (CharSequence) (Result.m16isFailureimpl(m10constructorimpl) ? null : m10constructorimpl);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setSettingsToolbarTitle(final Activity $this$setSettingsToolbarTitle, final CharSequence title) {
        View decor;
        $this$setSettingsToolbarTitle.setTitle(title);
        Window window = $this$setSettingsToolbarTitle.getWindow();
        if (window == null || (decor = window.getDecorView()) == null) {
            return;
        }
        decor.post(new Runnable() { // from class: icu.nullptr.polyglot.youtube.settings.HostToolbarTitleKt$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                HostToolbarTitleKt.setSettingsToolbarTitle$lambda$1($this$setSettingsToolbarTitle, title);
            }
        });
    }

    static final void setSettingsToolbarTitle$lambda$1(Activity $this_setSettingsToolbarTitle, CharSequence $title) {
        $this_setSettingsToolbarTitle.setTitle($title);
        TextView findToolbarTitleView = findToolbarTitleView($this_setSettingsToolbarTitle);
        if (findToolbarTitleView != null) {
            findToolbarTitleView.setText($title);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence findToolbarTitleText(Activity $this$findToolbarTitleText) {
        CharSequence text;
        TextView findToolbarTitleView = findToolbarTitleView($this$findToolbarTitleText);
        if (findToolbarTitleView == null || (text = findToolbarTitleView.getText()) == null || StringsKt.isBlank(text)) {
            return null;
        }
        return text;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isPolyglotTitle(CharSequence $this$isPolyglotTitle) {
        return Intrinsics.areEqual($this$isPolyglotTitle.toString(), SettingsConstantsKt.ENTRY_TITLE);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004d A[EDGE_INSN: B:17:0x004d->B:18:0x004d BREAK  A[LOOP:0: B:8:0x0023->B:23:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:? A[LOOP:0: B:8:0x0023->B:23:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final android.widget.TextView findToolbarTitleView(android.app.Activity r12) {
        /*
            android.view.Window r0 = r12.getWindow()
            r1 = 0
            if (r0 == 0) goto Lae
            android.view.View r0 = r0.getDecorView()
            if (r0 != 0) goto Lf
            goto Lae
        Lf:
            android.view.View r2 = findToolbarContainer(r12, r0)
            r3 = 1
            if (r2 == 0) goto L55
        L17:
            java.lang.Class<android.widget.TextView> r4 = android.widget.TextView.class
            kotlin.sequences.Sequence r4 = descendants(r2, r4)
            r5 = 0
            java.util.Iterator r6 = r4.iterator()
        L23:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L4c
            java.lang.Object r7 = r6.next()
            r8 = r7
            android.widget.TextView r8 = (android.widget.TextView) r8
            r9 = 0
            boolean r10 = r8.isShown()
            if (r10 == 0) goto L48
            java.lang.CharSequence r10 = r8.getText()
            java.lang.String r11 = "getText(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r11)
            boolean r10 = kotlin.text.StringsKt.isBlank(r10)
            if (r10 != 0) goto L48
            r10 = r3
            goto L49
        L48:
            r10 = 0
        L49:
            if (r10 == 0) goto L23
            goto L4d
        L4c:
            r7 = r1
        L4d:
            android.widget.TextView r7 = (android.widget.TextView) r7
            if (r7 == 0) goto L55
        L53:
            r1 = 0
            return r7
        L55:
            r4 = 2
            int[] r4 = new int[r4]
            java.lang.Class<android.widget.TextView> r5 = android.widget.TextView.class
            kotlin.sequences.Sequence r5 = descendants(r0, r5)
            icu.nullptr.polyglot.youtube.settings.HostToolbarTitleKt$$ExternalSyntheticLambda0 r6 = new icu.nullptr.polyglot.youtube.settings.HostToolbarTitleKt$$ExternalSyntheticLambda0
            r6.<init>()
            kotlin.sequences.Sequence r5 = kotlin.sequences.SequencesKt.filter(r5, r6)
            icu.nullptr.polyglot.youtube.settings.HostToolbarTitleKt$$ExternalSyntheticLambda1 r6 = new icu.nullptr.polyglot.youtube.settings.HostToolbarTitleKt$$ExternalSyntheticLambda1
            r6.<init>()
            kotlin.sequences.Sequence r5 = kotlin.sequences.SequencesKt.filter(r5, r6)
            r6 = 0
            java.util.Iterator r7 = r5.iterator()
            boolean r8 = r7.hasNext()
            if (r8 != 0) goto L7d
            goto Lab
        L7d:
            java.lang.Object r1 = r7.next()
            boolean r8 = r7.hasNext()
            if (r8 != 0) goto L88
            goto Lab
        L88:
            r8 = r1
            android.widget.TextView r8 = (android.widget.TextView) r8
            r9 = 0
            r8.getLocationOnScreen(r4)
            r8 = r4[r3]
        L92:
            java.lang.Object r9 = r7.next()
            r10 = r9
            android.widget.TextView r10 = (android.widget.TextView) r10
            r11 = 0
            r10.getLocationOnScreen(r4)
            r10 = r4[r3]
            if (r8 <= r10) goto La4
            r1 = r9
            r8 = r10
        La4:
            boolean r9 = r7.hasNext()
            if (r9 != 0) goto L92
        Lab:
            android.widget.TextView r1 = (android.widget.TextView) r1
            return r1
        Lae:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.youtube.settings.HostToolbarTitleKt.findToolbarTitleView(android.app.Activity):android.widget.TextView");
    }

    static final boolean findToolbarTitleView$lambda$5(TextView it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.isShown()) {
            CharSequence text = it.getText();
            Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
            if (!StringsKt.isBlank(text)) {
                return true;
            }
        }
        return false;
    }

    static final boolean findToolbarTitleView$lambda$6(int[] $screenLocation, View $decor, TextView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.getLocationOnScreen($screenLocation);
        return $screenLocation[1] < $decor.getHeight() / 5;
    }

    private static final View findToolbarContainer(Activity $this$findToolbarContainer, View decor) {
        View findViewById;
        for (String name : TOOLBAR_RESOURCE_NAMES) {
            int id = $this$findToolbarContainer.getResources().getIdentifier(name, "id", $this$findToolbarContainer.getPackageName());
            if (id != 0 && (findViewById = decor.findViewById(id)) != null) {
                return findViewById;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T extends View> Sequence<T> descendants(View $this$descendants, Class<T> cls) {
        return SequencesKt.sequence(new HostToolbarTitleKt$descendants$1(cls, $this$descendants, null));
    }
}
