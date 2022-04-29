package homeworks.homework4.view

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import homeworks.homework4.Screen
import homeworks.homework4.ViewModel

@Composable
@Preview
fun View(viewModel: ViewModel) {
    when (viewModel.state.screen) {
        Screen.MainScreen -> MainView(viewModel::showGraphView)
        Screen.ChartView -> ChartView()
    }
}
