class Day11(filename: String) : Advent, InputFileReader {


    data class Monkey(
        val items: MutableList<Int>,
        val operation: Operation,
        val test: Int,
        val trueDest: Int,
        val falseDest: Int
    ) {
        var inspections = 0

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
            fun parseStartingList(input: String): MutableList<Int> =
                input.trim().removePrefix("Starting items: ").split(',').map {
                    it.trim().toInt()
                }.toMutableList()

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

        fun inspect(): Int {
            inspections++
            val item = items.removeFirst()
            val worry = operation(item)
            val newWorry = worry / 3
            return newWorry
        }

        fun catch(item: Int) {
            items.add(items.size, item)
        }
    }

    private val lines = loadInput(filename)

    fun parseMonkeys(): List<Monkey> {
        val monkeyLines: List<List<String>> = lines.chunked(7)
        return monkeyLines.map { Monkey.parseMonkey(it.take(6)) }
    }

    class Game(val monkeys: List<Monkey>) {
        fun turn(monkey: Monkey) {
            while (monkey.items.isNotEmpty()) {
                val newWorry = monkey.inspect()
                if (newWorry % monkey.test == 0) {
                    monkeys[monkey.trueDest].catch(newWorry)
                } else {
                    monkeys[monkey.falseDest].catch(newWorry)
                }
            }
        }

        fun round() {
            monkeys.forEach { turn(it) }
        }

        fun printState() {
            monkeys.forEachIndexed { index, monkey -> println("Monkey $index: ${monkey.items}") }
        }
    }

    override fun part1(): String {
        val game = Game(parseMonkeys())
        repeat(20) { game.round() }
        return game.monkeys.map { it.inspections }.sorted().takeLast(2).reduce{ a, b -> a * b }.toString()
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
