package icu.nullptr.polyglot.translate

import org.junit.Assert.assertEquals
import org.junit.Test

class LanguageNamesTest {
    @Test
    fun resolvesChineseVariants() {
        assertEquals("Chinese (Simplified)", LanguageNames.displayName("zh-Hans"))
        assertEquals("Chinese (Traditional)", LanguageNames.displayName("zh-Hant"))
    }

    @Test
    fun resolvesPlainLanguages() {
        assertEquals("English", LanguageNames.displayName("en"))
        assertEquals("Japanese", LanguageNames.displayName("ja"))
    }

    @Test
    fun handlesAutoAndUnknownCodes() {
        assertEquals("auto-detect", LanguageNames.displayName("auto"))
        assertEquals("auto-detect", LanguageNames.displayName(""))
        assertEquals("xx-unknown", LanguageNames.displayName("xx-unknown"))
    }
}
