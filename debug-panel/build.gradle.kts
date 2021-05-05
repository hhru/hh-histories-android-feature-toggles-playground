plugins {
    id("convention.kotlin-android-library")
    kotlin("kapt")
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

    // DI
    implementation(Libs.toothpick.core)
    implementation(Libs.toothpick.ktp)
    kapt(Libs.toothpick.compiler)

    // Debug
    implementation(Libs.debug.processPhoenix)
    implementation(Libs.debug.classIndex)
    kapt(Libs.debug.classIndex)
    // endregion
}