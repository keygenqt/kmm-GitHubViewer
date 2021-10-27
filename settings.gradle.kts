pluginManagement {

    val spotlessVersions: String by settings
    val dokkaVersions: String by settings
    val kotlinVersion: String by settings

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    plugins {
        kotlin("plugin.serialization") version kotlinVersion
        id("com.diffplug.spotless") version spotlessVersions
        id("org.jetbrains.dokka") version dokkaVersions
    }
}

enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://artifactory.keygenqt.com/artifactory/open-source")
    }
    versionCatalogs {
        create("libs") {
            from(fileTree("dependencies"))
        }
    }
}

rootProject.name = "GitHubViewer"
include(":androidApp")
include(":shared")