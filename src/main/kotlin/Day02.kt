class Day02(filename: String) : Advent, InputFileReader {

    val lines = loadInput(filename)

    enum class Shape {
        ROCK {
            override fun score() = 1
        },
        PAPER {
            override fun score() = 2
        },
        SCISSORS {
            override fun score() = 3
        };

        companion object Factory {
            fun fromElfMove(input: String) = when (input) {
                "A" -> ROCK
                "B" -> PAPER
                "C" -> SCISSORS
                else -> throw Exception("Unknown move: $input")
            }

            fun fromOwnMove(input: String) = when (input) {
                "X" -> ROCK
                "Y" -> PAPER
                "Z" -> SCISSORS
                else -> throw Exception("Unknown move: $input")
            }
        }

        abstract fun score(): Int
    }

    class Round(val elfMove: Shape, val ownMove: Shape) {
        fun winnerScore(): Int =
            if (elfMove == ownMove) 3
            else {
                when (ownMove) {
                    Shape.ROCK -> if (elfMove == Shape.PAPER) 0 else 6
                    Shape.PAPER -> if (elfMove == Shape.SCISSORS) 0 else 6
                    Shape.SCISSORS -> if (elfMove == Shape.ROCK) 0 else 6
                }
            }

        fun ownScore() = winnerScore() + ownMove.score()
    }

    val rounds = parseInputIntoRounds(loadInput(filename))

    fun parseInputIntoRounds(lines: List<String>): List<Round> {
        return lines.map {
            val moves = it.split(" ")
            val elfMove = Shape.fromElfMove(moves[0])
            val ownMove = Shape.fromOwnMove(moves[1])
            Round(elfMove, ownMove)
        }
    }

    fun losingMove(otherMove: Shape) = when (otherMove) {
        Shape.ROCK -> Shape.SCISSORS
        Shape.PAPER -> Shape.ROCK
        Shape.SCISSORS -> Shape.PAPER
    }

    fun winningMove(otherMove: Shape) = when (otherMove) {
        Shape.ROCK -> Shape.PAPER
        Shape.PAPER -> Shape.SCISSORS
        Shape.SCISSORS -> Shape.ROCK
    }

    fun decideMove(moveInstruction: String, elfMove: Shape): Shape = when (moveInstruction) {
        "X" -> losingMove(elfMove)
        "Y" -> elfMove
        "Z" -> winningMove(elfMove)
        else -> throw Exception("Unexpected move instruction: $moveInstruction")
    }

    override fun part1(): String {
        return rounds.map { it.ownScore() }.sum().toString()
    }

    override fun part2(): String {
        return lines.map {
            val moves = it.split(" ")
            val elfMove = Shape.fromElfMove(moves[0])
            val ownMove = decideMove(moves[1], elfMove)
            Round(elfMove, ownMove)
        }.map { it.ownScore() }.sum().toString()
    }
}

fun main() {
    val advent: Advent = Day02("src/main/resources/input02")
    println("Part 1 answer ${advent.part1()}")
    println("Part 2 answer ${advent.part2()}")
}
