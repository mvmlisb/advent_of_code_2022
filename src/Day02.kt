private enum class GameMove(private val __Values: List<String>) {
    Rock(listOf("A", "X")),
    Paper(listOf("B", "Y")),
    Scissors(listOf("C", "Z"));

    private fun GetPoints() = when (this) {
        Rock -> 1
        Paper -> 2
        Scissors -> 3
    }

    fun FightInFairWay(opponent: GameMove) = (if (this == opponent)
        DRAW_POINTS
    else
        when {
            this == Rock && opponent == Scissors ||
                    this == Scissors && opponent == Paper ||
                    this == Paper && opponent == Rock -> WINNER_POINTS

            else -> LOSER_POINTS
        }) + GetPoints()

    fun FightInPredeterminedWay(opponent: GameMove) = when (this) {
        Scissors -> WINNER_POINTS + when (opponent) {
            Rock -> Paper
            Paper -> Scissors
            Scissors -> Rock
        }.GetPoints()

        Rock -> LOSER_POINTS + when (opponent) {
            Rock -> Scissors
            Paper -> Rock
            Scissors -> Paper
        }.GetPoints()

        Paper -> DRAW_POINTS + opponent.GetPoints()
    }

    companion object {
        private const val WINNER_POINTS = 6
        private const val LOSER_POINTS = 0
        private const val DRAW_POINTS = 3

        fun FromString(string: String) = values().first { it.__Values.contains(string) }
    }
}

private fun CalculateTotalScore(fight: (you: GameMove, opponent: GameMove) -> Int): Int {
    return readInput("Day02_test").fold(0) { acc: Int, line: String ->
        acc + fight(GameMove.FromString(line.substringAfter(" ")), GameMove.FromString(line.substringBefore(" ")))
    }
}

fun main() {
    val part1 = CalculateTotalScore { you, opponent -> you.FightInFairWay(opponent) }
    println(part1)
    val part2 = CalculateTotalScore { you, opponent -> you.FightInPredeterminedWay(opponent) }
    println(part2)
}
