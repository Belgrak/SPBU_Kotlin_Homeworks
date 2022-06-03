package homeworks.homework4

class ViewModel {
    fun showTimeFromThreads() = timeFromThreadsChart()

    fun showTimeFromLists(sortMode: SortMode) = timeFromListsChart(sortMode)

    fun showTimeFromCoroutines() = timeFromCoroutinesChart()
}
