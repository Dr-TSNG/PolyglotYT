package icu.nullptr.polyglot.youtube.settings;

import icu.nullptr.polyglot.ModuleEntryKt;
import icu.nullptr.polyglot.R;
import icu.nullptr.polyglot.captions.CaptionSession;
import icu.nullptr.polyglot.core.ConfigManager;
import icu.nullptr.polyglot.settings.SettingsOptions;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: SettingsTree.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Licu/nullptr/polyglot/youtube/settings/PolyglotSettingsTree;", "", "<init>", "()V", "root", "Licu/nullptr/polyglot/youtube/settings/SettingsScreenNode;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class PolyglotSettingsTree {
    public static final PolyglotSettingsTree INSTANCE = new PolyglotSettingsTree();

    private PolyglotSettingsTree() {
    }

    public final SettingsScreenNode root() {
        String string = ModuleEntryKt.getModule().getRes().getString(R.string.enable_module);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = ModuleEntryKt.getModule().getRes().getString(R.string.test_connectivity);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        String string3 = ModuleEntryKt.getModule().getRes().getString(R.string.translation_service);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        String string4 = ModuleEntryKt.getModule().getRes().getString(R.string.microsoft_endpoint);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        String string5 = ModuleEntryKt.getModule().getRes().getString(R.string.microsoft_api_key);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
        String string6 = ModuleEntryKt.getModule().getRes().getString(R.string.microsoft_region);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
        String string7 = ModuleEntryKt.getModule().getRes().getString(R.string.openai_endpoint);
        Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
        String string8 = ModuleEntryKt.getModule().getRes().getString(R.string.openai_api_key);
        Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
        String string9 = ModuleEntryKt.getModule().getRes().getString(R.string.openai_model);
        Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
        String string10 = ModuleEntryKt.getModule().getRes().getString(R.string.target_language);
        Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
        String string11 = ModuleEntryKt.getModule().getRes().getString(R.string.subtitle_style);
        Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
        String str = string11;
        SettingsIcon settingsIcon = SettingsIcon.Subtitle;
        String string12 = ModuleEntryKt.getModule().getRes().getString(R.string.subtitle_style_enabled);
        Intrinsics.checkNotNullExpressionValue(string12, "getString(...)");
        String string13 = ModuleEntryKt.getModule().getRes().getString(R.string.subtitle_base_scale);
        Intrinsics.checkNotNullExpressionValue(string13, "getString(...)");
        String string14 = ModuleEntryKt.getModule().getRes().getString(R.string.translation_scale);
        Intrinsics.checkNotNullExpressionValue(string14, "getString(...)");
        String string15 = ModuleEntryKt.getModule().getRes().getString(R.string.subtitle_line_width);
        Intrinsics.checkNotNullExpressionValue(string15, "getString(...)");
        String string16 = ModuleEntryKt.getModule().getRes().getString(R.string.translation_color);
        Intrinsics.checkNotNullExpressionValue(string16, "getString(...)");
        String string17 = ModuleEntryKt.getModule().getRes().getString(R.string.subtitle_separator);
        Intrinsics.checkNotNullExpressionValue(string17, "getString(...)");
        SettingsNode[] settingsNodeArr = {new SwitchSettingsNode("icu.nullptr.polyglot.settings.style_enabled", string12, SettingsIcon.Subtitle, null, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda27
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean subtitleStyleEnabled;
                subtitleStyleEnabled = ModuleEntryKt.getModule().getConfig().getSubtitleStyleEnabled();
                return Boolean.valueOf(subtitleStyleEnabled);
            }
        }, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda28
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PolyglotSettingsTree.root$lambda$35();
            }
        }, new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda29
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PolyglotSettingsTree.root$lambda$36(((Boolean) obj).booleanValue());
            }
        }, 8, null), new SelectionSettingsNode("icu.nullptr.polyglot.settings.base_scale", string13, SettingsIcon.Subtitle, null, SettingsOptions.INSTANCE.getBaseScales(), new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda30
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String valueOf;
                valueOf = String.valueOf(ModuleEntryKt.getModule().getConfig().getSubtitleBaseScale());
                return valueOf;
            }
        }, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda31
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CharSequence baseScaleLabel;
                baseScaleLabel = SettingsOptions.INSTANCE.baseScaleLabel(String.valueOf(ModuleEntryKt.getModule().getConfig().getSubtitleBaseScale()));
                return baseScaleLabel;
            }
        }, new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda32
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PolyglotSettingsTree.root$lambda$39((String) obj);
            }
        }, 8, null), new SelectionSettingsNode("icu.nullptr.polyglot.settings.translation_scale", string14, SettingsIcon.Subtitle, null, SettingsOptions.INSTANCE.getTranslationScales(), new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda34
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String valueOf;
                valueOf = String.valueOf(ModuleEntryKt.getModule().getConfig().getSubtitleTranslationScale());
                return valueOf;
            }
        }, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda35
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CharSequence translationScaleLabel;
                translationScaleLabel = SettingsOptions.INSTANCE.translationScaleLabel(String.valueOf(ModuleEntryKt.getModule().getConfig().getSubtitleTranslationScale()));
                return translationScaleLabel;
            }
        }, new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda36
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PolyglotSettingsTree.root$lambda$42((String) obj);
            }
        }, 8, null), new SelectionSettingsNode("icu.nullptr.polyglot.settings.line_width", string15, SettingsIcon.Subtitle, null, SettingsOptions.INSTANCE.getLineWidths(), new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda37
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String valueOf;
                valueOf = String.valueOf(ModuleEntryKt.getModule().getConfig().getSubtitleLineWidth());
                return valueOf;
            }
        }, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda38
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CharSequence lineWidthLabel;
                lineWidthLabel = SettingsOptions.INSTANCE.lineWidthLabel(String.valueOf(ModuleEntryKt.getModule().getConfig().getSubtitleLineWidth()));
                return lineWidthLabel;
            }
        }, new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda39
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PolyglotSettingsTree.root$lambda$45((String) obj);
            }
        }, 8, null), new SelectionSettingsNode("icu.nullptr.polyglot.settings.translation_color", string16, SettingsIcon.Subtitle, null, SettingsOptions.INSTANCE.getTranslationColors(), new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda40
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String subtitleTranslationColor;
                subtitleTranslationColor = ModuleEntryKt.getModule().getConfig().getSubtitleTranslationColor();
                return subtitleTranslationColor;
            }
        }, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda41
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CharSequence translationColorLabel;
                translationColorLabel = SettingsOptions.INSTANCE.translationColorLabel(ModuleEntryKt.getModule().getConfig().getSubtitleTranslationColor());
                return translationColorLabel;
            }
        }, new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda42
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PolyglotSettingsTree.root$lambda$48((String) obj);
            }
        }, 8, null), new SelectionSettingsNode("icu.nullptr.polyglot.settings.subtitle_separator", string17, SettingsIcon.Subtitle, null, SettingsOptions.INSTANCE.getSubtitleSeparators(), new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda43
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String subtitleSeparator;
                subtitleSeparator = ModuleEntryKt.getModule().getConfig().getSubtitleSeparator();
                return subtitleSeparator;
            }
        }, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda45
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CharSequence subtitleSeparatorLabel;
                subtitleSeparatorLabel = SettingsOptions.INSTANCE.subtitleSeparatorLabel(ModuleEntryKt.getModule().getConfig().getSubtitleSeparator());
                return subtitleSeparatorLabel;
            }
        }, new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda46
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PolyglotSettingsTree.root$lambda$51((String) obj);
            }
        }, 8, null)};
        String string18 = ModuleEntryKt.getModule().getRes().getString(R.string.translation_performance);
        Intrinsics.checkNotNullExpressionValue(string18, "getString(...)");
        String str2 = string18;
        SettingsIcon settingsIcon2 = SettingsIcon.Model;
        String string19 = ModuleEntryKt.getModule().getRes().getString(R.string.translation_batch_size);
        Intrinsics.checkNotNullExpressionValue(string19, "getString(...)");
        String string20 = ModuleEntryKt.getModule().getRes().getString(R.string.translation_batch_window);
        Intrinsics.checkNotNullExpressionValue(string20, "getString(...)");
        TextSettingsNode[] textSettingsNodeArr = {new TextSettingsNode("icu.nullptr.polyglot.settings.batch_size", string19, SettingsIcon.Model, null, 2, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda47
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String valueOf;
                valueOf = String.valueOf(ModuleEntryKt.getModule().getConfig().getTranslationBatchSize());
                return valueOf;
            }
        }, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda48
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PolyglotSettingsTree.root$lambda$53();
            }
        }, new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda49
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PolyglotSettingsTree.root$lambda$54((String) obj);
            }
        }, 8, null), new TextSettingsNode("icu.nullptr.polyglot.settings.batch_window", string20, SettingsIcon.Model, null, 2, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda50
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String valueOf;
                valueOf = String.valueOf(ModuleEntryKt.getModule().getConfig().getTranslationBatchWindowMs());
                return valueOf;
            }
        }, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda51
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PolyglotSettingsTree.root$lambda$56();
            }
        }, new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda52
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PolyglotSettingsTree.root$lambda$57((String) obj);
            }
        }, 8, null)};
        String string21 = ModuleEntryKt.getModule().getRes().getString(R.string.subtitle_mode);
        Intrinsics.checkNotNullExpressionValue(string21, "getString(...)");
        return new SettingsScreenNode("icu.nullptr.polyglot.settings.screen", SettingsConstantsKt.ENTRY_TITLE, null, null, CollectionsKt.listOf((Object[]) new SettingsNode[]{new SwitchSettingsNode("icu.nullptr.polyglot.settings.enabled", string, SettingsIcon.Enable, null, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean enabled;
                enabled = ModuleEntryKt.getModule().getConfig().getEnabled();
                return Boolean.valueOf(enabled);
            }
        }, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CharSequence enabledSummary;
                enabledSummary = SettingsOptions.INSTANCE.enabledSummary(ModuleEntryKt.getModule().getConfig().getEnabled());
                return enabledSummary;
            }
        }, new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PolyglotSettingsTree.root$lambda$2(((Boolean) obj).booleanValue());
            }
        }, 8, null), new ActionSettingsNode("icu.nullptr.polyglot.settings.test_connectivity", string2, SettingsIcon.NetworkCheck, null, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda33
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PolyglotSettingsTree.root$lambda$3();
            }
        }, SettingsAction.TestConnectivity, 8, null), new SelectionSettingsNode("icu.nullptr.polyglot.settings.provider", string3, SettingsIcon.Service, null, SettingsOptions.INSTANCE.getProviders(), new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda44
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String provider;
                provider = ModuleEntryKt.getModule().getConfig().getProvider();
                return provider;
            }
        }, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda55
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CharSequence providerLabel;
                providerLabel = SettingsOptions.INSTANCE.providerLabel(ModuleEntryKt.getModule().getConfig().getProvider());
                return providerLabel;
            }
        }, new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda57
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PolyglotSettingsTree.root$lambda$6((String) obj);
            }
        }, 8, null), new TextSettingsNode("icu.nullptr.polyglot.settings.microsoft_endpoint", string4, SettingsIcon.Endpoint, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda58
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean areEqual;
                areEqual = Intrinsics.areEqual(ModuleEntryKt.getModule().getConfig().getProvider(), ConfigManager.PROVIDER_MICROSOFT);
                return Boolean.valueOf(areEqual);
            }
        }, 17, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda59
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String microsoftEndpoint;
                microsoftEndpoint = ModuleEntryKt.getModule().getConfig().getMicrosoftEndpoint();
                return microsoftEndpoint;
            }
        }, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda60
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CharSequence textOrNotSet;
                textOrNotSet = SettingsOptions.INSTANCE.textOrNotSet(ModuleEntryKt.getModule().getConfig().getMicrosoftEndpoint());
                return textOrNotSet;
            }
        }, new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PolyglotSettingsTree.root$lambda$10((String) obj);
            }
        }), new TextSettingsNode("icu.nullptr.polyglot.settings.microsoft_api_key", string5, SettingsIcon.ApiKey, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean areEqual;
                areEqual = Intrinsics.areEqual(ModuleEntryKt.getModule().getConfig().getProvider(), ConfigManager.PROVIDER_MICROSOFT);
                return Boolean.valueOf(areEqual);
            }
        }, 524433, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String microsoftApiKey;
                microsoftApiKey = ModuleEntryKt.getModule().getConfig().getMicrosoftApiKey();
                return microsoftApiKey;
            }
        }, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CharSequence secretSummary;
                secretSummary = SettingsOptions.INSTANCE.secretSummary(ModuleEntryKt.getModule().getConfig().getMicrosoftApiKey());
                return secretSummary;
            }
        }, new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PolyglotSettingsTree.root$lambda$14((String) obj);
            }
        }), new TextSettingsNode("icu.nullptr.polyglot.settings.microsoft_region", string6, SettingsIcon.Language, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean areEqual;
                areEqual = Intrinsics.areEqual(ModuleEntryKt.getModule().getConfig().getProvider(), ConfigManager.PROVIDER_MICROSOFT);
                return Boolean.valueOf(areEqual);
            }
        }, 524289, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String microsoftRegion;
                microsoftRegion = ModuleEntryKt.getModule().getConfig().getMicrosoftRegion();
                return microsoftRegion;
            }
        }, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CharSequence textOrNotSet;
                textOrNotSet = SettingsOptions.INSTANCE.textOrNotSet(ModuleEntryKt.getModule().getConfig().getMicrosoftRegion());
                return textOrNotSet;
            }
        }, new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PolyglotSettingsTree.root$lambda$18((String) obj);
            }
        }), new TextSettingsNode("icu.nullptr.polyglot.settings.openai_endpoint", string7, SettingsIcon.Endpoint, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean areEqual;
                areEqual = Intrinsics.areEqual(ModuleEntryKt.getModule().getConfig().getProvider(), ConfigManager.PROVIDER_OPENAI);
                return Boolean.valueOf(areEqual);
            }
        }, 17, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String openAiEndpoint;
                openAiEndpoint = ModuleEntryKt.getModule().getConfig().getOpenAiEndpoint();
                return openAiEndpoint;
            }
        }, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CharSequence textOrNotSet;
                textOrNotSet = SettingsOptions.INSTANCE.textOrNotSet(ModuleEntryKt.getModule().getConfig().getOpenAiEndpoint());
                return textOrNotSet;
            }
        }, new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PolyglotSettingsTree.root$lambda$22((String) obj);
            }
        }), new TextSettingsNode("icu.nullptr.polyglot.settings.openai_api_key", string8, SettingsIcon.ApiKey, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean areEqual;
                areEqual = Intrinsics.areEqual(ModuleEntryKt.getModule().getConfig().getProvider(), ConfigManager.PROVIDER_OPENAI);
                return Boolean.valueOf(areEqual);
            }
        }, 524433, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String openAiApiKey;
                openAiApiKey = ModuleEntryKt.getModule().getConfig().getOpenAiApiKey();
                return openAiApiKey;
            }
        }, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CharSequence secretSummary;
                secretSummary = SettingsOptions.INSTANCE.secretSummary(ModuleEntryKt.getModule().getConfig().getOpenAiApiKey());
                return secretSummary;
            }
        }, new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PolyglotSettingsTree.root$lambda$26((String) obj);
            }
        }), new TextSettingsNode("icu.nullptr.polyglot.settings.openai_model", string9, SettingsIcon.Model, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                boolean areEqual;
                areEqual = Intrinsics.areEqual(ModuleEntryKt.getModule().getConfig().getProvider(), ConfigManager.PROVIDER_OPENAI);
                return Boolean.valueOf(areEqual);
            }
        }, 524289, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String openAiModel;
                openAiModel = ModuleEntryKt.getModule().getConfig().getOpenAiModel();
                return openAiModel;
            }
        }, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CharSequence textOrNotSet;
                textOrNotSet = SettingsOptions.INSTANCE.textOrNotSet(ModuleEntryKt.getModule().getConfig().getOpenAiModel());
                return textOrNotSet;
            }
        }, new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda23
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PolyglotSettingsTree.root$lambda$30((String) obj);
            }
        }), new SelectionSettingsNode("icu.nullptr.polyglot.settings.target_language", string10, SettingsIcon.Language, null, SettingsOptions.INSTANCE.getTargetLanguages(), new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda24
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String targetLanguage;
                targetLanguage = ModuleEntryKt.getModule().getConfig().getTargetLanguage();
                return targetLanguage;
            }
        }, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CharSequence languageLabel;
                languageLabel = SettingsOptions.INSTANCE.languageLabel(ModuleEntryKt.getModule().getConfig().getTargetLanguage());
                return languageLabel;
            }
        }, new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PolyglotSettingsTree.root$lambda$33((String) obj);
            }
        }, 8, null), new SettingsScreenNode("icu.nullptr.polyglot.settings.style_screen", str, settingsIcon, null, CollectionsKt.listOf((Object[]) settingsNodeArr), 8, null), new SettingsScreenNode("icu.nullptr.polyglot.settings.performance_screen", str2, settingsIcon2, null, CollectionsKt.listOf((Object[]) textSettingsNodeArr), 8, null), new SelectionSettingsNode("icu.nullptr.polyglot.settings.subtitle_mode", string21, SettingsIcon.Subtitle, null, SettingsOptions.INSTANCE.getSubtitleMode(), new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda53
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                String subtitleMode;
                subtitleMode = ModuleEntryKt.getModule().getConfig().getSubtitleMode();
                return subtitleMode;
            }
        }, new Function0() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda54
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CharSequence subtitleModeLabel;
                subtitleModeLabel = SettingsOptions.INSTANCE.subtitleModeLabel(ModuleEntryKt.getModule().getConfig().getSubtitleMode());
                return subtitleModeLabel;
            }
        }, new Function1() { // from class: icu.nullptr.polyglot.youtube.settings.PolyglotSettingsTree$$ExternalSyntheticLambda56
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PolyglotSettingsTree.root$lambda$60((String) obj);
            }
        }, 8, null)}), 12, null);
    }

    static final Unit root$lambda$2(boolean it) {
        ModuleEntryKt.getModule().getConfig().setEnabled(it);
        return Unit.INSTANCE;
    }

    static final CharSequence root$lambda$3() {
        String string = ModuleEntryKt.getModule().getRes().getString(R.string.test_connectivity_summary);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    static final Unit root$lambda$6(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ModuleEntryKt.getModule().getConfig().setProvider(it);
        return Unit.INSTANCE;
    }

    static final Unit root$lambda$10(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ModuleEntryKt.getModule().getConfig().setMicrosoftEndpoint(StringsKt.trim((CharSequence) it).toString());
        return Unit.INSTANCE;
    }

    static final Unit root$lambda$14(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ModuleEntryKt.getModule().getConfig().setMicrosoftApiKey(StringsKt.trim((CharSequence) it).toString());
        return Unit.INSTANCE;
    }

    static final Unit root$lambda$18(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ModuleEntryKt.getModule().getConfig().setMicrosoftRegion(StringsKt.trim((CharSequence) it).toString());
        return Unit.INSTANCE;
    }

    static final Unit root$lambda$22(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ModuleEntryKt.getModule().getConfig().setOpenAiEndpoint(StringsKt.trim((CharSequence) it).toString());
        return Unit.INSTANCE;
    }

    static final Unit root$lambda$26(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ModuleEntryKt.getModule().getConfig().setOpenAiApiKey(StringsKt.trim((CharSequence) it).toString());
        return Unit.INSTANCE;
    }

    static final Unit root$lambda$30(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ModuleEntryKt.getModule().getConfig().setOpenAiModel(StringsKt.trim((CharSequence) it).toString());
        return Unit.INSTANCE;
    }

    static final Unit root$lambda$33(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ModuleEntryKt.getModule().getConfig().setTargetLanguage(it);
        return Unit.INSTANCE;
    }

    static final CharSequence root$lambda$35() {
        String string = ModuleEntryKt.getModule().getRes().getString(R.string.subtitle_style_enabled_summary);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    static final Unit root$lambda$36(boolean it) {
        ModuleEntryKt.getModule().getConfig().setSubtitleStyleEnabled(it);
        return Unit.INSTANCE;
    }

    static final Unit root$lambda$39(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ModuleEntryKt.getModule().getConfig().setSubtitleBaseScale(Float.parseFloat(it));
        return Unit.INSTANCE;
    }

    static final Unit root$lambda$42(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ModuleEntryKt.getModule().getConfig().setSubtitleTranslationScale(Float.parseFloat(it));
        return Unit.INSTANCE;
    }

    static final Unit root$lambda$45(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ModuleEntryKt.getModule().getConfig().setSubtitleLineWidth(Integer.parseInt(it));
        return Unit.INSTANCE;
    }

    static final Unit root$lambda$48(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ModuleEntryKt.getModule().getConfig().setSubtitleTranslationColor(it);
        return Unit.INSTANCE;
    }

    static final Unit root$lambda$51(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ModuleEntryKt.getModule().getConfig().setSubtitleSeparator(it);
        return Unit.INSTANCE;
    }

    static final CharSequence root$lambda$53() {
        String string = ModuleEntryKt.getModule().getRes().getString(R.string.translation_batch_size_summary, Integer.valueOf(ModuleEntryKt.getModule().getConfig().getTranslationBatchSize()));
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    static final Unit root$lambda$54(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ConfigManager config = ModuleEntryKt.getModule().getConfig();
        Integer intOrNull = StringsKt.toIntOrNull(it);
        config.setTranslationBatchSize(intOrNull != null ? RangesKt.coerceIn(intOrNull.intValue(), 1, 64) : 8);
        return Unit.INSTANCE;
    }

    static final CharSequence root$lambda$56() {
        String string = ModuleEntryKt.getModule().getRes().getString(R.string.translation_batch_window_summary, Integer.valueOf(ModuleEntryKt.getModule().getConfig().getTranslationBatchWindowMs()));
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    static final Unit root$lambda$57(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ConfigManager config = ModuleEntryKt.getModule().getConfig();
        Integer intOrNull = StringsKt.toIntOrNull(it);
        config.setTranslationBatchWindowMs(intOrNull != null ? RangesKt.coerceIn(intOrNull.intValue(), 0, CaptionSession.MAX_TRANSLATIONS) : 150);
        return Unit.INSTANCE;
    }

    static final Unit root$lambda$60(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ModuleEntryKt.getModule().getConfig().setSubtitleMode(it);
        return Unit.INSTANCE;
    }
}
