package ru.hh.feature_toggles_playground.di

import android.app.Application
import android.content.Context
import toothpick.config.Module
import toothpick.ktp.binding.bind


internal class RootModule(application: Application) : Module() {

    init {
        bind<Context>().toInstance(application)
    }

}