package tests.test1

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

internal class QueueTest {
    var queue = Queue<String, Int>()

    @Test
    fun enqueue() {
        queue.enqueue("a", 1)
    }

    @Test
    fun peekEmptyQueue() {
        val e = assertThrows<IllegalArgumentException> { queue.peek() }
        assertEquals(e.message, "map is empty")
    }

    @Test
    fun rollEmptyQueue() {
        val e = assertThrows<IllegalArgumentException> { queue.roll() }
        assertEquals(e.message, "map is empty")
    }

    @Test
    fun removeEmptyQueue() {
        val e = assertThrows<IllegalArgumentException> { queue.remove() }
        assertEquals(e.message, "map is empty")
    }

    @Test
    fun peek() {
        queue.enqueue("a", 1)
        queue.enqueue("b", 2)
        queue.enqueue("a", 1)
        assertEquals(queue.peek(), "b")
    }

    @Test
    fun roll() {
        queue.enqueue("a", 1)
        queue.enqueue("b", 2)
        queue.enqueue("a", 1)
        assertEquals(queue.roll(), "b")
    }

    @Test
    fun remove() {
        queue.enqueue("a", 1)
        queue.enqueue("b", 2)
        queue.remove()
        assertEquals(queue.peek(), "a")
    }
}
