package com.jabota31.sos.screens

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.jabota31.sos.SwordOfSouls
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import ktx.graphics.use

class FirstScreen(game: SwordOfSouls) : SwordOfSoulsScreen(game) {
    private val image = Texture("logo.png".toInternalFile()).apply {
        setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
    }

    private val sprite = Sprite(image).apply {
        setSize(4.4f, 1.6f)
        setPosition(gameViewport.worldWidth / 2 - width / 2, gameViewport.worldHeight / 2 - height / 2)
    }

    override fun resize(width: Int, height: Int) {
        gameViewport.update(width, height, true)
    }

    override fun render(delta: Float) {
        gameViewport.apply()
        batch.use(gameViewport.camera.combined) {
            sprite.draw(it)
        }
    }

    override fun dispose() {
        image.disposeSafely()
        batch.disposeSafely()
    }
}