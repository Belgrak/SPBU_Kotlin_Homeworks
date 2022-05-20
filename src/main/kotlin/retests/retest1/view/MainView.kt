@file:Suppress("FunctionNaming")

package retests.retest1.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
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
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retests.retest1.getBest
import retests.retest1.getLast
import retests.retest1.getRandom

const val OFFSET = 50

@Composable
fun MainView() {
    var textField by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Цитаты", fontWeight = FontWeight.Bold, fontSize = 40.sp, modifier = Modifier.padding(OFFSET.dp))
        Button(onClick = {
            runBlocking { launch { textField = getBest() } }
            title = "Лучшие цитаты за все время"
        }) { Text("Показать лучшие цитаты за все время") }
        Button(onClick = {
            runBlocking { launch { textField = getLast() } }
            title = "Последние цитаты"
        }) { Text("Показать последние цитаты") }
        Button(onClick = {
            runBlocking { launch { textField = getRandom() } }
            title = "Случайная цитата"
        }) { Text("Показать случайную цитату") }
        Text(
            title,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding((OFFSET / 2).dp)
        )
        Text(textField, fontWeight = FontWeight.Medium, fontSize = 16.sp, modifier = Modifier.padding(OFFSET.dp))
    }
}
