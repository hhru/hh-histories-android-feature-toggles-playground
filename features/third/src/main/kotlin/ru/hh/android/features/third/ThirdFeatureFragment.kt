package ru.hh.android.features.third

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.hh.android.core.di.ScopeNames
import ru.hh.android.features.third.di.ThirdFeatureModule
import toothpick.Toothpick
import javax.inject.Inject


internal class ThirdFeatureFragment : Fragment(R.layout.fragment_feature_third) {

    private companion object {
        const val SCOPE_NAME = "third_feature"
    }


    @Inject
    internal lateinit var viewModel: ThirdFeatureViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        val scope = Toothpick.openScopes(ScopeNames.APP_SCOPE, SCOPE_NAME)
        scope.installModules(
            ThirdFeatureModule()
        )
        scope.inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.experimentState.observe(this) {
            view.findViewById<TextView>(R.id.fragment_feature_third__text__experiment_state)
                .text = "Experiment (third_key) state: $it"
        }
    }

}