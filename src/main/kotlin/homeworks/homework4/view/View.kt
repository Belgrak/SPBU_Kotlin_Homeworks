package homeworks.homework4.view

import androidx.compose.runtime.Composable
import homeworks.homework4.Screen
import homeworks.homework4.ViewModel

@Composable
fun View(viewModel: ViewModel) {
    when (viewModel.state.screen) {
        Screen.MainScreen -> MainView(
            viewModel::onClickGenerateList,
            viewModel::showTimeFromThreads,
            viewModel::showTimeFromLists
        )
    }
}
