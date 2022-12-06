private fun String.parseIntRange(): IntRange {
    val split = split("-")
    return split.first().toInt()..split.last().toInt()
}

private fun processRanges(process: (IntRange, IntRange) -> Boolean) = readInput("Day04_test").map {
    val split = it.split(",")
    if (process(split.first().parseIntRange(), split.last().parseIntRange())) 1 else 0
}.sum()

private fun IntRange.fullyContains(other: IntRange) = first >= other.first && last <= other.last

fun main() {
    val part1 = processRanges { first, second -> first.fullyContains(second) || second.fullyContains(first) }
    val part2 = processRanges { first, second -> first.contains(second.first) || second.contains(first.first) }

    println(part1)
    println(part2)
}
