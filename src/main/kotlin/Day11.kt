import java.math.BigInteger

class Day11(filename: String) : Advent, InputFileReader {

    data class Monkey(
        val items: MutableList<BigInteger>,
        val operation: Operation,
        val test: Int,
        val trueDest: Int,
        val falseDest: Int
    ) {
        var inspections = 0

        companion object {
            val add: BigInteger.(BigInteger) -> BigInteger = { b -> plus(b) }
            val multiply: BigInteger.(BigInteger) -> BigInteger = { b -> times(b) }

            fun parseOperation(input: String): Operation {
                val (a, op, b) = input.trim().removePrefix("Operation: new = ").split(" ")
                val opFun = when (op) {
                    "*" -> multiply
                    "+" -> add
                    else -> throw Exception("Unknown operation $op")
                }

                if (a != "old") throw Exception("Unexpected first operand $a")

                return if (b == "old") { x -> x.opFun(x) }
                else { x -> x.opFun(b.toBigInteger()) }
            }

            private fun parseIntInput(prefix: String, input: String): Int =
                input.trim().removePrefix(prefix).toInt()

            fun parseDivisor(input: String): Int = parseIntInput("Test: divisible by ", input)
            fun parseStartingList(input: String): MutableList<BigInteger> =
                input.trim().removePrefix("Starting items: ").split(',').map {
                    it.trim().toBigInteger()
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

        private fun inspect(): BigInteger {
            inspections++
            val item = items.removeFirst()
            return operation(item)
        }

        fun inspectPart1(): BigInteger {
            val worry = inspect()
            return worry / 3.toBigInteger()
        }

        fun inspectPart2(): BigInteger {
            return inspect()
        }

        fun catch(item: BigInteger) {
            items.add(items.size, item)
        }
    }

    private val lines = loadInput(filename)

    fun parseMonkeys(): List<Monkey> {
        val monkeyLines: List<List<String>> = lines.chunked(7)
        return monkeyLines.map { Monkey.parseMonkey(it.take(6)) }
    }

    enum class Part { Part1, Part2 }

    class Game(val monkeys: List<Monkey>, val part: Part) {
        private val testProduct = monkeys.map { it.test.toBigInteger() }.reduce { a, b -> a.times(b) }
        fun turn(monkey: Monkey) {
            while (monkey.items.isNotEmpty()) {

                val newWorry = if (part == Part.Part1) monkey.inspectPart1() else (monkey.inspectPart2() % testProduct)
                if ((newWorry % monkey.test.toBigInteger()) == BigInteger.ZERO) {
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

        fun printInspections() {
            monkeys.forEachIndexed { index, monkey -> println("Monkey $index inspected items ${monkey.inspections} times.") }
        }
    }

    override fun part1(): String {
        val game = Game(parseMonkeys(), Part.Part1)
        repeat(20) { game.round() }
        game.monkeys.forEach { println(it.inspections) }
        return game.monkeys.map { it.inspections }.sorted().takeLast(2).reduce { a, b -> a * b }.toString()
    }

    override fun part2(): String {
        val game = Game(parseMonkeys(), Part.Part2)
        repeat(10000) { game.round() }
        return game.monkeys.map { it.inspections }.sorted().takeLast(2).map { it.toBigInteger() }
            .reduce { a, b -> a * b }.toString()
    }
}

typealias Operation = (BigInteger) -> BigInteger

fun main() {
    val advent: Advent = Day11("src/main/resources/input11")
    println("Part 1 answer ${advent.part1()}")
    println("Part 2 answer ${advent.part2()}")
}
