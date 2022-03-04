package homeworks.homework1

fun sieveOfEratosthenes(n: Int): List<Int> {
    require(n >= 0) { "Number must be non-negative, was $n" }
    if (n == 0) {
        return emptyList()
    }
    val isPrimeNumber = BooleanArray(n + 1) { true }.toTypedArray()
    isPrimeNumber[0] = false
    isPrimeNumber[1] = false
    for (i in 2..n) {
        if (isPrimeNumber[i]) {
            for (j in (i * i)..n step i) {
                isPrimeNumber[j] = false
            }
        }
    }
    return isPrimeNumber.mapIndexedNotNull { index, b -> if (b) index else null }
}

fun main() {
    print("Please enter a number: ")
    val num = readLine()?.toIntOrNull()
    if (num == null) {
        print("Integer was expected")
        return
    }
    try {
        val result = sieveOfEratosthenes(num)
        if (result.isEmpty()) {
            print("There aren't any prime numbers(")
        } else {
            print("Prime numbers: " + result.joinToString())
        }
    } catch (e: IllegalArgumentException) {
        print(e.message)
        return
    }
}
