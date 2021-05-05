plugins {
    `kotlin-dsl`
    id("convention.libraries")
}

group = "ru.hh.android.build_logic"

dependencies {
    implementation("ru.hh.android.build_logic:libraries")
    implementation("ru.hh.android.build_logic:testing-convention")
    implementation(Libs.kotlinPlugin)
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}