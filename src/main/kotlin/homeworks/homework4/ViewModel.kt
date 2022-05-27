package homeworks.homework4

class ViewModel {
    fun showTimeFromThreads() = timeFromThreadsChart()

    fun showTimeFromListsThreads() = timeFromListsChartThreads()

    fun onSortClick(list: MutableList<Int>, count: Int, asynchronousSort: Boolean = false) {
        list.mergeSort(count, asynchronousSort)
    }

    fun onClickGenerateList(size: Int) = generateRandomList(size)
}
