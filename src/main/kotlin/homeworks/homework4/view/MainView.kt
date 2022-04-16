package homeworks.homework4.view

import javafx.beans.property.SimpleStringProperty
import tornadofx.Controller
import tornadofx.View
import tornadofx.action
import tornadofx.button
import tornadofx.field
import tornadofx.fieldset
import tornadofx.form
import tornadofx.label
import tornadofx.textfield

class MainView : View() {
    private val controller: MyController by inject()
    private val input = SimpleStringProperty()

    override val root = form {
        primaryStage.height = ConstsAndSettings.HEIGHT
        primaryStage.width = ConstsAndSettings.WIDTH
        label("Please enter a data for sort using whitespace\n")
        fieldset {
            field("Input") {
                textfield(input)
            }

            button("Commit") {
                action {
                    controller.getInputData(input.value)
                    input.value = ""
                    replaceWith<ResultAndGraphView>()
                }
            }
        }
    }
}

class MyController : Controller() {
    // TODO("replace with calling multithreaded sort function")
    fun getInputData(inputValue: String?) {
        println(inputValue)
    }
}
