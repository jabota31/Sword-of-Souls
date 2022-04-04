package com.jabota31.sos.screens

import com.badlogic.gdx.graphics.Texture
import com.jabota31.sos.SwordOfSouls
import com.jabota31.sos.components.AnimationComponent
import com.jabota31.sos.components.AnimationType
import com.jabota31.sos.components.GraphicComponent
import com.jabota31.sos.components.TransformComponent
import ktx.ashley.entity
import ktx.ashley.with
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile

class FirstScreen(game: SwordOfSouls) : SwordOfSoulsScreen(game) {
    private val image = Texture("logo.png".toInternalFile()).apply {
        setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
    }

    override fun show() {
        engine.entity {
            with<TransformComponent>() {
                position.set(3f, 3f, 0f)
                size.set(1f, 1f)
            }
            with<AnimationComponent> {
                type = AnimationType.WALK
            }
            with<GraphicComponent>()
        }
    }

    override fun render(delta: Float) {
        engine.update(delta)
    }

    override fun dispose() {
        image.disposeSafely()
        batch.disposeSafely()
    }
}