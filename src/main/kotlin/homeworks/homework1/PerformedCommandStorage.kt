package homeworks.homework1

class PerformedCommandStorage {
    private val listOfActions = mutableListOf<Action>()
    val listOfInts = mutableListOf<Int>()

    fun addAction(action: Action) {
        listOfActions.add(action)
    }

    fun removeAction() {
        if (listOfActions.isEmpty()) {
            throw NoSuchElementException("There aren't any actions")
        }
        val currentElement = listOfActions.last()
        when (currentElement.type) {
            ActionType.ADDFIRST -> currentElement.listOfInts.removeAt(0)
            ActionType.ADDLAST -> currentElement.listOfInts.removeAt(currentElement.listOfInts.size - 1)
            ActionType.SHIFT -> Action(
                currentElement.listOfInts,
                ActionType.SHIFT,
                currentElement.secondArgument,
                currentElement.firstArgument
            )
        }
        listOfActions.removeAt(listOfActions.lastIndex)
    }
}
