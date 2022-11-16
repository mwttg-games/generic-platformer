package io.github.mwttg.games.platformer.game

import io.github.mwttg.games.platformer.ConfigurationFactory
import io.github.mwttg.games.platformer.opengl.Mesh
import io.github.mwttg.games.platformer.opengl.Texture
import io.github.mwttg.games.platformer.renderer.AnimatedSprite
import io.github.mwttg.games.platformer.renderer.Drawable
import io.github.mwttg.games.platformer.renderer.Sprite
import org.joml.Matrix4f
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL41

class GameLoop {

    private val sprite: Drawable
    private val model: Matrix4f = Matrix4f()
    private val battery1: Drawable
    private val battery1Model = Matrix4f().translate(18.8f, 8.8f, 0.5f)
    private val battery2: Drawable
    private val battery2Model = Matrix4f().translate(-0.5f, 5.8f, 0.5f)
    private val view: Matrix4f = ConfigurationFactory.createViewMatrix()
    private val projection: Matrix4f = ConfigurationFactory.createProjectionMatrix()

    init {
        sprite = createLevel()
        battery1 = createBattery1()
        battery2 = createBattery2()
    }

    private fun createLevel() =
        Sprite.create(
            mesh = Mesh.createSpriteMesh(20.0f, 10.0f),
            textureId = Texture.create("./data/level.png")
        )

    private fun createBattery1(): AnimatedSprite =
        AnimatedSprite.create(
            mesh = Mesh.createAnimatedSpriteMesh(1.0f, 1.0f, 7),
            textureId = Texture.create("./data/battery1.png"),
            delaysInMs = listOf(1000, 750, 500, 250, 150, 150, 1000)
        )

    private fun createBattery2(): AnimatedSprite =
        AnimatedSprite.create(
            mesh = Mesh.createAnimatedSpriteMesh(4.0f, 4.0f, 9),
            textureId = Texture.create("./data/battery2.png"),
            delaysInMs = listOf(1000, 75, 125, 175, 250, 500, 750, 875, 1000)
        )

    fun loop(gameWindowId: Long) {
        while (!GLFW.glfwWindowShouldClose(gameWindowId)) {
            GL41.glClear(GL41.GL_COLOR_BUFFER_BIT or GL41.GL_DEPTH_BUFFER_BIT)

            sprite.draw(model, view, projection)
            battery1.draw(battery1Model, view, projection)
            battery2.draw(battery2Model, view, projection)

            GLFW.glfwSwapBuffers(gameWindowId)
            GLFW.glfwPollEvents()
        }
    }
}