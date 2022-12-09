package io.github.mwttg.games.platformer.renderer

data class Timing(
    val delaysInMs: List<Int>,
    val maxFrames: Int,
    var currentFrame: Int,
    var lastTick: Long,
) {
    fun computeCurrentFrame(): Int {
        val now = System.currentTimeMillis()
        if (now - lastTick > delaysInMs[currentFrame]) {
            lastTick = System.currentTimeMillis()
            incCurrentFrame()
        }

        return currentFrame
    }

    private fun incCurrentFrame() {
        currentFrame++
        if (currentFrame >= maxFrames) {
            currentFrame = 0
        }
    }

    companion object {

        fun create(delaysInMs: List<Int>): Timing =
            Timing(
                delaysInMs = delaysInMs,
                maxFrames = delaysInMs.size,
                currentFrame = 0,
                lastTick = System.currentTimeMillis()
            )
    }
}
