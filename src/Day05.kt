fun main() {
    val part1 = solve(false)
    val part2 = solve(true)

    println(part1)
    println(part2)
}

private fun solve(reverseFirstCrates: Boolean): String {
    val input = readInput("Day05_test")
    val lineIndexWithCrateStackNumbers = input.indexOfFirst { it.matches("^[\\s,1-9]*\$".toRegex()) }

    val stacks = createStacks(input, lineIndexWithCrateStackNumbers)

    input.drop(lineIndexWithCrateStackNumbers + 2).forEach { line ->
        val split = line.split(" ")
        val count = split[1].toInt()
        val from = split[3].toInt()
        val to = split[5].toInt()

        val targetStack = stacks[to - 1]
        repeat(count) {
            targetStack.addFirst(stacks[from - 1].removeFirst())
        }

        if (reverseFirstCrates)
            targetStack.reverseFirst(count)
    }

    return stacks.joinToString("") { it.first().trim('[', ']') }
}

private fun createStacks(input: List<String>, lineIndexWithCrateStackNumbers: Int): List<ArrayDeque<String>> {
    val stackCrateCount = input[lineIndexWithCrateStackNumbers].split("   ").size
    val stacks = (0 until stackCrateCount).map { ArrayDeque<String>() }

    input.take(lineIndexWithCrateStackNumbers).forEach { line ->
        val crates = line.chunked(4).map(String::trim)
        (0 until stackCrateCount).forEach { stackIndex ->
            val crate = crates.getOrNull(stackIndex)
            if (!crate.isNullOrBlank())
                stacks[stackIndex].addLast(crate)
        }
    }

    return stacks
}

private fun ArrayDeque<String>.reverseFirst(count: Int) {
    var startIndex = 0
    while (startIndex < count / 2) {
        val temp = this[startIndex]

        this[startIndex] = this[count - startIndex - 1]
        this[count - startIndex - 1] = temp

        startIndex++
    }
}
