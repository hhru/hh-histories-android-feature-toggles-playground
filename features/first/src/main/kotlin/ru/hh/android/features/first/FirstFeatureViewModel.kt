package ru.hh.android.features.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.hh.android.core.experiments.models.extensions.isUserAffected
import ru.hh.android.features.first.experiment.FirstFeatureExperiment
import javax.inject.Inject


@HiltViewModel
internal class FirstFeatureViewModel @Inject constructor() : ViewModel() {

    private val _experimentState = MutableLiveData(false)
    val experimentState: LiveData<Boolean> = _experimentState


    init {
        _experimentState.postValue(FirstFeatureExperiment().isUserAffected())
    }

}