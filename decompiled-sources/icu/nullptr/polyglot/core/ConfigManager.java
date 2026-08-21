package icu.nullptr.polyglot.core;

import android.content.Context;
import com.tencent.mmkv.MMKV;
import icu.nullptr.polyglot.translate.providers.MicrosoftTranslator;
import icu.nullptr.polyglot.translate.providers.OpenAICompatibleTranslator;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* compiled from: ConfigManager.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b?\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 q2\u00020\u0001:\u0002pqB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R+\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR+\u0010\u0012\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00118F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u0010\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R+\u0010\u0018\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00118F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u0010\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u0016R+\u0010\u001c\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00118F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010\u0010\u001a\u0004\b\u001d\u0010\u0014\"\u0004\b\u001e\u0010\u0016R+\u0010 \u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010\u0010\u001a\u0004\b!\u0010\f\"\u0004\b\"\u0010\u000eR+\u0010%\u001a\u00020$2\u0006\u0010\b\u001a\u00020$8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b*\u0010\u0010\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R+\u0010+\u001a\u00020$2\u0006\u0010\b\u001a\u00020$8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b.\u0010\u0010\u001a\u0004\b,\u0010'\"\u0004\b-\u0010)R+\u00100\u001a\u00020/2\u0006\u0010\b\u001a\u00020/8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b5\u0010\u0010\u001a\u0004\b1\u00102\"\u0004\b3\u00104R+\u00106\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00118F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b9\u0010\u0010\u001a\u0004\b7\u0010\u0014\"\u0004\b8\u0010\u0016R+\u0010:\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00118F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b=\u0010\u0010\u001a\u0004\b;\u0010\u0014\"\u0004\b<\u0010\u0016R+\u0010>\u001a\u00020/2\u0006\u0010\b\u001a\u00020/8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bA\u0010\u0010\u001a\u0004\b?\u00102\"\u0004\b@\u00104R+\u0010B\u001a\u00020/2\u0006\u0010\b\u001a\u00020/8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bE\u0010\u0010\u001a\u0004\bC\u00102\"\u0004\bD\u00104R+\u0010F\u001a\u00020/2\u0006\u0010\b\u001a\u00020/8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bI\u0010\u0010\u001a\u0004\bG\u00102\"\u0004\bH\u00104R+\u0010J\u001a\u00020/2\u0006\u0010\b\u001a\u00020/8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bM\u0010\u0010\u001a\u0004\bK\u00102\"\u0004\bL\u00104R+\u0010N\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00118F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bQ\u0010\u0010\u001a\u0004\bO\u0010\u0014\"\u0004\bP\u0010\u0016R+\u0010R\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00118F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bU\u0010\u0010\u001a\u0004\bS\u0010\u0014\"\u0004\bT\u0010\u0016R+\u0010V\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00118F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bY\u0010\u0010\u001a\u0004\bW\u0010\u0014\"\u0004\bX\u0010\u0016R+\u0010Z\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00118F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b]\u0010\u0010\u001a\u0004\b[\u0010\u0014\"\u0004\b\\\u0010\u0016R+\u0010^\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00118F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\ba\u0010\u0010\u001a\u0004\b_\u0010\u0014\"\u0004\b`\u0010\u0016R+\u0010b\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00118F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\be\u0010\u0010\u001a\u0004\bc\u0010\u0014\"\u0004\bd\u0010\u0016R+\u0010f\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00118F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bi\u0010\u0010\u001a\u0004\bg\u0010\u0014\"\u0004\bh\u0010\u0016R+\u0010j\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00118F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bm\u0010\u0010\u001a\u0004\bk\u0010\u0014\"\u0004\bl\u0010\u0016R\u000e\u0010n\u001a\u00020oX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006r"}, d2 = {"Licu/nullptr/polyglot/core/ConfigManager;", "", "context", "Landroid/content/Context;", "directory", "Ljava/io/File;", "<init>", "(Landroid/content/Context;Ljava/io/File;)V", "<set-?>", "", "enabled", "getEnabled", "()Z", "setEnabled", "(Z)V", "enabled$delegate", "Licu/nullptr/polyglot/core/ConfigManager$Pref;", "", "provider", "getProvider", "()Ljava/lang/String;", "setProvider", "(Ljava/lang/String;)V", "provider$delegate", "targetLanguage", "getTargetLanguage", "setTargetLanguage", "targetLanguage$delegate", "subtitleMode", "getSubtitleMode", "setSubtitleMode", "subtitleMode$delegate", "subtitleStyleEnabled", "getSubtitleStyleEnabled", "setSubtitleStyleEnabled", "subtitleStyleEnabled$delegate", "", "subtitleTranslationScale", "getSubtitleTranslationScale", "()F", "setSubtitleTranslationScale", "(F)V", "subtitleTranslationScale$delegate", "subtitleBaseScale", "getSubtitleBaseScale", "setSubtitleBaseScale", "subtitleBaseScale$delegate", "", "subtitleLineWidth", "getSubtitleLineWidth", "()I", "setSubtitleLineWidth", "(I)V", "subtitleLineWidth$delegate", "subtitleTranslationColor", "getSubtitleTranslationColor", "setSubtitleTranslationColor", "subtitleTranslationColor$delegate", "subtitleSeparator", "getSubtitleSeparator", "setSubtitleSeparator", "subtitleSeparator$delegate", "requestTimeoutMs", "getRequestTimeoutMs", "setRequestTimeoutMs", "requestTimeoutMs$delegate", "maxRetries", "getMaxRetries", "setMaxRetries", "maxRetries$delegate", "translationBatchSize", "getTranslationBatchSize", "setTranslationBatchSize", "translationBatchSize$delegate", "translationBatchWindowMs", "getTranslationBatchWindowMs", "setTranslationBatchWindowMs", "translationBatchWindowMs$delegate", "microsoftEndpoint", "getMicrosoftEndpoint", "setMicrosoftEndpoint", "microsoftEndpoint$delegate", "microsoftApiKey", "getMicrosoftApiKey", "setMicrosoftApiKey", "microsoftApiKey$delegate", "microsoftRegion", "getMicrosoftRegion", "setMicrosoftRegion", "microsoftRegion$delegate", "openAiEndpoint", "getOpenAiEndpoint", "setOpenAiEndpoint", "openAiEndpoint$delegate", "openAiApiKey", "getOpenAiApiKey", "setOpenAiApiKey", "openAiApiKey$delegate", "openAiModel", "getOpenAiModel", "setOpenAiModel", "openAiModel$delegate", "openAiSystemPrompt", "getOpenAiSystemPrompt", "setOpenAiSystemPrompt", "openAiSystemPrompt$delegate", "openAiUserPrompt", "getOpenAiUserPrompt", "setOpenAiUserPrompt", "openAiUserPrompt$delegate", "kv", "Lcom/tencent/mmkv/MMKV;", "Pref", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ConfigManager {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "enabled", "getEnabled()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "provider", "getProvider()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "targetLanguage", "getTargetLanguage()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "subtitleMode", "getSubtitleMode()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "subtitleStyleEnabled", "getSubtitleStyleEnabled()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "subtitleTranslationScale", "getSubtitleTranslationScale()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "subtitleBaseScale", "getSubtitleBaseScale()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "subtitleLineWidth", "getSubtitleLineWidth()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "subtitleTranslationColor", "getSubtitleTranslationColor()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "subtitleSeparator", "getSubtitleSeparator()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "requestTimeoutMs", "getRequestTimeoutMs()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "maxRetries", "getMaxRetries()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "translationBatchSize", "getTranslationBatchSize()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "translationBatchWindowMs", "getTranslationBatchWindowMs()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "microsoftEndpoint", "getMicrosoftEndpoint()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "microsoftApiKey", "getMicrosoftApiKey()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "microsoftRegion", "getMicrosoftRegion()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "openAiEndpoint", "getOpenAiEndpoint()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "openAiApiKey", "getOpenAiApiKey()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "openAiModel", "getOpenAiModel()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "openAiSystemPrompt", "getOpenAiSystemPrompt()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ConfigManager.class, "openAiUserPrompt", "getOpenAiUserPrompt()Ljava/lang/String;", 0))};
    public static final String PROVIDER_GOOGLE = "google";
    public static final String PROVIDER_MICROSOFT = "microsoft";
    public static final String PROVIDER_OPENAI = "openai";
    public static final String SUBTITLE_ORIGINAL_FIRST = "original_first";
    public static final String SUBTITLE_TRANSLATION_FIRST = "translation_first";
    public static final String SUBTITLE_TRANSLATION_ONLY = "translation_only";

    /* renamed from: enabled$delegate, reason: from kotlin metadata */
    private final Pref enabled;
    private final MMKV kv;

    /* renamed from: maxRetries$delegate, reason: from kotlin metadata */
    private final Pref maxRetries;

    /* renamed from: microsoftApiKey$delegate, reason: from kotlin metadata */
    private final Pref microsoftApiKey;

    /* renamed from: microsoftEndpoint$delegate, reason: from kotlin metadata */
    private final Pref microsoftEndpoint;

    /* renamed from: microsoftRegion$delegate, reason: from kotlin metadata */
    private final Pref microsoftRegion;

    /* renamed from: openAiApiKey$delegate, reason: from kotlin metadata */
    private final Pref openAiApiKey;

    /* renamed from: openAiEndpoint$delegate, reason: from kotlin metadata */
    private final Pref openAiEndpoint;

    /* renamed from: openAiModel$delegate, reason: from kotlin metadata */
    private final Pref openAiModel;

    /* renamed from: openAiSystemPrompt$delegate, reason: from kotlin metadata */
    private final Pref openAiSystemPrompt;

    /* renamed from: openAiUserPrompt$delegate, reason: from kotlin metadata */
    private final Pref openAiUserPrompt;

    /* renamed from: provider$delegate, reason: from kotlin metadata */
    private final Pref provider;

    /* renamed from: requestTimeoutMs$delegate, reason: from kotlin metadata */
    private final Pref requestTimeoutMs;

    /* renamed from: subtitleBaseScale$delegate, reason: from kotlin metadata */
    private final Pref subtitleBaseScale;

    /* renamed from: subtitleLineWidth$delegate, reason: from kotlin metadata */
    private final Pref subtitleLineWidth;

    /* renamed from: subtitleMode$delegate, reason: from kotlin metadata */
    private final Pref subtitleMode;

    /* renamed from: subtitleSeparator$delegate, reason: from kotlin metadata */
    private final Pref subtitleSeparator;

    /* renamed from: subtitleStyleEnabled$delegate, reason: from kotlin metadata */
    private final Pref subtitleStyleEnabled;

    /* renamed from: subtitleTranslationColor$delegate, reason: from kotlin metadata */
    private final Pref subtitleTranslationColor;

    /* renamed from: subtitleTranslationScale$delegate, reason: from kotlin metadata */
    private final Pref subtitleTranslationScale;

    /* renamed from: targetLanguage$delegate, reason: from kotlin metadata */
    private final Pref targetLanguage;

    /* renamed from: translationBatchSize$delegate, reason: from kotlin metadata */
    private final Pref translationBatchSize;

    /* renamed from: translationBatchWindowMs$delegate, reason: from kotlin metadata */
    private final Pref translationBatchWindowMs;

    public ConfigManager(Context context, File directory) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(directory, "directory");
        this.enabled = new Pref(this, "enabled", true);
        this.provider = new Pref(this, "translator_provider", PROVIDER_GOOGLE);
        this.targetLanguage = new Pref(this, "target_language", "zh-Hans");
        this.subtitleMode = new Pref(this, "bilingual_order", SUBTITLE_ORIGINAL_FIRST);
        this.subtitleStyleEnabled = new Pref(this, "subtitle_style_enabled", true);
        this.subtitleTranslationScale = new Pref(this, "subtitle_translation_scale", Float.valueOf(0.75f));
        this.subtitleBaseScale = new Pref(this, "subtitle_base_scale", Float.valueOf(1.0f));
        this.subtitleLineWidth = new Pref(this, "subtitle_line_width", 44);
        this.subtitleTranslationColor = new Pref(this, "subtitle_translation_color", "dim");
        this.subtitleSeparator = new Pref(this, "subtitle_separator", "·");
        this.requestTimeoutMs = new Pref(this, "request_timeout_ms", 45000);
        this.maxRetries = new Pref(this, "max_retries", 2);
        this.translationBatchSize = new Pref(this, "translation_batch_size", 8);
        this.translationBatchWindowMs = new Pref(this, "translation_batch_window_ms", 150);
        this.microsoftEndpoint = new Pref(this, "microsoft_endpoint", MicrosoftTranslator.DEFAULT_AZURE_ENDPOINT);
        this.microsoftApiKey = new Pref(this, "microsoft_api_key", "");
        this.microsoftRegion = new Pref(this, "microsoft_region", "");
        this.openAiEndpoint = new Pref(this, "openai_endpoint", OpenAICompatibleTranslator.DEFAULT_ENDPOINT);
        this.openAiApiKey = new Pref(this, "openai_api_key", "");
        this.openAiModel = new Pref(this, "openai_model", OpenAICompatibleTranslator.DEFAULT_MODEL);
        this.openAiSystemPrompt = new Pref(this, "openai_system_prompt", "You are a professional, authentic machine translation engine.");
        this.openAiUserPrompt = new Pref(this, "openai_user_prompt", "Translate the following subtitle text into {{to}}. If translation is unnecessary, return the original text. NO explanations. NO notes:\n\n{{origin}}");
        MMKV.initialize(context, directory.getAbsolutePath());
        MMKV defaultMMKV = MMKV.defaultMMKV();
        Intrinsics.checkNotNullExpressionValue(defaultMMKV, "defaultMMKV(...)");
        this.kv = defaultMMKV;
    }

    public final boolean getEnabled() {
        return ((Boolean) this.enabled.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    public final void setEnabled(boolean z) {
        this.enabled.setValue2(this, $$delegatedProperties[0], (KProperty<?>) Boolean.valueOf(z));
    }

    public final String getProvider() {
        return (String) this.provider.getValue(this, $$delegatedProperties[1]);
    }

    public final void setProvider(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.provider.setValue2(this, $$delegatedProperties[1], (KProperty<?>) str);
    }

    public final String getTargetLanguage() {
        return (String) this.targetLanguage.getValue(this, $$delegatedProperties[2]);
    }

    public final void setTargetLanguage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.targetLanguage.setValue2(this, $$delegatedProperties[2], (KProperty<?>) str);
    }

    public final String getSubtitleMode() {
        return (String) this.subtitleMode.getValue(this, $$delegatedProperties[3]);
    }

    public final void setSubtitleMode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.subtitleMode.setValue2(this, $$delegatedProperties[3], (KProperty<?>) str);
    }

    public final boolean getSubtitleStyleEnabled() {
        return ((Boolean) this.subtitleStyleEnabled.getValue(this, $$delegatedProperties[4])).booleanValue();
    }

    public final void setSubtitleStyleEnabled(boolean z) {
        this.subtitleStyleEnabled.setValue2(this, $$delegatedProperties[4], (KProperty<?>) Boolean.valueOf(z));
    }

    public final float getSubtitleTranslationScale() {
        return ((Number) this.subtitleTranslationScale.getValue(this, $$delegatedProperties[5])).floatValue();
    }

    public final void setSubtitleTranslationScale(float f) {
        this.subtitleTranslationScale.setValue2(this, $$delegatedProperties[5], (KProperty<?>) Float.valueOf(f));
    }

    public final float getSubtitleBaseScale() {
        return ((Number) this.subtitleBaseScale.getValue(this, $$delegatedProperties[6])).floatValue();
    }

    public final void setSubtitleBaseScale(float f) {
        this.subtitleBaseScale.setValue2(this, $$delegatedProperties[6], (KProperty<?>) Float.valueOf(f));
    }

    public final int getSubtitleLineWidth() {
        return ((Number) this.subtitleLineWidth.getValue(this, $$delegatedProperties[7])).intValue();
    }

    public final void setSubtitleLineWidth(int i) {
        this.subtitleLineWidth.setValue2(this, $$delegatedProperties[7], (KProperty<?>) Integer.valueOf(i));
    }

    public final String getSubtitleTranslationColor() {
        return (String) this.subtitleTranslationColor.getValue(this, $$delegatedProperties[8]);
    }

    public final void setSubtitleTranslationColor(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.subtitleTranslationColor.setValue2(this, $$delegatedProperties[8], (KProperty<?>) str);
    }

    public final String getSubtitleSeparator() {
        return (String) this.subtitleSeparator.getValue(this, $$delegatedProperties[9]);
    }

    public final void setSubtitleSeparator(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.subtitleSeparator.setValue2(this, $$delegatedProperties[9], (KProperty<?>) str);
    }

    public final int getRequestTimeoutMs() {
        return ((Number) this.requestTimeoutMs.getValue(this, $$delegatedProperties[10])).intValue();
    }

    public final void setRequestTimeoutMs(int i) {
        this.requestTimeoutMs.setValue2(this, $$delegatedProperties[10], (KProperty<?>) Integer.valueOf(i));
    }

    public final int getMaxRetries() {
        return ((Number) this.maxRetries.getValue(this, $$delegatedProperties[11])).intValue();
    }

    public final void setMaxRetries(int i) {
        this.maxRetries.setValue2(this, $$delegatedProperties[11], (KProperty<?>) Integer.valueOf(i));
    }

    public final int getTranslationBatchSize() {
        return ((Number) this.translationBatchSize.getValue(this, $$delegatedProperties[12])).intValue();
    }

    public final void setTranslationBatchSize(int i) {
        this.translationBatchSize.setValue2(this, $$delegatedProperties[12], (KProperty<?>) Integer.valueOf(i));
    }

    public final int getTranslationBatchWindowMs() {
        return ((Number) this.translationBatchWindowMs.getValue(this, $$delegatedProperties[13])).intValue();
    }

    public final void setTranslationBatchWindowMs(int i) {
        this.translationBatchWindowMs.setValue2(this, $$delegatedProperties[13], (KProperty<?>) Integer.valueOf(i));
    }

    public final String getMicrosoftEndpoint() {
        return (String) this.microsoftEndpoint.getValue(this, $$delegatedProperties[14]);
    }

    public final void setMicrosoftEndpoint(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.microsoftEndpoint.setValue2(this, $$delegatedProperties[14], (KProperty<?>) str);
    }

    public final String getMicrosoftApiKey() {
        return (String) this.microsoftApiKey.getValue(this, $$delegatedProperties[15]);
    }

    public final void setMicrosoftApiKey(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.microsoftApiKey.setValue2(this, $$delegatedProperties[15], (KProperty<?>) str);
    }

    public final String getMicrosoftRegion() {
        return (String) this.microsoftRegion.getValue(this, $$delegatedProperties[16]);
    }

    public final void setMicrosoftRegion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.microsoftRegion.setValue2(this, $$delegatedProperties[16], (KProperty<?>) str);
    }

    public final String getOpenAiEndpoint() {
        return (String) this.openAiEndpoint.getValue(this, $$delegatedProperties[17]);
    }

    public final void setOpenAiEndpoint(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.openAiEndpoint.setValue2(this, $$delegatedProperties[17], (KProperty<?>) str);
    }

    public final String getOpenAiApiKey() {
        return (String) this.openAiApiKey.getValue(this, $$delegatedProperties[18]);
    }

    public final void setOpenAiApiKey(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.openAiApiKey.setValue2(this, $$delegatedProperties[18], (KProperty<?>) str);
    }

    public final String getOpenAiModel() {
        return (String) this.openAiModel.getValue(this, $$delegatedProperties[19]);
    }

    public final void setOpenAiModel(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.openAiModel.setValue2(this, $$delegatedProperties[19], (KProperty<?>) str);
    }

    public final String getOpenAiSystemPrompt() {
        return (String) this.openAiSystemPrompt.getValue(this, $$delegatedProperties[20]);
    }

    public final void setOpenAiSystemPrompt(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.openAiSystemPrompt.setValue2(this, $$delegatedProperties[20], (KProperty<?>) str);
    }

    public final String getOpenAiUserPrompt() {
        return (String) this.openAiUserPrompt.getValue(this, $$delegatedProperties[21]);
    }

    public final void setOpenAiUserPrompt(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.openAiUserPrompt.setValue2(this, $$delegatedProperties[21], (KProperty<?>) str);
    }

    /* compiled from: ConfigManager.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0002B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0004\b\u0007\u0010\bJ\"\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00020\u00032\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rH\u0096\u0002¢\u0006\u0002\u0010\u000eJ*\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u00032\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\u0011\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u0013"}, d2 = {"Licu/nullptr/polyglot/core/ConfigManager$Pref;", "T", "Lkotlin/properties/ReadWriteProperty;", "Licu/nullptr/polyglot/core/ConfigManager;", "key", "", "defaultValue", "<init>", "(Licu/nullptr/polyglot/core/ConfigManager;Ljava/lang/String;Ljava/lang/Object;)V", "Ljava/lang/Object;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Licu/nullptr/polyglot/core/ConfigManager;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "value", "(Licu/nullptr/polyglot/core/ConfigManager;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private final class Pref<T> implements ReadWriteProperty<ConfigManager, T> {
        private final T defaultValue;
        private final String key;
        final /* synthetic */ ConfigManager this$0;

        public Pref(ConfigManager this$0, String key, T t) {
            Intrinsics.checkNotNullParameter(key, "key");
            this.this$0 = this$0;
            this.key = key;
            this.defaultValue = t;
        }

        @Override // kotlin.properties.ReadWriteProperty, kotlin.properties.ReadOnlyProperty
        public /* bridge */ /* synthetic */ Object getValue(Object thisRef, KProperty property) {
            return getValue((ConfigManager) thisRef, (KProperty<?>) property);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.properties.ReadWriteProperty
        public /* bridge */ /* synthetic */ void setValue(ConfigManager configManager, KProperty property, Object obj) {
            setValue2(configManager, (KProperty<?>) property, (KProperty) obj);
        }

        public T getValue(ConfigManager thisRef, KProperty<?> property) {
            Intrinsics.checkNotNullParameter(thisRef, "thisRef");
            Intrinsics.checkNotNullParameter(property, "property");
            T t = this.defaultValue;
            if (t instanceof String) {
                return (T) this.this$0.kv.getString(this.key, (String) this.defaultValue);
            }
            if (t instanceof Integer) {
                return (T) Integer.valueOf(this.this$0.kv.getInt(this.key, ((Number) this.defaultValue).intValue()));
            }
            if (t instanceof Long) {
                return (T) Long.valueOf(this.this$0.kv.getLong(this.key, ((Number) this.defaultValue).longValue()));
            }
            if (t instanceof Float) {
                return (T) Float.valueOf(this.this$0.kv.getFloat(this.key, ((Number) this.defaultValue).floatValue()));
            }
            if (t instanceof Boolean) {
                return (T) Boolean.valueOf(this.this$0.kv.getBoolean(this.key, ((Boolean) this.defaultValue).booleanValue()));
            }
            throw new IllegalArgumentException("Unsupported type");
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(ConfigManager thisRef, KProperty<?> property, T value) {
            Intrinsics.checkNotNullParameter(thisRef, "thisRef");
            Intrinsics.checkNotNullParameter(property, "property");
            if (value instanceof String) {
                this.this$0.kv.putString(this.key, (String) value);
                return;
            }
            if (value instanceof Integer) {
                this.this$0.kv.putInt(this.key, ((Number) value).intValue());
                return;
            }
            if (value instanceof Long) {
                this.this$0.kv.putLong(this.key, ((Number) value).longValue());
            } else if (value instanceof Float) {
                this.this$0.kv.putFloat(this.key, ((Number) value).floatValue());
            } else {
                if (value instanceof Boolean) {
                    this.this$0.kv.putBoolean(this.key, ((Boolean) value).booleanValue());
                    return;
                }
                throw new IllegalArgumentException("Unsupported type");
            }
        }
    }
}
