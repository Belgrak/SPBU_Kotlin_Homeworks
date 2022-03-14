package homeworks.homework1

fun main() {
    println(
        """Please enter a command. To cancel input use Ctrl^D. List of commands:
        |addFirst A  --  add element to the beginning of the list
        |addLast A  --  add element to the end of the list
        |shift a b  --  move element from a position to b
        |reverse  -- reverse last action in the list
        |print  --  print list
        |exit  --  finish the program
    """.trimMargin()
    )

    val commandStorage = PerformedCommandStorage()
    val commandHandler = CommandHandler()

    var inputLine: String
    var command: String
    var listOfArguments = listOf<Int>()

    while (true) {
        inputLine = readLine() ?: return
        inputLine.split(" ").let {
            command = it[0]
            if (it.size > 1) {
                listOfArguments = it.mapNotNull { argument -> argument.toIntOrNull() }
            }
        }
        try {
            commandHandler.isValidCountOfArguments(command, listOfArguments.size)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            continue
        }
        commandHandler.handleCommands(commandStorage, commandStorage.listOfInts, command, listOfArguments)
        listOfArguments = emptyList()
    }
}
