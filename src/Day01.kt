fun main() {
    val input = readInput("Day01_test").foldIndexed(mutableListOf()) { index: Int, acc: MutableList<Int>, line: String ->
        if (line.isEmpty() || index == 0)
            acc.add(0)
        else
            acc[acc.lastIndex] = acc[acc.lastIndex] + line.toInt()
        acc
    }

    val part1 = input.max()
    println(part1)

    val part2 = input.sortedByDescending { it }.take(3).sum()
    println(part2)
}
