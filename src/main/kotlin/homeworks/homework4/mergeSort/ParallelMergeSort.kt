package homeworks.homework4.mergeSort

import kotlin.math.ceil
import kotlin.math.floor

abstract class ParallelMergeSort<E : Comparable<E>>(
    final override val listForSort: MutableList<E>,
    count: Int
) : MergeSort<E> {
    final override val listCopy: MutableList<E>
        get() = super.listCopy

    private val averageIndex = ceil(listForSort.size.toFloat() / 2).toInt()
    val firstHalfOfCount = floor(count.toFloat() / 2).toInt()
    val firstListSorted = listCopy.subList(0, averageIndex)
    val secondListSorted = listCopy.subList(averageIndex, listForSort.size)
}
