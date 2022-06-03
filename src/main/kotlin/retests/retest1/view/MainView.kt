@file:Suppress("FunctionNaming")

package retests.retest1.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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

const val OFFSET = 50
const val BUTTON_WIDTH = 350
const val BUTTON_FONT = 14

@Composable
fun MainView(
    onBestQuotesClick: () -> String,
    onLastQuotesClick: () -> String,
    onRandomQuoteClick: () -> String
) {
    var textField by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Цитаты", fontWeight = FontWeight.Bold, fontSize = 40.sp, modifier = Modifier.padding(OFFSET.dp))
        Button(
            modifier = Modifier.width(BUTTON_WIDTH.dp),
            onClick = {
                textField = onBestQuotesClick()
                title = "Лучшие цитаты за все время"
            }
        ) { Text("Показать лучшие цитаты за все время", fontWeight = FontWeight.Bold, fontSize = BUTTON_FONT.sp) }
        Button(
            modifier = Modifier.width(BUTTON_WIDTH.dp),
            onClick = {
                textField = onLastQuotesClick()
                title = "Последние цитаты"
            }
        ) { Text("Показать последние цитаты", fontWeight = FontWeight.Bold, fontSize = BUTTON_FONT.sp) }
        Button(
            modifier = Modifier.width(BUTTON_WIDTH.dp),
            onClick = {
                textField = onRandomQuoteClick()
                title = "Случайная цитата"
            }
        ) { Text("Показать случайную цитату", fontWeight = FontWeight.Bold, fontSize = BUTTON_FONT.sp) }
        Text(
            title,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding((OFFSET / 2).dp)
        )
        Text(textField, fontWeight = FontWeight.Medium, fontSize = 16.sp, modifier = Modifier.padding(OFFSET.dp))
    }
}
