package retests.retest1

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.net.UnknownHostException

const val LAST_URL = "http://bashorg.org/"
const val RANDOM_URL = "http://bashorg.org/casual"
const val BEST_URL = "http://bashorg.org/best"

fun getRandom(): String {
    return try {
        val doc = Jsoup.connect(RANDOM_URL).get()
        val data = doc.select(".q div")
        data.forEach { it.select("br").forEach { element -> element.html("\n") } }
        when {
            data.size < 2 -> data.firstOrNull()?.wholeText() ?: ""
            else -> data[1].wholeText()
        }
    } catch (e: UnknownHostException) {
        "Error handled. ${e.message}"
    }
}

fun getQuotes(doc: Document): String {
    val data = doc.select(".quote")
    data.forEach { it.select("br").forEach { element -> element.html("\n") } }
    val formatResult = data.mapIndexed { index, element -> "${index + 1}) ${element.wholeText()}" }
    return formatResult.joinToString("\n\n")
}

fun getLast(): String {
    return try {
        val doc = Jsoup.connect(LAST_URL).get()
        getQuotes(doc)
    } catch (e: UnknownHostException) {
        "Error handled. ${e.message}"
    }
}

fun getBest(): String {
    return try {
        val doc = Jsoup.connect(BEST_URL).get()
        getQuotes(doc)
    } catch (e: UnknownHostException) {
        "Error handled. ${e.message}"
    }
}
