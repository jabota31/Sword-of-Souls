package com.jabota31.sos.components

import com.badlogic.gdx.utils.Array
import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Pool.Poolable
import com.jabota31.sos.enums.ANIMATION
import ktx.ashley.mapperFor

private const val DEFAULT_FRAME_DURATION = 1 / 20f

class Animation2D(
    val type: ANIMATION,
    keyFrames: Array<out TextureRegion>,
    playMode: PlayMode = PlayMode.NORMAL,
    speedRate: Float = 1f
) : Animation<TextureRegion>(DEFAULT_FRAME_DURATION / speedRate, keyFrames, playMode) {}

class AnimationComponent : Component, Poolable {
    var type = ANIMATION.IDLE
    var stateTime = 0f
    lateinit var animation: Animation2D

    override fun reset() {
        type = ANIMATION.IDLE
    }

    companion object {
        val mapper = mapperFor<AnimationComponent>()
    }
}