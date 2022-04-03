package com.jabota31.sos.screens

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.jabota31.sos.SwordOfSouls
import ktx.app.KtxScreen

abstract class SwordOfSoulsScreen(
    val game: SwordOfSouls,
    val batch: Batch = game.batch,
    val gameViewport: FitViewport = game.gameViewport
) : KtxScreen {

}