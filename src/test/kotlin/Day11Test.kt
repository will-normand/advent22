import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day11Test {

    class MonkeyTest {
        @Test
        fun `parseOperation with multiply`() {
            val op = Day11.Monkey.parseOperation("old * 19")
            assertEquals(19, op(1))
            assertEquals(38, op(2))
        }

        @Test
        fun `parseOperation with add`() {
            val op = Day11.Monkey.parseOperation("old + 12")
            assertEquals(13, op(1))
            assertEquals(38, op(26))
        }

        @Test
        fun `parseOperation with two olds`() {
            val square = Day11.Monkey.parseOperation("old * old")
            assertEquals(36, square(6))
            val double = Day11.Monkey.parseOperation("old + old")
            assertEquals(64, double(32))
        }
    }

    @Test
    fun part1() {
    }
}