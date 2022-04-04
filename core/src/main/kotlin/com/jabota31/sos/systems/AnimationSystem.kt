package com.jabota31.sos.systems

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntityListener
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.utils.GdxRuntimeException
import com.jabota31.sos.components.Animation2D
import com.jabota31.sos.components.AnimationComponent
import com.jabota31.sos.components.AnimationType
import com.jabota31.sos.components.GraphicComponent
import ktx.ashley.allOf
import ktx.ashley.get
import java.util.EnumMap

class AnimationSystem(private val atlas: TextureAtlas) : IteratingSystem(
    allOf(AnimationComponent::class, GraphicComponent::class).get()
), EntityListener {
    private val animationCache = EnumMap<AnimationType, Animation2D>(AnimationType::class.java)

    override fun addedToEngine(engine: Engine) {
        super.addedToEngine(engine)
        engine.addEntityListener(family, this)
    }

    override fun removedFromEngine(engine: Engine) {
        super.removedFromEngine(engine)
        engine.removeEntityListener(this)
    }

    override fun entityRemoved(entity: Entity) = Unit

    override fun entityAdded(entity: Entity) {
        entity[AnimationComponent.mapper]?.let { animationComponent ->
            animationComponent.animation = getAnimation(animationComponent.type)
            val frame = animationComponent.animation.getKeyFrame(animationComponent.stateTime)
            entity[GraphicComponent.mapper]?.setSpriteRegion(frame)
        }
    }

    private fun getAnimation(type: AnimationType): Animation2D {
        var animation = animationCache[type]
        if (animation == null) {
            var regions = atlas.findRegions(type.atlasKey)
            if (regions.isEmpty) {
                regions = atlas.findRegions("error")
                if (regions.isEmpty) {
                    throw GdxRuntimeException("No animation found for type: $type")
                }
            }
            animation = Animation2D(type, regions, type.playMode, type.speedRate)
            animationCache[type] = animation
        }
        return animation
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val animationComponent = entity[AnimationComponent.mapper]
        require(animationComponent != null) { "AnimationComponent not found" }
        val graphicComponent = entity[GraphicComponent.mapper]
        require(graphicComponent != null) { "GraphicComponent not found" }

        if (animationComponent.type == AnimationType.IDLE) return

        if (animationComponent.type == animationComponent.animation.type) {
            animationComponent.stateTime += deltaTime
        } else {
            animationComponent.stateTime = 0f
            animationComponent.animation = getAnimation(animationComponent.type)
        }

        val frame = animationComponent.animation.getKeyFrame(animationComponent.stateTime)
        graphicComponent.setSpriteRegion(frame)
    }
}
