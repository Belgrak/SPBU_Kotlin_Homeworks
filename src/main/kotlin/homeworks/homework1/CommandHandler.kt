package homeworks.homework1

enum class Commands(val asString: String) {
    ADDFIRST("addFirst"), ADDLAST("addLast"), SHIFT("shift"),
    REVERSE("reverse"), PRINT("print"), EXIT("exit")
}

class CommandHandler {
    fun handleCommands(
        commandStorage: PerformedCommandStorage,
        listOfInts: MutableList<Int>,
        command: String,
        listOfArguments: List<Int>
    ): Boolean {
        when (command) {
            Commands.ADDFIRST.asString -> addFirst(commandStorage, listOfArguments[0])
            Commands.ADDLAST.asString -> addLast(commandStorage, listOfArguments[0])
            Commands.SHIFT.asString -> shift(commandStorage, listOfArguments[0], listOfArguments[1])
            Commands.REVERSE.asString -> reverse(commandStorage)
            Commands.PRINT.asString -> println(listOfInts)
            Commands.EXIT.asString -> return false
            else -> println("Unknown command")
        }
        return true
    }

    fun isValidCountOfArguments(command: String, countOfArguments: Int) {
        when (command) {
            in listOf("addFirst", "addLast") -> require(countOfArguments == 1) { "Invalid count of arguments" }
            "shift" -> require(countOfArguments == 2) { "Invalid count of arguments" }
            in listOf("reverse", "print") -> require(countOfArguments == 0) { "Invalid count of arguments" }
        }
    }

    private fun addFirst(commandStorage: PerformedCommandStorage, element: Int) {
        commandStorage.addAction(AddFirst(element))
    }

    private fun addLast(commandStorage: PerformedCommandStorage, element: Int) {
        commandStorage.addAction(AddLast(element))
    }

    private fun shift(commandStorage: PerformedCommandStorage, indexFrom: Int, indexTo: Int) {
        try {
            commandStorage.addAction(Shift(indexFrom, indexTo))
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
