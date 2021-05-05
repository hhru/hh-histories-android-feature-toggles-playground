plugins {
    id("convention.kotlin-android-library")
    kotlin("kapt")
}

dependencies {
    // External libraries
    implementation(Libs.kotlinStdlib)
    implementation(Libs.jetpack.lifecycleLiveData)

    // DI
    implementation(Libs.toothpick.core)
    implementation(Libs.toothpick.ktp)
    kapt(Libs.toothpick.compiler)

    // Debug
    implementation(Libs.debug.classIndex)
}