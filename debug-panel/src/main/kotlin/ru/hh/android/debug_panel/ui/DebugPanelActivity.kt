package ru.hh.android.debug_panel.ui

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.hh.android.debug_panel.R


@AndroidEntryPoint
class DebugPanelActivity : AppCompatActivity(R.layout.activity_debug_panel) {

    private val viewModel by viewModels<DebugPanelViewModel>()

    private val recyclerAdapter by lazy {
        ExperimentsAdapter(
            activityContext = this,
            items = emptyList(),
            onSwitchChanged = { viewModel.updateExperiment(it) }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
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