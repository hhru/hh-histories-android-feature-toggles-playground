package ru.hh.android.features.first.experiment

import ru.hh.android.core.experiments.models.Experiment
import ru.hh.android.core.experiments.models.ExperimentAnnotation


@ExperimentAnnotation
internal class FirstFeatureExperiment : Experiment {

    override val key: String get() = "first_key"

}