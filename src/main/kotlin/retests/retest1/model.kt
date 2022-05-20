package retests.retest1

import org.jsoup.Jsoup

const val LAST_URL = "http://bashorg.org/"
const val RANDOM_URL = "http://bashorg.org/casual"
const val BEST_URL = "http://bashorg.org/best"

fun getRandom(): String {
    val doc = Jsoup.connect(RANDOM_URL).get()
    val data = doc.select(".q div")
    if (data.size < 2) {
        return data.firstOrNull()?.text() ?: ""
    }
    return data[1].text()
}

fun getLast(): String {
    val doc = Jsoup.connect(LAST_URL).get()
    val data = doc.select(".quote").mapIndexed { index, element -> "${index + 1}) ${element.text()}" }
    return data.joinToString("\n\n")
}

fun getBest(): String {
    val doc = Jsoup.connect(BEST_URL).get()
    val data = doc.select(".quote").mapIndexed { index, element -> "${index + 1}) ${element.text()}" }
    return data.joinToString("\n\n")
}
