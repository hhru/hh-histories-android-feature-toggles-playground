plugins {
    id("convention.kotlin-android-library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    // Core modules
    implementation(project(":core:di"))
    implementation(project(":core:design-system"))
    implementation(project(":core:experiments"))

    // region External dependencies
    // AndroidX
    implementation(Libs.androidX.constraintLayout)
    implementation(Libs.androidX.appCompat)
    implementation(Libs.androidX.fragmentKtx)
    implementation(Libs.androidX.coreKtx)

    /// Jetpack
    implementation(Libs.jetpack.recyclerView)
    implementation(Libs.jetpack.lifecycleViewModel)
    implementation(Libs.jetpack.lifecycleLiveData)

    // Hilt
    implementation(Libs.jetpack.hiltAndroid)
    kapt(Libs.jetpack.hiltCompiler)

    // Debug
    implementation(Libs.debug.processPhoenix)
    // endregion
}