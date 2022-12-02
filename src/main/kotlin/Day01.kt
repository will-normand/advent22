import java.io.File

class Day01(filename: String) : Advent {
    val lines = loadInput(filename)
    val elves = parseInput(lines)

    fun loadInput(filename: String): List<String> {
        val lines = mutableListOf<String>()
        File(filename).useLines { it.forEach { l -> lines.add(l) } }

        return lines.toList()
    }

    fun parseInput(lines: List<String>): List<List<Int>> {
        val elves = mutableListOf<List<Int>>()
        var currentElf = mutableListOf<Int>()

        for (line in lines) {
            if (line.isEmpty()) {
                elves.add(currentElf)
                currentElf = mutableListOf()
            } else {
                currentElf.add(line.toInt())
            }
        }

        return elves
    }

    override fun part1(): String {
        return elves.map { elf -> elf.sum() }
            .max()
            .toString()
    }

    override fun part2(): String {
        return elves.map { it.sum() }
            .sorted()
            .takeLast(3)
            .sum()
            .toString()
    }
}

fun main() {
    val advent: Advent = Day01("src/main/resources/input01")
    println("Part 1 answer ${advent.part1()}")
    println("Part 2 answer ${advent.part2()}")
}
