class Day04(filename: String) : Advent, InputFileReader {

    private val lines = loadInput(filename)

    private fun parseInput(): List<Pair<IntRange, IntRange>> {
        return lines.map { parseLine(it) }
    }

    private fun parseLine(line: String): Pair<IntRange, IntRange> {
        val (elf1, elf2) = line.split(',')
        val (elf1a, elf1b) = elf1.split('-')
        val (elf2a, elf2b) = elf2.split('-')
        return Pair(elf1a.toInt()..elf1b.toInt(), elf2a.toInt()..elf2b.toInt())
    }

    fun fullyContains(elf1: Set<Int>, elf2: Set<Int>): Boolean {
        val (bigger, smaller) = if (elf1.size > elf2.size) Pair(elf1, elf2) else Pair(elf2, elf1)
        return (smaller subtract bigger).isEmpty()
    }

    override fun part1(): String {
        return parseInput().filter {
            fullyContains(it.first.toSet(), it.second.toSet())
        }.size.toString()
    }

    override fun part2(): String {
        return parseInput().filter {
            (it.first.toSet() intersect it.second.toSet()).isNotEmpty()
        }.size.toString()
    }
}

fun main() {
    val advent: Advent = Day04("src/main/resources/input04")
    println("Part 1 answer ${advent.part1()}")
    println("Part 2 answer ${advent.part2()}")
}
