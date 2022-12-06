import kotlin.math.min

private fun FindStartOfPacketMarker(string: String, startOfPacketMarkerLength: Int): Int {
    string.forEachIndexed { startIndex, _ ->
        val endIndex = min(startIndex + startOfPacketMarkerLength, string.length)

        if (string.substring(startIndex, endIndex).AllUnique())
            return endIndex
    }
    return -1
}

private fun String.AllUnique() = all(hashSetOf<Char>()::add)


fun main() {
    val input = readInput("Day06_test")

    val part1 = FindStartOfPacketMarker(input.first(), 4)
    val part2 = FindStartOfPacketMarker(input.first(), 14)

    println(part1)
    println(part2)
}
