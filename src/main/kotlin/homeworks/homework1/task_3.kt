package homeworks.homework1

fun addFirst(commandStorage: PerformedCommandStorage, list: MutableList<Int>, element: Int) {
    commandStorage.addAction(AddFirst(list, listOf(element)))
}

fun addLast(commandStorage: PerformedCommandStorage, list: MutableList<Int>, element: Int) {
    commandStorage.addAction(AddLast(list, listOf(element)))
}

fun shift(commandStorage: PerformedCommandStorage, list: MutableList<Int>, elementA: Int, elementB: Int) {
    try {
        commandStorage.addAction(Shift(list, listOf(elementA, elementB)))
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }
}

fun reverse(commandStorage: PerformedCommandStorage) {
    try {
        commandStorage.removeAction()
    } catch (e: NoSuchElementException) {
        println(e.message)
    }
}

fun commandHandler(
    commandStorage: PerformedCommandStorage,
    listOfInts: MutableList<Int>,
    command: String,
    firstArgument: Int?,
    secondArgument: Int?
) {
    // here was 'when' expression, but I decide to turn it into map
    // Maybe it is strange and bad decision, because of using memory:)
    val mapOfCommands = mapOf(
        "addFirst" to firstArgument?.let { addFirst(commandStorage, listOfInts, it) },
        "addLast" to firstArgument?.let { addLast(commandStorage, listOfInts, it) },
        "shift" to firstArgument?.let { first ->
            secondArgument?.let { second -> shift(commandStorage, listOfInts, first, second) }
        },
        "reverse" to reverse(commandStorage),
        "print" to println(listOfInts)
    )
    if (command !in mapOfCommands.keys) {
        println("Unknown command")
        return
    }
    mapOfCommands[command]
}

fun main() {
    println(
        """Please enter a command. To cancel input use Ctrl^D. List of commands:
        |addFirst A  --  add element to the beginning of the list
        |addLast A  --  add element to the end of the list
        |shift a b  --  move element from a position to b
        |reverse  -- reverse last action in the list
        |print  --  print list
    """.trimMargin()
    )

    val commandStorage = PerformedCommandStorage()

    var inputLine: String
    var command: String
    var firstArgument: Int?
    var secondArgument: Int?

    while (true) {
        inputLine = readLine() ?: return
        inputLine.split(" ").let {
            command = it[0]
            when {
                it.size <= 1 -> {
                    firstArgument = 0
                    secondArgument = 0
                }
                it.size == 2 -> {
                    firstArgument = it[1].toIntOrNull()
                    secondArgument = it[1].toIntOrNull()
                }
                else -> {
                    firstArgument = it[1].toIntOrNull()
                    secondArgument = it[2].toIntOrNull()
                }
            }
        }
        if (firstArgument == null || secondArgument == null) {
            println("Invalid arguments. Please enter again")
            continue
        }

        commandHandler(commandStorage, commandStorage.listOfInts, command, firstArgument, secondArgument)
    }
}
