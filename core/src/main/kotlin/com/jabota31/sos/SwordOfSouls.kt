package com.jabota31.sos

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.jabota31.sos.screens.FirstScreen
import com.jabota31.sos.systems.RenderSystem
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.assets.disposeSafely

const val UNIT_SCALE = 1 / 64f

class SwordOfSouls : KtxGame<KtxScreen>() {
    val batch by lazy { SpriteBatch() }
    val gameViewport by lazy { FitViewport(16f, 9f) }
    val engine : Engine by lazy { PooledEngine().apply {
        addSystem(RenderSystem(batch, gameViewport))
    } }

    override fun create() {
        addScreen(FirstScreen(this))
        setScreen<FirstScreen>()
    }

    override fun dispose() {
        super.dispose()
        batch.disposeSafely()
    }
}