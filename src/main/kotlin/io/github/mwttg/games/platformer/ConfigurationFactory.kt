package io.github.mwttg.games.platformer

import io.github.mwttg.games.platformer.opengl.OpenGlConfiguration

object ConfigurationFactory {

    fun createOpenGlConfiguration() = OpenGlConfiguration(
        majorVersion = 4,
        minorVersion = 1,
        title = "Generic-2D-Platformer",
        screenWidth = 1920,
        screenHeight = 1080,
        fullscreen = true,
        vSync = true,
        wireFrane = false,
        clearColorRed = 0.25f,
        clearColorGreen = 0.25f,
        clearColorBlue = 0.25f,
        nearPlane = 0.01f,
        farPlane = 100.0f
    )
}