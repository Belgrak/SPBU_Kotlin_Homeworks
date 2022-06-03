package homeworks.homework4.mergeSort

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AsynchronousMergeSort<E : Comparable<E>>(list: MutableList<E>, private val nCoroutines: Int) :
    ParallelMergeSort<E>(list, nCoroutines) {
    override fun sort(): MutableList<E> {
        require(nCoroutines >= 1) { "Incorrect number of coroutines" }

        if (nCoroutines == 1) {
            return SimpleMergeSort(listForSort).sort()
        }

        runBlocking {
            val firstCoroutine = launch { AsynchronousMergeSort(firstListSorted, firstHalfOfCount).sort() }
            val secondCoroutine = launch {
                AsynchronousMergeSort(secondListSorted, nCoroutines - firstHalfOfCount).sort()
            }
            firstCoroutine.join()
            secondCoroutine.join()
            merge(firstListSorted, secondListSorted, listForSort)
        }
        return listForSort
    }
}
