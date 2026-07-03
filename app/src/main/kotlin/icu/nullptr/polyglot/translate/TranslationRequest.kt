package icu.nullptr.polyglot.translate

data class TranslationRequest(
    val texts: List<String>,
    val sourceLanguage: String,
    val targetLanguage: String,
    val context: String,
    val timeoutMs: Int,
)
