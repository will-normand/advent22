@file:Suppress("UNREACHABLE_CODE")

class Template(filename: String) : Advent, InputFileReader {

    private val lines = loadInput(filename)
    override fun part1(): String {
        return TODO()
    }

    override fun part2(): String {
        return TODO()
    }
}

fun main() {
    val advent: Advent = Template("src/main/resources/inputXX")
    println("Part 1 answer ${advent.part1()}")
    println("Part 2 answer ${advent.part2()}")
}
