import com.android.build.gradle.internal.dsl.SigningConfig
import org.reflections.vfs.Vfs
import java.net.URL
import java.net.URLClassLoader


plugins {
    id("convention.kotlin-android-app")
    kotlin("kapt")
}

android {
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "ru.hh.ftplayground"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = true

            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )

            signingConfig = SigningConfig("release").apply {
                storeFile(rootProject.rootDir.resolve("debug_key.keystore"))
                keyAlias("debug_key")
                keyPassword("123456")
                storePassword("123456")
            }
        }
    }
}


// TODO [reflection-library-problems] When Reflections tried to read .aar archives,
//  it throws exceptions like this:
//  org.reflections.ReflectionsException: could not create Vfs.Dir from url, no matching UrlType was found
//  --> find fix in https://github.com/ronmamo/reflections/issues/80
//  --> adopted solution from https://gist.github.com/nonrational/287ed109bb0852f982e8
class ReflectionsHelper {

    companion object {
        fun registerUrlTypes() {
            val urlTypes = mutableListOf<Vfs.UrlType>()

            // include a list of file extensions / filenames to be recognized
            urlTypes += EmptyIfFileEndingsUrlType(listOf(".aar"))
            urlTypes += Vfs.DefaultUrlTypes.values()

            Vfs.setDefaultURLTypes(urlTypes)
        }
    }


    private class EmptyIfFileEndingsUrlType(
        val fileEndings: List<String>
    ) : Vfs.UrlType {

        override fun matches(url: URL): Boolean {
            val protocol = url.protocol
            val externalForm = url.toExternalForm()
            if (protocol.equals("file").not()) {
                return false
            }

            for (fileEnding in fileEndings) {
                if (externalForm.endsWith(fileEnding)) {
                    return true
                }
            }
            return false
        }

        override fun createDir(url: URL): Vfs.Dir {
            return emptyVfsDir(url)
        }

        private fun emptyVfsDir(url: URL): Vfs.Dir {
            return object : Vfs.Dir {
                override fun getPath(): String {
                    return url.toExternalForm()
                }

                override fun getFiles(): MutableIterable<Vfs.File> {
                    return mutableListOf()
                }

                override fun close() {
                    // do nothing
                }

            }
        }
    }

}

afterEvaluate {
    android {
        applicationVariants.forEach { variant ->
            println("applicationVariants.forEach: ${variant.name}")

            variant.javaCompileProvider.get().doLast {
                val urls = project.configurations.compile.get().all
                    .asSequence()
                    .filter { it.isCanBeResolved }
                    .filter { it.name.contains("test", ignoreCase = true).not() }
                    .map { it.files }
                    .flatten()
                    .map { it.toURI().toURL() }
                    .toSet()
                println("urls = $urls")

                val classLoader = URLClassLoader(
                    urls.toTypedArray(),
                    ClassLoader.getSystemClassLoader()
                )

                // TODO [reflection-library-problems] When Reflections tried to read .aar archives,
                //  it throws exceptions like this:
                //  org.reflections.ReflectionsException: could not create Vfs.Dir from url, no matching UrlType was found
                //  --> find fix in https://github.com/ronmamo/reflections/issues/80
                //  --> adopted solution from https://gist.github.com/nonrational/287ed109bb0852f982e8
                ReflectionsHelper.registerUrlTypes()
                val config = org.reflections.util.ConfigurationBuilder.build()
                    .forPackages("ru.hh.ftplayground")
                    .addClassLoader(classLoader)
                    .setUrls(urls)
                val reflections = org.reflections.Reflections(config)

                // (a) generate file for current debug or release build
                reflections.save(
                    "${variant.javaCompileProvider.get().destinationDir}/../../assets/${variant.buildType.name}/reflections/my-reflections.json",
                    org.reflections.serializers.JsonSerializer()
                )

                // (b) always update fall-back file for debug (used when running app from Android Studio or IntelliJ)
                reflections.save(
                    "${variant.javaCompileProvider.get().destinationDir}/../../../../src/debug/assets/reflections/my-reflections.json",
                    org.reflections.serializers.JsonSerializer()
                )
            }
        }
    }
}

dependencies {
    // Core

    // TODO [reflections-library-problems] Added specification of configuration for resolving
    //   'Cannot choose between the following configurations of project'
    //   Find fix in https://github.com/dialogflow/dialogflow-android-client/issues/57
    implementation(project(":core:di", configuration = "default"))
    implementation(project(":core:design-system", configuration = "default"))
    implementation(project(":core:experiments", configuration = "default"))

    // Features
    implementation(project(":features:first", configuration = "default"))
    implementation(project(":features:second", configuration = "default"))
    implementation(project(":features:third", configuration = "default"))

    // Debug panel
    implementation(project(":debug-panel", configuration = "default"))

    // region External dependencies
    // Jetpack
    implementation(Libs.jetpack.navigationComponentFragment)
    implementation(Libs.jetpack.navigationComponentFragmentKtx)
    implementation(Libs.jetpack.lifecycleViewModel)
    implementation(Libs.jetpack.lifecycleLiveData)

    implementation(Libs.kotlinStdlib)

    // AndroidX
    implementation(Libs.androidX.coreKtx)
    implementation(Libs.androidX.fragmentKtx)
    implementation(Libs.androidX.appCompat)
    implementation(Libs.androidX.constraintLayout)
    implementation(Libs.androidX.materialComponents)

    // Toothpick
    implementation(Libs.toothpick.core)
    implementation(Libs.toothpick.ktp)
    kapt(Libs.toothpick.compiler)

    // Debug
    implementation(Libs.debug.processPhoenix)
    // endregion
}