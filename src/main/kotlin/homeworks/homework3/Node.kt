package homeworks.homework3

import java.lang.Integer.max

class Node<K : Comparable<K>, V>(
    val key: K,
    var value: V,
) {
    var leftChild: Node<K, V>? = null
    var rightChild: Node<K, V>? = null
    private var height = 1
    private val balance: Int
        get() = (rightChild?.height ?: 0) - (leftChild?.height ?: 0)
    private val minNode: Node<K, V>
        get() = leftChild?.minNode ?: this

    companion object SettingsAndConsts {
        const val RIGHT_SUB_BALANCE = 2
        const val LEFT_SUB_BALANCE = -2
        const val SEPARATOR_SIGN = "--"
    }

    fun updateHeight() {
        height = max(leftChild?.height ?: 0, rightChild?.height ?: 0) + 1
    }

    private fun rotateRight(): Node<K, V> {
        val rotated = leftChild ?: return this
        leftChild = rotated.rightChild
        rotated.rightChild = this
        updateHeight()
        rotated.updateHeight()
        return rotated
    }

    private fun rotateLeft(): Node<K, V> {
        val rotated = rightChild ?: return this
        rightChild = rotated.leftChild
        rotated.leftChild = this
        updateHeight()
        rotated.updateHeight()
        return rotated
    }

    private fun rightSubBalance(): Node<K, V> {
        if (rightChild?.balance == -1) {
            rightChild = rightChild?.rotateRight() ?: return this
        }
        return this.rotateLeft()
    }

    private fun leftSubBalance(): Node<K, V> {
        if (leftChild?.balance == 1) {
            leftChild = leftChild?.rotateLeft() ?: return this
        }
        return this.rotateRight()
    }

    fun rebalance() = when (this.balance) {
        RIGHT_SUB_BALANCE -> this.rightSubBalance()
        LEFT_SUB_BALANCE -> this.leftSubBalance()
        else -> this
    }

    fun deleteNode(deleteKey: K): Node<K, V>? {
        when {
            deleteKey < key -> leftChild = leftChild?.deleteNode(deleteKey)
            deleteKey > key -> rightChild = rightChild?.deleteNode(deleteKey)
            else -> return rightChild?.let {
                val newRoot = rightChild?.minNode ?: this
                newRoot.rightChild = rightChild?.removeMin()
                newRoot.leftChild = leftChild
                newRoot.updateHeight()
                newRoot.rebalance()
            } ?: leftChild
        }
        updateHeight()
        return rebalance()
    }

    private fun removeMin(): Node<K, V>? {
        leftChild ?: return rightChild
        leftChild = leftChild?.removeMin()
        this.updateHeight()
        return this.rebalance()
    }

    fun toString(separatorCount: Int): String {
        var nodeAsString = ""
        rightChild?.let { nodeAsString += it.toString(separatorCount + 2) }

        nodeAsString += "${SEPARATOR_SIGN.repeat(separatorCount)}($key, $value)\n"

        leftChild?.let { nodeAsString += it.toString(separatorCount + 2) }
        return nodeAsString
    }

    fun <S> traverse(lambda: (Node<K, V>) -> (S)): List<S> {
        return listOf(lambda(this)) +
            (leftChild?.traverse(lambda) ?: emptyList()) + (rightChild?.traverse(lambda) ?: emptyList())
    }
}
