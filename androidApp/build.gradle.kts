plugins {
    id("com.android.application")

    kotlin("android")
    kotlin("kapt")

    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization")
    id("com.google.firebase.crashlytics")
}

android {

    compileSdk = findProperty("compileSdk").toString().toInt()

    defaultConfig {

        minSdk = findProperty("minSdk").toString().toInt()
        targetSdk = findProperty("targetSdk").toString().toInt()

        applicationId = "com.keygenqt.viewer.android"

        versionCode = 1
        versionName = "0.0.1"

        // Secret DB
        buildConfigField(
            "String",
            "SECRET_DB",
            findProperty("secret_db").toString()
        )

        // Client ID GitHub
        buildConfigField(
            "String",
            "GITHUB_CLIENT_ID",
            findProperty("github_client_id").toString()
        )

        // Secret GitHub
        buildConfigField(
            "String",
            "GITHUB_CLIENT_SECRET",
            findProperty("github_client_secret").toString()
        )

        // Dynamic Links
        buildConfigField(
            "String",
            "dynamicLinksHost",
            """"${findProperty("dynamicLinksHost")}""""
        )

        addManifestPlaceholders(mapOf("dynamicLinksHost" to findProperty("dynamicLinksHost").toString()))
    }

    composeOptions {
        kotlinCompilerExtensionVersion = findProperty("composeCompilerVersion").toString()
    }

    buildFeatures {
        compose = true
    }

    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    // division resources
    sourceSets {
        getByName("main").let { data ->
            data.res.setSrcDirs(emptySet<String>())
            file("src/main/res").listFiles()?.toList()?.forEach { dir ->
                data.res.srcDir(dir)
            }
        }
    }

    packagingOptions {
        resources {
            excludes.add("**/attach_hotspot_windows.dll")
            excludes.add("META-INF/licenses/**")
            excludes.add("META-INF/AL2.0")
            excludes.add("META-INF/LGPL2.1")
        }
    }
}

dependencies {
    // kmm
    implementation(project(":shared"))
    // libs
    if (findProperty("internalLibrariesEnable").toString().toBoolean()) {
        implementation(project(":android-response-result"))
        implementation(project(":compose-forms"))
        implementation(project(":compose-modifier-ext"))
        implementation(project(":compose-routing"))
        implementation(project(":keygenqt-accompanist"))
    } else {
        implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    }
}

// Common
dependencies {
    implementation(kmm.bundles.common)
}

// Android
dependencies {

    implementation(libs.bundles.accompanist)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.coil)
    implementation(libs.bundles.hilt)
    implementation(libs.bundles.other)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.room)
    implementation(libs.bundles.lottie)
    implementation(libs.bundles.firebase)

    kapt(libs.bundles.hiltKapt)
    kapt(libs.bundles.roomKapt)
}