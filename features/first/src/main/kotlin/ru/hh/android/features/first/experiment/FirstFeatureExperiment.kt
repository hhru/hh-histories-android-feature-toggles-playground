package ru.hh.android.features.first.experiment

import org.kohsuke.MetaInfServices
import ru.hh.android.core.experiments.models.Experiment


@MetaInfServices
internal class FirstFeatureExperiment : Experiment {

    override val key: String get() = "first_key"

}