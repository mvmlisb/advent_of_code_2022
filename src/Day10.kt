import kotlin.math.abs

fun main() {
    val input = readInput("Day10_test")

    var cycleNumber = 1

    var signalStrengthSum = 0

    var number = 1

    input.forEach {
        val split = it.split(" ")
        val instruction = split.first()
        val count = if (split.size > 1) split.last().toInt() else 0

        val lastCycleNumberExclusive = cycleNumber + when (instruction) {
            "noop" -> 1
            "addx" -> 2
            else -> 0
        }
        while (cycleNumber < lastCycleNumberExclusive) {
            signalStrengthSum += when (cycleNumber) {
                20 -> {
                    println(number)
                    20 * number
                }

                60 -> {
                    println(number)
                    60 * number
                }

                100 -> {
                    println(number)
                    100 * number
                }

                140 -> {
                    println(number)
                    140 * number
                }

                180 -> {
                    println(number)
                    180 * number
                }

                220 -> {
                    println(number)
                    220 * number
                }

                else -> 0
            }

            cycleNumber++
        }
        if (count > 0)
            number += count
        else
            number -= abs(count)
    }


    // неправильно 14800
    println(signalStrengthSum)
}
