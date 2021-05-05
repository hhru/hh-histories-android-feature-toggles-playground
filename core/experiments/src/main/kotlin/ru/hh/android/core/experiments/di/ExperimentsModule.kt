package ru.hh.android.core.experiments.di

import ru.hh.android.core.experiments.ExperimentsConfig
import ru.hh.android.core.experiments.ExperimentsConfigImpl
import toothpick.config.Module
import toothpick.ktp.binding.bind


class ExperimentsModule : Module() {

    init {
        bind<ExperimentsConfig>().toClass<ExperimentsConfigImpl>()
    }

}