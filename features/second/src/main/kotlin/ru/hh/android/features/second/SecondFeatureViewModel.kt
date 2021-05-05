package ru.hh.android.features.second

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.hh.android.core.experiments.models.extensions.isUserAffected
import ru.hh.android.features.second.experiment.SecondFeatureExperiment
import toothpick.InjectConstructor


@InjectConstructor
internal class SecondFeatureViewModel : ViewModel() {

    private val _experimentState = MutableLiveData(false)
    val experimentState: LiveData<Boolean> = _experimentState


    init {
        _experimentState.postValue(SecondFeatureExperiment().isUserAffected())
    }

}