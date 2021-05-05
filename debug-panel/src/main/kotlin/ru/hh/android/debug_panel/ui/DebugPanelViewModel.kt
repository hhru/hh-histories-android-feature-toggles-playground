package ru.hh.android.debug_panel.ui

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.processphoenix.ProcessPhoenix
import ru.hh.android.core.experiments.ExperimentsConfig
import ru.hh.android.core.experiments.models.ExperimentModel
import ru.hh.android.debug_panel.domain.DebugExperimentsInteractor
import toothpick.InjectConstructor


@InjectConstructor
internal class DebugPanelViewModel(
    private val applicationContext: Context,
    private val debugExperimentsInteractor: DebugExperimentsInteractor,
    private val experimentsConfig: ExperimentsConfig
) : ViewModel() {

    private val _experimentsSet = MutableLiveData<List<ExperimentModel>>(emptyList())
    val experimentsSet: LiveData<List<ExperimentModel>> = _experimentsSet


    init {
        reloadExperimentsList()
    }


    fun updateExperiment(experimentModel: ExperimentModel) {
        debugExperimentsInteractor.changeExperiment(experimentModel)
        reloadExperimentsList()
    }

    private fun reloadExperimentsList() {
        _experimentsSet.postValue(experimentsConfig.getAllExperiments())
    }

    fun restartApp(activity: Activity) {
        ProcessPhoenix.triggerRebirth(
            activity,
            applicationContext.packageManager.getLaunchIntentForPackage(applicationContext.packageName)
        )
    }

}