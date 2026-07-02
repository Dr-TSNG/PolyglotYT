package icu.nullptr.polyglot.translate.providers

import icu.nullptr.polyglot.translate.TranslationRequest
import icu.nullptr.polyglot.translate.TranslationResult
import icu.nullptr.polyglot.translate.Translator

class OpenAICompatibleTranslator : Translator {
    override fun translate(request: TranslationRequest): TranslationResult =
        TranslationResult(request.texts)
}
