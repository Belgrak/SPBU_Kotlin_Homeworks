package tests.final

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class WikiRace(var searchDepth: Int, var nThreads: Int, val articleTitle: String) {
    var path = mutableListOf<String>()
    private val depthToTitles = mutableMapOf<Int, ArrayDeque<Pair<Int, String>>>().also {
        for (i in 0 until searchDepth) {
            it[i] = ArrayDeque()
        }
    }

    companion object Settings {
        const val RANDOM_URL = "https://en.wikipedia.org/wiki/Special:Random"
        const val LINKS_ON_PAGE =
            "https://en.wikipedia.org/w/api.php?action=query&prop=links&pllimit=max&format=xml&titles="
        const val ARTICLE_FOR_SEARCH = "Adolf Hitler"
    }

    fun addNewTitles(articleTitle: String, depth: Int, parentIndex: Int): Boolean {
        if (depth == 0) {
            return false
        }

        val document: Document = Jsoup.connect(LINKS_ON_PAGE + articleTitle).get()
        val linksTitles = document.select("pl").map { Pair(parentIndex, it.attr("title").toString()) }
        depthToTitles[searchDepth - depth]?.addAll(linksTitles)
        return ARTICLE_FOR_SEARCH in linksTitles.map { it.second }
    }

    private fun creatingPath(articleTitle: String, depthIndex: Int, parentIndex: Int) {
        path.add(0, articleTitle)
        var current = Pair(parentIndex, articleTitle)
        for (i in 1..depthIndex) {
            current = depthToTitles[depthIndex - i]?.get(current.first) ?: Pair(0, "")
            path.add(0, current.second)
        }
    }

    @Suppress("ReturnCount", "LoopWithTooManyJumpStatements")
    fun search(articleTitle: String, visited: MutableList<String>, depth: Int): String {
        visited.add(articleTitle)
        val doc = Jsoup.connect(LINKS_ON_PAGE + articleTitle).get()
        val linksTitles = doc.select("pl").map { Pair(0, it.attr("title").toString()) }
        val stack = ArrayDeque(linksTitles)
        depthToTitles[0]?.addAll(stack)

        when {
            ARTICLE_FOR_SEARCH in linksTitles.map { it.second } -> {
                return (LINKS_ON_PAGE + articleTitle)
            }
            depth == 0 -> return "Not found"
        }

        var result = false
        var found = Triple(0, 0, "")
        runBlocking {
            for (j in 0 until searchDepth) {
                if (result) {
                    break
                }

                for ((n, i) in depthToTitles[j]?.withIndex() ?: emptyList()) {
                    if (i.second in visited) {
                        continue
                    }

                    val newElements = async { addNewTitles(i.second, depth - 1, n) }
                    result = newElements.await()

                    if (result) {
                        found = Triple(j, i.first, i.second)
                        break
                    }
                }
            }
        }
        if (result) {
            creatingPath(found.third, found.first, found.second)
            path.add(0, articleTitle)
            path.add(path.size, ARTICLE_FOR_SEARCH)
        }
        return path.joinToString(" -> ")
    }
}
