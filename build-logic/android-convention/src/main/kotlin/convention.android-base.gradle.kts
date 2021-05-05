import com.android.build.gradle.BaseExtension

plugins {
    id("convention.libraries")
}

configure<BaseExtension> {
    sourceSets {
        named("main").configure { java.srcDir("src/main/kotlin") }
        named("androidTest").configure { java.srcDir("src/androidTest/kotlin") }
        named("test").configure { java.srcDir("src/test/kotlin") }
    }

    buildToolsVersion(Libs.buildToolsVersion)
    compileSdkVersion(Libs.compileSdkVersion)

    compileOptions {
        sourceCompatibility = Libs.javaVersion
        targetCompatibility = Libs.javaVersion
        isCoreLibraryDesugaringEnabled = true
    }

    defaultConfig {
        minSdkVersion(Libs.minSdkVersion)
        targetSdkVersion(Libs.targetSdkVersion)

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    @Suppress("UnstableApiUsage")
    with(buildFeatures) {
        aidl = false
        compose = false
        buildConfig = false
        prefab = false
        renderScript = false
        resValues = false
        shaders = false
        viewBinding = false
    }
}

dependencies {
    add("coreLibraryDesugaring", Libs.build.jdkDesugaring)
}