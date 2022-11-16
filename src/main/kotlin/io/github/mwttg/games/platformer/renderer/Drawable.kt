package io.github.mwttg.games.platformer.renderer

import org.joml.Matrix4f

sealed interface Drawable {

    fun draw(model: Matrix4f, view: Matrix4f, projection: Matrix4f)
}