package homeworks.homework4.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import homeworks.homework4.MIDDLE_COUNT
import homeworks.homework4.SortMode
import kotlin.reflect.KFunction3

const val RANGE_LIMIT = 100f

@Suppress("FunctionNaming")
@Composable
fun MainView(
    onClickGenerateList: (Int) -> MutableList<Int>,
    onClickShowTimeThreads: () -> Unit,
    onClickShowTimeLists: () -> Unit,
    onClickGetResult: KFunction3<MutableList<Int>, Int, SortMode, Unit>
) {
    var listSize by remember { mutableStateOf(0) }
    var list by remember { mutableStateOf(mutableListOf<Int>()) }
    var listState by remember { mutableStateOf("Generated List: ") }
    val sortMode by remember { mutableStateOf(SortMode.MULTITHREADED) }
    MaterialTheme {
        Column(
            Modifier.fillMaxSize().padding(5.dp),
            Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "List size: $listSize", fontWeight = FontWeight.Bold)
            Slider(
                value = listSize.toFloat(),
                onValueChange = { listSize = it.toInt() },
                valueRange = 0f..RANGE_LIMIT,
                onValueChangeFinished = {
                    list = onClickGenerateList(listSize)
                    listState = "Generated List: "
                }
            )
            Text(listState, fontWeight = FontWeight.Bold)
            Text(list.toString())
            Button(onClick = {
                listState = "Sorted List: "
                onClickGetResult(list, MIDDLE_COUNT, sortMode)
            }) {
                Text("GET RESULT")
            }
            Button(onClick = onClickShowTimeThreads) {
                Text("Show time from threads chart")
            }
            Button(onClick = onClickShowTimeLists) {
                Text("Show time from lists size chart")
            }
        }
    }
}
