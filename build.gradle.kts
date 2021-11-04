buildscript {

    val kotlinVersion: String by project
    val gradleVersion: String by project
    val hiltCoreVersion: String by project

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:$gradleVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltCoreVersion")
    }
}

plugins {
    id("com.diffplug.spotless")
}

subprojects {

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
}