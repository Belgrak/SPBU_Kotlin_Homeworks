package tests.test1

import java.util.TreeMap

class Bor {
    val tree = TreeMap<String, List<Char>>()

    fun add(string: String): Boolean {
        val list = mutableListOf<Char>()
        for (i in string) {
            if (tree.containsKey(i.toString())) {
                list.add(i)
                continue
            }
        }
        TODO()
    }
}
