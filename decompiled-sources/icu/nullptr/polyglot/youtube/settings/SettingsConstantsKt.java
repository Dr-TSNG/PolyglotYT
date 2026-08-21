package icu.nullptr.polyglot.youtube.settings;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;

/* compiled from: SettingsConstants.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\fX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\fX\u0080T¢\u0006\u0002\n\u0000\"\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"ENTRY_TITLE", "", "ENTRY_KEY", "PREFERENCE_CLASS_NAME", "PREFERENCE_GROUP_CLASS_NAME", "PREFERENCE_SCREEN_CLASS_NAME", "SWITCH_PREFERENCE_CLASS_NAME", "PREFERENCE_RESOURCE_LOAD_ERROR", "PREFERENCE_KEY_ERROR", "PREFERENCE_SUMMARY_PROVIDER_ERROR", "PREFERENCE_DUPLICATED_KEY_PREFIX", "PREFERENCE_TOP_ORDER", "", "BACK_CALLBACK_PRIORITY", "MAIN_SETTINGS_RESOURCES", "", "getMAIN_SETTINGS_RESOURCES", "()Ljava/util/Set;", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class SettingsConstantsKt {
    public static final int BACK_CALLBACK_PRIORITY = 1000001;
    public static final String ENTRY_KEY = "icu.nullptr.polyglot.settings";
    public static final String ENTRY_TITLE = "PolyglotYT";
    private static final Set<String> MAIN_SETTINGS_RESOURCES = SetsKt.setOf((Object[]) new String[]{"settings_fragment", "settings_fragment_cairo"});
    public static final String PREFERENCE_CLASS_NAME = "androidx.preference.Preference";
    public static final String PREFERENCE_DUPLICATED_KEY_PREFIX = "Found duplicated key";
    public static final String PREFERENCE_GROUP_CLASS_NAME = "androidx.preference.PreferenceGroup";
    public static final String PREFERENCE_KEY_ERROR = "Preference does not have a key assigned.";
    public static final String PREFERENCE_RESOURCE_LOAD_ERROR = "This should be called after super.onCreate.";
    public static final String PREFERENCE_SCREEN_CLASS_NAME = "androidx.preference.PreferenceScreen";
    public static final String PREFERENCE_SUMMARY_PROVIDER_ERROR = "Preference already has a SummaryProvider set.";
    public static final int PREFERENCE_TOP_ORDER = -1;
    public static final String SWITCH_PREFERENCE_CLASS_NAME = "androidx.preference.SwitchPreference";

    public static final Set<String> getMAIN_SETTINGS_RESOURCES() {
        return MAIN_SETTINGS_RESOURCES;
    }
}
