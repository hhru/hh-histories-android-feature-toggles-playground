package ru.hh.android.features.second

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.hh.android.core.di.ScopeNames
import ru.hh.android.features.second.di.SecondFeatureModule
import toothpick.Toothpick
import javax.inject.Inject


internal class SecondFeatureFragment : Fragment(R.layout.fragment_feature_second) {

    private companion object {
        const val SCOPE_NAME = "second_feature"
    }


    @Inject
    internal lateinit var viewModel: SecondFeatureViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        val scope = Toothpick.openScopes(ScopeNames.APP_SCOPE, SCOPE_NAME)
        scope.installModules(
            SecondFeatureModule()
        )
        scope.inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.experimentState.observe(this) {
            view.findViewById<TextView>(R.id.fragment_feature_second__text__experiment_state)
                .text = "Experiment (second_key) state: $it"
        }
    }

}