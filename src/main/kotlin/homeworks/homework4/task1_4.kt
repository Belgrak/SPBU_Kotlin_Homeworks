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
        title = "Multithreaded & Asynchronous merge sort",
        state = rememberWindowState(width = ConstsAndSettings.WIDTH.dp, height = ConstsAndSettings.HEIGHT.dp)
    ) {
        val viewModel = remember { ViewModel() }
        MainView(
            viewModel::showTimeFromThreads,
            viewModel::showTimeFromLists,
            viewModel::showTimeFromCoroutines
        )
    }
}
