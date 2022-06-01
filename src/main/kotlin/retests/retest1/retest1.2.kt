package retests.retest1

import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import retests.retest1.view.MainView

const val WIDTH = 1280
const val HEIGHT = 720

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Цитаты",
        state = rememberWindowState(width = WIDTH.dp, height = HEIGHT.dp)
    ) {
        val viewModel = remember { ViewModel() }
        MainView(viewModel::showBestQuotes, viewModel::showLastQuotes, viewModel::showRandomQuote)
    }
}
