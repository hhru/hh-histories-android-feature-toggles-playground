package ru.hh.android.features.first

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.hh.android.core.di.ScopeNames
import ru.hh.android.features.first.di.FirstFeatureModule
import toothpick.Toothpick
import javax.inject.Inject


class FirstFeatureFragment : Fragment(R.layout.fragment_feature_first) {

    private companion object {
        const val SCOPE_NAME = "first_feature"
    }


    @Inject
    internal lateinit var viewModel: FirstFeatureViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        val scope = Toothpick.openScopes(ScopeNames.APP_SCOPE, SCOPE_NAME)
        scope.installModules(
            FirstFeatureModule()
        )
        scope.inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.experimentState.observe(this) {
            view.findViewById<TextView>(R.id.fragment_feature_first__text__experiment_state)
                .text = "Experiment (first_key) state: $it"
        }
    }

}