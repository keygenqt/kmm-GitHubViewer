pluginManagement {

    val gradleVersion: String by settings
    val kotlinVersion: String by settings

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        id("org.jetbrains.kotlin.android") version kotlinVersion
        id("com.android.application") version gradleVersion

        kotlin("kapt") version kotlinVersion
    }
}

// https://docs.gradle.org/current/userguide/platforms.html#sub:central-declaration-of-dependencies
enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
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