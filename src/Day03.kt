private val Priorities = (('a'.code..'z'.code) + ('A'.code..'Z'.code))
    .mapIndexed { charIndex, charCode -> charCode to charIndex + 1 }.toMap()

private fun FindCommonChar(strings: List<String>) =
    strings.first().first { char -> strings.drop(1).all { string -> string.contains(char) } }

fun main() {
    val input = readInput("Day03_test")

    val part1 = input.sumOf {
        Priorities[FindCommonChar(it.chunked(it.length / 2)).code] ?: 0
    }

    val part2 = input.chunked(3).sumOf {
        Priorities[FindCommonChar(it).code] ?: 0
    }

    println(part1)
    println(part2)
}
