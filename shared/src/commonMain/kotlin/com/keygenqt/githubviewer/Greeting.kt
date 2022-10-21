package com.keygenqt.githubviewer

class Greeting {
    private val platform: Platform = getPlatform()

    fun greeting(): String {
        return "(${platform.name})"
    }
}