package com.jabota31.sos.screens

import com.badlogic.gdx.graphics.Texture
import com.jabota31.sos.SwordOfSouls
import com.jabota31.sos.components.GraphicComponent
import com.jabota31.sos.components.TransformComponent
import ktx.ashley.entity
import ktx.ashley.get
import ktx.ashley.with
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import ktx.graphics.use

class FirstScreen(game: SwordOfSouls) : SwordOfSoulsScreen(game) {
    private val image = Texture("logo.png".toInternalFile()).apply {
        setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
    }

    private val player = engine.entity {
        with<TransformComponent>() {
            position.set(1f, 1f, 0f)
            size.set(4.4f, 1.6f)
        }
        with<GraphicComponent>() {
            sprite.run {
                setRegion(image)
            }
        }
    }

    override fun resize(width: Int, height: Int) {
        gameViewport.update(width, height, true)
    }

    override fun render(delta: Float) {
        engine.update(delta)
        gameViewport.apply()

        batch.use(gameViewport.camera.combined) {
            batch
            player[GraphicComponent.mapper]?.let { graphic ->
                player[TransformComponent.mapper]?.let { transform ->
                    graphic.sprite.run {
                        rotation = transform.rotationDeg
                        setBounds(transform.position.x, transform.position.y, transform.size.x, transform.size.y)
                        draw(batch)
                    }
                }
            }
        }
    }

    override fun dispose() {
        image.disposeSafely()
        batch.disposeSafely()
    }
}