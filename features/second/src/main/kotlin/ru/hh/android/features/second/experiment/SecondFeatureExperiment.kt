package ru.hh.android.features.second.experiment

import org.kohsuke.MetaInfServices
import ru.hh.android.core.experiments.models.Experiment


@MetaInfServices
internal class SecondFeatureExperiment : Experiment {

    override val key: String get() = "second_key"

}