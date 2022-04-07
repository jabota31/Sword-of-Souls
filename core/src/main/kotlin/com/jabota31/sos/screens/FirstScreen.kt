package com.jabota31.sos.screens

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.jabota31.sos.SwordOfSouls
import com.jabota31.sos.components.*
import com.jabota31.sos.enums.ANIMATION
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
                type = ANIMATION.WALK_DOWN
            }
            with<GraphicComponent>()
            with<MoveComponent> {
                speed.set(Vector2.Zero)
            }
            with<UserInputComponent>()
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