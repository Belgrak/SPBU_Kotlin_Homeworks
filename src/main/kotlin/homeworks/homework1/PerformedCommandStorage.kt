package homeworks.homework1

class PerformedCommandStorage {
    private val listOfActions = mutableListOf<Action>()
    val listOfInts = mutableListOf<Int>()

    fun addAction(action: Action) {
        action.doAction(listOfInts)
        listOfActions.add(action)
    }

    fun removeAction() {
        require(listOfActions.isNotEmpty()) { "There aren't any actions" }
        listOfActions.removeLast().cancelAction(listOfInts)
    }
}
