package ru.hh.android.debug_panel.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import ru.hh.android.core.experiments.models.ExperimentModel
import ru.hh.android.debug_panel.R


internal class ExperimentsAdapter(
    activityContext: Context,
    private var items: List<ExperimentModel>,
    private val onSwitchChanged: (ExperimentModel) -> Unit
) : RecyclerView.Adapter<ExperimentsAdapter.ExperimentsViewHolder>() {

    private val layoutInflater = LayoutInflater.from(activityContext)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperimentsViewHolder {
        return ExperimentsViewHolder(
            layoutInflater.inflate(R.layout.item_experiment, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExperimentsViewHolder, position: Int) {
        val item = items[position]

        holder.title.text = item.key
        holder.switch.isChecked = item.isUserAffected
        holder.switch.setOnCheckedChangeListener { _, _ ->
            onSwitchChanged.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    fun submitList(items: List<ExperimentModel>) {
        this.items = items
        notifyDataSetChanged()
    }


    class ExperimentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.item_experiment__text_view)
        val switch: SwitchCompat = itemView.findViewById(R.id.item_experiment__switch)
    }


}