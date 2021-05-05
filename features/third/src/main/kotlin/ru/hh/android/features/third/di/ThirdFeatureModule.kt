package ru.hh.android.features.third.di

import toothpick.config.Module
import toothpick.ktp.binding.bind


internal class ThirdFeatureModule : Module() {

    init {
        bind<ThirdFeatureModule>().singleton()
    }

}