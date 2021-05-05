package ru.hh.android.core.experiments.scanner

import ru.hh.android.core.experiments.models.Experiment


internal class ExperimentsClassScanner(
    packageCodePath: String,
) : ClassScanner(packageCodePath) {

    companion object {
        // TODO [class-loader-dex-problems] You have to introduce a naming convention
        //  for experiment classes to speed up classes scanning.
        private const val EXPERIMENTS_CLASS_NAME_SUFFIX = "Experiment"
    }

    private val experiments = mutableListOf<Experiment>()


    init {
        scan()
    }


    override fun isTargetClassName(className: String): Boolean {
        return className.endsWith(EXPERIMENTS_CLASS_NAME_SUFFIX)
    }

    override fun isTargetClass(clazz: Class<*>): Boolean {
        return try {
            Experiment::class.java.isAssignableFrom(clazz) && clazz != Experiment::class.java
        } catch (ex: ClassCastException) {
            false
        }
    }

    override fun onScanResult(clazz: Class<*>) {
        experiments += clazz.newInstance() as Experiment
    }


    fun getExperiments() = experiments
}