package ru.hh.android.core.experiments

import ru.hh.android.core.experiments.domain.ExperimentsInteractor
import ru.hh.android.core.experiments.models.ExperimentModel
import ru.hh.android.core.experiments.models.ExperimentsSet
import toothpick.InjectConstructor


@InjectConstructor
internal class ExperimentsConfigImpl(
    private val experimentsInteractor: ExperimentsInteractor
) : ExperimentsConfig {

    override fun isFirstExperimentEnabled(): Boolean {
        return experimentsInteractor.isUserAffected(ExperimentsSet.EXPERIMENT_FIRST.key)
    }

    override fun isSecondExperimentEnabled(): Boolean {
        return experimentsInteractor.isUserAffected(ExperimentsSet.EXPERIMENT_SECOND.key)
    }

    override fun isThirdExperimentEnabled(): Boolean {
        return experimentsInteractor.isUserAffected(ExperimentsSet.EXPERIMENT_THIRD.key)
    }

    override fun getAllExperiments(): List<ExperimentModel> {
        return ExperimentsSet.values().map { experiment ->
            ExperimentModel(
                key = experiment.key,
                isUserAffected = experimentsInteractor.isUserAffected(experiment.key)
            )
        }
    }
}