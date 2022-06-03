package homeworks.homework4.mergeSort

interface MergeSort<E : Comparable<E>> {
    val listForSort: MutableList<E>
    val listCopy: MutableList<E>
        get() = listForSort.toMutableList()

    fun sort(): MutableList<E>

    fun merge(firstList: MutableList<E>, secondList: MutableList<E>, destination: MutableList<E>) =
        when {
            firstList.isEmpty() -> secondList.forEachIndexed { index, element -> destination[index] = element }
            secondList.isEmpty() -> firstList.forEachIndexed { index, element -> destination[index] = element }
            else -> {
                var i = 0
                var j = 0
                while (i < firstList.size && j < secondList.size) {
                    when {
                        firstList[i] <= secondList[j] -> {
                            destination[i + j] = firstList[i]
                            i += 1
                        }
                        else -> {
                            destination[i + j] = secondList[j]
                            j += 1
                        }
                    }
                    when {
                        i == firstList.size -> secondList.forEachIndexed { index, element ->
                            if (index >= j) {
                                destination[index + i] = element
                            }
                        }
                        j == secondList.size -> firstList.forEachIndexed { index, element ->
                            if (index >= i) {
                                destination[index + j] = element
                            }
                        }
                    }
                }
            }
        }
}
