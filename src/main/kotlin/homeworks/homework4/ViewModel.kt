package homeworks.homework4

class ViewModel {
    fun showTimeFromThreads() = timeFromThreadsChart()

    fun showTimeFromListsThreads() = timeFromListsChartThreads()

    fun onSortClick(list: MutableList<Int>, count: Int, sortMode: SortMode = SortMode.MULTITHREADED) {
        list.mergeSort(count, sortMode)
    }

    fun onClickGenerateList(size: Int) = generateRandomList(size)
}
