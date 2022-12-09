data class Coords(var x: Int, var y: Int)


class Rope {
    private var head = Coords(0, 0)
    private var tail = Coords(0, 0)

    var _visitedPositionsByTail = setOf<Coords>()
        private set


    fun move(string: String, field: MutableList<MutableList<Int>>) {
        val split = string.split(" ")
        val direction = split.first()
        val step = split.last().toInt()

        val prevHead = head.copy()
        val prevTail = head.copy()

        when(direction) {
            "U" -> {
                head.y += step
                tail.y = head.y - 1
                if (head.x != tail.x) {
                    tail.x = head.x
                }
            }
            "D" -> {
                head.y -= step
                tail.y = head.y + 1
                if (head.x != tail.x) {
                    tail.x = head.x
                }
            }
            "R" -> {
                head.x += step
                tail.x = head.x - 1
                if (head.y != tail.y) {
                    tail.y = head.y
                }
            }
            "L" -> {
                head.x -= step
                tail.x = head.x + 1
                if (head.y != tail.y) {
                    tail.y = head.y
                }
            }
        }

        val reversed = field.reversed()
        (prevTail.y..tail.y).forEach { lineIndex ->
            (prevTail.x..tail.x).forEach { indexInLine ->
                reversed[lineIndex][indexInLine] = reversed[lineIndex][indexInLine] + 1
            }
        }
    }


    override fun toString() = "Head : $head || Tail : $tail"
}

fun main() {
    val input = readInput("Day09_test")

    val field = buildList {
        repeat(5) {
            add(MutableList(6) { 0})
        }
    }.toMutableList()

    val rope = Rope()

    input.forEach {
        rope.move(it, field)
    }

    println(field.joinToString("\n"))
}
