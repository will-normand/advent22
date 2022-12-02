class Day02(filename: String) : Advent, InputFileReader {

    private val lines = loadInput(filename)

    enum class Shape {
        ROCK {
            override fun score() = 1
            override fun losesTo() = PAPER
            override fun beats() = SCISSORS
        },
        PAPER {
            override fun score() = 2
            override fun losesTo() = SCISSORS
            override fun beats() = ROCK
        },
        SCISSORS {
            override fun score() = 3
            override fun losesTo() = ROCK
            override fun beats() = PAPER
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
        abstract fun losesTo(): Shape
        abstract fun beats(): Shape
    }

    class Round(private val elfMove: Shape, private val ownMove: Shape) {
        private fun winnerScore(): Int =
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

    private fun parseInputAsMoves(): List<Round> {
        return lines.map {
            val moves = it.split(" ")
            val elfMove = Shape.fromElfMove(moves[0])
            val ownMove = Shape.fromOwnMove(moves[1])
            Round(elfMove, ownMove)
        }
    }

    private fun parseInputAsInstructions() = lines.map {
        val moves = it.split(" ")
        val elfMove = Shape.fromElfMove(moves[0])
        val ownMove = decideMove(moves[1], elfMove)
        Round(elfMove, ownMove)
    }

    private fun decideMove(moveInstruction: String, elfMove: Shape): Shape = when (moveInstruction) {
        "X" -> elfMove.beats()
        "Y" -> elfMove
        "Z" -> elfMove.losesTo()
        else -> throw Exception("Unexpected move instruction: $moveInstruction")
    }

    override fun part1(): String {
        return parseInputAsMoves().sumOf { it.ownScore() }.toString()
    }

    override fun part2(): String {
        return parseInputAsInstructions().sumOf { it.ownScore() }.toString()
    }
}

fun main() {
    val advent: Advent = Day02("src/main/resources/input02")
    println("Part 1 answer ${advent.part1()}")
    println("Part 2 answer ${advent.part2()}")
}
