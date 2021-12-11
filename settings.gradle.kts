pluginManagement {

    val spotlessVersion: String by settings
    val dokkaVersions: String by settings
    val kotlinVersion: String by settings
    val artifactoryVersion: String by settings
    val manesPluginVersion: String by settings

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://artifactory.keygenqt.com/artifactory/open-source")
    }

    plugins {
        // https://github.com/Kotlin/kotlinx.serialization
        kotlin("plugin.serialization") version kotlinVersion
        // https://github.com/diffplug/spotless
        id("com.diffplug.spotless") version spotlessVersion
        // https://github.com/Kotlin/dokka
        id("org.jetbrains.dokka") version dokkaVersions
        // https://www.jfrog.com/confluence/display/JFROG/Gradle+Artifactory+Plugin
        id("com.jfrog.artifactory") version artifactoryVersion
        // https://github.com/ben-manes/gradle-versions-plugin
        id("com.github.ben-manes.versions") version manesPluginVersion
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

val internalLibrariesEnable: String by settings

// Include internal libraries
// create a folder "libs" next to the application and pull libs projects there
// access for ./gradlew :{module}:artifactoryPublish - keygenqt@gmail.com || zarubin@surfstudio.ru
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

    // git@github.com:keygenqt/surf-accompanist.git
    include(":surf-accompanist")
    project(":surf-accompanist").projectDir = File(settingsDir, "../libs/surf-accompanist")
}