package ru.hh.android.features.third.experiment

import ru.hh.android.core.experiments.models.Experiment
import ru.hh.android.core.experiments.models.ExperimentSettler


@ExperimentSettler
internal class ThirdFeatureExperiment : Experiment {

    override val key: String get() = "third_key"

}