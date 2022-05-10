package homeworks.homework5

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun MutableList<MutableList<Int>>.getRowsSize() = when {
    size != 0 -> {
        if (first().size == 0) {
            0
        } else {
            size
        }
    }
    else -> size
}

fun MutableList<MutableList<Int>>.getColumnsSize() = when {
    size != 0 -> {
        if (first().size == 0) {
            0
        } else {
            first().size
        }
    }
    else -> 0
}

fun multiplyMatrix(
    firstMatrix: MutableList<MutableList<Int>>,
    secondMatrix: MutableList<MutableList<Int>>
): MutableList<MutableList<Int>> {
    require(firstMatrix.getColumnsSize() == secondMatrix.getRowsSize()) { "Incorrect matrices size" }
    val multiplicationResult = MutableList(firstMatrix.size) { MutableList(secondMatrix[0].size) { 0 } }
    runBlocking {
        for (i in 0 until firstMatrix.size) {
            for (j in 0 until secondMatrix[0].size) {
                launch {
                    multiplicationResult[i][j] =
                        firstMatrix[i].mapIndexed { index, first -> first * secondMatrix[index][j] }.sum()
                }
            }
        }
    }
    return multiplicationResult
}

@Suppress("MagicNumber")
fun main() {
    val firstMatrix = mutableListOf(
        mutableListOf(1, 2, 3),
        mutableListOf(4, 5, 6)
    )
    val secondMatrix = mutableListOf(
        mutableListOf(1, 2, 3),
        mutableListOf(4, 5, 6),
        mutableListOf(7, 8, 9)
    )
    val result = multiplyMatrix(firstMatrix, secondMatrix)
    println(result)
}
