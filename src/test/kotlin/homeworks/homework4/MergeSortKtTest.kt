package homeworks.homework4

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

internal class MergeSortKtTest {
    @ParameterizedTest
    @MethodSource("getListForSort")
    fun <E : Comparable<E>> mergeSort(list: MutableList<E>, expected: MutableList<E>) {
        assertEquals(expected, list.mergeSort())
    }

    companion object {
        var list = generateRandomList(100)
        var temp = list.subList(0, list.size).toMutableList()

        @JvmStatic
        fun getListForSort() = listOf(
            Arguments.of(temp, list.also { it.sort() })
        )
    }
}
