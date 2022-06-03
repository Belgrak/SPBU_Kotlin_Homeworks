package homeworks.homework4.mergeSort

class MultithreadedMergeSort<E : Comparable<E>>(list: MutableList<E>, private val nThreads: Int) :
    ParallelMergeSort<E>(list, nThreads) {
    override fun sort(): MutableList<E> {
        require(nThreads >= 1) { "Incorrect number of threads" }

        if (nThreads == 1) {
            return SimpleMergeSort(listForSort).sort()
        }

        val firstThread = Thread { MultithreadedMergeSort(firstListSorted, firstHalfOfCount).sort() }
        val secondThread = Thread { MultithreadedMergeSort(secondListSorted, nThreads - firstHalfOfCount).sort() }
        firstThread.start()
        secondThread.start()
        firstThread.join()
        secondThread.join()
        merge(firstListSorted, secondListSorted, listForSort)
        return listForSort
    }
}
