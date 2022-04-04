package com.jabota31.sos.components

import com.badlogic.gdx.utils.Array
import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Pool.Poolable
import ktx.ashley.mapperFor

private const val DEFAULT_FRAME_DURATION = 1 / 20f

enum class AnimationType(
    val atlasKey: String, val playMode: Animation.PlayMode = Animation.PlayMode.NORMAL, val speedRate: Float = 1f
) {
    IDLE(""),
    WALK("larry_walk_front", Animation.PlayMode.LOOP, 1f)
}

class Animation2D(
    val type: AnimationType,
    keyFrames: Array<out TextureRegion>,
    playMode: PlayMode = PlayMode.NORMAL,
    speedRate: Float = 1f
) : Animation<TextureRegion>(DEFAULT_FRAME_DURATION / speedRate, keyFrames, playMode) {}

class AnimationComponent : Component, Poolable {
    var type = AnimationType.IDLE
    var stateTime = 0f
    lateinit var animation: Animation2D

    override fun reset() {
        type = AnimationType.IDLE
    }

    companion object {
        val mapper = mapperFor<AnimationComponent>()
    }
}