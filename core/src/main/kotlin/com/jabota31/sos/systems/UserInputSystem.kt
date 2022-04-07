package com.jabota31.sos.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.jabota31.sos.components.AnimationComponent
import com.jabota31.sos.components.MoveComponent
import com.jabota31.sos.components.UserInputComponent
import com.jabota31.sos.enums.ANIMATION
import com.jabota31.sos.enums.DIRECTION
import com.jabota31.sos.services.PlayerInputService
import ktx.ashley.allOf
import ktx.ashley.get

class UserInputSystem(var playerInputService: PlayerInputService) :
    IteratingSystem(allOf(UserInputComponent::class, MoveComponent::class).get()) {
    override fun processEntity(entity: Entity, deltaTime: Float) {
        val moveComponent = entity[MoveComponent.mapper]
        require(moveComponent != null) { "MoveComponent is null" }
        val animationComponent = entity[AnimationComponent.mapper]
        require(animationComponent != null) { "UserInputComponent is null" }

        if (playerInputService.currentMoveDirection != DIRECTION.NONE) {
            moveComponent.speed.set(playerInputService.directionToSpeedVector(playerInputService.currentMoveDirection))
        } else {
            moveComponent.speed.set(0f, 0f)
        }

        if (animationComponent.type.direction != playerInputService.currentMoveDirection) {
            animationComponent.type = ANIMATION.animationByDirection(playerInputService.currentMoveDirection)
        }
    }
}