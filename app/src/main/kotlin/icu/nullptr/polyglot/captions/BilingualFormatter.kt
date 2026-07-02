package icu.nullptr.polyglot.captions

import icu.nullptr.polyglot.module

object BilingualFormatter {
    fun format(original: String, translated: String?): CharSequence {
        if (translated.isNullOrBlank() || translated == original) return original
        return if (module.config.bilingualOrder == "translation_first") {
            "$translated\n$original"
        } else {
            "$original\n$translated"
        }
    }
}
