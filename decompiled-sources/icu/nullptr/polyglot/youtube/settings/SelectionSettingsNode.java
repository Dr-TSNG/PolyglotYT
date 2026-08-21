package icu.nullptr.polyglot.youtube.settings;

import icu.nullptr.polyglot.settings.SettingsOption;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.uuid.Uuid;

/* compiled from: SettingsTree.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u001b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001Bq\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\t\u0012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000f\u0010'\u001a\b\u0012\u0004\u0012\u00020\r0\fHÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J\u000f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00050\tHÆ\u0003J\u0015\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00120\u0011HÆ\u0003J\u007f\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00120\u0011HÆ\u0001J\u0013\u0010,\u001a\u00020\n2\b\u0010-\u001a\u0004\u0018\u00010.HÖ\u0003J\t\u0010/\u001a\u000200HÖ\u0001J\t\u00101\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\t¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u00062"}, d2 = {"Licu/nullptr/polyglot/youtube/settings/SelectionSettingsNode;", "Licu/nullptr/polyglot/youtube/settings/SettingsNode;", "key", "", "title", "", "icon", "Licu/nullptr/polyglot/youtube/settings/SettingsIcon;", "visible", "Lkotlin/Function0;", "", "options", "", "Licu/nullptr/polyglot/settings/SettingsOption;", "selectedValue", "selectedLabel", "onSelected", "Lkotlin/Function1;", "", "<init>", "(Ljava/lang/String;Ljava/lang/CharSequence;Licu/nullptr/polyglot/youtube/settings/SettingsIcon;Lkotlin/jvm/functions/Function0;Ljava/util/List;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "getKey", "()Ljava/lang/String;", "getTitle", "()Ljava/lang/CharSequence;", "getIcon", "()Licu/nullptr/polyglot/youtube/settings/SettingsIcon;", "getVisible", "()Lkotlin/jvm/functions/Function0;", "getOptions", "()Ljava/util/List;", "getSelectedValue", "getSelectedLabel", "getOnSelected", "()Lkotlin/jvm/functions/Function1;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final /* data */ class SelectionSettingsNode implements SettingsNode {
    private final SettingsIcon icon;
    private final String key;
    private final Function1<String, Unit> onSelected;
    private final List<SettingsOption> options;
    private final Function0<CharSequence> selectedLabel;
    private final Function0<String> selectedValue;
    private final CharSequence title;
    private final Function0<Boolean> visible;

    public static /* synthetic */ SelectionSettingsNode copy$default(SelectionSettingsNode selectionSettingsNode, String str, CharSequence charSequence, SettingsIcon settingsIcon, Function0 function0, List list, Function0 function02, Function0 function03, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = selectionSettingsNode.key;
        }
        if ((i & 2) != 0) {
            charSequence = selectionSettingsNode.title;
        }
        if ((i & 4) != 0) {
            settingsIcon = selectionSettingsNode.icon;
        }
        if ((i & 8) != 0) {
            function0 = selectionSettingsNode.visible;
        }
        if ((i & 16) != 0) {
            list = selectionSettingsNode.options;
        }
        if ((i & 32) != 0) {
            function02 = selectionSettingsNode.selectedValue;
        }
        if ((i & 64) != 0) {
            function03 = selectionSettingsNode.selectedLabel;
        }
        if ((i & Uuid.SIZE_BITS) != 0) {
            function1 = selectionSettingsNode.onSelected;
        }
        Function0 function04 = function03;
        Function1 function12 = function1;
        List list2 = list;
        Function0 function05 = function02;
        return selectionSettingsNode.copy(str, charSequence, settingsIcon, function0, list2, function05, function04, function12);
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

    public final List<SettingsOption> component5() {
        return this.options;
    }

    public final Function0<String> component6() {
        return this.selectedValue;
    }

    public final Function0<CharSequence> component7() {
        return this.selectedLabel;
    }

    public final Function1<String, Unit> component8() {
        return this.onSelected;
    }

    public final SelectionSettingsNode copy(String key, CharSequence title, SettingsIcon icon, Function0<Boolean> visible, List<SettingsOption> options, Function0<String> selectedValue, Function0<? extends CharSequence> selectedLabel, Function1<? super String, Unit> onSelected) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(visible, "visible");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(selectedValue, "selectedValue");
        Intrinsics.checkNotNullParameter(selectedLabel, "selectedLabel");
        Intrinsics.checkNotNullParameter(onSelected, "onSelected");
        return new SelectionSettingsNode(key, title, icon, visible, options, selectedValue, selectedLabel, onSelected);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SelectionSettingsNode)) {
            return false;
        }
        SelectionSettingsNode selectionSettingsNode = (SelectionSettingsNode) other;
        return Intrinsics.areEqual(this.key, selectionSettingsNode.key) && Intrinsics.areEqual(this.title, selectionSettingsNode.title) && this.icon == selectionSettingsNode.icon && Intrinsics.areEqual(this.visible, selectionSettingsNode.visible) && Intrinsics.areEqual(this.options, selectionSettingsNode.options) && Intrinsics.areEqual(this.selectedValue, selectionSettingsNode.selectedValue) && Intrinsics.areEqual(this.selectedLabel, selectionSettingsNode.selectedLabel) && Intrinsics.areEqual(this.onSelected, selectionSettingsNode.onSelected);
    }

    public int hashCode() {
        return (((((((((((((this.key.hashCode() * 31) + this.title.hashCode()) * 31) + (this.icon == null ? 0 : this.icon.hashCode())) * 31) + this.visible.hashCode()) * 31) + this.options.hashCode()) * 31) + this.selectedValue.hashCode()) * 31) + this.selectedLabel.hashCode()) * 31) + this.onSelected.hashCode();
    }

    public String toString() {
        String str = this.key;
        CharSequence charSequence = this.title;
        return "SelectionSettingsNode(key=" + str + ", title=" + ((Object) charSequence) + ", icon=" + this.icon + ", visible=" + this.visible + ", options=" + this.options + ", selectedValue=" + this.selectedValue + ", selectedLabel=" + this.selectedLabel + ", onSelected=" + this.onSelected + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SelectionSettingsNode(String key, CharSequence title, SettingsIcon icon, Function0<Boolean> visible, List<SettingsOption> options, Function0<String> selectedValue, Function0<? extends CharSequence> selectedLabel, Function1<? super String, Unit> onSelected) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(visible, "visible");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(selectedValue, "selectedValue");
        Intrinsics.checkNotNullParameter(selectedLabel, "selectedLabel");
        Intrinsics.checkNotNullParameter(onSelected, "onSelected");
        this.key = key;
        this.title = title;
        this.icon = icon;
        this.visible = visible;
        this.options = options;
        this.selectedValue = selectedValue;
        this.selectedLabel = selectedLabel;
        this.onSelected = onSelected;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ SelectionSettingsNode(java.lang.String r10, java.lang.CharSequence r11, icu.nullptr.polyglot.youtube.settings.SettingsIcon r12, kotlin.jvm.functions.Function0 r13, java.util.List r14, kotlin.jvm.functions.Function0 r15, kotlin.jvm.functions.Function0 r16, kotlin.jvm.functions.Function1 r17, int r18, kotlin.jvm.internal.DefaultConstructorMarker r19) {
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
            icu.nullptr.polyglot.youtube.settings.SelectionSettingsNode$$ExternalSyntheticLambda0 r13 = new icu.nullptr.polyglot.youtube.settings.SelectionSettingsNode$$ExternalSyntheticLambda0
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
        throw new UnsupportedOperationException("Method not decompiled: icu.nullptr.polyglot.youtube.settings.SelectionSettingsNode.<init>(java.lang.String, java.lang.CharSequence, icu.nullptr.polyglot.youtube.settings.SettingsIcon, kotlin.jvm.functions.Function0, java.util.List, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
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

    public final List<SettingsOption> getOptions() {
        return this.options;
    }

    public final Function0<String> getSelectedValue() {
        return this.selectedValue;
    }

    public final Function0<CharSequence> getSelectedLabel() {
        return this.selectedLabel;
    }

    public final Function1<String, Unit> getOnSelected() {
        return this.onSelected;
    }
}
