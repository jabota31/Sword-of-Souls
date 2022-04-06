package com.jabota31.sos.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.math.MathUtils
import com.jabota31.sos.V_HEIGHT
import com.jabota31.sos.V_WIDTH
import com.jabota31.sos.components.MoveComponent
import com.jabota31.sos.components.TransformComponent
import ktx.ashley.allOf
import ktx.ashley.get

private const val UPDATE_RATE = 1 / 25f

class MoveSystem : IteratingSystem(allOf(TransformComponent::class, MoveComponent::class).get()) {
    private var accumulator = 0f

    override fun update(deltaTime: Float) {
        accumulator += deltaTime
        while (accumulator >= UPDATE_RATE) {
            accumulator -= UPDATE_RATE
            super.update(UPDATE_RATE)
        }
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val transformComponent = entity[TransformComponent.mapper]
        require(transformComponent != null) { "TransformComponent is null" }
        val moveComponent = entity[MoveComponent.mapper]
        require(moveComponent != null) { "MoveComponent is null" }
        
        moveEntity(transformComponent, moveComponent, deltaTime)
    }

    private fun moveEntity(transformComponent: TransformComponent, moveComponent: MoveComponent, deltaTime: Float) {
        transformComponent.position.x = MathUtils.clamp(
            transformComponent.position.x + moveComponent.speed.x * deltaTime,
            0f,
            V_WIDTH - transformComponent.size.x
        )
        transformComponent.position.y = MathUtils.clamp(
            transformComponent.position.y + moveComponent.speed.y * deltaTime,
            0f,
            V_HEIGHT - transformComponent.size.y
        )
    }
}