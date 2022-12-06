private val Priorities = (('a'.code..'z'.code) + ('A'.code..'Z'.code))
    .mapIndexed { charIndex, charCode -> charCode to charIndex + 1 }.toMap()

private fun findCommonChar(strings: List<String>) =
    strings.first().first { char -> strings.drop(1).all { string -> string.contains(char) } }

fun main() {
    val input = readInput("Day03_test")

    val part1 = input.sumOf {
        Priorities[findCommonChar(it.chunked(it.length / 2)).code] ?: 0
    }

    val part2 = input.chunked(3).sumOf {
        Priorities[findCommonChar(it).code] ?: 0
    }

    println(part1)
    println(part2)
}
