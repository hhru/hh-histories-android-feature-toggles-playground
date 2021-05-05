package ru.hh.android.features.second.di

import ru.hh.android.features.second.SecondFeatureViewModel
import toothpick.config.Module
import toothpick.ktp.binding.bind


internal class SecondFeatureModule : Module() {

    init {
        bind<SecondFeatureViewModel>().singleton()
    }

}