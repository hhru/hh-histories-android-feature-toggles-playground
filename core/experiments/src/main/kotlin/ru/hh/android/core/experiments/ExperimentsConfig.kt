package ru.hh.android.core.experiments

import ru.hh.android.core.experiments.models.ExperimentModel


interface ExperimentsConfig {

    fun isFirstExperimentEnabled(): Boolean
    fun isSecondExperimentEnabled(): Boolean
    fun isThirdExperimentEnabled(): Boolean

    fun getAllExperiments(): List<ExperimentModel>

}