package com.jabota31.sos.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.jabota31.sos.SwordOfSouls

fun main() {
    Lwjgl3Application(SwordOfSouls(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("SwordOfSouls")
        setWindowedMode(640, 480)
        setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
    })
}
