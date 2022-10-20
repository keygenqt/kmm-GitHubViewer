pluginManagement {

    val spotlessVersion: String by settings
    val dokkaVersions: String by settings
    val kotlinVersion: String by settings

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    plugins {
        // https://github.com/Kotlin/kotlinx.serialization
        kotlin("plugin.serialization") version kotlinVersion
        // https://github.com/diffplug/spotless
        id("com.diffplug.spotless") version spotlessVersion
        // https://github.com/Kotlin/dokka
        id("org.jetbrains.dokka") version dokkaVersions
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files(
                "dependencies/accompanist.versions.toml",
                "dependencies/coil.versions.toml",
                "dependencies/compose.versions.toml",
                "dependencies/firebase.versions.toml",
                "dependencies/hilt.versions.toml",
                "dependencies/lottie.versions.toml",
                "dependencies/other.versions.toml",
                "dependencies/retrofit.versions.toml",
                "dependencies/room.versions.toml",
                "dependencies/versions.toml"
            ))
        }
    }
}

rootProject.name = "GitHubViewer"

include(":androidApp")
include(":shared")

val internalLibrariesEnable: String by settings

// Include internal libraries

if (internalLibrariesEnable.toBoolean()) {
    // git@github.com:keygenqt/android-response-result.git
    include(":android-response-result")
    project(":android-response-result").projectDir =
        File(settingsDir, "../libs/android-response-result")

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

