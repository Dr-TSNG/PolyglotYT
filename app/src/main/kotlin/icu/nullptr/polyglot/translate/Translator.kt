package icu.nullptr.polyglot.translate

fun interface Translator {
    @Throws(Exception::class)
    fun translate(request: TranslationRequest): TranslationResult
}
