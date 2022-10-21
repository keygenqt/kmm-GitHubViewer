package com.keygenqt.githubviewer

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform