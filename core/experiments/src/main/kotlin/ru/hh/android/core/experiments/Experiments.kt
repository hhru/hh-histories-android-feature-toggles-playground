package ru.hh.android.core.experiments

import ru.hh.android.core.experiments.domain.ExperimentsInteractor
import ru.hh.android.core.experiments.models.Experiment
import javax.inject.Inject


// TODO [hilt-dagger-problems] We need assurance of instantiation 'Experiments' class before all use cases.
class Experiments @Inject constructor(
    private val experimentsInteractor: ExperimentsInteractor
) {

    init {
        // TODO [hilt-dagger-problems] Dagger can't inject into Kotlin's objects.
        staticExperimentsInteractor = this.experimentsInteractor
    }


    companion object {
        private lateinit var staticExperimentsInteractor: ExperimentsInteractor

        fun isUserAffected(experiment: Experiment): Boolean {
            return staticExperimentsInteractor.isUserAffected(experimentKey = experiment.key)
        }
    }

}