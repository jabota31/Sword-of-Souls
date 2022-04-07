package com.jabota31.sos.services

import com.badlogic.gdx.Input
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.math.Vector2
import com.jabota31.sos.enums.DIRECTION


class PlayerInputService : InputProcessor {
    var currentMoveDirection : DIRECTION = DIRECTION.NONE
    var movementKeysBeingPressed : ArrayDeque<Int> = ArrayDeque()

    private fun keycodeToDirection(keycode: Int): DIRECTION {
        when (keycode) {
            Input.Keys.W -> return DIRECTION.UP
            Input.Keys.S -> return DIRECTION.DOWN
            Input.Keys.A -> return DIRECTION.LEFT
            Input.Keys.D -> return DIRECTION.RIGHT
        }
        return DIRECTION.NONE
    }

    fun directionToSpeedVector(direction: DIRECTION): Vector2 {
        return when (direction) {
            DIRECTION.UP -> Vector2(0f, 1f)
            DIRECTION.DOWN -> Vector2(0f, -1f)
            DIRECTION.LEFT -> Vector2(-1f, 0f)
            DIRECTION.RIGHT -> Vector2(1f, 0f)
            else -> Vector2(0f, 0f)
        }
    }

    override fun keyDown(keycode: Int): Boolean {
        movementKeysBeingPressed.add(keycode)
        currentMoveDirection = keycodeToDirection(keycode)
        return true
    }

    override fun keyUp(keycode: Int): Boolean {
        movementKeysBeingPressed.remove(keycode)

        currentMoveDirection = if (movementKeysBeingPressed.isEmpty()) {
            DIRECTION.NONE
        } else {
            keycodeToDirection(movementKeysBeingPressed.last())
        }
        return true
    }

    override fun keyTyped(character: Char): Boolean = true
    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = true
    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = true
    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = true
    override fun mouseMoved(screenX: Int, screenY: Int): Boolean = true
    override fun scrolled(amountX: Float, amountY: Float): Boolean = true
}