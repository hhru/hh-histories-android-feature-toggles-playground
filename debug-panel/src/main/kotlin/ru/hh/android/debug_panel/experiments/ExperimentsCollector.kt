package ru.hh.android.debug_panel.experiments

import com.joom.colonist.Colonist
import com.joom.colonist.OnAcceptSettler
import ru.hh.android.core.experiments.models.Experiment


@ExperimentsColony
internal class ExperimentsCollector {

    private val experiments = mutableListOf<Experiment>()


    init {
        Colonist.settle(this)
    }


    @OnAcceptSettler(colonyAnnotation = ExperimentsColony::class)
    fun onAcceptExperiment(experiment: Experiment) {
        experiments += experiment
    }

    fun getAllExperiments(): List<Experiment> = experiments

}