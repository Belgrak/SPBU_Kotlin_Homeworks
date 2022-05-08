package homeworks.homework4

import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import homeworks.homework4.view.MainView

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Multithreaded & coroutines merge sort",
        state = rememberWindowState(width = 1280.dp, height = 720.dp)
    ) {
        val viewModel = remember { ViewModel() }
        MainView(
            viewModel::onClickGenerateList,
            viewModel::showTimeFromThreads,
            viewModel::showTimeFromLists
        )
    }
}
