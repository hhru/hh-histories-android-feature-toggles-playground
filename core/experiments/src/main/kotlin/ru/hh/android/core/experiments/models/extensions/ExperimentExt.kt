package ru.hh.android.core.experiments.models.extensions

import ru.hh.android.core.experiments.Experiments
import ru.hh.android.core.experiments.models.Experiment


fun Experiment.isUserAffected(): Boolean = Experiments.isUserAffected(this)