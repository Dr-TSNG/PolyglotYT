package icu.nullptr.polyglot.core

import android.content.Context
import com.tencent.mmkv.MMKV
import java.io.File
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ConfigManager(context: Context, directory: File) {

    var enabled: Boolean by Pref("enabled", true)

    var provider: String by Pref("translator_provider", PROVIDER_GOOGLE)

    var targetLanguage: String by Pref("target_language", "zh-Hans")

    var subtitleMode: String by Pref("bilingual_order", SUBTITLE_ORIGINAL_FIRST)

    var subtitleStyleEnabled: Boolean by Pref("subtitle_style_enabled", true)

    var subtitleSentenceBreak: Boolean by Pref("subtitle_sentence_break", false)

    var subtitleTranslationScale: Float by Pref("subtitle_translation_scale", 0.75f)

    var subtitleBaseScale: Float by Pref("subtitle_base_scale", 0.85f)

    var subtitleLineWidth: Int by Pref("subtitle_line_width", 120)

    var subtitleTranslationColor: String by Pref("subtitle_translation_color", "dim")

    var subtitleSeparator: String by Pref("subtitle_separator", "")

    var requestTimeoutMs: Int by Pref("request_timeout_ms", 45000)

    var maxRetries: Int by Pref("max_retries", 2)

    var translationBatchSize: Int by Pref("translation_batch_size", 8)

    var translationBatchWindowMs: Int by Pref("translation_batch_window_ms", 150)

    var microsoftEndpoint: String by Pref(
        "microsoft_endpoint",
        "https://api.cognitive.microsofttranslator.com/translate",
    )

    var microsoftApiKey: String by Pref("microsoft_api_key", "")

    var microsoftRegion: String by Pref("microsoft_region", "")

    var openAiEndpoint: String by Pref(
        "openai_endpoint",
        "https://api.openai.com/v1/chat/completions",
    )

    var openAiApiKey: String by Pref("openai_api_key", "")

    var openAiModel: String by Pref("openai_model", "gpt-4o-mini")

    var openAiSystemPrompt: String by Pref(
        "openai_system_prompt",
        "You are a professional, authentic machine translation engine.",
    )

    var openAiUserPrompt: String by Pref(
        "openai_user_prompt",
        "Translate the following subtitle text into {{to}}. " +
            "If translation is unnecessary, return the original text. " +
            "NO explanations. NO notes:\n\n{{origin}}",
    )

    private inner class Pref<T>(
        private val key: String,
        private val defaultValue: T,
    ) : ReadWriteProperty<ConfigManager, T> {
        @Suppress("UNCHECKED_CAST")
        override fun getValue(thisRef: ConfigManager, property: KProperty<*>): T {
            return when (defaultValue) {
                is String -> kv.getString(key, defaultValue) as T
                is Int -> kv.getInt(key, defaultValue) as T
                is Long -> kv.getLong(key, defaultValue) as T
                is Float -> kv.getFloat(key, defaultValue) as T
                is Boolean -> kv.getBoolean(key, defaultValue) as T
                else -> throw IllegalArgumentException("Unsupported type")
            }
        }

        override fun setValue(thisRef: ConfigManager, property: KProperty<*>, value: T) {
            when (value) {
                is String -> kv.putString(key, value)
                is Int -> kv.putInt(key, value)
                is Long -> kv.putLong(key, value)
                is Float -> kv.putFloat(key, value)
                is Boolean -> kv.putBoolean(key, value)
                else -> throw IllegalArgumentException("Unsupported type")
            }
        }
    }

    private val kv: MMKV

    init {
        MMKV.initialize(context, directory.absolutePath)
        kv = MMKV.defaultMMKV()
        migrateLegacyDefaults()
    }

    /**
     * The subtitle base scale used to default to 1.0f, which made long
     * captions wrap onto several lines inside YouTube's narrow subtitle
     * container. New installs default to 0.85f; existing installs that never
     * touched the setting still hold the old 1.0f value and are migrated here.
     */
    private fun migrateLegacyDefaults() {
        if (kv.containsKey("subtitle_base_scale") && kv.getFloat("subtitle_base_scale", 0.85f) == 1.0f) {
            kv.putFloat("subtitle_base_scale", 0.85f)
        }
        // Old line-width default was 80 (aggressive wrapping). Migrate it to
        // 120 so long captions stay on one line unless YouTube wraps them.
        if (kv.containsKey("subtitle_line_width") && kv.getInt("subtitle_line_width", 120) == 80) {
            kv.putInt("subtitle_line_width", 120)
        }
    }

    companion object {
        const val PROVIDER_GOOGLE = "google"
        const val PROVIDER_MICROSOFT = "microsoft"
        const val PROVIDER_OPENAI = "openai"

        const val SUBTITLE_ORIGINAL_FIRST = "original_first"
        const val SUBTITLE_TRANSLATION_FIRST = "translation_first"
        const val SUBTITLE_TRANSLATION_ONLY = "translation_only"
    }
}
