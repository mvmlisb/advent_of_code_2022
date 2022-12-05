private fun String.ParseIntRange(): IntRange {
    val split = split("-")
    return split.first().toInt()..split.last().toInt()
}

private fun ProcessRanges(process: (IntRange, IntRange) -> Boolean) = readInput("Day04_test").map {
    val split = it.split(",")
    if (process(split.first().ParseIntRange(), split.last().ParseIntRange())) 1 else 0
}.sum()

private fun IntRange.FullyContains(other: IntRange) = first >= other.first && last <= other.last

fun main() {
    val part1 = ProcessRanges { first, second -> first.FullyContains(second) || second.FullyContains(first) }
    val part2 = ProcessRanges { first, second -> first.contains(second.first) || second.contains(first.first) }

    println(part1)
    println(part2)
}
