package homeworks.homework1

import kotlin.system.exitProcess

class CommandHandler {
    fun handleCommands(
        commandStorage: PerformedCommandStorage,
        listOfInts: MutableList<Int>,
        command: String,
        listOfArguments: List<Int>
    ) {
        when (command) {
            "addFirst" -> addFirst(commandStorage, listOfInts, listOfArguments[0])
            "addLast" -> addLast(commandStorage, listOfInts, listOfArguments[0])
            "shift" -> shift(commandStorage, listOfInts, listOfArguments[0], listOfArguments[1])
            "reverse" -> reverse(commandStorage)
            "print" -> println(listOfInts)
            "exit" -> exitProcess(0)
            else -> println("Unknown command")
        }
    }

    fun isValidCountOfArguments(command: String, countOfArguments: Int) {
        when (command) {
            in listOf("addFirst", "addLast") -> require(countOfArguments == 1) { "Invalid count of arguments" }
            "shift" -> require(countOfArguments == 2) { "Invalid count of arguments" }
            in listOf("reverse", "print") -> require(countOfArguments == 0) { "Invalid count of arguments" }
        }
    }

    private fun addFirst(commandStorage: PerformedCommandStorage, list: MutableList<Int>, element: Int) {
        commandStorage.addAction(AddFirst(list, element))
    }

    private fun addLast(commandStorage: PerformedCommandStorage, list: MutableList<Int>, element: Int) {
        commandStorage.addAction(AddLast(list, element))
    }

    private fun shift(commandStorage: PerformedCommandStorage, list: MutableList<Int>, indexFrom: Int, indexTo: Int) {
        try {
            commandStorage.addAction(Shift(list, indexFrom, indexTo))
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }

    private fun reverse(commandStorage: PerformedCommandStorage) {
        try {
            commandStorage.removeAction()
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}
