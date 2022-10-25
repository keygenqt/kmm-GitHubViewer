buildscript {

    val kotlinVersion: String by project
    val gradleVersion: String by project
    val hiltVersion: String by project
    val crashlyticsPluginVersion: String by project
    val buildkonfigVersion: String by project

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:$gradleVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
        classpath("com.google.firebase:firebase-crashlytics-gradle:$crashlyticsPluginVersion")
        classpath("com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:$buildkonfigVersion")
    }
}

plugins {
    id("com.diffplug.spotless")
    id("org.jetbrains.dokka")
}

tasks.withType<org.jetbrains.dokka.gradle.DokkaMultiModuleTask>().configureEach {
    outputDirectory.set(rootDir.resolve("docs/api"))
    failOnWarning.set(true)
}

subprojects {

    apply(plugin = "org.jetbrains.dokka")
    apply(plugin = "com.diffplug.spotless")

    spotless {
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**")
            ktlint(libs.versions.ktlintVersion.get())
                .userData(
                    mapOf(
                        "disabled_rules" to "no-wildcard-imports",
                    )
                )
            licenseHeaderFile("$rootDir/copyright.txt")
        }
        format("misc") {
            target("**/*.gradle", "**/*.md", "**/.gitignore")
            trimTrailingWhitespace()
            indentWithSpaces()
            endWithNewline()
        }
    }

    tasks.withType<org.jetbrains.dokka.gradle.DokkaTaskPartial>().configureEach {
        suppressInheritedMembers.set(true)
        dokkaSourceSets {
            configureEach {
                includes.from("dokka.md")
                includeNonPublic.set(true)
            }
        }
    }
}