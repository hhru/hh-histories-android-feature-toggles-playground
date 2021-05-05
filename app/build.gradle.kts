import com.android.build.gradle.internal.dsl.SigningConfig

plugins {
    id("convention.kotlin-android-app")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "ru.hh.ftplayground"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = true

            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )

            signingConfig = SigningConfig("release").apply {
                storeFile(rootProject.rootDir.resolve("debug_key.keystore"))
                keyAlias("debug_key")
                keyPassword("123456")
                storePassword("123456")
            }
        }
    }
}

dependencies {
    // Core
    implementation(project(":core:di"))
    implementation(project(":core:design-system"))
    implementation(project(":core:experiments"))

    // Features
    implementation(project(":features:first"))
    implementation(project(":features:second"))
    implementation(project(":features:third"))

    // Debug panel
    implementation(project(":debug-panel"))

    // region External dependencies
    // Jetpack
    implementation(Libs.jetpack.navigationComponentFragment)
    implementation(Libs.jetpack.navigationComponentFragmentKtx)
    implementation(Libs.jetpack.lifecycleViewModel)
    implementation(Libs.jetpack.lifecycleLiveData)

    implementation(Libs.kotlinStdlib)

    // AndroidX
    implementation(Libs.androidX.coreKtx)
    implementation(Libs.androidX.fragmentKtx)
    implementation(Libs.androidX.appCompat)
    implementation(Libs.androidX.constraintLayout)
    implementation(Libs.androidX.materialComponents)

    // Hilt
    implementation(Libs.jetpack.hiltAndroid)
    kapt(Libs.jetpack.hiltCompiler)

    // Debug
    implementation(Libs.debug.processPhoenix)
    // endregion
}