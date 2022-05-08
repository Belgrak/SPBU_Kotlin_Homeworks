package homeworks.homework4

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

internal class MergeSortKtTest {
    @ParameterizedTest
    @MethodSource("getListForSort")
    fun <E : Comparable<E>> simpleMergeSort(list: MutableList<E>, expected: MutableList<E>) {
        assertEquals(expected, list.mergeSort())
    }

    @ParameterizedTest
    @MethodSource("getListForSort")
    fun <E : Comparable<E>> multithreadedMergeSort(list: MutableList<E>, expected: MutableList<E>) {
        assertEquals(expected, list.mergeSort(10))
    }

    companion object {
        private val listToExpected = mutableListOf<MutableList<Int>>().also { list ->
            repeat(5) {
                list.add(generateRandomList((2..100).random()))
            }
        }

        @JvmStatic
        fun getListForSort() = listToExpected.map { Arguments.of(it.subList(0, it.size), it) }
    }
}
