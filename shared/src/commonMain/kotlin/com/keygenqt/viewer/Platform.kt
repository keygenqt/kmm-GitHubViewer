package com.keygenqt.viewer

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform