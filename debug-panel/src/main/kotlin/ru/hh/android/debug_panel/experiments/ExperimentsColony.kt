package ru.hh.android.debug_panel.experiments

import com.joom.colonist.AcceptSettlersViaCallback
import com.joom.colonist.Colony
import com.joom.colonist.ProduceSettlersViaConstructor
import com.joom.colonist.SelectSettlersBySuperType
import ru.hh.android.core.experiments.models.Experiment


@Colony
@SelectSettlersBySuperType(Experiment::class)
@ProduceSettlersViaConstructor
@AcceptSettlersViaCallback
@Target(AnnotationTarget.CLASS)
internal annotation class ExperimentsColony