package ru.hh.android

import org.gradle.api.JavaVersion
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory
import javax.inject.Inject

abstract class ExternalLibrariesExtension @Inject constructor(private val providers: ProviderFactory) {

    private object Versions {
        const val junit5 = "5.7.1"
        const val junit5Platform = "1.6.0"
    }

    val javaVersion = JavaVersion.VERSION_1_8
    val compileSdkVersion = 29
    val targetSdkVersion = 28
    val minSdkVersion = 21

    val buildToolsVersion = "29.0.3"

    private val kotlinVersion = systemProperty("kotlinVersion").get()
    private val androidGradlePluginVersion = systemProperty("androidGradlePluginVersion").get()


    val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"


    val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    val androidGradlePlugin = "com.android.tools.build:gradle:$androidGradlePluginVersion"


    val androidX = AndroidXLibraries
    val jetpack = JetpackLibraries
    val build = BuildLibraries
    val toothpick = ToothpickLibraries
    val test = TestsLibraries


    object AndroidXLibraries {
        const val coreKtx = "androidx.core:core-ktx:1.3.2"
        const val appCompat = "androidx.appcompat:appcompat:1.2.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.3"
    }

    object JetpackLibraries {
        private const val navigationComponentVersion = "2.3.5"
        private const val lifecycleVersion = "2.3.1"

        const val navigationComponentFragment = "androidx.navigation:navigation-fragment:$navigationComponentVersion"
        const val navigationComponentFragmentKtx = "androidx.navigation:navigation-fragment-ktx:$navigationComponentVersion"

        const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    }

    object BuildLibraries {
        private const val jdkDesugaringVersion = "1.0.9"

        const val jdkDesugaring = "com.android.tools:desugar_jdk_libs:${jdkDesugaringVersion}"
    }

    object ToothpickLibraries {

        private const val toothpickVersion = "3.1.0"

        const val core = "com.github.stephanenicolas.toothpick:ktp:$toothpickVersion"
        const val api = "com.github.stephanenicolas.toothpick:toothpick:$toothpickVersion"
        const val compiler = "com.github.stephanenicolas.toothpick:toothpick-compiler:$toothpickVersion"
        const val ktp = "com.github.stephanenicolas.toothpick:ktp:$toothpickVersion"
        const val testing = "com.github.stephanenicolas.toothpick:toothpick-testing:$toothpickVersion"
    }

    object TestsLibraries {
        const val junit5Api = "org.junit.jupiter:junit-jupiter-api:${Versions.junit5}"
        const val junit5Engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5}"
        const val junitPlatformRunner = "org.junit.platform:junit-platform-runner:${Versions.junit5Platform}"
        const val junitPlatformLauncher = "org.junit.platform:junit-platform-launcher:${Versions.junit5Platform}"
        const val junit5ParametrizedTestsApi = "org.junit.jupiter:junit-jupiter-params:${Versions.junit5}"
        const val junit5VintageEngine = "org.junit.vintage:junit-vintage-engine:${Versions.junit5}"
    }

    @Suppress("UnstableApiUsage")
    private fun systemProperty(name: String): Provider<String> {
        return providers.systemProperty(name).forUseAtConfigurationTime()
    }
}