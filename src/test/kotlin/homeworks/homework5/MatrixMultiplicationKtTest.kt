package homeworks.homework5

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

internal class MatrixMultiplicationKtTest {
    @ParameterizedTest
    @MethodSource("getMatrixData")
    fun multiplication(
        firstMatrix: MutableList<MutableList<Int>>,
        secondMatrix: MutableList<MutableList<Int>>,
        expected: MutableList<MutableList<Int>>
    ) {
        assertEquals(expected, multiplyMatrix(firstMatrix, secondMatrix))
    }

    @ParameterizedTest
    @MethodSource("getMultiplyErrorData")
    fun multiplicationError(
        firstMatrix: MutableList<MutableList<Int>>,
        secondMatrix: MutableList<MutableList<Int>>
    ) {
        val error = assertThrows<IllegalArgumentException> { multiplyMatrix(firstMatrix, secondMatrix) }
        assertEquals(error.message, "Incorrect matrices size")
    }

    companion object {
        @JvmStatic
        fun getMatrixData() = listOf(
            Arguments.of(
                mutableListOf<MutableList<Int>>(),
                mutableListOf<MutableList<Int>>(),
                mutableListOf<MutableList<Int>>()
            ),
            Arguments.of(
                mutableListOf(
                    mutableListOf(1, 2)
                ),
                mutableListOf(
                    mutableListOf(1),
                    mutableListOf(2)
                ),
                mutableListOf(mutableListOf(5))
            ),
            Arguments.of(
                mutableListOf(
                    mutableListOf(1, 2),
                    mutableListOf(3, 4)
                ),
                mutableListOf(
                    mutableListOf(1),
                    mutableListOf(2)
                ),
                mutableListOf(
                    mutableListOf(5),
                    mutableListOf(11)
                )
            )
        )

        @JvmStatic
        fun getMultiplyErrorData() = listOf(
            Arguments.of(
                mutableListOf<MutableList<Int>>(),
                mutableListOf(mutableListOf(1))
            ),
            Arguments.of(
                mutableListOf(
                    mutableListOf(1, 2)
                ),
                mutableListOf(
                    mutableListOf(1, 2, 3)
                )
            )
        )
    }
}
