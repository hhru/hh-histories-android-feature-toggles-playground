package ru.hh.android.core.experiments.di

import ru.hh.android.core.experiments.domain.ExperimentsInteractor
import toothpick.config.Module
import toothpick.ktp.binding.bind


class ExperimentsModule : Module() {

    init {
        bind<ExperimentsInteractor>().singleton()
    }

}