package ru.hh.android.features.first.experiment

import ru.hh.android.core.experiments.models.Experiment


// TODO [class-loader-dex-problems] This class must have an 'Experiment' suffix in its name.
internal class FirstFeatureExperiment : Experiment {

    override val key: String get() = "first_key"

}