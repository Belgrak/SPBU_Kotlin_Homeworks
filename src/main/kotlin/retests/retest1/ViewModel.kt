package retests.retest1

import kotlinx.coroutines.runBlocking

class ViewModel {
    fun showBestQuotes() = runBlocking { return@runBlocking getBest() }

    fun showLastQuotes() = runBlocking { return@runBlocking getLast() }

    fun showRandomQuote() = runBlocking { return@runBlocking getRandom() }
}
