package homeworks.homework4

class ViewModel {
    fun showTimeFromThreads() = timeFromThreadsChart()

    fun showTimeFromLists(asynchronousMode: Boolean = false) = timeFromListsChart(asynchronousMode)

    fun showTimeFromCoroutines() = timeFromCoroutinesChart()

    fun onSortClick(list: MutableList<Int>, count: Int, asynchronousMode: Boolean = false) {
        list.mergeSort(count, asynchronousMode)
    }

    fun onClickGenerateList(size: Int) = generateRandomList(size)
}
