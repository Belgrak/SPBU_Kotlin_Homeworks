package homeworks.homework4

import kotlin.math.ceil
import kotlin.math.floor

/**
 * count - Count of threads/coroutines. By default, it is 1.
 * coroutinesMode - If it's true, list will be sorted by coroutines, otherwise by threads. By default, it is false.
 *
 * Coroutines sort will be implemented soon.
 * */
fun <E : Comparable<E>> MutableList<E>.mergeSort(count: Int = 1, asynchronousMode: Boolean = false): MutableList<E> {
    require(count >= 1) { "Incorrect number of threads/coroutines" }
    if (this.size < 2) {
        return this
    }
    val tempList = this.toMutableList()
    when (count) {
        1 -> simpleSort(tempList)
        else -> when (asynchronousMode) {
            true -> {}
            false -> multithreadedSort(count, tempList)
        }
    }
    return this
}

private fun <E : Comparable<E>> MutableList<E>.simpleSort(tempList: MutableList<E>) {
    val averageIndex = ceil(this.size.toFloat() / 2).toInt()
    val firstListSorted = tempList.subList(0, averageIndex).mergeSort()
    val secondListSorted = tempList.subList(averageIndex, this.size).mergeSort()
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
