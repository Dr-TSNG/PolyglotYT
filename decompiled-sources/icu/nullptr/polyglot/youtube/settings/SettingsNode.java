package icu.nullptr.polyglot.youtube.settings;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: SettingsTree.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bp\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\u0082\u0001\u0005\u0013\u0014\u0015\u0016\u0017¨\u0006\u0018À\u0006\u0003"}, d2 = {"Licu/nullptr/polyglot/youtube/settings/SettingsNode;", "", "key", "", "getKey", "()Ljava/lang/String;", "title", "", "getTitle", "()Ljava/lang/CharSequence;", "icon", "Licu/nullptr/polyglot/youtube/settings/SettingsIcon;", "getIcon", "()Licu/nullptr/polyglot/youtube/settings/SettingsIcon;", "visible", "Lkotlin/Function0;", "", "getVisible", "()Lkotlin/jvm/functions/Function0;", "Licu/nullptr/polyglot/youtube/settings/ActionSettingsNode;", "Licu/nullptr/polyglot/youtube/settings/SelectionSettingsNode;", "Licu/nullptr/polyglot/youtube/settings/SettingsScreenNode;", "Licu/nullptr/polyglot/youtube/settings/SwitchSettingsNode;", "Licu/nullptr/polyglot/youtube/settings/TextSettingsNode;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public interface SettingsNode {
    SettingsIcon getIcon();

    String getKey();

    CharSequence getTitle();

    Function0<Boolean> getVisible();
}
