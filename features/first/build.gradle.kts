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
    implementation(Libs.androidX.constraintLayout)

    // DI
    implementation(Libs.toothpick.core)
    implementation(Libs.toothpick.ktp)
    kapt(Libs.toothpick.compiler)

    // Debug
    compileOnly(Libs.debug.classIndex)
    kapt(Libs.debug.classIndex)
    // endregion
}