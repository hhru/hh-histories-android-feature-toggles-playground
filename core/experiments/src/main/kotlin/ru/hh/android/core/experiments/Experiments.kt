package ru.hh.android.core.experiments

import ru.hh.android.core.experiments.domain.ExperimentsInteractor
import ru.hh.android.core.experiments.models.Experiment


object Experiments {

    private var experimentsInteractor: ExperimentsInteractor? = null


    fun init(experimentsInteractor: ExperimentsInteractor) {
        this.experimentsInteractor = experimentsInteractor
    }


    fun isUserAffected(experiment: Experiment): Boolean {
        return experimentsInteractor?.isUserAffected(experimentKey = experiment.key)
            ?: false
    }

}