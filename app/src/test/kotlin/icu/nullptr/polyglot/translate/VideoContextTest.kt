package icu.nullptr.polyglot.translate

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test

class VideoContextTest {
    @After
    fun tearDown() {
        VideoContext.clear()
    }

    @Test
    fun emptyTitleFallsBackToUnknown() {
        VideoContext.clear()
        assertEquals("unknown", VideoContext.promptTitle())
    }

    @Test
    fun titleIsCollapsedAndTruncated() {
        VideoContext.updateTitle("  Line one\n\n line two  ")
        assertEquals("Line one line two", VideoContext.currentTitle())

        VideoContext.updateTitle("x".repeat(300))
        assertEquals(200, VideoContext.currentTitle().length)
    }
}
