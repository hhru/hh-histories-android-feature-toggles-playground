package ru.hh.android.features.second.experiment

import ru.hh.android.core.experiments.models.Experiment
import ru.hh.android.core.experiments.models.ExperimentAnnotation


@ExperimentAnnotation
internal class SecondFeatureExperiment : Experiment {

    override val key: String get() = "second_key"

}