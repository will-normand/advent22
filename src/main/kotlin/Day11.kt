class Day11(filename: String) : Advent, InputFileReader {


    data class Monkey(
        val items: List<Int>,
        val operation: Operation,
        val test: Int,
        val trueDest: Int,
        val falseDest: Int
    ) {
        companion object {
            val add: Int.(Int) -> Int = { b -> plus(b) }
            val multiply: Int.(Int) -> Int = { b -> times(b) }

            fun parseOperation(input: String): Operation {
                val (a, op, b) = input.trim().removePrefix("Operation: new = ").split(" ")
                val opFun = when (op) {
                    "*" -> multiply
                    "+" -> add
                    else -> throw Exception("Unknown operation $op")
                }

                if (a != "old") throw Exception("Unexpected first operand $a")

                return if (b == "old") { x -> x.opFun(x) }
                else { x -> x.opFun(b.toInt()) }
            }

            private fun parseIntInput(prefix: String, input: String): Int =
                input.trim().removePrefix(prefix).toInt()

            fun parseDivisor(input: String): Int = parseIntInput("Test: divisible by ", input)
            fun parseStartingList(input: String): List<Int> =
                input.trim().removePrefix("Starting items: ").split(',').map {
                    it.trim().toInt()
                }

            fun parseMonkeyNumber(input: String): Int =
                input.removePrefix("Monkey ").removeSuffix(":").toInt()

            fun parseTrue(input: String): Int = parseIntInput("If true: throw to monkey ", input)
            fun parseFalse(input: String): Int = parseIntInput("If false: throw to monkey ", input)

            fun parseMonkey(monkeyDef: List<String>): Monkey =
                Monkey(
                    parseStartingList(monkeyDef[1]),
                    parseOperation(monkeyDef[2]),
                    parseDivisor(monkeyDef[3]),
                    parseTrue(monkeyDef[4]),
                    parseFalse(monkeyDef[5])
                )
        }
    }

    private val lines = loadInput(filename)

    fun parseMonkeys(): List<Monkey> {
        val monkeyLines: List<List<String>> = lines.chunked(7)
        return monkeyLines.map { Monkey.parseMonkey(it.take(6)) }
    }

    override fun part1(): String {
        println(parseMonkeys())
        return parseMonkeys().toString()
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
