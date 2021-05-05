package ru.hh.android.features.third.experiment

import ru.hh.android.core.experiments.models.Experiment


// TODO [class-loader-dex-problems] This class must have an 'Experiment' suffix in its name.
internal class ThirdFeatureExperiment : Experiment {

    override val key: String get() = "third_key"

}