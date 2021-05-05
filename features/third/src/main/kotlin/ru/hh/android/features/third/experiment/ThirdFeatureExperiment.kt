package ru.hh.android.features.third.experiment

import ru.hh.android.core.experiments.models.Experiment
import ru.hh.android.core.experiments.models.ExperimentAnnotation


@ExperimentAnnotation
internal class ThirdFeatureExperiment : Experiment {

    override val key: String get() = "third_key"

}