package icu.nullptr.polyglot.youtube.settings;

import icu.nullptr.polyglot.R;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* compiled from: SettingsTree.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Licu/nullptr/polyglot/youtube/settings/SettingsIcon;", "", "drawableRes", "", "<init>", "(Ljava/lang/String;II)V", "getDrawableRes", "()I", "Entry", "Enable", "Service", "Endpoint", "ApiKey", "Model", "NetworkCheck", "Language", "Subtitle", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public enum SettingsIcon {
    Entry(R.drawable.outline_translate_24),
    Enable(R.drawable.outline_check_circle_24),
    Service(R.drawable.outline_linked_services_24),
    Endpoint(R.drawable.outline_data_object_24),
    ApiKey(R.drawable.outline_key_24),
    Model(R.drawable.outline_deployed_code_24),
    NetworkCheck(R.drawable.outline_network_check_24),
    Language(R.drawable.outline_language_24),
    Subtitle(R.drawable.outline_closed_caption_24);

    private final int drawableRes;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<SettingsIcon> getEntries() {
        return $ENTRIES;
    }

    SettingsIcon(int drawableRes) {
        this.drawableRes = drawableRes;
    }

    public final int getDrawableRes() {
        return this.drawableRes;
    }
}
