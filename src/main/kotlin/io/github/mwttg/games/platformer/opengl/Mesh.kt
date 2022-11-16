package io.github.mwttg.games.platformer.opengl

data class Mesh(val positions: FloatArray, val uvCoordinates: FloatArray) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Mesh) return false

        if (!positions.contentEquals(other.positions)) return false
        if (!uvCoordinates.contentEquals(other.uvCoordinates)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = positions.contentHashCode()
        result = 31 * result + uvCoordinates.contentHashCode()
        return result
    }

    companion object {

        fun createSpriteMesh(width: Float, height: Float): Mesh =
            Mesh(
                positions = createPositions(width, height),
                uvCoordinates = createTextureCoordinates()
            )

        private fun createPositions(width: Float, height: Float): FloatArray =
            floatArrayOf(
                width, 0.0f, 0.0f,
                0.0f, height, 0.0f,
                0.0f, 0.0f, 0.0f,
                width, 0.0f, 0.0f,
                width, height, 0.0f,
                0.0f, height, 0.0f
            )

        private fun createTextureCoordinates(index: Int = 0, maxFrames: Int = 1): FloatArray {
            val spriteWidth = 1.0f / maxFrames
            val left = spriteWidth * index
            val right = left + spriteWidth

            return floatArrayOf(
                right, 0.0f,
                left, 1.0f,
                left, 0.0f,
                right, 0.0f,
                right, 1.0f,
                left, 1.0f
            )
        }
    }
}
