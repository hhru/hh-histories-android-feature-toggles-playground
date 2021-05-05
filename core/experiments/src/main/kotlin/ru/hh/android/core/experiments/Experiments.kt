package ru.hh.android.core.experiments

import ru.hh.android.core.experiments.domain.ExperimentsInteractor
import ru.hh.android.core.experiments.models.Experiment
import ru.hh.android.core.experiments.scanner.ExperimentsClassScanner


object Experiments {

    private var experimentsInteractor: ExperimentsInteractor? = null
    private var packageCodePath: String? = null


    fun init(
        packageCodePath: String,
        experimentsInteractor: ExperimentsInteractor
    ) {
        this.packageCodePath = packageCodePath
        this.experimentsInteractor = experimentsInteractor
    }


    fun isUserAffected(experiment: Experiment): Boolean {
        return experimentsInteractor?.isUserAffected(experimentKey = experiment.key)
            ?: false
    }

    fun getAllExperiments(): List<Experiment> {
        return packageCodePath?.let { ExperimentsClassScanner(it).getExperiments() } ?: emptyList()
    }

}