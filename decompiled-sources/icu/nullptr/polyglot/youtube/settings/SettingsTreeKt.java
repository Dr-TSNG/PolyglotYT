package icu.nullptr.polyglot.youtube.settings;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SettingsTree.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"summary", "", "Licu/nullptr/polyglot/youtube/settings/SettingsNode;", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class SettingsTreeKt {
    public static final CharSequence summary(SettingsNode $this$summary) {
        Intrinsics.checkNotNullParameter($this$summary, "<this>");
        if ($this$summary instanceof SwitchSettingsNode) {
            return ((SwitchSettingsNode) $this$summary).getSummary().invoke();
        }
        if ($this$summary instanceof SelectionSettingsNode) {
            return ((SelectionSettingsNode) $this$summary).getSelectedLabel().invoke();
        }
        if ($this$summary instanceof TextSettingsNode) {
            return ((TextSettingsNode) $this$summary).getSummary().invoke();
        }
        if ($this$summary instanceof ActionSettingsNode) {
            return ((ActionSettingsNode) $this$summary).getSummary().invoke();
        }
        if ($this$summary instanceof SettingsScreenNode) {
            return null;
        }
        throw new NoWhenBranchMatchedException();
    }
}
