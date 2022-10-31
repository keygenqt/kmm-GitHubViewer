import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import java.util.*

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("com.codingfeline.buildkonfig")
    id("dev.petuska.npm.publish") version "3.0.3"
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    js(IR) {
        moduleName = "shared"
        version = "0.0.3"
        nodejs()
        binaries.library()
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "0.0.1"
        ios.deploymentTarget = "16"
        podfile = project.file("../iosApp/Podfile")
        framework {
            isStatic = true
            baseName = "shared"
        }
    }

    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.js.ExperimentalJsExport")
            }
        }

        val commonMain by getting {
            dependencies {
                implementation(kmm.bundles.common)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(kmm.bundles.android)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(kmm.bundles.ios)
            }
        }

        val mobileMain by creating {
            dependsOn(commonMain)
            androidMain.dependsOn(this)
            iosMain.dependsOn(this)
            dependencies {
                implementation(kmm.bundles.commonMobile)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(kmm.bundles.js)
            }
        }
    }
}

buildkonfig {
    packageName = "com.keygenqt.viewer"

    defaultConfigs {
        // Client ID GitHub
        buildConfigField(
            STRING,
            "GITHUB_CLIENT_ID",
            findProperty("github_client_id").toString().replace("\"", "")
        )

        // Secret GitHub
        buildConfigField(
            STRING,
            "GITHUB_CLIENT_SECRET",
            findProperty("github_client_secret").toString().replace("\"", "")
        )

        // Dynamic Links
        buildConfigField(
            STRING,
            "dynamicLinksHost",
            findProperty("dynamicLinksHost").toString().replace("\"", "")
        )
    }
}

android {
    namespace = "com.keygenqt.viewer"
    compileSdk = 32
    defaultConfig {
        minSdk = 26
        targetSdk = 32
    }
}

npmPublish {
    packages {
        named("js") {
            packageJson {
                version.set("0.0." + Date().time)
            }
        }
    }
}