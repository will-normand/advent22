private const val ASCII_LOWERCASE_OFFSET = 96
private const val ASCII_UPPERCASE_OFFSET = 64
private const val LOCAL_UPPERCASE_OFFSET = 26

class Day03(filename: String) : Advent, InputFileReader {

    private val lines = loadInput(filename)

    class Rucksack(val compartment1: CharArray, val compartment2: CharArray) {
        fun commonItems(): CharArray =
            compartment1.toSet().intersect(compartment2.toSet()).toCharArray()
    }

    fun parseInput(): List<Rucksack> {
        return lines.map {
            assert(it.length.mod(2) == 0)
            val first = it.slice(0 until it.length / 2)
            val second = it.slice(it.length / 2 until it.length)
            Rucksack(first.toCharArray(), second.toCharArray())
        }
    }

    override fun part1(): String {
        return parseInput().sumOf { rucksack ->
            rucksack.commonItems().sumOf { priority(it) }
        }.toString()
    }

    override fun part2(): String {
        return TODO()
    }

    companion object {
        fun priority(c: Char) =
            if (c.isLowerCase()) c.code - ASCII_LOWERCASE_OFFSET
            else c.code - ASCII_UPPERCASE_OFFSET + LOCAL_UPPERCASE_OFFSET
    }
}

fun main() {
    val advent: Advent = Day03("src/main/resources/input03")
    println("Part 1 answer ${advent.part1()}")
    println("Part 2 answer ${advent.part2()}")
}
