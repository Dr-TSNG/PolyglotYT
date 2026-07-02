package icu.nullptr.polyglot.dex

import org.luckypray.dexkit.DexKitCacheBridge
import org.luckypray.dexkit.annotations.DexKitExperimentalApi
import java.io.File
import java.util.Properties

@OptIn(DexKitExperimentalApi::class)
class DexKitFileCache(directory: File) : DexKitCacheBridge.Cache {
    private val file = File(directory, "dexkit-cache.properties")
    private val properties = Properties()

    init {
        if (file.exists()) {
            runCatching {
                file.inputStream().use(properties::load)
            }.onFailure {
                properties.clear()
            }
        }
    }

    @Synchronized
    override fun getString(key: String, default: String?): String? =
        properties.getProperty(STRING_PREFIX + key, default)

    @Synchronized
    override fun putString(key: String, value: String) {
        properties.setProperty(STRING_PREFIX + key, value)
        flush()
    }

    @Synchronized
    override fun getStringList(key: String, default: List<String>?): List<String>? {
        val propertyKey = LIST_PREFIX + key
        if (!properties.containsKey(propertyKey)) return default
        val raw = properties.getProperty(propertyKey, "")
        if (raw.isEmpty()) return emptyList()
        return raw.split(LIST_SEPARATOR)
    }

    @Synchronized
    override fun putStringList(key: String, value: List<String>) {
        properties.setProperty(LIST_PREFIX + key, value.joinToString(LIST_SEPARATOR))
        flush()
    }

    @Synchronized
    override fun remove(key: String) {
        properties.remove(STRING_PREFIX + key)
        properties.remove(LIST_PREFIX + key)
        flush()
    }

    @Synchronized
    override fun getAllKeys(): Collection<String> = properties.keys
        .asSequence()
        .map { it.toString() }
        .mapNotNull { key ->
            when {
                key.startsWith(STRING_PREFIX) -> key.removePrefix(STRING_PREFIX)
                key.startsWith(LIST_PREFIX) -> key.removePrefix(LIST_PREFIX)
                else -> null
            }
        }
        .toSet()

    @Synchronized
    override fun clearAll() {
        properties.clear()
        flush()
    }

    private fun flush() {
        file.outputStream().use {
            properties.store(it, "PolyglotYT DexKit cache")
        }
    }

    private companion object {
        const val STRING_PREFIX = "s."
        const val LIST_PREFIX = "l."
        const val LIST_SEPARATOR = "\u001f"
    }
}
