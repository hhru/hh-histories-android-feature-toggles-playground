package ru.hh.android.features.second

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
internal class SecondFeatureFragment : Fragment(R.layout.fragment_feature_second) {

    private val viewModel by viewModels<SecondFeatureViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.experimentState.observe(this) {
            view.findViewById<TextView>(R.id.fragment_feature_second__text__experiment_state)
                .text = "Experiment (second_key) state: $it"
        }
    }

}