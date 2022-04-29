package homeworks.homework4.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainView(onGetResultClick: () -> Unit) {
    MaterialTheme {
        Column(Modifier.fillMaxSize(), Arrangement.spacedBy(5.dp)) {
            Text("Array:", Modifier.align(Alignment.CenterHorizontally))
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = onGetResultClick
            ) {
                Text("GET RESULT")
            }
        }
    }
}
