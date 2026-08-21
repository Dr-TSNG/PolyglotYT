package icu.nullptr.polyglot.youtube.settings;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.uuid.Uuid;

/* compiled from: SettingsTree.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u001b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0080\b\u0018\u00002\u00020\u0001Bk\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\t\u0012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\t\u0010&\u001a\u00020\fHÆ\u0003J\u000f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00050\tHÆ\u0003J\u0015\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00110\u0010HÆ\u0003Jy\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0014\b\u0002\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00110\u0010HÆ\u0001J\u0013\u0010+\u001a\u00020\n2\b\u0010,\u001a\u0004\u0018\u00010-HÖ\u0003J\t\u0010.\u001a\u00020\fHÖ\u0001J\t\u0010/\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001bR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\t¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001bR\u001d\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b \u0010!¨\u00060"}, d2 = {"Licu/nullptr/polyglot/youtube/settings/TextSettingsNode;", "Licu/nullptr/polyglot/youtube/settings/SettingsNode;", "key", "", "title", "", "icon", "Licu/nullptr/polyglot/youtube/settings/SettingsIcon;", "visible", "Lkotlin/Function0;", "", "inputType", "", "value", "summary", "onSubmitted", "Lkotlin/Function1;", "", "<init>", "(Ljava/lang/String;Ljava/lang/CharSequence;Licu/nullptr/polyglot/youtube/settings/SettingsIcon;Lkotlin/jvm/functions/Function0;ILkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "getKey", "()Ljava/lang/String;", "getTitle", "()Ljava/lang/CharSequence;", "getIcon", "()Licu/nullptr/polyglot/youtube/settings/SettingsIcon;", "getVisible", "()Lkotlin/jvm/functions/Function0;", "getInputType", "()I", "getValue", "getSummary", "getOnSubmitted", "()Lkotlin/jvm/functions/Function1;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final /* data */ class TextSettingsNode implements SettingsNode {
    private final SettingsIcon icon;
    private final int inputType;
    private final String key;
    private final Function1<String, Unit> onSubmitted;
    private final Function0<CharSequence> summary;
    private final CharSequence title;
    private final Function0<String> value;
    private final Function0<Boolean> visible;

    public static /* synthetic */ TextSettingsNode copy$default(TextSettingsNode textSettingsNode, String str, CharSequence charSequence, SettingsIcon settingsIcon, Function0 function0, int i, Function0 function02, Function0 function03, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = textSettingsNode.key;
        }
        if ((i2 & 2) != 0) {
            charSequence = textSettingsNode.title;
        }
        if ((i2 & 4) != 0) {
            settingsIcon = textSettingsNode.icon;
        }
        if ((i2 & 8) != 0) {
            function0 = textSettingsNode.visible;
        }
        if ((i2 & 16) != 0) {
            i = textSettingsNode.inputType;
        }
        if ((i2 & 32) != 0) {
            function02 = textSettingsNode.value;
        }
        if ((i2 & 64) != 0) {
            function03 = textSettingsNode.summary;
        }
        if ((i2 & Uuid.SIZE_BITS) != 0) {
            function1 = textSettingsNode.onSubmitted;
        }
        Function0 function04 = function03;
        Function1 function12 = function1;
        int i3 = i;
        Function0 function05 = function02;
        return textSettingsNode.copy(str, charSequence, settingsIcon, function0, i3, function05, function04, function12);
    }

    /* renamed from: component1, reason: from getter */
    public final String getKey() {
        return this.key;
    }

    /* renamed from: component2, reason: from getter */
    public final CharSequence getTitle() {
        return this.title;
    }

    /* renamed from: component3, reason: from getter */
    public final SettingsIcon getIcon() {
        return this.icon;
    }

    public final Function0<Boolean> component4() {
        return this.visible;
    }

    /* renamed from: component5, reason: from getter */
    public final int getInputType() {
        return this.inputType;
    }

    public final Function0<String> component6() {
        return this.value;
    }

    public final Function0<CharSequence> component7() {
        return this.summary;
    }

    public final Function1<String, Unit> component8() {
        return this.onSubmitted;
    }

    public final TextSettingsNode copy(String key, CharSequence title, SettingsIcon icon, Function0<Boolean> visible, int inputType, Function0<String> value, Function0<? extends CharSequence> summary, Function1<? super String, Unit> onSubmitted) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(visible, "visible");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(summary, "summary");
        Intrinsics.checkNotNullParameter(onSubmitted, "onSubmitted");
        return new TextSettingsNode(key, title, icon, visible, inputType, value, summary, onSubmitted);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TextSettingsNode)) {
            return false;
        }
        TextSettingsNode textSettingsNode = (TextSettingsNode) other;
        return Intrinsics.areEqual(this.key, textSettingsNode.key) && Intrinsics.areEqual(this.title, textSettingsNode.title) && this.icon == textSettingsNode.icon && Intrinsics.areEqual(this.visible, textSettingsNode.visible) && this.inputType == textSettingsNode.inputType && Intrinsics.areEqual(this.value, textSettingsNode.value) && Intrinsics.areEqual(this.summary, textSettingsNode.summary) && Intrinsics.areEqual(this.onSubmitted, textSettingsNode.onSubmitted);
    }

    public int hashCode() {
        return (((((((((((((this.key.hashCode() * 31) + this.title.hashCode()) * 31) + (this.icon == null ? 0 : this.icon.hashCode())) * 31) + this.visible.hashCode()) * 31) + Integer.hashCode(this.inputType)) * 31) + this.value.hashCode()) * 31) + this.summary.hashCode()) * 31) + this.onSubmitted.hashCode();
    }

    public String toString() {
        String str = this.key;
        CharSequence charSequence = this.title;
        return "TextSettingsNode(key=" + str + ", title=" + ((Object) charSequence) + ", icon=" + this.icon + ", visible=" + this.visible + ", inputType=" + this.inputType + ", value=" + this.value + ", summary=" + this.summary + ", onSubmitted=" + this.onSubmitted + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TextSettingsNode(String key, CharSequence title, SettingsIcon icon, Function0<Boolean> visible, int inputType, Function0<String> value, Function0<? extends CharSequence> summary, Function1<? super String, Unit> onSubmitted) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(visible, "visible");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(summary, "summary");
        Intrinsics.checkNotNullParameter(onSubmitted, "onSubmitted");
        this.key = key;
        this.title = title;
        this.icon = icon;
        this.visible = visible;
        this.inputType = inputType;
        this.value = value;
        this.summary = summary;
        this.onSubmitted = onSubmitted;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ TextSettingsNode(java.lang.String r10, java.lang.CharSequence r11, icu.nullptr.polyglot.youtube.settings.SettingsIcon r12, kotlin.jvm.functions.Function0 r13, int r14, kotlin.jvm.functions.Function0 r15, kotlin.jvm.functions.Function0 r16, kotlin.jvm.functions.Function1 r17, int r18, kotlin.jvm.internal.DefaultConstructorMarker r19) {
        /*
            r9 = this;
            r0 = r18 & 4
            if (r0 == 0) goto L7
            r12 = 0
            r3 = r12
            goto L8
        L7:
            r3 = r12
        L8:
            r12 = r18 & 8
            if (r12 == 0) goto L13
            icu.nullptr.polyglot.youtube.settings.TextSettingsNode$$ExternalSyntheticLambda0 r13 = new icu.nullptr.polyglot.youtube.settings.TextSettingsNode$$ExternalSyntheticLambda0
            r13.<init>()
            r4 = r13
            goto L14
        L13:
            r4 = r13
        L14:
            r0 = r9
            r1 = r10
            r2 = r11
            r5 = r14
            r6 = r15
            r7 = r16
            r8 = r17
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.youtube.settings.TextSettingsNode.<init>(java.lang.String, java.lang.CharSequence, icu.nullptr.polyglot.youtube.settings.SettingsIcon, kotlin.jvm.functions.Function0, int, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    @Override // icu.nullptr.polyglot.youtube.settings.SettingsNode
    public String getKey() {
        return this.key;
    }

    @Override // icu.nullptr.polyglot.youtube.settings.SettingsNode
    public CharSequence getTitle() {
        return this.title;
    }

    @Override // icu.nullptr.polyglot.youtube.settings.SettingsNode
    public SettingsIcon getIcon() {
        return this.icon;
    }

    static final boolean _init_$lambda$0() {
        return true;
    }

    @Override // icu.nullptr.polyglot.youtube.settings.SettingsNode
    public Function0<Boolean> getVisible() {
        return this.visible;
    }

    public final int getInputType() {
        return this.inputType;
    }

    public final Function0<String> getValue() {
        return this.value;
    }

    public final Function0<CharSequence> getSummary() {
        return this.summary;
    }

    public final Function1<String, Unit> getOnSubmitted() {
        return this.onSubmitted;
    }
}
