pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    @Suppress("UnstableApiUsage")
    fun systemProperty(name: String): Provider<String> {
        return providers.systemProperty(name).forUseAtConfigurationTime()
    }

    val androidGradlePluginVersion = systemProperty("androidGradlePluginVersion")
    val kotlinVersion = systemProperty("kotlinVersion")

    resolutionStrategy {
        eachPlugin {
            val pluginId = requested.id.id
            when {
                pluginId.startsWith("com.android.") ->
                    useModule("com.android.tools.build:gradle:${androidGradlePluginVersion.get()}")

                pluginId.startsWith("org.jetbrains.kotlin.") ->
                    useVersion(kotlinVersion.get())
            }
        }
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "android-feature-toggles-playground"

// Included builds
includeBuild("libraries")
includeBuild("build-logic")

// Application modules
include(":app")

// Debug panel
include(":debug-panel")

// Feature modules
include(":features:first")
include(":features:second")
include(":features:third")

// Core modules
include(":core:di")
include(":core:design-system")
include(":core:experiments")