package ru.hh.android.core.experiments.scanner

import android.util.Log
import dalvik.system.DexFile
import kotlin.system.measureTimeMillis


internal abstract class ClassScanner(
    private val packageCodePath: String,
) {

    companion object {
        private const val LOG_TAG = "ClassScanner"
    }


    protected abstract fun isTargetClassName(className: String): Boolean
    protected abstract fun isTargetClass(clazz: Class<*>): Boolean
    protected abstract fun onScanResult(clazz: Class<*>)


    fun scan() {
        val time = measureTimeMillis {
            val classLoader = Thread.currentThread().contextClassLoader
            // TODO [class-loader-dex-problems] DexFile class was deprecacted in API level 26.
            val dexFile = DexFile(packageCodePath)
            val classNamesEnumeration = dexFile.entries()

            while (classNamesEnumeration.hasMoreElements()) {
                val nextClassName = classNamesEnumeration.nextElement()
                if (isTargetClassName(nextClassName)) {
                    val clazz = classLoader.loadClass(nextClassName)
                    if (isTargetClass(clazz)) {
                        onScanResult(clazz)
                    }
                }
            }
        }

        Log.d(LOG_TAG, "Scanner time: $time ms")
    }

}
