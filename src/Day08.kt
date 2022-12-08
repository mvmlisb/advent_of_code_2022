private fun processInnerRow(rows: List<List<Int>>, process: (rowIndex: Int, row: List<Int>, indexInRow: Int) -> Unit) {
    rows.forEachIndexed { rowIndex, row ->
        val isOuterRowIndex = rowIndex == 0 || rowIndex == rows.lastIndex
        if (!isOuterRowIndex)
            row.indices.forEach { indexInRow ->
                val isOuterIndexInRow = indexInRow == 0 || indexInRow == row.lastIndex
                if (!isOuterIndexInRow) process(rowIndex, row, indexInRow)
            }
    }
}

private inline fun <T> processUpDown(
    rowIndex: Int,
    indexInRow: Int,
    rows: List<List<Int>>,
    process: (up: List<Int>, bottom: List<Int>, number: Int) -> T
): T {
    val number = rows[rowIndex][indexInRow]
    val up = (0 until rowIndex).map { rows[it][indexInRow] }
    val bottom = (rowIndex + 1 until rows.size).map { rows[it][indexInRow] }
    return process(up, bottom, number)
}

private inline fun <T> processLeftRight(
    indexInRow: Int,
    row: List<Int>,
    process: (left: List<Int>, right: List<Int>, number: Int) -> T
): T {
    val number = row[indexInRow]
    val left = row.subList(0, indexInRow)
    val right = row.subList(indexInRow + 1, row.size)
    return process(left, right, number)
}

private fun List<Int>.incrementUntilReceiveEqualOrGreaterNumber(numberInclusive: Int): Int {
    var count = 0
    for (number in this) {
        count += 1
        if (number >= numberInclusive)
            break
    }

    return count
}

private fun part1(rows: List<List<Int>>): Int {
    val outerVisibleTrees = ((rows.size + rows.first().size) * 2) - 4

    var innerVisibleTrees = 0
    processInnerRow(rows) { rowIndex, row, indexInRow ->
        if (processUpDown(
                rowIndex,
                indexInRow,
                rows
            ) { up: List<Int>, bottom: List<Int>, number: Int -> up.none { it >= number } || bottom.none { it >= number } } || processLeftRight(
                indexInRow,
                row
            ) { left, right, number -> left.none { it >= number } || right.none { it >= number } }
        )
            innerVisibleTrees++
    }


    return innerVisibleTrees + outerVisibleTrees
}

private fun part2(rows: List<List<Int>>) = buildSet {
    processInnerRow(rows) { rowIndex, row, indexInRow ->
        add(processUpDown(rowIndex, indexInRow, rows) { up, bottom, number ->
            up.reversed()
                .incrementUntilReceiveEqualOrGreaterNumber(number) * bottom.incrementUntilReceiveEqualOrGreaterNumber(
                number
            )
        } * processLeftRight(indexInRow, row) { left, right, number ->
            left.reversed()
                .incrementUntilReceiveEqualOrGreaterNumber(number) * right.incrementUntilReceiveEqualOrGreaterNumber(
                number
            )
        })
    }
}.max()

fun main() {
    val input = readInput("Day08_test")

    val rows = input
        .map { line -> line.toCharArray() }
        .map { charArray -> charArray.map { char -> char.toString().toInt() } }

    println(part1(rows))
    println(part2(rows))
}

