package homeworks.homework3

@Suppress("MagicNumber")
fun main() {
    val tree = AVLTree<Int, String>()
    println("Empty tree:\n$tree\n")
    mapOf(1 to "a", 2 to "b", 3 to "c").forEach { (key, value) -> tree.put(key, value) }
    println("Balanced tree:\n$tree")
    tree.remove(2)
    println("Tree with deleted root:\n$tree")
}
