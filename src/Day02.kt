private enum class GameMove(private val values: List<String>) {
    Rock(listOf("A", "X")),
    Paper(listOf("B", "Y")),
    Scissors(listOf("C", "Z"));

    private fun getPoints() = when (this) {
        Rock -> 1
        Paper -> 2
        Scissors -> 3
    }

    fun fightInFairWay(opponent: GameMove) = (if (this == opponent)
        DRAW_POINTS
    else
        when {
            this == Rock && opponent == Scissors ||
                    this == Scissors && opponent == Paper ||
                    this == Paper && opponent == Rock -> WINNER_POINTS

            else -> LOSER_POINTS
        }) + getPoints()

    fun fightInPredeterminedWay(opponent: GameMove) = when (this) {
        Scissors -> WINNER_POINTS + when (opponent) {
            Rock -> Paper
            Paper -> Scissors
            Scissors -> Rock
        }.getPoints()

        Rock -> LOSER_POINTS + when (opponent) {
            Rock -> Scissors
            Paper -> Rock
            Scissors -> Paper
        }.getPoints()

        Paper -> DRAW_POINTS + opponent.getPoints()
    }

    companion object {
        private const val WINNER_POINTS = 6
        private const val LOSER_POINTS = 0
        private const val DRAW_POINTS = 3

        fun fromString(string: String) = values().first { it.values.contains(string) }
    }
}

private fun score(fight: (you: GameMove, opponent: GameMove) -> Int) =
    readInput("Day02_test").fold(0) { acc: Int, line: String ->
        acc + fight(GameMove.fromString(line.substringAfter(" ")), GameMove.fromString(line.substringBefore(" ")))
    }

fun main() {
    val part1 = score { you, opponent -> you.fightInFairWay(opponent) }
    println(part1)
    val part2 = score { you, opponent -> you.fightInPredeterminedWay(opponent) }
    println(part2)
}
