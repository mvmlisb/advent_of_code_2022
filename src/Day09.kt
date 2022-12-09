data class Coords(var x: Int, var y: Int)

class Rope(private val field: Array<Array<Int>>) {
    private var head = Coords(0, 0)
    private var tail = Coords(0, 0)

    fun move(string: String) {
        val split = string.split(" ")
        val direction = split.first()
        val step = split.last().toInt()

        val prevTail = tail.copy()

        when (direction) {
            "U" -> {
                head.y += step
                tail.y = head.y - 1
                if (head.x != tail.x) {
                    tail.x = head.x
                    markYRange(tail.x, prevTail.y + 1..tail.y)
                } else
                    markYRange(tail.x, prevTail.y..tail.y)
            }

            "D" -> {
                head.y -= step
                tail.y = head.y + 1
                if (head.x != tail.x) {
                    tail.x = head.x
                    markYRange(tail.x, prevTail.y downTo tail.y)
                } else
                    markYRange(tail.x, prevTail.y downTo tail.y)
            }

            "R" -> {
                head.x += step
                tail.x = head.x - 1
                if (head.y != tail.y) {
                    tail.y = head.y
                    markXRange(tail.y, prevTail.x + 1..tail.x)
                } else
                    markXRange(tail.y, prevTail.x..tail.x)
            }

            "L" -> {
                head.x -= step
                tail.x = head.x + 1
                if (head.y != tail.y) {
                    tail.y = head.y
                    markXRange(tail.y, prevTail.x downTo tail.x)
                } else
                    markXRange(tail.y, prevTail.x downTo tail.x)
            }
        }
    }

    private fun markYRange(x: Int, yRange: IntProgression) {
        yRange.forEach {
            field[it][x]++
        }
    }

    private fun markXRange(y: Int, xRange: IntProgression) {
        xRange.forEach {
            field[y][it]++
        }
    }

    override fun toString() = "Head : $head || Tail : $tail"
}

fun main() {
    val input = readInput("Day09_test")

    val field = Array(5) { Array(6) { 0 } }

    val rope = Rope(field)

    input.forEach(rope::move)

    println(rope.toString())
    println(field.reversed().joinToString("\n") { it.joinToString { it.toString() } })
    println(field.reversed().map { it.filter { it > 0 } }.sumOf { it.size })
}
