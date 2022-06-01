package retests.retest1

import org.jsoup.nodes.Document
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class ModelKtTest {
    @Test
    fun getRandomTest() {
        assertTrue { getRandom() != "" }
    }

    @Test
    fun getLastTest() {
        assertTrue { getLast() != "" }
    }

    @Test
    fun getBestTest() {
        assertTrue { getBest() != "" }
    }

    @Test
    fun getQuotesTest() {
        val g = Document("0")
        g.html("<div class='quote'>Hello world</div>")
        assertEquals("1) Hello world", getQuotes(g))
    }
}
