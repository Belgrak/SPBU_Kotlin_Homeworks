package homeworks.homework1

fun sieveOfEratosthenes(n: Int): MutableList<Int> {
    val primeNumbers = (2..n).toMutableList()
    val tempList = primeNumbers.toList()
    for (i in tempList) {
        for (j in tempList) {
            if (i in primeNumbers && j in primeNumbers && i != j && j % i == 0)
                primeNumbers.remove(j)
        }
    }
    return primeNumbers
}

fun main() {
    print("Please enter a number: ")
    try {
        val num = readLine()!!.toIntOrNull() ?: throw TypeCastException("Integer was expected")
        print("Prime numbers: " + sieveOfEratosthenes(num).joinToString())
    } catch (e: TypeCastException) {
        print("Integer was expected")
        return
    }
}
