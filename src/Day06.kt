import kotlin.math.min

private fun solve(string: String, packetMarkerLength: Int): Int {
    string.forEachIndexed { startIndex, _ ->
        val endIndex = min(startIndex + packetMarkerLength, string.length)

        if (string.substring(startIndex, endIndex).allUnique())
            return endIndex
    }
    return -1
}

private fun String.allUnique() = all(hashSetOf<Char>()::add)


fun main() {
    val input = readInput("Day06_test")

    val part1 = solve(input.first(), 4)
    val part2 = solve(input.first(), 14)

    println(part1)
    println(part2)
}
