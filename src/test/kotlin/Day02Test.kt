import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day02Test {

    @Test
    fun part1() {
        val advent = Day02("src/test/resources/input02")
        assertEquals("15", advent.part1())
    }

    @Test
    fun part2() {
        val advent = Day02("src/test/resources/input02")
        assertEquals("12", advent.part2())
    }
}
