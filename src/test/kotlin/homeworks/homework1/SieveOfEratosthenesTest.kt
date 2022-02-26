package homeworks.homework1

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

internal class SieveOfEratosthenesTest {

    @Test
    fun correctTest() {
        assertArrayEquals(intArrayOf(2, 3, 5), sieveOfEratosthenes(5).toIntArray())
    }

    @Test
    fun testWithZero() {
        assertArrayEquals(intArrayOf(), sieveOfEratosthenes(0).toIntArray())
    }

    @Test
    fun testWithNegativeNumber() {
        assertArrayEquals(intArrayOf(), sieveOfEratosthenes(-1).toIntArray())
    }
}
