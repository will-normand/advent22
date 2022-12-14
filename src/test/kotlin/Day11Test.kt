import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day11Test {

    private val day11 = Day11("src/test/resources/input11")


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

        @Test
        fun parseDivisor() {
            assertEquals(13, Day11.Monkey.parseDivisor("  Test: divisible by 13"))
        }

        @Test
        fun parseStartingList() {
            assertEquals(listOf(65, 77), Day11.Monkey.parseStartingList("  Starting items: 65, 77"))
        }

        @Test
        fun `parse a monkey`() {
            val monkeyInput = """
                Monkey 2:
                  Starting items: 90, 92, 63, 91, 96, 63, 64
                  Operation: new = old + 1
                  Test: divisible by 13
                    If true: throw to monkey 4
                    If false: throw to monkey 3
            """.trimIndent().split('\n')

            val monkey = Day11.Monkey.parseMonkey(monkeyInput)
            assertEquals(listOf(90, 92, 63, 91, 96, 63, 64), monkey.items)
            assertEquals(13, monkey.test)
            assertEquals(4, monkey.trueDest)
            assertEquals(3, monkey.falseDest)
        }
    }

    @Test
    fun parseMonkeys() {
        val result = day11.parseMonkeys()
        assertEquals(4, result.size)
    }

    @Test
    fun part1() {
    }
}