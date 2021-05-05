buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")

        // TODO [reflections-library-problems] We can't use Reflections library at runtime, so
        //   we need to collect classes at build time into assets file. To do this we need
        //   some dependencies from Reflections library at build configuration.
        classpath("org.reflections:reflections:0.9.12")
    }
}


tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}