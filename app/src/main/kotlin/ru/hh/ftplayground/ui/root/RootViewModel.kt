package ru.hh.ftplayground.ui.root

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.hh.android.core.experiments.Experiments
import javax.inject.Inject


@HiltViewModel
internal class RootViewModel @Inject constructor(
    private val experiments: Experiments
) : ViewModel() {

    fun onInited() {
        // Simple method for assurance of instantiation
    }

}