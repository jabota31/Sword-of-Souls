package com.jabota31.sos.enums

import com.badlogic.gdx.graphics.g2d.Animation

enum class ANIMATION(
    val atlasKey: String,
    val playMode: Animation.PlayMode = Animation.PlayMode.NORMAL,
    val speedRate: Float = 1f,
    val direction: DIRECTION = DIRECTION.NONE
) {
    IDLE(""),
    WALK_DOWN("larry_walk_front", Animation.PlayMode.LOOP, 1f, DIRECTION.DOWN),
    WALK_UP("larry_walk_back", Animation.PlayMode.LOOP, 1f, DIRECTION.UP),
    WALK_LEFT("larry_walk_left", Animation.PlayMode.LOOP, 1f, DIRECTION.LEFT),
    WALK_RIGHT("larry_walk_right", Animation.PlayMode.LOOP, 1f, DIRECTION.RIGHT);

    companion object {
        fun animationByDirection(direction: DIRECTION): ANIMATION {
            return when (direction) {
                DIRECTION.DOWN -> WALK_DOWN
                DIRECTION.UP -> WALK_UP
                DIRECTION.LEFT -> WALK_LEFT
                DIRECTION.RIGHT -> WALK_RIGHT
                else -> IDLE
            }
        }
    }
}