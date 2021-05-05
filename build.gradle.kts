buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
        classpath("io.github.classgraph:classgraph:4.8.105")
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}