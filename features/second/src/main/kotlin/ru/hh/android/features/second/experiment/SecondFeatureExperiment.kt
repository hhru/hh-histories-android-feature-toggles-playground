package ru.hh.android.features.second.experiment

import ru.hh.android.core.experiments.models.Experiment


// TODO [class-loader-dex-problems] This class must have an 'Experiment' suffix in its name.
internal class SecondFeatureExperiment : Experiment {

    override val key: String get() = "second_key"

}