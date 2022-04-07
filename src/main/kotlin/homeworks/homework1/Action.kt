package homeworks.homework1

interface Action {
    fun doAction(listOfInts: MutableList<Int>)
    fun cancelAction(listOfInts: MutableList<Int>)
}

class AddFirst(private val element: Int) : Action {
    override fun doAction(listOfInts: MutableList<Int>) {
        listOfInts.add(0, element)
    }

    override fun cancelAction(listOfInts: MutableList<Int>) {
        listOfInts.removeFirst()
    }
}

class AddLast(private val element: Int) : Action {
    override fun doAction(listOfInts: MutableList<Int>) {
        listOfInts.add(element)
    }

    override fun cancelAction(listOfInts: MutableList<Int>) {
        listOfInts.removeLast()
    }
}

class Shift(private val indexFrom: Int, private val indexTo: Int) : Action {
    override fun doAction(listOfInts: MutableList<Int>) {
        require(indexFrom in listOfInts.indices && indexTo in listOfInts.indices) { "Index/Indices out of range" }
        val shiftElement = listOfInts.removeAt(indexFrom)
        listOfInts.add(indexTo, shiftElement)
    }

    override fun cancelAction(listOfInts: MutableList<Int>) {
        val shiftElement = listOfInts.removeAt(indexTo)
        listOfInts.add(indexFrom, shiftElement)
    }
}
