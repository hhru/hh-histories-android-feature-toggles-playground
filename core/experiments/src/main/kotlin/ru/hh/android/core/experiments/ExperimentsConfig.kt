package ru.hh.android.core.experiments

import ru.hh.android.core.experiments.models.ExperimentModel


interface ExperimentsConfig {

    fun getAllExperiments(): List<ExperimentModel>

}