package homeworks.homework1

import java.util.Collections

interface Action {
    fun doAction()
    fun cancelAction()
}

class AddFirst(private val listOfInts: MutableList<Int>, private val listOfArguments: List<Int>) : Action {
    override fun doAction() {
        listOfInts.add(0, listOfArguments[0])
    }

    override fun cancelAction() {
        listOfInts.removeAt(0)
    }
}

class AddLast(private val listOfInts: MutableList<Int>, private val listOfArguments: List<Int>) : Action {
    override fun doAction() {
        listOfInts.add(listOfInts.size, listOfArguments[0])
    }

    override fun cancelAction() {
        listOfInts.removeAt(listOfInts.size)
    }
}

class Shift(private val listOfInts: MutableList<Int>, private val listOfArguments: List<Int>) : Action {
    override fun doAction() {
        require(listOfArguments.size >= 2) { "Too few arguments" }
        val aIndex = listOfArguments[0]
        val bIndex = listOfArguments[1]
        require(aIndex in listOfInts.indices && bIndex in listOfInts.indices) { "Index/Indices out of range" }

        val sign = if (aIndex <= bIndex) 1 else -1
        for (i in 1..kotlin.math.abs(aIndex - bIndex)) {
            Collections.swap(listOfInts, aIndex + sign * (i - 1), aIndex + sign * i)
        }
    }

    override fun cancelAction() {
        Collections.swap(listOfArguments, 0, 1)
        doAction()
    }
}
