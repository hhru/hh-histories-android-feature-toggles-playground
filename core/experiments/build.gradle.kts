plugins {
    id("convention.kotlin-android-library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    // region External libraries
    implementation(Libs.kotlinStdlib)
    implementation(Libs.jetpack.lifecycleLiveData)

    // Hilt
    implementation(Libs.jetpack.hiltAndroid)
    kapt(Libs.jetpack.hiltCompiler)
    // endregion
}