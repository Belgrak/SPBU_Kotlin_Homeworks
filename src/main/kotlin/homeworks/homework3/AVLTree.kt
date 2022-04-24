package homeworks.homework3

class AVLTree<K : Comparable<K>, V> : MutableMap<K, V> {
    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = (root?.traverse { node -> Entry(node.key, node.value) })?.toMutableSet() ?: mutableSetOf()
    override val keys: MutableSet<K>
        get() = (root?.traverse { node -> node.key })?.toMutableSet() ?: mutableSetOf()
    override val values: MutableCollection<V>
        get() = (root?.traverse { node -> node.value })?.toMutableList() ?: mutableListOf()
    override var size: Int = 0
        private set
    var root: Node<K, V>? = null
        private set

    private fun insert(node: Node<K, V>?, key: K, value: V): Node<K, V> {
        node ?: return Node(key, value)
        when {
            key < node.key -> node.leftChild = insert(node.leftChild, key, value)
            key > node.key -> node.rightChild = insert(node.rightChild, key, value)
            else -> node.value = value
        }
        node.updateHeight()
        return node.rebalance()
    }

    override fun put(key: K, value: V): V? {
        if (!containsKey(key)) {
            size++
        }
        root = insert(root, key, value)
        return value
    }

    override fun remove(key: K): V? {
        if (!containsKey(key)) {
            return null
        }
        val value = get(key)
        root = root?.deleteNode(key)
        size--
        root?.updateHeight()
        return value
    }

    override fun containsKey(key: K) = key in keys

    override fun containsValue(value: V) = value in values

    override fun get(key: K): V? {
        var current: Node<K, V>? = root
        while (current != null) {
            current = when {
                current.key == key -> return current.value
                key < current.key -> current.leftChild
                else -> current.rightChild
            }
        }
        return null
    }

    override fun isEmpty() = size == 0

    override fun toString() = root?.toString(0) ?: "Empty tree"

    override fun clear() {
        root = null
        size = 0
    }

    override fun putAll(from: Map<out K, V>) = from.forEach { (key, value) -> put(key, value) }
}
