private data class Coords(var x: Int, var y: Int)

private class Rope {
    private var head = Coords(0, 0)
    private var tail = Coords(0, 0)

    val visitedCordsByTail = mutableSetOf<Coords>()

    init {
        visitedCordsByTail += tail.copy()
    }

    fun move(string: String) {
        val split = string.split(" ")
        val direction = split.first()
        val count = split.last().toInt()

        when (direction) {
            "U" -> {
                repeat(count) {
                    head.y ++
                    if (head.y - tail.y > 1) {
                        tail.x = head.x
                        tail.y++
                        visitedCordsByTail += tail.copy()
                    }

                }
            }
            "D" -> {
                repeat(count) {
                    head.y--
                    if (tail.y - head.y > 1) {
                        tail.x = head.x
                        tail.y--
                        visitedCordsByTail += tail.copy()

                    }
                }
            }
            "R" -> {
                repeat(count) {
                    head.x++
                    if (head.x - tail.x > 1) {
                        tail.y = head.y
                        tail.x++
                        visitedCordsByTail += tail.copy()

                    }
                }

            }
            "L" -> {
                repeat(count) {
                    head.x--
                    if (tail.x - head.x > 1) {
                        tail.y = head.y
                        tail.x--
                        visitedCordsByTail += tail.copy()
                    }
                }
            }
        }

    }

    override fun toString() = "Head : $head :::: Tail : $tail"
}

fun main() {
    val input = readInput("Day09_test")
    val rope = Rope()
    input.forEach(rope::move)
    println(rope.visitedCordsByTail.size)
    println(rope.toString())
}
