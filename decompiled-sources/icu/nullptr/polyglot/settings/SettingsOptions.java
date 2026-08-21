package icu.nullptr.polyglot.settings;

import icu.nullptr.polyglot.ModuleEntryKt;
import icu.nullptr.polyglot.R;
import icu.nullptr.polyglot.core.ConfigManager;
import icu.nullptr.polyglot.translate.ConnectivityTester;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SettingsOptions.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0018J\u000e\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0018J\u000e\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0018J\u000e\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0018J\u000e\u0010 \u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0018J\u000e\u0010!\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0018J\u000e\u0010\"\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0018J\u000e\u0010#\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0018J\u000e\u0010$\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0018J\u000e\u0010%\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0018R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\bR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\b¨\u0006&"}, d2 = {"Licu/nullptr/polyglot/settings/SettingsOptions;", "", "<init>", "()V", "providers", "", "Licu/nullptr/polyglot/settings/SettingsOption;", "getProviders", "()Ljava/util/List;", "targetLanguages", "getTargetLanguages", "subtitleMode", "getSubtitleMode", "translationScales", "getTranslationScales", "baseScales", "getBaseScales", "lineWidths", "getLineWidths", "translationColors", "getTranslationColors", "subtitleSeparators", "getSubtitleSeparators", "enabledSummary", "", "enabled", "", "providerLabel", "value", "languageLabel", "subtitleModeLabel", "translationScaleLabel", "baseScaleLabel", "lineWidthLabel", "translationColorLabel", "subtitleSeparatorLabel", "textOrNotSet", "secretSummary", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class SettingsOptions {
    private static final List<SettingsOption> baseScales;
    private static final List<SettingsOption> lineWidths;
    private static final List<SettingsOption> subtitleMode;
    private static final List<SettingsOption> subtitleSeparators;
    private static final List<SettingsOption> translationColors;
    private static final List<SettingsOption> translationScales;
    public static final SettingsOptions INSTANCE = new SettingsOptions();
    private static final List<SettingsOption> providers = CollectionsKt.listOf((Object[]) new SettingsOption[]{new SettingsOption(ConfigManager.PROVIDER_GOOGLE, "Google Translate"), new SettingsOption(ConfigManager.PROVIDER_MICROSOFT, "Microsoft Translator"), new SettingsOption(ConfigManager.PROVIDER_OPENAI, "OpenAI compatible")});
    private static final List<SettingsOption> targetLanguages = CollectionsKt.listOf((Object[]) new SettingsOption[]{new SettingsOption(ConnectivityTester.TEST_SOURCE_LANGUAGE, "English"), new SettingsOption("zh-Hans", "Chinese (Simplified)"), new SettingsOption("zh-Hant", "Chinese (Traditional)"), new SettingsOption("ja", "Japanese"), new SettingsOption("ko", "Korean"), new SettingsOption("es", "Spanish"), new SettingsOption("fr", "French"), new SettingsOption("de", "German"), new SettingsOption("ru", "Russian")});

    private SettingsOptions() {
    }

    static {
        String string = ModuleEntryKt.getModule().getRes().getString(R.string.subtitle_mode_original_first);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = ModuleEntryKt.getModule().getRes().getString(R.string.subtitle_mode_translation_first);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        String string3 = ModuleEntryKt.getModule().getRes().getString(R.string.subtitle_mode_translation_only);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        subtitleMode = CollectionsKt.listOf((Object[]) new SettingsOption[]{new SettingsOption(ConfigManager.SUBTITLE_ORIGINAL_FIRST, string), new SettingsOption(ConfigManager.SUBTITLE_TRANSLATION_FIRST, string2), new SettingsOption(ConfigManager.SUBTITLE_TRANSLATION_ONLY, string3)});
        translationScales = CollectionsKt.listOf((Object[]) new SettingsOption[]{new SettingsOption("0.6", "60%"), new SettingsOption("0.7", "70%"), new SettingsOption("0.75", "75%"), new SettingsOption("0.8", "80%"), new SettingsOption("0.9", "90%"), new SettingsOption("1.0", "100%")});
        baseScales = CollectionsKt.listOf((Object[]) new SettingsOption[]{new SettingsOption("0.8", "80%"), new SettingsOption("0.9", "90%"), new SettingsOption("1.0", "100%"), new SettingsOption("1.1", "110%"), new SettingsOption("1.25", "125%"), new SettingsOption("1.4", "140%")});
        lineWidths = CollectionsKt.listOf((Object[]) new SettingsOption[]{new SettingsOption("28", "Narrow"), new SettingsOption("36", "Normal"), new SettingsOption("44", "Wide"), new SettingsOption("56", "Extra wide")});
        translationColors = CollectionsKt.listOf((Object[]) new SettingsOption[]{new SettingsOption("white", "White"), new SettingsOption("dim", "Dim White"), new SettingsOption("faint", "Faint White")});
        subtitleSeparators = CollectionsKt.listOf((Object[]) new SettingsOption[]{new SettingsOption("", "None"), new SettingsOption("·", "· (dot)"), new SettingsOption("—", "— (dash)"), new SettingsOption("»", "» (arrow)")});
    }

    public final List<SettingsOption> getProviders() {
        return providers;
    }

    public final List<SettingsOption> getTargetLanguages() {
        return targetLanguages;
    }

    public final List<SettingsOption> getSubtitleMode() {
        return subtitleMode;
    }

    public final List<SettingsOption> getTranslationScales() {
        return translationScales;
    }

    public final List<SettingsOption> getBaseScales() {
        return baseScales;
    }

    public final List<SettingsOption> getLineWidths() {
        return lineWidths;
    }

    public final List<SettingsOption> getTranslationColors() {
        return translationColors;
    }

    public final List<SettingsOption> getSubtitleSeparators() {
        return subtitleSeparators;
    }

    public final String enabledSummary(boolean enabled) {
        if (enabled) {
            String string = ModuleEntryKt.getModule().getRes().getString(R.string.enabled);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        }
        String string2 = ModuleEntryKt.getModule().getRes().getString(R.string.disabled);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        return string2;
    }

    public final String providerLabel(String value) {
        Object obj;
        String label;
        Intrinsics.checkNotNullParameter(value, "value");
        Iterator it = providers.iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                if (Intrinsics.areEqual(((SettingsOption) obj).getValue(), value)) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        SettingsOption settingsOption = (SettingsOption) obj;
        return (settingsOption == null || (label = settingsOption.getLabel()) == null) ? value : label;
    }

    public final String languageLabel(String value) {
        Object obj;
        String label;
        Intrinsics.checkNotNullParameter(value, "value");
        Iterator it = targetLanguages.iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                if (Intrinsics.areEqual(((SettingsOption) obj).getValue(), value)) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        SettingsOption settingsOption = (SettingsOption) obj;
        return (settingsOption == null || (label = settingsOption.getLabel()) == null) ? value : label;
    }

    public final String subtitleModeLabel(String value) {
        Object obj;
        String label;
        Intrinsics.checkNotNullParameter(value, "value");
        Iterator it = subtitleMode.iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                if (Intrinsics.areEqual(((SettingsOption) obj).getValue(), value)) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        SettingsOption settingsOption = (SettingsOption) obj;
        return (settingsOption == null || (label = settingsOption.getLabel()) == null) ? value : label;
    }

    public final String translationScaleLabel(String value) {
        Object obj;
        String label;
        Intrinsics.checkNotNullParameter(value, "value");
        Iterator it = translationScales.iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                if (Intrinsics.areEqual(((SettingsOption) obj).getValue(), value)) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        SettingsOption settingsOption = (SettingsOption) obj;
        return (settingsOption == null || (label = settingsOption.getLabel()) == null) ? value : label;
    }

    public final String baseScaleLabel(String value) {
        Object obj;
        String label;
        Intrinsics.checkNotNullParameter(value, "value");
        Iterator it = baseScales.iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                if (Intrinsics.areEqual(((SettingsOption) obj).getValue(), value)) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        SettingsOption settingsOption = (SettingsOption) obj;
        return (settingsOption == null || (label = settingsOption.getLabel()) == null) ? value : label;
    }

    public final String lineWidthLabel(String value) {
        Object obj;
        String label;
        Intrinsics.checkNotNullParameter(value, "value");
        Iterator it = lineWidths.iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                if (Intrinsics.areEqual(((SettingsOption) obj).getValue(), value)) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        SettingsOption settingsOption = (SettingsOption) obj;
        return (settingsOption == null || (label = settingsOption.getLabel()) == null) ? value : label;
    }

    public final String translationColorLabel(String value) {
        Object obj;
        String label;
        Intrinsics.checkNotNullParameter(value, "value");
        Iterator it = translationColors.iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                if (Intrinsics.areEqual(((SettingsOption) obj).getValue(), value)) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        SettingsOption settingsOption = (SettingsOption) obj;
        return (settingsOption == null || (label = settingsOption.getLabel()) == null) ? value : label;
    }

    public final String subtitleSeparatorLabel(String value) {
        Object obj;
        String label;
        Intrinsics.checkNotNullParameter(value, "value");
        Iterator it = subtitleSeparators.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((SettingsOption) obj).getValue(), value)) {
                break;
            }
        }
        SettingsOption settingsOption = (SettingsOption) obj;
        if (settingsOption != null && (label = settingsOption.getLabel()) != null) {
            return label;
        }
        String str = value;
        if (str.length() == 0) {
            String string = ModuleEntryKt.getModule().getRes().getString(R.string.not_set);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            str = string;
        }
        return str;
    }

    public final String textOrNotSet(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        String str = value;
        if (StringsKt.isBlank(str)) {
            String string = ModuleEntryKt.getModule().getRes().getString(R.string.not_set);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            str = string;
        }
        return str;
    }

    public final String secretSummary(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (!StringsKt.isBlank(value)) {
            return "********";
        }
        String string = ModuleEntryKt.getModule().getRes().getString(R.string.not_set);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }
}
