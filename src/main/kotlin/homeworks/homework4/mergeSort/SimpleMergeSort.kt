package homeworks.homework4.mergeSort

import kotlin.math.ceil

class SimpleMergeSort<E : Comparable<E>>(override val listForSort: MutableList<E>) : MergeSort<E> {
    override fun sort(): MutableList<E> {

        if (listForSort.size < 2) {
            return listForSort
        }

        val averageIndex = ceil(listForSort.size.toFloat() / 2).toInt()
        val firstListSorted = SimpleMergeSort(listCopy.subList(0, averageIndex)).sort()
        val secondListSorted =
            SimpleMergeSort(listCopy.subList(averageIndex, listForSort.size)).sort()
        merge(firstListSorted, secondListSorted, listForSort)
        return listForSort
    }
}
