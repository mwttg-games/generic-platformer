package io.github.mwttg.games.platformer.utility

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class TextFileTest {

    @Test
    fun `read a valid text-file`() {
        assertEquals(
            expected = listOf("1. Line", "2. Line", "3. Line"),
            actual = TextFile.readFromResources("/files/valid-text-file.txt")
        )
    }

    @Test
    fun `try to read a text-file that does not exists`() {
        assertFailsWith<RuntimeException>(
            message = "An Error occurred during reading the TextFile: '/files/this-file-does-not-exist.txt'",
            block = { TextFile.readFromResources("/files/this-file-does-not-exist.txt") }
        )
    }
}