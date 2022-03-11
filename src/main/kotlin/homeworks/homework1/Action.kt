package homeworks.homework1

import java.util.Collections

enum class ActionType {
    ADDFIRST, ADDLAST, SHIFT
}

class Action(list: MutableList<Int>, actionType: ActionType, firstArg: Int, secondArg: Int = -1) {
    val type = actionType
    val listOfInts = list
    val firstArgument = firstArg
    val secondArgument = secondArg

    init {
        when (type) {
            ActionType.ADDFIRST -> list.add(0, firstArgument)
            ActionType.ADDLAST -> list.add(list.size, firstArgument)
            ActionType.SHIFT -> {
                require(firstArg in list.indices && secondArg in list.indices) { "Index/Indices out of range" }
                val sign = if (firstArgument <= secondArgument) 1 else -1
                for (i in 1..kotlin.math.abs(secondArgument - firstArgument)) {
                    Collections.swap(list, firstArgument + sign * (i - 1), firstArgument + sign * i)
                }
            }
        }
    }
}
