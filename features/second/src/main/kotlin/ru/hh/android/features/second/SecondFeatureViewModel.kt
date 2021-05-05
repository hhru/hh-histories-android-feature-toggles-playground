package ru.hh.android.features.second

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.hh.android.core.experiments.ExperimentsConfig
import toothpick.InjectConstructor


@InjectConstructor
internal class SecondFeatureViewModel(
    experimentsConfig: ExperimentsConfig
) : ViewModel() {

    private val _experimentState = MutableLiveData(false)
    val experimentState: LiveData<Boolean> = _experimentState


    init {
        _experimentState.postValue(experimentsConfig.isSecondExperimentEnabled())
    }

}