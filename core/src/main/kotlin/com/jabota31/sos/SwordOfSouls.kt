package com.jabota31.sos

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.jabota31.sos.screens.FirstScreen
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.assets.disposeSafely

class SwordOfSouls : KtxGame<KtxScreen>() {
    val batch by lazy { SpriteBatch() }
    val gameViewport by lazy { FitViewport(16f, 9f) }

    override fun create() {
        addScreen(FirstScreen(this))
        setScreen<FirstScreen>()
    }

    override fun dispose() {
        batch.disposeSafely()
    }
}