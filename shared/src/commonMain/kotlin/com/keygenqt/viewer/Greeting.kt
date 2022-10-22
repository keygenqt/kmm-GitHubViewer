package com.keygenqt.viewer

class Greeting {
    private val platform: Platform = getPlatform()

    fun greeting(): String {
        return "Platform: (${platform.name})"
    }
}