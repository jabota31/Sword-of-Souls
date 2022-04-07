package com.jabota31.sos

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.utils.viewport.FitViewport
import com.jabota31.sos.screens.FirstScreen
import com.jabota31.sos.services.PlayerInputService
import com.jabota31.sos.systems.AnimationSystem
import com.jabota31.sos.systems.MoveSystem
import com.jabota31.sos.systems.RenderSystem
import com.jabota31.sos.systems.UserInputSystem
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.assets.disposeSafely

const val UNIT_SCALE = 1 / 64f
const val V_WIDTH = 16f
const val V_HEIGHT = 9f

class SwordOfSouls : KtxGame<KtxScreen>() {
    val batch by lazy { SpriteBatch() }
    val gameViewport by lazy { FitViewport(16f, 9f) }
    private val playerInputService by lazy { PlayerInputService() }

    private val larryGraphicAtlas by lazy { TextureAtlas("graphics/larry.atlas") }

    val engine : Engine by lazy { PooledEngine().apply {
        addSystem(AnimationSystem(larryGraphicAtlas))
        addSystem(RenderSystem(batch, gameViewport))
        addSystem(MoveSystem())
        addSystem(UserInputSystem(playerInputService))
    } }

    override fun create() {
        Gdx.input.inputProcessor = playerInputService
        addScreen(FirstScreen(this))
        setScreen<FirstScreen>()
    }

    override fun dispose() {
        super.dispose()
        batch.disposeSafely()
    }
}