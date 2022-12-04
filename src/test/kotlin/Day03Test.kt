import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class Day03Test {
    private val day03 = Day03("src/test/resources/input03")

    @Test
    fun `parseInput creates rucksacks`() {
        val rucksacks = day03.parseInput()
        val first = "vJrwpWtwJgWr".toCharArray()
        val second = "hcsFMMfFFhFp".toCharArray()
        val rucksack = rucksacks.first()
        assertContentEquals(first, rucksack.compartment1)
        assertContentEquals(second, rucksack.compartment2)
    }

    @Test
    fun `find items in both compartments`() {
        val rucksack = Day03.Rucksack("vJrwpWtwJgWr".toCharArray(), "hcsFMMfFFhFp".toCharArray())
        val common = rucksack.commonItems()
        assertContentEquals("p".toCharArray(), common)
    }

    @Test
    fun `prioritise items`() {
        assertEquals(16, Day03.priority('p'))
        assertEquals(38, Day03.priority('L'))
    }

    @Test
    fun part1() {
        assertEquals("157", day03.part1())
    }

    @Test
    fun part2() {
        assertEquals("70", day03.part2())
    }
}
