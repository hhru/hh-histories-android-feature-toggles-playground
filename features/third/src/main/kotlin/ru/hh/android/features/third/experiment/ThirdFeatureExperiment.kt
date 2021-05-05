package ru.hh.android.features.third.experiment

import ru.hh.android.core.experiments.models.Experiment


// TODO [manual-problem] We need to expose this experiment for every module to use constructor
//   outside current module
class ThirdFeatureExperiment : Experiment {

    override val key: String get() = "third_key"

}