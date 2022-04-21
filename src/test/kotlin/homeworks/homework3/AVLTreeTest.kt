package homeworks.homework3

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class AVLTreeTest {
    private val tree = AVLTree<Int, String>()
    private val treeWithData = AVLTree<Int, String>()

    @BeforeEach
    fun setUp() {
        mapOf(5 to "a").forEach { (key, value) -> tree[key] = value }
        mapOf(
            1 to "a",
            2 to "b",
            3 to "c",
            4 to "d",
            5 to "e",
            6 to "f",
            7 to "g",
            8 to "h"
        ).forEach { (key, value) -> treeWithData[key] = value }
    }

    @Test
    fun put() {
        tree[6] = "b"
        assertEquals(6, tree.root?.let { it.rightChild?.key })
    }

    @Test
    fun rightBalanceTest() {
        tree[6] = "b"
        tree[7] = "c"
        assertEquals(listOf(5, 7), tree.root?.let { listOf(it.leftChild?.key, it.rightChild?.key) })
    }

    @Test
    fun leftBalanceTest() {
        tree[4] = "b"
        tree[3] = "c"
        assertEquals(listOf(3, 5), tree.root?.let { listOf(it.leftChild?.key, it.rightChild?.key) })
    }

    @Test
    fun remove() {
        treeWithData.remove(6)
        assertEquals(7, treeWithData.root?.let { it.rightChild?.key })
        assertEquals(
            listOf(5, 8),
            treeWithData.root?.rightChild.let { listOf(it?.leftChild?.key, it?.rightChild?.key) }
        )
    }

    @Test
    fun get() {
        assertEquals("g", treeWithData[7])
    }

    @Test
    fun isEmpty() {
        tree.remove(5)
        assertEquals(true, tree.isEmpty())
    }

    @Test
    fun containsKey() {
        assertEquals(true, treeWithData.containsKey(5))
    }

    @Test
    fun containsValue() {
        assertEquals(true, treeWithData.containsValue("g"))
    }
}
