package homeworks.homework1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class PerformedCommandStorageTest {
    private val commandStorage = PerformedCommandStorage()

    @BeforeEach
    fun setup() {
        for (i in 0..10) {
            commandStorage.addAction(AddLast(commandStorage.listOfInts, i))
        }
    }

    @Test
    fun addLastElements() {
        assertEquals(listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10), commandStorage.listOfInts)
    }

    @Test
    fun addFirstElement() {
        commandStorage.addAction(AddFirst(commandStorage.listOfInts, -1))
        assertEquals(listOf(-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10), commandStorage.listOfInts)
    }

    @Test
    fun shift() {
        commandStorage.addAction(Shift(commandStorage.listOfInts, 0, 10))
        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0), commandStorage.listOfInts)
    }

    @Test
    fun reverseShift() {
        commandStorage.addAction(Shift(commandStorage.listOfInts, 0, 10))
        commandStorage.removeAction()
        assertEquals(listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10), commandStorage.listOfInts)
    }

    @Test
    fun removeFirstElement() {
        commandStorage.addAction(AddFirst(commandStorage.listOfInts, -1))
        commandStorage.removeAction()
        assertEquals(listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10), commandStorage.listOfInts)
    }

    @Test
    fun shiftOutOfRange() {
        val exception = assertFailsWith<IllegalArgumentException> {
            commandStorage.addAction(
                Shift(
                    commandStorage.listOfInts,
                    0, 100
                )
            )
        }
        assertEquals(exception.message, "Index/Indices out of range")
    }

    @Test
    fun reverseFromEmptyList() {
        val exception = assertFailsWith<IllegalArgumentException> {
            PerformedCommandStorage().removeAction()
        }
        assertEquals(exception.message, "There aren't any actions")
    }
}
