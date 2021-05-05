package ru.hh.android.features.first.di

import ru.hh.android.features.first.FirstFeatureViewModel
import toothpick.config.Module
import toothpick.ktp.binding.bind


internal class FirstFeatureModule : Module() {

    init {
        bind<FirstFeatureViewModel>().singleton()
    }

}