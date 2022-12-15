import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day11Test {

    private val day11 = Day11("src/test/resources/input11")


    class MonkeyTest {
        @Test
        fun `parseOperation with multiply`() {
            val op = Day11.Monkey.parseOperation("old * 19")
            assertEquals(19.toBigInteger(), op(1.toBigInteger()))
            assertEquals(38.toBigInteger(), op(2.toBigInteger()))
        }

        @Test
        fun `parseOperation with add`() {
            val op = Day11.Monkey.parseOperation("old + 12")
            assertEquals(13.toBigInteger(), op(1.toBigInteger()))
            assertEquals(38.toBigInteger(), op(26.toBigInteger()))
        }

        @Test
        fun `parseOperation with two olds`() {
            val square = Day11.Monkey.parseOperation("old * old")
            assertEquals(36.toBigInteger(), square(6.toBigInteger()))
            val double = Day11.Monkey.parseOperation("old + old")
            assertEquals(64.toBigInteger(), double(32.toBigInteger()))
        }

        @Test
        fun parseDivisor() {
            assertEquals(13, Day11.Monkey.parseDivisor("  Test: divisible by 13"))
        }

        @Test
        fun parseStartingList() {
            assertEquals(
                listOf(65, 77).map { it.toBigInteger() },
                Day11.Monkey.parseStartingList("  Starting items: 65, 77")
            )
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
            assertEquals(listOf(90, 92, 63, 91, 96, 63, 64).map { it.toBigInteger() }, monkey.items)
            assertEquals(13, monkey.test)
            assertEquals(4, monkey.trueDest)
            assertEquals(3, monkey.falseDest)
        }
    }

    @Test
    fun `parse the monkeys`() {
        val result = day11.parseMonkeys()
        assertEquals(4, result.size)
    }

    @Test
    fun `do the first round`() {
        val game = Day11.Game(day11.parseMonkeys(), Day11.Part.Part1)
        game.round()
        assertEquals(mutableListOf(20, 23, 27, 26).map { it.toBigInteger() }, game.monkeys[0].items)
        assertEquals(mutableListOf(2080, 25, 167, 207, 401, 1046).map { it.toBigInteger() }, game.monkeys[1].items)
    }

    @Test
    fun `do 20 rounds`() {
        val game = Day11.Game(day11.parseMonkeys(), Day11.Part.Part1)
        repeat(20) { game.round() }
        assertEquals(mutableListOf(10, 12, 14, 26, 34).map { it.toBigInteger() }, game.monkeys[0].items)
        assertEquals(mutableListOf(245, 93, 53, 199, 115).map { it.toBigInteger() }, game.monkeys[1].items)
        assertTrue(game.monkeys[2].items.isEmpty())
        assertTrue(game.monkeys[3].items.isEmpty())
    }

    @Test
    fun part1() {
        val result = day11.part1()

        assertEquals("10605", result)
    }

    @Test
    fun `do the first round in part 2`() {
        val game = Day11.Game(day11.parseMonkeys(), Day11.Part.Part2)
        repeat(20) { game.round() }
        game.printInspections()
    }

    @Test
    fun part2() {
        val result = day11.part2()
        assertEquals("2713310158", result)
    }
}