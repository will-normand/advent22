class Day06(filename: String) : Advent, InputFileReader {

    private val lines = loadInput(filename)
    private fun startOfPacket(markerSize: Int) =
        lines.first().windowed(markerSize) { it.toSet().size }.indexOf(markerSize).plus(markerSize)

    override fun part1(): String {
        return startOfPacket(4).toString()
    }

    override fun part2(): String {
        return startOfPacket(14).toString()
    }
}

fun main() {
    val advent: Advent = Day06("src/main/resources/input06")
    println("Part 1 answer ${advent.part1()}")
    println("Part 2 answer ${advent.part2()}")
}
