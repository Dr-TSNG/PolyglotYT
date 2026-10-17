package icu.nullptr.polyglot.translate

import org.junit.Assert.assertEquals
import org.junit.Test

class BatchResponseParserTest {
    @Test
    fun parsesJsonObjectInOrder() {
        val content = """
            {"translations":[{"id":1,"text":"第一句"},{"id":2,"text":"第二句"}]}
        """.trimIndent()

        assertEquals(listOf("第一句", "第二句"), BatchResponseParser.parse(content, 2))
    }

    @Test
    fun mapsTranslationsByIdWhenOutOfOrder() {
        val content = """
            {"translations":[{"id":2,"text":"第二句"},{"id":1,"text":"第一句"}]}
        """.trimIndent()

        assertEquals(listOf("第一句", "第二句"), BatchResponseParser.parse(content, 2))
    }

    @Test
    fun stripsThinkBlockAndCodeFence() {
        val content = """
            <think>internal reasoning</think>
            ```json
            {"translations":[{"id":1,"text":"译文"}]}
            ```
        """.trimIndent()

        assertEquals(listOf("译文"), BatchResponseParser.parse(content, 1))
    }

    @Test
    fun parsesStringArray() {
        assertEquals(
            listOf("one", "two"),
            BatchResponseParser.parse("""["one","two"]""", 2),
        )
    }

    @Test
    fun leavesMissingEntriesEmpty() {
        val content = """{"translations":[{"id":1,"text":"only one"}]}"""

        assertEquals(listOf("only one", ""), BatchResponseParser.parse(content, 2))
    }

    @Test
    fun removesSurroundingQuotes() {
        val content = """{"translations":[{"id":1,"text":"\"quoted\""}]}"""

        assertEquals(listOf("quoted"), BatchResponseParser.parse(content, 1))
    }

    @Test
    fun parsesNumberedLinesWhenJsonIsMissing() {
        assertEquals(
            listOf("第一句", "第二句"),
            BatchResponseParser.parse("1. 第一句\n2. 第二句", 2),
        )
    }

    @Test
    fun returnsEmptyListForUnparseableContent() {
        assertEquals(listOf("", ""), BatchResponseParser.parse("no json here", 2))
    }
}
