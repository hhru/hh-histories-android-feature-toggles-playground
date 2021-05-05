package ru.hh.android.debug_panel.ui

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jakewharton.processphoenix.ProcessPhoenix
import ru.hh.android.core.experiments.models.Experiment
import ru.hh.android.core.experiments.models.ExperimentModel
import ru.hh.android.core.experiments.models.extensions.isUserAffected
import ru.hh.android.debug_panel.domain.DebugExperimentsInteractor
import toothpick.InjectConstructor
import java.util.*


@InjectConstructor
internal class DebugPanelViewModel(
    private val applicationContext: Context,
    private val debugExperimentsInteractor: DebugExperimentsInteractor,
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

    fun restartApp(activity: Activity) {
        ProcessPhoenix.triggerRebirth(
            activity,
            applicationContext.packageManager.getLaunchIntentForPackage(applicationContext.packageName)
        )
    }

    private fun reloadExperimentsList() {
        _experimentsSet.postValue(getAllExperiments())
    }

    private fun getAllExperiments(): List<ExperimentModel> {
        // TODO [service-loader-problem] Another opportunity for merge conflicts
        //   in META-INF/services/ru.hh.android.core.experiments.models.Experiment
        return ServiceLoader.load(Experiment::class.java).map { experiment ->
            ExperimentModel(
                key = experiment.key,
                isUserAffected = experiment.isUserAffected()
            )
        }
    }

}