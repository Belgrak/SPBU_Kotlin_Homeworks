package homeworks.homework4

class ViewModel {
    fun showTimeFromThreads() {
        timeFromThreadsChart()
    }

    fun showTimeFromLists() {
        timeFromListsChart()
    }

    fun onClickGenerateList(size: Int) = generateRandomList(size)
}
