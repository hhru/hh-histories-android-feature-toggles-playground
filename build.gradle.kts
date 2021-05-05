buildscript {
    repositories {
        google()
        mavenCentral()
    }

    @Suppress("UnstableApiUsage")
    fun systemProperty(name: String): Provider<String> {
        return providers.systemProperty(name).forUseAtConfigurationTime()
    }

    val androidGradlePluginVersion = systemProperty("androidGradlePluginVersion")
    val hiltVersion = systemProperty("hiltVersion")


    dependencies {
        classpath("com.android.tools.build:gradle:${androidGradlePluginVersion.get()}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${hiltVersion.get()}")
    }
}


tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}