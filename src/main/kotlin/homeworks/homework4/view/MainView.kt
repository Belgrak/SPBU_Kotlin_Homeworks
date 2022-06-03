package homeworks.homework4.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import homeworks.homework4.SortMode
import kotlin.reflect.KFunction1

@Suppress("FunctionNaming")
@Composable
fun MainView(
    onClickShowTimeThreads: () -> Unit,
    onClickShowTimeLists: KFunction1<SortMode, Unit>,
    onClickShowTimeCoroutines: () -> Unit
) {
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Merge Sort Charts",
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                modifier = Modifier.padding(50.dp)
            )
            Button(onClick = onClickShowTimeThreads) {
                Text("Time from threads chart")
            }
            Button(onClick = { onClickShowTimeLists(SortMode.MULTITHREADED) }) {
                Text("Time from lists size chart (Threads)")
            }
            Button(onClick = onClickShowTimeCoroutines) {
                Text("Time from coroutines chart")
            }
            Button(onClick = { onClickShowTimeLists(SortMode.ASYNCHRONOUS) }) {
                Text("Time from lists size chart (Coroutines)")
            }
        }
    }
}
