package homeworks.homework5

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun MutableList<MutableList<Int>>.getRowsSize() = if (firstOrNull()?.isNotEmpty() == true) size else 0

fun MutableList<MutableList<Int>>.getColumnsSize() = firstOrNull()?.size ?: 0

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
