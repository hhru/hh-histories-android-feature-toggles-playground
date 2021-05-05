package ru.hh.ftplayground

import android.app.Application
import ru.hh.android.core.di.ScopeNames
import ru.hh.android.core.experiments.Experiments
import ru.hh.android.core.experiments.domain.ExperimentsInteractor
import ru.hh.ftplayground.di.DI
import toothpick.Toothpick


class FTPApp : Application() {

    override fun onCreate() {
        super.onCreate()

        DI.init(this)
        setupExperiments()
    }

    private fun setupExperiments() {
        val interactor = Toothpick
            .openScope(ScopeNames.APP_SCOPE)
            .getInstance(ExperimentsInteractor::class.java)

        Experiments.init(interactor)
    }

}