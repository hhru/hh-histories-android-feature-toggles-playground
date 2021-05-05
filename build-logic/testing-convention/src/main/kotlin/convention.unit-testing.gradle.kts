@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.gradle.plugin.KotlinBasePluginWrapper

plugins {
    id("convention.libraries")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()

    @Suppress("MagicNumber")
    maxParallelForks = 8

    failFast = false

    /**
     * IDEA adds an init script, using it to define if it is an IDE run
     * used in `:test-project`
     */
    systemProperty(
        "isInvokedFromIde",
        gradle.startParameter.allInitScripts.find { it.name.contains("ijtestinit") } != null
    )
}

plugins.withType<KotlinBasePluginWrapper>() {
    dependencies {
        add("testImplementation", Libs.test.junit5Api)
        add("testImplementation", Libs.test.junit5ParametrizedTestsApi)

        add("testRuntimeOnly", Libs.test.junit5Engine)
        add("testRuntimeOnly", Libs.test.junitPlatformRunner)
        add("testRuntimeOnly", Libs.test.junitPlatformLauncher)
    }
}