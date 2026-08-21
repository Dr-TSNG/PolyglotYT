package icu.nullptr.polyglot.youtube.settings;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SettingsTree.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0018\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\t\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00050\tHÆ\u0003J\u0015\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000f0\u000eHÆ\u0003Jo\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000f0\u000eHÆ\u0001J\u0013\u0010&\u001a\u00020\n2\b\u0010'\u001a\u0004\u0018\u00010(HÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\t\u0010+\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006,"}, d2 = {"Licu/nullptr/polyglot/youtube/settings/SwitchSettingsNode;", "Licu/nullptr/polyglot/youtube/settings/SettingsNode;", "key", "", "title", "", "icon", "Licu/nullptr/polyglot/youtube/settings/SettingsIcon;", "visible", "Lkotlin/Function0;", "", "checked", "summary", "onChanged", "Lkotlin/Function1;", "", "<init>", "(Ljava/lang/String;Ljava/lang/CharSequence;Licu/nullptr/polyglot/youtube/settings/SettingsIcon;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "getKey", "()Ljava/lang/String;", "getTitle", "()Ljava/lang/CharSequence;", "getIcon", "()Licu/nullptr/polyglot/youtube/settings/SettingsIcon;", "getVisible", "()Lkotlin/jvm/functions/Function0;", "getChecked", "getSummary", "getOnChanged", "()Lkotlin/jvm/functions/Function1;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final /* data */ class SwitchSettingsNode implements SettingsNode {
    private final Function0<Boolean> checked;
    private final SettingsIcon icon;
    private final String key;
    private final Function1<Boolean, Unit> onChanged;
    private final Function0<CharSequence> summary;
    private final CharSequence title;
    private final Function0<Boolean> visible;

    public static /* synthetic */ SwitchSettingsNode copy$default(SwitchSettingsNode switchSettingsNode, String str, CharSequence charSequence, SettingsIcon settingsIcon, Function0 function0, Function0 function02, Function0 function03, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = switchSettingsNode.key;
        }
        if ((i & 2) != 0) {
            charSequence = switchSettingsNode.title;
        }
        if ((i & 4) != 0) {
            settingsIcon = switchSettingsNode.icon;
        }
        if ((i & 8) != 0) {
            function0 = switchSettingsNode.visible;
        }
        if ((i & 16) != 0) {
            function02 = switchSettingsNode.checked;
        }
        if ((i & 32) != 0) {
            function03 = switchSettingsNode.summary;
        }
        if ((i & 64) != 0) {
            function1 = switchSettingsNode.onChanged;
        }
        Function0 function04 = function03;
        Function1 function12 = function1;
        Function0 function05 = function02;
        SettingsIcon settingsIcon2 = settingsIcon;
        return switchSettingsNode.copy(str, charSequence, settingsIcon2, function0, function05, function04, function12);
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

    public final Function0<Boolean> component5() {
        return this.checked;
    }

    public final Function0<CharSequence> component6() {
        return this.summary;
    }

    public final Function1<Boolean, Unit> component7() {
        return this.onChanged;
    }

    public final SwitchSettingsNode copy(String key, CharSequence title, SettingsIcon icon, Function0<Boolean> visible, Function0<Boolean> checked, Function0<? extends CharSequence> summary, Function1<? super Boolean, Unit> onChanged) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(visible, "visible");
        Intrinsics.checkNotNullParameter(checked, "checked");
        Intrinsics.checkNotNullParameter(summary, "summary");
        Intrinsics.checkNotNullParameter(onChanged, "onChanged");
        return new SwitchSettingsNode(key, title, icon, visible, checked, summary, onChanged);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SwitchSettingsNode)) {
            return false;
        }
        SwitchSettingsNode switchSettingsNode = (SwitchSettingsNode) other;
        return Intrinsics.areEqual(this.key, switchSettingsNode.key) && Intrinsics.areEqual(this.title, switchSettingsNode.title) && this.icon == switchSettingsNode.icon && Intrinsics.areEqual(this.visible, switchSettingsNode.visible) && Intrinsics.areEqual(this.checked, switchSettingsNode.checked) && Intrinsics.areEqual(this.summary, switchSettingsNode.summary) && Intrinsics.areEqual(this.onChanged, switchSettingsNode.onChanged);
    }

    public int hashCode() {
        return (((((((((((this.key.hashCode() * 31) + this.title.hashCode()) * 31) + (this.icon == null ? 0 : this.icon.hashCode())) * 31) + this.visible.hashCode()) * 31) + this.checked.hashCode()) * 31) + this.summary.hashCode()) * 31) + this.onChanged.hashCode();
    }

    public String toString() {
        String str = this.key;
        CharSequence charSequence = this.title;
        return "SwitchSettingsNode(key=" + str + ", title=" + ((Object) charSequence) + ", icon=" + this.icon + ", visible=" + this.visible + ", checked=" + this.checked + ", summary=" + this.summary + ", onChanged=" + this.onChanged + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SwitchSettingsNode(String key, CharSequence title, SettingsIcon icon, Function0<Boolean> visible, Function0<Boolean> checked, Function0<? extends CharSequence> summary, Function1<? super Boolean, Unit> onChanged) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(visible, "visible");
        Intrinsics.checkNotNullParameter(checked, "checked");
        Intrinsics.checkNotNullParameter(summary, "summary");
        Intrinsics.checkNotNullParameter(onChanged, "onChanged");
        this.key = key;
        this.title = title;
        this.icon = icon;
        this.visible = visible;
        this.checked = checked;
        this.summary = summary;
        this.onChanged = onChanged;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ SwitchSettingsNode(java.lang.String r9, java.lang.CharSequence r10, icu.nullptr.polyglot.youtube.settings.SettingsIcon r11, kotlin.jvm.functions.Function0 r12, kotlin.jvm.functions.Function0 r13, kotlin.jvm.functions.Function0 r14, kotlin.jvm.functions.Function1 r15, int r16, kotlin.jvm.internal.DefaultConstructorMarker r17) {
        /*
            r8 = this;
            r0 = r16 & 4
            if (r0 == 0) goto L7
            r11 = 0
            r3 = r11
            goto L8
        L7:
            r3 = r11
        L8:
            r11 = r16 & 8
            if (r11 == 0) goto L13
            icu.nullptr.polyglot.youtube.settings.SwitchSettingsNode$$ExternalSyntheticLambda0 r12 = new icu.nullptr.polyglot.youtube.settings.SwitchSettingsNode$$ExternalSyntheticLambda0
            r12.<init>()
            r4 = r12
            goto L14
        L13:
            r4 = r12
        L14:
            r0 = r8
            r1 = r9
            r2 = r10
            r5 = r13
            r6 = r14
            r7 = r15
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.youtube.settings.SwitchSettingsNode.<init>(java.lang.String, java.lang.CharSequence, icu.nullptr.polyglot.youtube.settings.SettingsIcon, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
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

    public final Function0<Boolean> getChecked() {
        return this.checked;
    }

    public final Function0<CharSequence> getSummary() {
        return this.summary;
    }

    public final Function1<Boolean, Unit> getOnChanged() {
        return this.onChanged;
    }
}
