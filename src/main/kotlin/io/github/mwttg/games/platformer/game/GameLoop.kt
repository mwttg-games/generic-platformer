package io.github.mwttg.games.platformer.game

import io.github.mwttg.games.platformer.ConfigurationFactory
import io.github.mwttg.games.platformer.opengl.Mesh
import io.github.mwttg.games.platformer.opengl.Texture
import io.github.mwttg.games.platformer.renderer.Drawable
import io.github.mwttg.games.platformer.renderer.Sprite
import org.joml.Matrix4f
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL41

class GameLoop {

    private val sprite: Drawable
    private val model: Matrix4f = Matrix4f()
    private val view: Matrix4f = ConfigurationFactory.createViewMatrix()
    private val projection: Matrix4f = ConfigurationFactory.createProjectionMatrix()

    init {
        val textureId = Texture.create("./data/level.png")
        val mesh = Mesh.createSpriteMesh(20.0f, 10.0f)
        sprite = Sprite.create(mesh, textureId)
    }

    fun loop(gameWindowId: Long) {
        while (!GLFW.glfwWindowShouldClose(gameWindowId)) {
            GL41.glClear(GL41.GL_COLOR_BUFFER_BIT or GL41.GL_DEPTH_BUFFER_BIT)
            sprite.draw(model, view, projection)
            GLFW.glfwSwapBuffers(gameWindowId)
            GLFW.glfwPollEvents()
        }
    }
}