package homeworks.homework3

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

internal class AVLTreeTest {
    @BeforeEach
    fun setUp() {
        treeWithA[5] = "a"
        treeWithData.putAll(
            mapOf(
                1 to "a",
                2 to "b",
                3 to "c",
                4 to "d",
                5 to "e",
                6 to "f",
                7 to "g",
                8 to "h"
            )
        )
    }

    @ParameterizedTest
    @MethodSource("putData")
    fun <K : Comparable<K>, V> put(
        tree: AVLTree<K, V>,
        key: K,
        value: V
    ) {
        tree[key] = value
        assertEquals(value, tree[key])
    }

    @ParameterizedTest
    @MethodSource("putAllData")
    fun <K : Comparable<K>, V> putAll(
        tree: AVLTree<K, V>,
        from: Map<out K, V>
    ) {
        tree.putAll(from)
        from.forEach { (key, value) -> assertEquals(value, tree[key]) }
    }

    @ParameterizedTest
    @MethodSource("removeData")
    fun <K : Comparable<K>, V> remove(
        tree: AVLTree<K, V>,
        key: K
    ) {
        tree.remove(key)
        assertEquals(key !in tree.keys, true)
    }

    @ParameterizedTest
    @MethodSource("containsKeyData")
    fun <K : Comparable<K>, V> containsKey(
        tree: AVLTree<K, V>,
        key: K,
        expectedResult: Boolean
    ) {
        assertEquals(expectedResult, tree.containsKey(key))
    }

    @ParameterizedTest
    @MethodSource("containsValueData")
    fun <K : Comparable<K>, V> containsValue(
        tree: AVLTree<K, V>,
        value: V,
        expectedResult: Boolean
    ) {
        assertEquals(expectedResult, tree.containsValue(value))
    }

    @ParameterizedTest
    @MethodSource("getData")
    fun <K : Comparable<K>, V> get(
        tree: AVLTree<K, V>,
        key: K,
        expectedResult: V
    ) {
        assertEquals(expectedResult, tree[key])
    }

    @ParameterizedTest
    @MethodSource("isEmptyData")
    fun <K : Comparable<K>, V> isEmpty(
        tree: AVLTree<K, V>,
        expectedResult: Boolean
    ) {
        assertEquals(expectedResult, tree.isEmpty())
    }

    @ParameterizedTest
    @MethodSource("balanceData")
    fun <K : Comparable<K>, V> balanceTest(
        tree: AVLTree<K, V>,
        expectedResult: List<K>
    ) {
        assertEquals(expectedResult, tree.root?.let { listOf(it.leftChild?.key, it.rightChild?.key) })
    }

    @ParameterizedTest
    @MethodSource("keysData")
    fun <K : Comparable<K>, V> keysTest(
        tree: AVLTree<K, V>,
        expectedResult: MutableSet<K>
    ) {
        assertEquals(expectedResult, tree.keys)
    }

    @ParameterizedTest
    @MethodSource("valuesData")
    fun <K : Comparable<K>, V> valuesTest(
        tree: AVLTree<K, V>,
        expectedResult: MutableList<V>
    ) {
        assertEquals(expectedResult, tree.values)
    }

    @ParameterizedTest
    @MethodSource("sizeData")
    fun <K : Comparable<K>, V> sizeTest(
        tree: AVLTree<K, V>,
        expectedResult: Int
    ) {
        assertEquals(expectedResult, tree.size)
    }

    companion object {
        private val treeWithA = AVLTree<Int, String>()
        private val treeWithData = AVLTree<Int, String>()

        @JvmStatic
        fun putData() = listOf(
            Arguments.of(AVLTree<Int, String>(), 5, "a"),
            Arguments.of(treeWithData, 8, "i")
        )

        @JvmStatic
        fun putAllData() = listOf(
            Arguments.of(AVLTree<Int, String>(), mapOf(5 to "a")),
            Arguments.of(
                AVLTree<Int, String>(),
                mapOf(
                    1 to "a",
                    2 to "b",
                    3 to "c",
                    4 to "d",
                    5 to "e",
                    6 to "f",
                    7 to "g",
                    8 to "h"
                )
            )
        )

        @JvmStatic
        fun removeData() = listOf(
            Arguments.of(treeWithA, 5),
            Arguments.of(treeWithData, 3)
        )

        @JvmStatic
        fun containsKeyData() = listOf(
            Arguments.of(treeWithA, 3, false),
            Arguments.of(AVLTree<Int, String>(), 2, false),
            Arguments.of(treeWithData, 4, true)
        )

        @JvmStatic
        fun containsValueData() = listOf(
            Arguments.of(treeWithA, "b", false),
            Arguments.of(AVLTree<Int, String>(), "c", false),
            Arguments.of(treeWithData, "d", true)
        )

        @JvmStatic
        fun getData() = listOf(
            Arguments.of(treeWithA, 5, "a"),
            Arguments.of(treeWithData, 7, "g")
        )

        @JvmStatic
        fun isEmptyData() = listOf(
            Arguments.of(treeWithA, false),
            Arguments.of(AVLTree<Int, String>(), true)
        )

        @JvmStatic
        fun balanceData() = listOf(
            Arguments.of(AVLTree<Int, String>().apply { putAll(mapOf(1 to "a", 2 to "a", 3 to "a")) }, listOf(1, 3)),
            Arguments.of(
                AVLTree<Int, String>().apply {
                    putAll(
                        mapOf(
                            1 to "a",
                            2 to "a",
                            3 to "a",
                            4 to "a",
                            5 to "a"
                        )
                    )
                },
                listOf(1, 4)
            )
        )

        @JvmStatic
        fun keysData() = listOf(
            Arguments.of(treeWithA, mutableSetOf(5)),
            Arguments.of(treeWithData, mutableSetOf(1, 2, 3, 4, 5, 6, 7, 8))
        )

        @JvmStatic
        fun valuesData() = listOf(
            Arguments.of(treeWithA, mutableListOf("a")),
            Arguments.of(treeWithData, mutableListOf("d", "b", "a", "c", "f", "e", "g", "h"))
        )

        @JvmStatic
        fun sizeData() = listOf(
            Arguments.of(treeWithA, 1),
            Arguments.of(treeWithA.apply { this[5] = "b" }, 1),
            Arguments.of(AVLTree<Int, String>(), 0)
        )
    }
}
