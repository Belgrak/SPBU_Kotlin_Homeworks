@file:Suppress("Filename", "MatchingDeclarationName")
package homeworks.homework4

enum class SortMode {
    MULTITHREADED, ASYNCHRONOUS
}

object ConstsAndSettings {
    const val MIN_LIST_SIZE = 10
    const val MAX_LIST_SIZE = 1000000
    const val LIST_STEP = 5000
    const val HEIGHT = 720
    const val WIDTH = 1280
    const val MIN_RANDOM_NUM = -100
    const val MAX_RANDOM_NUM = 100
    const val MIN_COUNT = 4
    const val MIDDLE_COUNT = 32
    const val MAX_COUNT = 1024
    const val SMALL_STEP = 4
    const val BIG_STEP = 32
    const val VERTICAL_OFFSET = 3
    const val THREADS_CHART_NAME = "timeFromThreads.png"
    const val LISTS_CHART_NAME_THREADS = "timeFromListsThreads.png"
    const val LISTS_CHART_NAME_COROUTINES = "timeFromListsCoroutines.png"
}
