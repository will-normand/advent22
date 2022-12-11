class Day11(filename: String) : Advent, InputFileReader {


    class Monkey(items: List<Int>, operation: Operation, test: Test, trueDest: Int, falseDest: Int) {
        companion object {
            val add: Int.(Int) -> Int = { b -> plus(b) }
            val multiply: Int.(Int) -> Int = { b -> times(b) }

            fun parseOperation(input: String): Operation {
                val (a, op, b) = input.split(" ")
                val opFun = when (op) {
                    "*" -> multiply
                    "+" -> add
                    else -> throw Exception("Unknown operation $op")
                }

                if (a != "old") throw Exception("Unexpected first operand $a")

                return if (b == "old") { x -> x.opFun(x) }
                else { x -> x.opFun(b.toInt()) }
            }
        }
    }

    private val lines = loadInput(filename)
    override fun part1(): String {
        return TODO()
    }

    override fun part2(): String {
        return TODO()
    }
}

typealias Operation = (Int) -> Int
typealias Test = (Int) -> Boolean

fun main() {
    val advent: Advent = Day11("src/main/resources/input11")
    println("Part 1 answer ${advent.part1()}")
    println("Part 2 answer ${advent.part2()}")
}
