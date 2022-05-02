package homeworks.homework4

import kotlin.math.ceil
import kotlin.math.floor

const val MIN_RANDOM_NUM = -1000000
const val MAX_RANDOM_NUM = 1000000

fun <E : Comparable<E>> MutableList<E>.mergeSort(nThreads: Int = 1): MutableList<E> {
    require(nThreads >= 1) { "Incorrect number of threads" }
    if (this.size < 2) {
        return this
    }
    val tempList = this.toMutableList()
    when (nThreads) {
        1 -> simpleSort(tempList)
        else -> multithreadedSort(nThreads, tempList)
    }
    return this
}

private fun <E : Comparable<E>> MutableList<E>.simpleSort(tempList: MutableList<E>) {
    val averageIndex = ceil(this.size.toFloat() / 2).toInt()
    var firstListSorted = tempList.subList(0, averageIndex).mergeSort()
    var secondListSorted = tempList.subList(averageIndex, this.size).mergeSort()
    firstListSorted.mergeWith(secondListSorted, this)
}

private fun <E : Comparable<E>> MutableList<E>.multithreadedSort(nThreads: Int, tempList: MutableList<E>) {
    val averageIndex = ceil(this.size.toFloat() / 2).toInt()
    val firstCountOfThreads = floor(nThreads.toFloat() / 2).toInt()
    var firstListSorted = mutableListOf<E>()
    var secondListSorted = mutableListOf<E>()
    val firstThread =
        Thread { firstListSorted = tempList.subList(0, averageIndex).mergeSort(firstCountOfThreads) }
    val secondThread =
        Thread {
            secondListSorted =
                tempList.subList(averageIndex, this.size).mergeSort(nThreads - firstCountOfThreads)
        }
    firstThread.start()
    secondThread.start()
    firstThread.join()
    secondThread.join()
    firstListSorted.mergeWith(secondListSorted, this)
}

private fun <E : Comparable<E>> MutableList<E>.mergeWith(secondList: MutableList<E>, destination: MutableList<E>) =
    when {
        this.isEmpty() -> secondList.forEachIndexed { index, element -> destination[index] = element }
        secondList.isEmpty() -> this.forEachIndexed { index, element -> destination[index] = element }
        else -> {
            var i = 0
            var j = 0
            while (i < this.size && j < secondList.size) {
                when {
                    this[i] <= secondList[j] -> {
                        destination[i + j] = this[i]
                        i += 1
                    }
                    else -> {
                        destination[i + j] = secondList[j]
                        j += 1
                    }
                }
                when {
                    i == this.size -> secondList.forEachIndexed { index, element ->
                        if (index >= j) {
                            destination[index + i] = element
                        }
                    }
                    j == secondList.size -> this.forEachIndexed { index, element ->
                        if (index >= i) {
                            destination[index + j] = element
                        }
                    }
                }
            }
        }
    }

fun generateRandomList(size: Int, start: Int = MIN_RANDOM_NUM, end: Int = MAX_RANDOM_NUM): MutableList<Int> {
    val randomList = mutableListOf<Int>()
    repeat(size) { randomList.add((start..end).random()) }
    return randomList
}
