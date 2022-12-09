package io.github.mwttg.games.platformer.renderer

import io.github.mwttg.games.platformer.opengl.Mesh
import io.github.mwttg.games.platformer.opengl.ShaderProgram
import io.github.mwttg.games.platformer.opengl.Uniform
import io.github.mwttg.games.platformer.opengl.VertexArrayObject
import org.joml.Matrix4f
import org.lwjgl.opengl.GL41

data class Sprite(
    val vertexArrayObjectId: Int,
    val textureId: Int,
    val shaderProgramId: Int,
    val uniforms: Uniform,
) : Drawable {

    override fun draw(model: Matrix4f, view: Matrix4f, projection: Matrix4f) {
        GL41.glBindVertexArray(vertexArrayObjectId)
        GL41.glUseProgram(shaderProgramId)
        uniforms.upload(model, view, projection, textureId)
        GL41.glDrawArrays(GL41.GL_TRIANGLES, 0, 6)
    }

    companion object {

        fun create(mesh: Mesh, textureId: Int): Sprite {
            val vertexArrayObjectId = VertexArrayObject.create(mesh.positions, mesh.uvCoordinates)
            val shaderProgramId = ShaderProgram.defaultProgram
            ShaderProgram.validate(shaderProgramId)
            val uniforms = Uniform(shaderProgramId)

            return Sprite(vertexArrayObjectId, textureId, shaderProgramId, uniforms)
        }
    }
}
