import com.android.build.gradle.internal.dsl.SigningConfig

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

            setProguardFiles(listOf(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            ))

            signingConfig = SigningConfig("release").apply {
                storeFile(rootProject.rootDir.resolve("debug_key.keystore"))
                keyAlias("debug_key")
                keyPassword("123456")
                storePassword("123456")
            }
        }
    }
}

// TODO [classgraph-library-problems] This code returns empty scan_result file, because
//  'compileDebugJavaWithJavac' task doesn't contains all classes of our multimodule app.
afterEvaluate {
    // Get some task references
    val compileTask = this.tasks.findByName("compileDebugJavaWithJavac") as? JavaCompile
    val mergeTask = this.tasks.findByName("mergeDebugResources") as? com.android.build.gradle.tasks.MergeResources

    println("project: ${this.name}")

    println("compileTask: $compileTask")
    println("mergeTask: $mergeTask")

    compileTask?.doLast {
        val packageToScan = "ru.hh.ftplayground"
        val annotationName = "ru.hh.android.core.experiments.models.ExperimentAnnotation"
        val scanResultFilename = "scan_result.txt"

        // Create a temporary raw resource directory for saving the scan result to
        val scanResultDir = compileTask.temporaryDir.toString() + "/res/raw/"
        File(scanResultDir).mkdirs()
        val scanResultPath = scanResultDir + scanResultFilename

        // Get location where .class files were compiled to
        val classPackageRoot = compileTask.destinationDir

        println("Scanning $classPackageRoot")
        io.github.classgraph.ClassGraph()
            // [Configure your ClassGraph instance here]
            .overrideClasspath(classPackageRoot)
            .acceptPackages(packageToScan)
            .enableAllInfo()
//            .verbose()
            .scan()
            .also { scanResult ->
                // [Get the scan results you're interested in here]
                val resultList = scanResult.getClassesWithAnnotation(annotationName)
                // Write the results to the output file
                val outputFile = File(scanResultPath)
                val writer = outputFile.writer()
                resultList.forEach { ci ->
                    // Write name of annotated class to output file
                    writer.write(ci.name)
                }

                println("Wrote scan result to " + outputFile + " ; size = " + outputFile.length())
            }

        // Run AAPT2 to convert the raw resource file into a .flat file
        val flatFileOutputPrefix = mergeTask?.outputDir?.get()?.asFile?.absolutePath + "/"

        val cmd = listOf(
            "/Users/p.strelchenko/Library/Android/sdk/build-tools/30.0.3/aapt2",
            "compile",
            "-o",
            flatFileOutputPrefix,
            scanResultPath
        )
        println("Executing: " + cmd.joinToString(separator = " "))

        project.exec {
            this.commandLine(cmd)
        }
        println("Wrote compiled resource as a .flat file to $flatFileOutputPrefix")
    }
}

dependencies {
    // Core
    implementation(project(":core:di"))
    implementation(project(":core:design-system"))
    implementation(project(":core:experiments"))

    // Features
    implementation(project(":features:first"))
    implementation(project(":features:second"))
    implementation(project(":features:third"))

    // Debug panel
    implementation(project(":debug-panel"))

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