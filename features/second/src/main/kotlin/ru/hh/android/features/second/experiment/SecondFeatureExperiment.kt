package ru.hh.android.features.second.experiment

import ru.hh.android.core.experiments.models.Experiment


// TODO [manual-problem] We need to expose this experiment for every module to use constructor
//   outside current module
class SecondFeatureExperiment : Experiment {

    override val key: String get() = "second_key"

}