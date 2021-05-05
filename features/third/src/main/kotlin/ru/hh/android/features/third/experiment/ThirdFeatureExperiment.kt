package ru.hh.android.features.third.experiment

import org.kohsuke.MetaInfServices
import ru.hh.android.core.experiments.models.Experiment


@MetaInfServices
internal class ThirdFeatureExperiment : Experiment {

    override val key: String get() = "third_key"

}