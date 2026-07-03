package icu.nullptr.polyglot.core

import android.content.Context
import com.tencent.mmkv.MMKV
import java.io.File
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ConfigManager(context: Context, directory: File) {

    var enabled: Boolean by Pref("enabled", true)

    var sourceLanguage: String by Pref("source_language", "auto")

    var targetLanguage: String by Pref("target_language", "zh-Hans")

    var provider: String by Pref("translator_provider", "microsoft")

    var bilingualOrder: String by Pref("bilingual_order", "original_first")

    var maxConcurrency: Int by Pref("max_concurrency", 4)

    var requestTimeoutMs: Int by Pref("request_timeout_ms", 45000)

    var maxRetries: Int by Pref("max_retries", 2)

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
    }
}
