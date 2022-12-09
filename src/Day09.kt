data class Coords(var x: Int, var y: Int)

class Rope {
    var head = Coords(0, 0)
    var tail = Coords(0, 0)

    fun move(string: String, field: MutableList<MutableList<Int>>) {
        val split = string.split(" ")
        val direction = split.first()
        val step = split.last().toInt()

        val prevHead = head.copy()
        val prevTail = tail.copy()

        when (direction) {
            "U" -> {
                head.y += step
                tail.y = head.y - 1
                if (head.x != tail.x && step > 1) {
                    tail.x = head.x
                }
                markYRange(field, tail.x, prevHead.y + 1 until head.y)
            }

            "D" -> {
                head.y -= step
                tail.y = head.y + 1
                if (head.x != tail.x && step > 1) {
                    tail.x = head.x
                }
            }

            "R" -> {
                head.x += step
                tail.x = head.x - 1
                if (head.y != tail.y && step > 1) {
                    tail.y = head.y
                }
                markXRange(field, tail.y, prevHead.x until head.x)
            }

            "L" -> {
                head.x -= step
                tail.x = head.x + 1
                if (head.y != tail.y && step > 1) {
                    tail.y = head.y
                }
                markXRange(field, tail.y, prevHead.x - 1 downTo head.x + 1)
            }
        }
    }

    private fun markYRange(field: MutableList<MutableList<Int>>, x: Int, yRange: IntProgression) {
        yRange.forEach {
            field[it][x]++
        }
    }

    private fun markXRange(field: MutableList<MutableList<Int>>, y: Int, xRange: IntProgression) {
        xRange.forEach {
            field[y][it]++
        }
    }
    override fun toString() = "Head : $head || Tail : $tail"
}

fun main() {
    val input = readInput("Day09_test")

    val field = MutableList(5) { MutableList(6) { 0 } }

    val rope = Rope()

    input.forEach {
        rope.move(it, field)
    }

    println(rope.toString())
    println(field.reversed().joinToString("\n"))
    println(field.reversed().map { it.filter { it > 0 } }.sumOf { it.size })
}
