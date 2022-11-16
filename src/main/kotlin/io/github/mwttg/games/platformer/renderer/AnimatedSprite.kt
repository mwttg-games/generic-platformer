package io.github.mwttg.games.platformer.renderer

import io.github.mwttg.games.platformer.opengl.Mesh
import org.joml.Matrix4f
import org.lwjgl.opengl.GL41

data class AnimatedSprite(val spriteFrames: Sprite, val timing: Timing) : Drawable {

    override fun draw(model: Matrix4f, view: Matrix4f, projection: Matrix4f) {
        GL41.glBindVertexArray(spriteFrames.vertexArrayObjectId)
        GL41.glUseProgram(spriteFrames.shaderProgramId)
        spriteFrames.uniforms.upload(model, view, projection, spriteFrames.textureId)
        val first = getFirstVertexOfCurrentSprite()
        GL41.glDrawArrays(GL41.GL_TRIANGLES, first, 6)
    }

    private fun getFirstVertexOfCurrentSprite() = timing.computeCurrentFrame() * 6

    companion object {

        fun create(mesh: Mesh, textureId: Int, delaysInMs: List<Int>): AnimatedSprite =
            AnimatedSprite(
                spriteFrames = Sprite.create(mesh, textureId),
                timing = Timing.create(delaysInMs)
            )
    }
}

