package io.github.mwttg.games.platformer

import io.github.mwttg.games.platformer.game.GameLoop
import io.github.mwttg.games.platformer.opengl.CleanUp
import io.github.mwttg.games.platformer.opengl.GameWindow
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main() {
    val config = ConfigurationFactory.createOpenGlConfiguration()
    logger.info { config.prettyFormat() }
    val id = GameWindow.create(config)

    GameLoop().loop(id)

    CleanUp.purge()
}