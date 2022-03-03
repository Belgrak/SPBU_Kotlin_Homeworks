package homeworks.homework1

fun sieveOfEratosthenes(n: Int): List<Int> {
    require(n >= 0) { "Number must be non-negative, was $n" }
    if (n == 0) return emptyList()
    val isPrimeNumber = BooleanArray(n + 1) { true }
    isPrimeNumber[0] = false
    isPrimeNumber[1] = false
    for (i in 2..n) {
        if (isPrimeNumber[i] && i * i <= n)
            for (j in i * i..n step i)
                isPrimeNumber[j] = false
    }
    return isPrimeNumber.flatMapIndexed { index, b -> if (b) listOf(index) else emptyList() }
}

fun main() {
    print("Please enter a number: ")
    try {
        val num = readLine()?.toIntOrNull() ?: throw TypeCastException("Integer was expected")
        val result = sieveOfEratosthenes(num)
        print(if (result.isEmpty()) "There aren't any prime numbers(" else "Prime numbers: " + result.joinToString())
    } catch (e: TypeCastException) {
        print(e.message)
        return
    }
}
