package ru.hh.android.core.experiments

import ru.hh.android.core.experiments.domain.ExperimentsInteractor
import ru.hh.android.core.experiments.models.ExperimentModel
import toothpick.InjectConstructor


@InjectConstructor
internal class ExperimentsConfigImpl(
    private val experimentsInteractor: ExperimentsInteractor
) : ExperimentsConfig {

    override fun getAllExperiments(): List<ExperimentModel> {
        TODO("How to collect all experiments across the codebase?..")
    }
}