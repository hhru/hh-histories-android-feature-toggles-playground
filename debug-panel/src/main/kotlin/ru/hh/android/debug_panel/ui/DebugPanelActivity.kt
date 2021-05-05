package ru.hh.android.debug_panel.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.hh.android.core.di.ScopeNames
import ru.hh.android.debug_panel.R
import ru.hh.android.debug_panel.di.DebugPanelModule
import toothpick.Toothpick
import javax.inject.Inject


class DebugPanelActivity : AppCompatActivity(R.layout.activity_debug_panel) {

    private companion object {
        private const val SCOPE_NAME = "debug_panel"
    }


    @Inject
    internal lateinit var viewModel: DebugPanelViewModel

    private val recyclerAdapter by lazy {
        ExperimentsAdapter(
            activityContext = this,
            items = emptyList(),
            onSwitchChanged = { viewModel.updateExperiment(it) }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val scope = Toothpick.openScopes(ScopeNames.APP_SCOPE, SCOPE_NAME)
        scope.installModules(
            DebugPanelModule()
        )
        scope.inject(this)

        super.onCreate(savedInstanceState)

        setupRecyclerView()
        findViewById<Button>(R.id.activity_debug_panel__button).setOnClickListener {
            viewModel.restartApp(this)

        }

        viewModel.experimentsSet.observe(this) {
            recyclerAdapter.submitList(it)
        }
    }


    private fun setupRecyclerView() {
        val recycler = findViewById<RecyclerView>(R.id.activity_debug_panel__recycler)

        with(recycler) {
            layoutManager = LinearLayoutManager(this@DebugPanelActivity)
            adapter = recyclerAdapter
        }
    }

}