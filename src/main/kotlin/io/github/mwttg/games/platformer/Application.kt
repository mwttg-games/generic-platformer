package io.github.mwttg.games.platformer

import io.github.mwttg.games.platformer.opengl.CleanUp
import io.github.mwttg.games.platformer.opengl.GameWindow
import mu.KotlinLogging
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL41

private val logger = KotlinLogging.logger {}

fun main() {
    val config = ConfigurationFactory.createOpenGlConfiguration()
    logger.info { config.prettyFormat() }
    val id = GameWindow.create(config)

    loop(id)

    CleanUp.purge()
}

private fun loop(gameWindowId: Long) {
    while (!GLFW.glfwWindowShouldClose(gameWindowId)) {
        GL41.glClear(GL41.GL_COLOR_BUFFER_BIT or GL41.GL_DEPTH_BUFFER_BIT)
        GLFW.glfwSwapBuffers(gameWindowId)
        GLFW.glfwPollEvents()
    }
}