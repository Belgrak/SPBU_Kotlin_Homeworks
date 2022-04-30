package tests.test1

import java.util.TreeMap

class Queue<E, K : Comparable<K>> {
    private var map = TreeMap<K, MutableList<E>>()

    fun enqueue(element: E, priority: K) {
        if (map[priority] == null) {
            map[priority] = mutableListOf(element)
        } else {
            map[priority]?.add(element)
        }
    }

    fun peek(): E {
        require(!map.isEmpty()) { "map is empty" }
        return map.lastEntry().value.first()
    }

    fun remove() {
        roll()
    }

    fun roll(): E {
        require(!map.isEmpty()) { "map is empty" }
        val element = map.lastEntry().value.removeFirst()
        if (map.lastEntry().value.isEmpty()) {
            map.remove(map.lastEntry().key)
        }
        return element
    }
}
