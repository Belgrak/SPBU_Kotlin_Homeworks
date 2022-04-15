package homeworks.homework3

import java.lang.Integer.max

class Node<K : Comparable<K>, V>(
    override val key: K,
    override var value: V,
    var leftChild: Node<K, V>? = null,
    var rightChild: Node<K, V>? = null
) : Map.Entry<K, V> {
    var height = 0

    companion object SettingsAndConsts {
        const val RIGHT_SUB_BALANCE = 2
        const val LEFT_SUB_BALANCE = -2
        const val SEPARATOR_SIGN = "--"
    }

    init {
        height = max(leftChild?.height ?: 0, rightChild?.height ?: 0) + 1
    }

    fun getMin(): Node<K, V> {
        return this.leftChild?.getMin() ?: this
    }

    private fun getBalanceFactor(): Int {

        return (rightChild?.height ?: 0) - (leftChild?.height ?: 0)
    }

    fun updateHeight() {
        val heightLeft = leftChild?.height ?: 0
        val heightRight = rightChild?.height ?: 0
        height = max(heightLeft, heightRight) + 1
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
        if (this.rightChild?.getBalanceFactor() == -1) {
            this.rightChild = this.rightChild?.rotateRight() ?: return this
        }
        return this.rotateLeft()
    }

    private fun leftSubBalance(): Node<K, V> {
        if (this.leftChild?.getBalanceFactor() == -1) {
            this.leftChild = this.leftChild?.rotateLeft() ?: return this
        }
        return this.rotateRight()
    }

    fun balance(): Node<K, V> {

        return when (this.getBalanceFactor()) {
            RIGHT_SUB_BALANCE -> this.rightSubBalance()
            LEFT_SUB_BALANCE -> this.leftSubBalance()
            else -> this
        }
    }

    fun removeMin(): Node<K, V>? {
        this.leftChild = this.leftChild?.removeMin() ?: return this.rightChild
        this.updateHeight()
        return this.balance()
    }

    fun toString(separatorCount: Int): String {
        var nodeAsString = ""
        if (this.rightChild != null) {
            nodeAsString += this.rightChild?.toString(separatorCount + 2)
        }

        nodeAsString += "${SEPARATOR_SIGN.repeat(separatorCount)}($key, $value)\n"

        if (this.leftChild != null) {
            nodeAsString += this.leftChild?.toString(separatorCount + 2)
        }
        return nodeAsString
    }
}
