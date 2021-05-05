package ru.hh.android.core.experiments.domain

import android.content.Context
import android.content.SharedPreferences
import ru.hh.android.core.experiments.ExperimentsConstants
import toothpick.InjectConstructor


@InjectConstructor
internal class ExperimentsInteractor(
    applicationContext: Context
) {
    private val experimentsPrefs: SharedPreferences by lazy {
        applicationContext.getSharedPreferences(
            ExperimentsConstants.PREFS_NAME,
            Context.MODE_PRIVATE
        )
    }


    fun isUserAffected(experimentKey: String): Boolean {
        return experimentsPrefs.getBoolean(experimentKey, false)
    }

}