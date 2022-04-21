package homeworks.homework3

@Suppress("MagicNumber")
fun main() {
    val tree = AVLTree<Int, String>()
    println("Empty tree:\n$tree\n")
    for (i in 1..7) {
        tree[i] = "a"
    }
    println("Balanced tree:\n$tree")
    tree.remove(4)
    println("Tree with deleted root:\n$tree")
}
