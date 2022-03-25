package homeworks.homework1

object Program {
    private const val IS_RUNNING = true
    private const val ENDED = false

    private var status = IS_RUNNING

    fun isRunning(): Boolean {
        return status == IS_RUNNING
    }

    fun endProgram() {
        status = ENDED
    }
}
