fun main() {
    val part1 = Solve(false)
    val part2 = Solve(true)

    println(part1)
    println(part2)
}

private fun Solve(reverseFirstCratesInTargetStackAfterAdd: Boolean): String {
    val input = readInput("Day05_test")
    val lineIndexWithCrateStackNumbers = input.indexOfFirst { it.matches("^[\\s,1-9]*\$".toRegex()) }

    val stacks = CreateStacks(input, lineIndexWithCrateStackNumbers)

    input.drop(lineIndexWithCrateStackNumbers + 2).forEach { line ->
        val count = line.substringAfter("move").substringBefore("from").TrimedInt()
        val from = line.substringAfter("from").substringBefore("to").TrimedInt()
        val to = line.substringAfter("to").TrimedInt()

        val targetStack = stacks[to - 1]
        repeat(count) {
            targetStack.addFirst(stacks[from - 1].removeFirst())
        }

        if (reverseFirstCratesInTargetStackAfterAdd)
            targetStack.ReverseFirst(count)
    }

    return stacks.joinToString("") { it.first().trim('[', ']') }
}

private fun CreateStacks(input: List<String>, lineIndexWithCrateStackNumbers: Int): List<ArrayDeque<String>> {
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

private fun String.TrimedInt() = trim().toInt()

private fun ArrayDeque<String>.ReverseFirst(count: Int) {
    var startIndex = 0
    while (startIndex < count / 2) {
        val temp = this[startIndex]

        this[startIndex] = this[count - startIndex - 1]
        this[count - startIndex - 1] = temp

        startIndex++
    }
}
