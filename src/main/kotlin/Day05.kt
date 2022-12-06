
class Day05(filename: String) : Advent, InputFileReader {

    private val lines = loadInput(filename)

    class CrateStack(private val initialStack: MutableList<Char>) {
        fun pop() = initialStack.drop(1)
        fun push(crate: Char) = initialStack.add(0, crate)
    }
    data class Move(val quantity: Int, val source: Int, val dest: Int)

    fun parseInput(): Pair<List<CrateStack>, List<Move>> {
        val crateInput = lines.takeWhile { it.isNotEmpty() }
        println(crateInput)
        TODO()
    }

    override fun part1(): String {
        return TODO()
    }

    override fun part2(): String {
        return TODO()
    }
}

fun main() {
    val advent: Advent = Day05("src/main/resources/input05")
    println("Part 1 answer ${advent.part1()}")
    println("Part 2 answer ${advent.part2()}")
}
