package homeworks.homework4

const val MIN_RANDOM_NUM = -10000
const val MAX_RANDOM_NUM = 10000

fun <E : Comparable<E>> MutableList<E>.mergeSort(nThreads: Int = 1): MutableList<E> {
    require(nThreads >= 1) { "Incorrect number of threads" }
    when (nThreads) {
        1 -> this.simpleSort()
    }
    return this
}

private fun <E : Comparable<E>> MutableList<E>.simpleSort() {
    var subArraySize = 1
    var tempArray: MutableList<E>
    while (subArraySize <= this.size) {
        for (i in this.indices step (2 * subArraySize)) {
            val j = i + subArraySize
            var secondArray = mutableListOf<E>()
            if (j < this.size) {
                secondArray = this.subList(j, (j + subArraySize).coerceAtMost(this.size))
            }
            tempArray = this.subList(i, (i + subArraySize).coerceAtMost(this.size)).mergeWith(secondArray)
            tempArray.forEachIndexed { index, element -> this[index + i] = element }
        }
        subArraySize *= 2
    }
}

private fun <E : Comparable<E>> MutableList<E>.mergeWith(secondArray: MutableList<E>): MutableList<E> {
    return when {
        this.isEmpty() -> secondArray.subList(0, secondArray.size)
        secondArray.isEmpty() -> this.subList(0, this.size)
        else -> {
            val mergedArray = mutableListOf<E>()
            var i = 0
            var j = 0
            while (i < this.size && j < secondArray.size) {
                if (this[i] <= secondArray[j]) {
                    mergedArray.add(i + j, this[i])
                    i += 1
                } else {
                    mergedArray.add(i + j, secondArray[j])
                    j += 1
                }
                when {
                    i == this.size -> secondArray.forEachIndexed { index, element ->
                        if (index >= j) {
                            mergedArray.add(index + i, element)
                        }
                    }
                    j == secondArray.size -> this.forEachIndexed { index, element ->
                        if (index >= i) {
                            mergedArray.add(index + j, element)
                        }
                    }
                }
            }
            mergedArray
        }
    }
}

fun generateRandomList(size: Int, start: Int = MIN_RANDOM_NUM, end: Int = MAX_RANDOM_NUM): MutableList<Int> {
    require(size <= end - start) { "Incorrect size" }
    val randomList = mutableListOf<Int>()
    repeat(size) { randomList.add((start..end).random()) }
    return randomList
}
