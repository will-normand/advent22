import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day04Test {

    private val day04 = Day04("src/test/resources/input04")

    @Test
    fun `fully contains another set`() {
        assertTrue(day04.fullyContains(setOf(1, 2, 3), setOf(1, 2, 3, 4)))
        assertTrue(day04.fullyContains(setOf(1, 2, 3, 4), setOf(1, 2, 3)))
        assertFalse(day04.fullyContains(setOf(1, 2, 3, 4), setOf(0, 1, 2, 3)))
    }

    @Test
    fun part1() {
        assertEquals("2", day04.part1())
    }

    @Test
    fun part2() {
        assertEquals("4", day04.part2())
    }
}
