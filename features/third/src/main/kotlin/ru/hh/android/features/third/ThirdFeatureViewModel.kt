package ru.hh.android.features.third

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.hh.android.core.experiments.models.extensions.isUserAffected
import ru.hh.android.features.third.experiment.ThirdFeatureExperiment
import javax.inject.Inject


@HiltViewModel
internal class ThirdFeatureViewModel @Inject constructor() : ViewModel() {

    private val _experimentState = MutableLiveData(false)
    val experimentState: LiveData<Boolean> = _experimentState


    init {
        _experimentState.postValue(ThirdFeatureExperiment().isUserAffected())
    }

}