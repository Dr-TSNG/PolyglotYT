package icu.nullptr.polyglot.youtube.settings;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SettingsTree.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0014\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\fHÆ\u0003JI\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\fHÆ\u0001J\u0013\u0010\u001f\u001a\u00020\n2\b\u0010 \u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006%"}, d2 = {"Licu/nullptr/polyglot/youtube/settings/SettingsScreenNode;", "Licu/nullptr/polyglot/youtube/settings/SettingsNode;", "key", "", "title", "", "icon", "Licu/nullptr/polyglot/youtube/settings/SettingsIcon;", "visible", "Lkotlin/Function0;", "", "children", "", "<init>", "(Ljava/lang/String;Ljava/lang/CharSequence;Licu/nullptr/polyglot/youtube/settings/SettingsIcon;Lkotlin/jvm/functions/Function0;Ljava/util/List;)V", "getKey", "()Ljava/lang/String;", "getTitle", "()Ljava/lang/CharSequence;", "getIcon", "()Licu/nullptr/polyglot/youtube/settings/SettingsIcon;", "getVisible", "()Lkotlin/jvm/functions/Function0;", "getChildren", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final /* data */ class SettingsScreenNode implements SettingsNode {
    private final List<SettingsNode> children;
    private final SettingsIcon icon;
    private final String key;
    private final CharSequence title;
    private final Function0<Boolean> visible;

    public static /* synthetic */ SettingsScreenNode copy$default(SettingsScreenNode settingsScreenNode, String str, CharSequence charSequence, SettingsIcon settingsIcon, Function0 function0, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = settingsScreenNode.key;
        }
        if ((i & 2) != 0) {
            charSequence = settingsScreenNode.title;
        }
        if ((i & 4) != 0) {
            settingsIcon = settingsScreenNode.icon;
        }
        if ((i & 8) != 0) {
            function0 = settingsScreenNode.visible;
        }
        if ((i & 16) != 0) {
            list = settingsScreenNode.children;
        }
        List list2 = list;
        SettingsIcon settingsIcon2 = settingsIcon;
        return settingsScreenNode.copy(str, charSequence, settingsIcon2, function0, list2);
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

    public final List<SettingsNode> component5() {
        return this.children;
    }

    public final SettingsScreenNode copy(String key, CharSequence title, SettingsIcon icon, Function0<Boolean> visible, List<? extends SettingsNode> children) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(visible, "visible");
        Intrinsics.checkNotNullParameter(children, "children");
        return new SettingsScreenNode(key, title, icon, visible, children);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SettingsScreenNode)) {
            return false;
        }
        SettingsScreenNode settingsScreenNode = (SettingsScreenNode) other;
        return Intrinsics.areEqual(this.key, settingsScreenNode.key) && Intrinsics.areEqual(this.title, settingsScreenNode.title) && this.icon == settingsScreenNode.icon && Intrinsics.areEqual(this.visible, settingsScreenNode.visible) && Intrinsics.areEqual(this.children, settingsScreenNode.children);
    }

    public int hashCode() {
        return (((((((this.key.hashCode() * 31) + this.title.hashCode()) * 31) + (this.icon == null ? 0 : this.icon.hashCode())) * 31) + this.visible.hashCode()) * 31) + this.children.hashCode();
    }

    public String toString() {
        String str = this.key;
        CharSequence charSequence = this.title;
        return "SettingsScreenNode(key=" + str + ", title=" + ((Object) charSequence) + ", icon=" + this.icon + ", visible=" + this.visible + ", children=" + this.children + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SettingsScreenNode(String key, CharSequence title, SettingsIcon icon, Function0<Boolean> visible, List<? extends SettingsNode> children) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(visible, "visible");
        Intrinsics.checkNotNullParameter(children, "children");
        this.key = key;
        this.title = title;
        this.icon = icon;
        this.visible = visible;
        this.children = children;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ SettingsScreenNode(java.lang.String r7, java.lang.CharSequence r8, icu.nullptr.polyglot.youtube.settings.SettingsIcon r9, kotlin.jvm.functions.Function0 r10, java.util.List r11, int r12, kotlin.jvm.internal.DefaultConstructorMarker r13) {
        /*
            r6 = this;
            r13 = r12 & 4
            if (r13 == 0) goto L7
            r9 = 0
            r3 = r9
            goto L8
        L7:
            r3 = r9
        L8:
            r9 = r12 & 8
            if (r9 == 0) goto L13
            icu.nullptr.polyglot.youtube.settings.SettingsScreenNode$$ExternalSyntheticLambda0 r10 = new icu.nullptr.polyglot.youtube.settings.SettingsScreenNode$$ExternalSyntheticLambda0
            r10.<init>()
            r4 = r10
            goto L14
        L13:
            r4 = r10
        L14:
            r0 = r6
            r1 = r7
            r2 = r8
            r5 = r11
            r0.<init>(r1, r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.youtube.settings.SettingsScreenNode.<init>(java.lang.String, java.lang.CharSequence, icu.nullptr.polyglot.youtube.settings.SettingsIcon, kotlin.jvm.functions.Function0, java.util.List, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
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

    public final List<SettingsNode> getChildren() {
        return this.children;
    }
}
