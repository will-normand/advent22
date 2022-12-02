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

    val rounds = parseInput(loadInput(filename))

    fun parseInput(lines: List<String>): List<Round> {
        return lines.map {
            val moves = it.split(" ")
            val elfMove = Shape.fromElfMove(moves[0])
            val ownMove = Shape.fromOwnMove(moves[1])
            Round(elfMove, ownMove)
        }
    }

    override fun part1(): String {
        return rounds.map { it.ownScore() }.sum().toString()
    }

    override fun part2(): String {
        return TODO()
    }
}

fun main() {
    val advent: Advent = Day02("src/main/resources/input02")
    println("Part 1 answer ${advent.part1()}")
    println("Part 2 answer ${advent.part2()}")
}
