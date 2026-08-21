package icu.nullptr.polyglot.youtube.settings;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SettingsTree.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\t\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00050\tHÆ\u0003J\t\u0010 \u001a\u00020\rHÆ\u0003JS\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010\"\u001a\u00020\n2\b\u0010#\u001a\u0004\u0018\u00010$HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006("}, d2 = {"Licu/nullptr/polyglot/youtube/settings/ActionSettingsNode;", "Licu/nullptr/polyglot/youtube/settings/SettingsNode;", "key", "", "title", "", "icon", "Licu/nullptr/polyglot/youtube/settings/SettingsIcon;", "visible", "Lkotlin/Function0;", "", "summary", "action", "Licu/nullptr/polyglot/youtube/settings/SettingsAction;", "<init>", "(Ljava/lang/String;Ljava/lang/CharSequence;Licu/nullptr/polyglot/youtube/settings/SettingsIcon;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Licu/nullptr/polyglot/youtube/settings/SettingsAction;)V", "getKey", "()Ljava/lang/String;", "getTitle", "()Ljava/lang/CharSequence;", "getIcon", "()Licu/nullptr/polyglot/youtube/settings/SettingsIcon;", "getVisible", "()Lkotlin/jvm/functions/Function0;", "getSummary", "getAction", "()Licu/nullptr/polyglot/youtube/settings/SettingsAction;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final /* data */ class ActionSettingsNode implements SettingsNode {
    private final SettingsAction action;
    private final SettingsIcon icon;
    private final String key;
    private final Function0<CharSequence> summary;
    private final CharSequence title;
    private final Function0<Boolean> visible;

    public static /* synthetic */ ActionSettingsNode copy$default(ActionSettingsNode actionSettingsNode, String str, CharSequence charSequence, SettingsIcon settingsIcon, Function0 function0, Function0 function02, SettingsAction settingsAction, int i, Object obj) {
        if ((i & 1) != 0) {
            str = actionSettingsNode.key;
        }
        if ((i & 2) != 0) {
            charSequence = actionSettingsNode.title;
        }
        if ((i & 4) != 0) {
            settingsIcon = actionSettingsNode.icon;
        }
        if ((i & 8) != 0) {
            function0 = actionSettingsNode.visible;
        }
        if ((i & 16) != 0) {
            function02 = actionSettingsNode.summary;
        }
        if ((i & 32) != 0) {
            settingsAction = actionSettingsNode.action;
        }
        Function0 function03 = function02;
        SettingsAction settingsAction2 = settingsAction;
        return actionSettingsNode.copy(str, charSequence, settingsIcon, function0, function03, settingsAction2);
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

    public final Function0<CharSequence> component5() {
        return this.summary;
    }

    /* renamed from: component6, reason: from getter */
    public final SettingsAction getAction() {
        return this.action;
    }

    public final ActionSettingsNode copy(String key, CharSequence title, SettingsIcon icon, Function0<Boolean> visible, Function0<? extends CharSequence> summary, SettingsAction action) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(visible, "visible");
        Intrinsics.checkNotNullParameter(summary, "summary");
        Intrinsics.checkNotNullParameter(action, "action");
        return new ActionSettingsNode(key, title, icon, visible, summary, action);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActionSettingsNode)) {
            return false;
        }
        ActionSettingsNode actionSettingsNode = (ActionSettingsNode) other;
        return Intrinsics.areEqual(this.key, actionSettingsNode.key) && Intrinsics.areEqual(this.title, actionSettingsNode.title) && this.icon == actionSettingsNode.icon && Intrinsics.areEqual(this.visible, actionSettingsNode.visible) && Intrinsics.areEqual(this.summary, actionSettingsNode.summary) && this.action == actionSettingsNode.action;
    }

    public int hashCode() {
        return (((((((((this.key.hashCode() * 31) + this.title.hashCode()) * 31) + (this.icon == null ? 0 : this.icon.hashCode())) * 31) + this.visible.hashCode()) * 31) + this.summary.hashCode()) * 31) + this.action.hashCode();
    }

    public String toString() {
        String str = this.key;
        CharSequence charSequence = this.title;
        return "ActionSettingsNode(key=" + str + ", title=" + ((Object) charSequence) + ", icon=" + this.icon + ", visible=" + this.visible + ", summary=" + this.summary + ", action=" + this.action + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ActionSettingsNode(String key, CharSequence title, SettingsIcon icon, Function0<Boolean> visible, Function0<? extends CharSequence> summary, SettingsAction action) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(visible, "visible");
        Intrinsics.checkNotNullParameter(summary, "summary");
        Intrinsics.checkNotNullParameter(action, "action");
        this.key = key;
        this.title = title;
        this.icon = icon;
        this.visible = visible;
        this.summary = summary;
        this.action = action;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ ActionSettingsNode(java.lang.String r8, java.lang.CharSequence r9, icu.nullptr.polyglot.youtube.settings.SettingsIcon r10, kotlin.jvm.functions.Function0 r11, kotlin.jvm.functions.Function0 r12, icu.nullptr.polyglot.youtube.settings.SettingsAction r13, int r14, kotlin.jvm.internal.DefaultConstructorMarker r15) {
        /*
            r7 = this;
            r15 = r14 & 4
            if (r15 == 0) goto L7
            r10 = 0
            r3 = r10
            goto L8
        L7:
            r3 = r10
        L8:
            r10 = r14 & 8
            if (r10 == 0) goto L13
            icu.nullptr.polyglot.youtube.settings.ActionSettingsNode$$ExternalSyntheticLambda0 r11 = new icu.nullptr.polyglot.youtube.settings.ActionSettingsNode$$ExternalSyntheticLambda0
            r11.<init>()
            r4 = r11
            goto L14
        L13:
            r4 = r11
        L14:
            r0 = r7
            r1 = r8
            r2 = r9
            r5 = r12
            r6 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.youtube.settings.ActionSettingsNode.<init>(java.lang.String, java.lang.CharSequence, icu.nullptr.polyglot.youtube.settings.SettingsIcon, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, icu.nullptr.polyglot.youtube.settings.SettingsAction, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
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

    public final Function0<CharSequence> getSummary() {
        return this.summary;
    }

    public final SettingsAction getAction() {
        return this.action;
    }
}
