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
    implementation(Libs.androidX.constraintLayout)
    implementation(Libs.androidX.fragmentKtx)

    // Hilt
    implementation(Libs.jetpack.hiltAndroid)
    kapt(Libs.jetpack.hiltCompiler)
    // endregion
}