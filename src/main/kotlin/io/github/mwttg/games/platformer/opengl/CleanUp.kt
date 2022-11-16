package io.github.mwttg.games.platformer.opengl

import mu.KotlinLogging
import org.lwjgl.glfw.Callbacks
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL41

private val logger = KotlinLogging.logger {}

object CleanUp {

    private var gameWindowId = 0L
    private val vertexBufferObjectIds = mutableListOf<Int>()
    private val vertexArrayObjectIds = mutableListOf<Int>()

    fun purge() {
        logger.info { "Start clean-up process ..." }

        logger.debug { "    Deleting VertexArrayObjects" }
        vertexArrayObjectIds.forEach(GL41::glDeleteVertexArrays)
        logger.debug { "    Deleting VertexBufferObjects" }
        vertexBufferObjectIds.forEach(GL41::glDeleteBuffers)
        logger.debug { "    Destroying GameWindow" }
        destroyGameWindow()

        logger.info { "... Finished clean-up process" }
    }

    private fun destroyGameWindow() {
        Callbacks.glfwFreeCallbacks(gameWindowId)
        GLFW.glfwSetErrorCallback(null)?.free()
        GLFW.glfwDestroyWindow(gameWindowId)
        GLFW.glfwTerminate()
    }

    fun setGameWindowId(id: Long) {
        gameWindowId = id
    }

    fun addVertexBufferObjectId(id: Int) {
        vertexBufferObjectIds.add(id)
    }

    fun addVertexArrayObjectId(id: Int) {
        vertexArrayObjectIds.add(id)
    }
}