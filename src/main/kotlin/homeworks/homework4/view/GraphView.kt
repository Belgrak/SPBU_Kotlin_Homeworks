package homeworks.homework4.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun ChartView() {
    Column {
        Canvas(
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            val lineDistance = size.width / (data.size + 2)
            val yDistance = size.height / 9
            for ((i, e) in data.withIndex()) {
                if (i + 1 < data.size) {
                    drawLine(
                        start = Offset(calculateXCord(e.first, lineDistance), calculateYCord(e.second, yDistance)),
                        end = Offset(
                            calculateXCord(
                                data[i + 1].first, lineDistance
                            ), calculateYCord(data[i + 1].second, yDistance)
                        ),
                        color = Color(0xFF3F51B5)
                    )
                }
            }
        }
        for ((i, e) in data.withIndex()) {
            Text("$i, $e")}
    }
}

val data = listOf(Pair(1, 2), Pair(3, 1), Pair(5, 9))

fun calculateXCord(x: Int, distance: Float): Float {
    return x * distance
}

fun calculateYCord(y: Int, distance: Float): Float {
    return (9 - y) * distance
}
