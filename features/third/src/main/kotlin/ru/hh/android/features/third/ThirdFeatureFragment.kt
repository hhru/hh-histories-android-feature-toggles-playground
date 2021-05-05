package ru.hh.android.features.third

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
internal class ThirdFeatureFragment : Fragment(R.layout.fragment_feature_third) {

    private val viewModel by viewModels<ThirdFeatureViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.experimentState.observe(this) {
            view.findViewById<TextView>(R.id.fragment_feature_third__text__experiment_state)
                .text = "Experiment (third_key) state: $it"
        }
    }

}