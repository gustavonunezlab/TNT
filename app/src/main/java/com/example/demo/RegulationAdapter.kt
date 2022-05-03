package com.example.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RegulationAdapter: RecyclerView.Adapter<RegulationAdapter.RegulationViewHolder>() {

    var regulations = listOf<Regulation>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegulationAdapter.RegulationViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.regulation_item, parent, false)
        return RegulationAdapter.RegulationViewHolder(view)
    }

    override fun onBindViewHolder(holder: RegulationAdapter.RegulationViewHolder, position: Int) {
        val regulation = regulations[position]
        holder.bind(regulation)
    }

    class RegulationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.regulation_title)
        val description: TextView = view.findViewById(R.id.regulation_description)

        fun bind(regulation: Regulation) {
            title.text = regulation.title
            description.text = regulation.description

        }
    }
    override fun getItemCount() = regulations.size
}