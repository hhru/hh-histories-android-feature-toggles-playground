package ru.hh.android.features.first

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FirstFeatureFragment : Fragment(R.layout.fragment_feature_first) {

    private val viewModel by viewModels<FirstFeatureViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.experimentState.observe(this) {
            view.findViewById<TextView>(R.id.fragment_feature_first__text__experiment_state)
                .text = "Experiment (first_key) state: $it"
        }
    }

}