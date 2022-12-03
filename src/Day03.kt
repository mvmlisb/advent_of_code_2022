private val Priorities = (('a'.code..'z'.code) + ('A'.code..'Z'.code))
    .mapIndexed { charIndex, charCode -> charCode to charIndex + 1 }.toMap()

private fun FindCommonChar(strings: List<String>): Char {
    val firstString = strings.first()
    val restStrings = strings.subList(1, strings.size)
    return firstString.first { char -> restStrings.all { string -> string.contains(char) } }
}

fun main() {
    val input = readInput("Day03_test")

    val part1 = input.sumOf {
        val middle = it.length / 2
        val commonChar = FindCommonChar(listOf(it.substring(0, middle), it.substring(middle, it.length)))
        Priorities[commonChar.code] ?: 0
    }

    val part2 = input.chunked(3).sumOf {
        Priorities[FindCommonChar(it).code] ?: 0
    }

    println(part1)
    println(part2)
}
