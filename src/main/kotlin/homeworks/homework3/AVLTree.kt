package homeworks.homework3

class AVLTree<K : Comparable<K>, V>(
    override val entries: MutableSet<Map.Entry<K, V>> = mutableSetOf(),
    override val keys: MutableSet<K> = mutableSetOf(),
    override var size: Int = 0,
    override val values: MutableCollection<V> = mutableListOf()
) : Map<K, V> {
    var root: Node<K, V>? = null
        private set

    private fun insert(node: Node<K, V>?, key: K, value: V): Node<K, V> {
        node ?: return Node(key, value)
        when {
            node.key > key -> node.leftChild = insert(node.leftChild, key, value)
            node.key < key -> node.rightChild = insert(node.rightChild, key, value)
            else -> node.value = value
        }
        node.updateHeight()
        return node.balance()
    }

    fun put(key: K, value: V) {
        root = insert(root, key, value)
        keys.add(key)
        values.add(value)
        size++
        entries.add(Node(key, value))
    }

    private fun getNewRootFromRightSubtree(rootNode: Node<K, V>): Node<K, V> {
        val newRoot = rootNode.rightChild?.getMin() ?: rootNode
        newRoot.leftChild = rootNode.leftChild
        newRoot.rightChild = rootNode.rightChild?.removeMin()
        newRoot.updateHeight()
        return newRoot.balance()
    }

    private fun deleteNode(rootNode: Node<K, V>, key: K): Node<K, V>? {
        when {
            rootNode.key > key -> rootNode.leftChild = rootNode.leftChild?.let { deleteNode(it, key) }
            rootNode.key < key -> rootNode.rightChild = rootNode.rightChild?.let { deleteNode(it, key) }
            else -> return rootNode.rightChild?.let { getNewRootFromRightSubtree(rootNode) }
        }
        rootNode.updateHeight()
        return rootNode.balance()
    }

    fun remove(key: K): Pair<K?, V?> {
        if (!containsKey(key))
            return Pair(null, null)
        val pair = Pair(key, get(key))
        root = root?.let { deleteNode(it, key) }
        keys.remove(pair.first)
        values.remove(pair.second)
        size--
        entries.remove(entries.find { it.key == pair.first && it.value == pair.second })
        root?.updateHeight()
        return pair
    }

    override fun containsKey(key: K): Boolean {
        return key in keys
    }

    override fun containsValue(value: V): Boolean {
        return value in values
    }

    override fun get(key: K): V? {
        var current: Node<K, V>? = root
        while (current != null) {
            current = when {
                current.key == key -> return current.value
                current.key > key -> current.leftChild
                else -> current.rightChild
            }
        }
        return null
    }

    override fun isEmpty() = size == 0

    override fun toString(): String {
        return root?.toString(0) ?: "Empty tree"
    }
}
