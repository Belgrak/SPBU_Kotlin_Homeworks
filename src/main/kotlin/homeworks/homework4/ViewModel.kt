package homeworks.homework4

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ViewModel {
    var state: State by mutableStateOf(initialState())
        private set

    data class State(
        val screen: Screen
    )

    private fun initialState() = State(Screen.MainScreen)

    private inline fun updateState(update: State.() -> State) {
        state = state.update()
    }

    fun showTimeFromThreads() {
        timeFromThreadsChart()
    }

    fun showTimeFromLists() {
        timeFromListsChart()
    }

    fun onClickGenerateList(size: Int) = generateRandomList(size)
}
