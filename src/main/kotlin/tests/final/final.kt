package tests.final

@Suppress("MagicNumber")
fun main() {
    val searchDepth = 2
    val nThreads = 32
    val articleTitle = "France"
    val wiki = WikiRace(searchDepth, nThreads, articleTitle)
    println(wiki.search(articleTitle, mutableListOf(), searchDepth))
}
