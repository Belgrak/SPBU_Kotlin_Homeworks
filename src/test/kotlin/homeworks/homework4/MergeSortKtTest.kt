package homeworks.homework4

import homeworks.homework4.mergeSort.AsynchronousMergeSort
import homeworks.homework4.mergeSort.MultithreadedMergeSort
import homeworks.homework4.mergeSort.SimpleMergeSort
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

internal class MergeSortKtTest {
    @ParameterizedTest
    @MethodSource("getListForSort")
    fun <E : Comparable<E>> simpleMergeSort(list: MutableList<E>, expected: MutableList<E>) {
        assertEquals(expected.sorted(), SimpleMergeSort(list).sort())
    }

    @ParameterizedTest
    @MethodSource("getDataThreadsAndCoroutines")
    fun <E : Comparable<E>> multithreadedMergeSort(list: MutableList<E>, threadsCount: Int, expected: MutableList<E>) {
        assertEquals(expected.sorted(), MultithreadedMergeSort(list, threadsCount).sort())
    }

    @ParameterizedTest
    @MethodSource("getDataThreadsAndCoroutines")
    fun <E : Comparable<E>> asynchronousMergeSort(
        list: MutableList<E>,
        coroutinesCount: Int,
        expected: MutableList<E>
    ) {
        assertEquals(expected.sorted(), AsynchronousMergeSort(list, coroutinesCount).sort())
    }

    companion object {
        private val listToExpected = mutableListOf<Pair<MutableList<Int>, MutableList<Int>>>().also { list ->
            repeat(5) {
                val generatedList = generateRandomList((2..100).random())
                list.add(
                    Pair(generatedList.toMutableList(), generatedList)
                )
            }
        }

        @JvmStatic
        fun getListForSort() = listToExpected.map { Arguments.of(it.first, it.second) }

        @JvmStatic
        fun getDataThreadsAndCoroutines() =
            listToExpected.map { Arguments.of(it.first, (1..100).random(), it.second) }
    }
}
