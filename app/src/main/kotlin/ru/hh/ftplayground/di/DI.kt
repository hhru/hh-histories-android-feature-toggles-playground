package ru.hh.ftplayground.di

import android.app.Application
import ru.hh.android.core.di.ScopeNames
import ru.hh.android.core.experiments.di.ExperimentsModule
import ru.hh.feature_toggles_playground.di.RootModule
import ru.hh.ftplayground.BuildConfig
import toothpick.Scope
import toothpick.Toothpick
import toothpick.configuration.Configuration


object DI {

    fun init(application: Application) {
        if (Toothpick.isScopeOpen(ScopeNames.APP_SCOPE)) return

        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment())
        } else {
            Toothpick.setConfiguration(Configuration.forProduction()) // Production
        }

        Toothpick.openScope(ScopeNames.APP_SCOPE)
            .installModules(
                RootModule(application),
                ExperimentsModule(),
            )
    }


}