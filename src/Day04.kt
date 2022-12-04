
private data class Range(val Start: Int,val EndInclusive: Int) {
    fun OverlapEachOther(other: Range) = Start in other.Start..other.EndInclusive || other.Start in Start..EndInclusive
    fun FullyContainEachOther(other: Range) = Start >= other.Start && EndInclusive <= other.EndInclusive ||
            other.Start >= Start && other.EndInclusive <= EndInclusive
    companion object {
        fun FromString(string: String): Range {
            val split = string.split("-")
            return Range(split.first().toInt(), split.last().toInt())
        }
    }
}

private fun ProcessRanges(process: (Range, Range) -> Boolean) = readInput("Day04_test").map {
     val split = it.split(",")
     Range.FromString(split.first()) to Range.FromString(split.last())
 }.fold(0) { acc: Int, (first, second): Pair<Range, Range> -> acc + if( process(first, second)) 1 else 0 }

fun main() {
    val part1 = ProcessRanges(Range::FullyContainEachOther)
    val part2 = ProcessRanges(Range::OverlapEachOther)

    println(part1)
    println(part2)
}
