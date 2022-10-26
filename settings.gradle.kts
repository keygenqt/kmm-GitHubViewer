pluginManagement {

    val spotlessVersion: String by settings
    val dokkaVersions: String by settings
    val kotlinVersion: String by settings
    val multiplatformVersion: String by settings

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        // https://kotlinlang.org/docs/multiplatform.html
        kotlin("multiplatform") version multiplatformVersion
        // https://github.com/Kotlin/kotlinx.serialization
        kotlin("plugin.serialization") version kotlinVersion
        // https://github.com/diffplug/spotless
        id("com.diffplug.spotless") version spotlessVersion
        // https://github.com/Kotlin/dokka
        id("org.jetbrains.dokka") version dokkaVersions
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("kmm") {
            from(files("dependencies/libs.kmm.toml"))
        }
        create("libs") {
            from(files("dependencies/libs.android.toml"))
        }
    }
}

rootProject.name = "GitHubViewer"

include(":androidApp")
include(":shared")

val internalLibrariesEnable: String by settings

// Include internal libraries

if (internalLibrariesEnable.toBoolean()) {
    // git@github.com:keygenqt/compose-forms.git
    include(":compose-forms")
    project(":compose-forms").projectDir = File(settingsDir, "../libs/compose-forms")

    // git@github.com:keygenqt/compose-modifier-ext.git
    include(":compose-modifier-ext")
    project(":compose-modifier-ext").projectDir = File(settingsDir, "../libs/compose-modifier-ext")

    // git@github.com:keygenqt/compose-routing.git
    include(":compose-routing")
    project(":compose-routing").projectDir = File(settingsDir, "../libs/compose-routing")

    // git@github.com:keygenqt/keygenqt-accompanist.git
    include(":keygenqt-accompanist")
    project(":keygenqt-accompanist").projectDir = File(settingsDir, "../libs/keygenqt-accompanist")
}