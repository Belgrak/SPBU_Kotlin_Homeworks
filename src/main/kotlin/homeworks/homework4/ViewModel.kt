package homeworks.homework4

class ViewModel {
    fun showTimeFromThreads() = timeFromThreadsChart()

    fun showTimeFromLists(sortMode: SortMode = SortMode.MULTITHREADED) = timeFromListsChart(sortMode)
}
