private val LINE_NUMBER_WITH_CRATE_STACK_NUMBERS = 8

fun main() {
    val input = readInput("Day05_test")

    val stackCrateCount = input[LINE_NUMBER_WITH_CRATE_STACK_NUMBERS].split("   ").size
    val stacks = (0 until stackCrateCount).map { ArrayDeque<String>() }

    input.take(LINE_NUMBER_WITH_CRATE_STACK_NUMBERS).forEach { line ->
        val crates = line.chunked(4).map(String::trim)
        (0 until stackCrateCount).forEach { stackIndex ->
            val crate = crates.getOrNull(stackIndex)
            if (!crate.isNullOrBlank())
                stacks[stackIndex].addLast(crate)
        }
    }

//    input.drop(LINE_NUMBER_WITH_CRATE_STACK_NUMBERS + 2).forEach { line ->
//        val count = line.substringAfter("move").substringBefore("from").trim().toInt()
//        val from = line.substringAfter("from").substringBefore("to").trim().toInt()
//        val to = line.substringAfter("to").trim().toInt()
//
//        repeat(count) {
//            stacks[to - 1].addFirst(stacks[from - 1].removeFirst())
//        }
//    }

    input.drop(LINE_NUMBER_WITH_CRATE_STACK_NUMBERS + 2).forEach { line ->
        val count = line.substringAfter("move").substringBefore("from").trim().toInt()
        val from = line.substringAfter("from").substringBefore("to").trim().toInt()
        val to = line.substringAfter("to").trim().toInt()

        val tempStack = ArrayDeque<String>()
        repeat(count) {
            tempStack.addFirst(stacks[from - 1].removeFirst())
        }
        tempStack.forEach { stacks[to - 1].addFirst(it) }
    }

//    val part1 = stacks.map(ArrayDeque<String>::first).map { it.substring(1..1) }.joinToString("")
    val part2 = stacks.map(ArrayDeque<String>::first).map { it.substring(1..1) }.joinToString("")

//    println(part1) // WSFTMRHPP
    println(part2) // GSLCMFBRP
}
