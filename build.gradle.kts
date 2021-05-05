buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
    }
}


tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}