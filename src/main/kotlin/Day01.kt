import java.io.File

fun main() {
    val lines = loadInput("src/main/resources/input01")
    val elves = parseInput(lines)

    val part1 = elves.map { elf -> elf.sum() }
        .max()

    val part2 = elves.map { it.sum() }
        .sorted()
        .takeLast(3)
        .sum()

    println("Part 1 answer $part1")
    println("Part 2 answer $part2")
}

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
