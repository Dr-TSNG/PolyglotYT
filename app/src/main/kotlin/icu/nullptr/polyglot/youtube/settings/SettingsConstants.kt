package icu.nullptr.polyglot.youtube.settings

internal const val ENTRY_TITLE = "PolyglotYT"
internal const val ENTRY_KEY = "icu.nullptr.polyglot.settings"

internal const val PREFERENCE_CLASS_NAME = "androidx.preference.Preference"
internal const val PREFERENCE_GROUP_CLASS_NAME = "androidx.preference.PreferenceGroup"
internal const val PREFERENCE_SCREEN_CLASS_NAME = "androidx.preference.PreferenceScreen"
internal const val SWITCH_PREFERENCE_CLASS_NAME = "androidx.preference.SwitchPreference"

internal const val PREFERENCE_RESOURCE_LOAD_ERROR = "This should be called after super.onCreate."
internal const val PREFERENCE_KEY_ERROR = "Preference does not have a key assigned."
internal const val PREFERENCE_SUMMARY_PROVIDER_ERROR = "Preference already has a SummaryProvider set."
internal const val PREFERENCE_DUPLICATED_KEY_PREFIX = "Found duplicated key"

internal const val PREFERENCE_TOP_ORDER = -1
internal const val BACK_CALLBACK_PRIORITY = 1_000_001

internal val MAIN_SETTINGS_RESOURCES = setOf(
    "settings_fragment",
    "settings_fragment_cairo",
)
