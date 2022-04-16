package homeworks.homework4.view

import javafx.scene.chart.NumberAxis
import tornadofx.View
import tornadofx.data
import tornadofx.hbox
import tornadofx.linechart
import tornadofx.series

@Suppress("MagicNumber")
class ResultAndGraphView : View() {
    override val root = hbox {
        primaryStage.height = ConstsAndSettings.HEIGHT
        primaryStage.width = ConstsAndSettings.WIDTH
        linechart("Time dependence on the number of threads", NumberAxis(), NumberAxis()) {
            series("Time dependence") {
                xAxis.label = "Number of threads"
                yAxis.label = "Time"
                data(1, 10)
                data(10, 1)
                data(100, 0.01)
            }
        }
    }
}
