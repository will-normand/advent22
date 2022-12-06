import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day06Test {

    private val day06 = Day06("src/test/resources/input06")

    @Test
    fun part1() {
        assertEquals("11", day06.part1())
    }

    @Test
    fun part2() {
        assertEquals("26", day06.part2())
    }
}