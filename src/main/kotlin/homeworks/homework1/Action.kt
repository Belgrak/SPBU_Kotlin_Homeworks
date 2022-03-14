package homeworks.homework1

interface Action {
    fun doAction()
    fun cancelAction()
}

class AddFirst(private val listOfInts: MutableList<Int>, private val element: Int) : Action {
    override fun doAction() {
        listOfInts.add(0, element)
    }

    override fun cancelAction() {
        listOfInts.removeFirst()
    }
}

class AddLast(private val listOfInts: MutableList<Int>, private val element: Int) : Action {
    override fun doAction() {
        listOfInts.add(element)
    }

    override fun cancelAction() {
        listOfInts.removeLast()
    }
}

class Shift(private val listOfInts: MutableList<Int>, private val indexFrom: Int, private val indexTo: Int) : Action {
    override fun doAction() {
        require(indexFrom in listOfInts.indices && indexTo in listOfInts.indices) { "Index/Indices out of range" }
        listOfInts.add(indexTo, listOfInts.removeAt(indexFrom))
    }

    override fun cancelAction() {
        listOfInts.add(indexFrom, listOfInts.removeAt(indexTo))
    }
}
