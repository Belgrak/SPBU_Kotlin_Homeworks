package homeworks.homework5

@Suppress("MagicNumber")
fun main() {
    val firstMatrix = mutableListOf(
        mutableListOf(1, 2, 3),
        mutableListOf(4, 5, 6)
    )
    val secondMatrix = mutableListOf(
        mutableListOf(1, 2, 3),
        mutableListOf(4, 5, 6),
        mutableListOf(7, 8, 9)
    )
    val result = multiplyMatrix(firstMatrix, secondMatrix)
    println(result)
}
