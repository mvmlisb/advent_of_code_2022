private data class Coords(var x: Int, var y: Int)

class Rope {
    private var head = Coords(0, 0)
    private var tail = Coords(0, 0)

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
                if (head.x != tail.x)
                    tail.x = head.x
            }
            "D" -> {
                head.y -= step
                tail.y = head.y + 1
                if (head.x != tail.x)
                    tail.x = head.x
            }

            "R" -> {
                head.x += step
                tail.x = head.x - 1
                if (head.y != tail.y)
                    tail.y = head.y
            }
            "L" -> {
                head.x -= step
                tail.x = head.x + 1
                if (head.y != tail.y)
                    tail.y = head.y
            }
        }

        fillField(field, prevHead, prevTail)
    }

   private fun fillField(field: MutableList<MutableList<Int>>,  prevHead: Coords,  prevTail: Coords) {
       val yRange = if (prevTail.y < tail.y)
           prevTail.y..tail.y
       else
           prevTail.y downTo tail.y

       val xRange = if (prevTail.x < tail.x)
           prevTail.x..tail.x
       else
           prevTail.x downTo tail.x

       yRange.forEachIndexed { indexY,  y ->
           xRange.forEachIndexed { indexX, x ->
               if ((prevTail.x != tail.x && indexX == 0) || (prevTail.y != tail.y && indexY == 0)){

               } else
                   field[y][x]++
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
    }.reversed().toMutableList()

    val rope = Rope()

    input.forEach {
        rope.move(it, field)
    }

    println(field.joinToString("\n"))
}
