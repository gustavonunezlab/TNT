package com.example.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistorialAdapter: RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder>() {

    var historials = listOf<Historial>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.historial_item, parent, false)
        return HistorialViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistorialViewHolder, position: Int) {
        val historial = historials[position]
        holder.bind(historial)
    }

    class HistorialViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.historial_title)
        val description: TextView = view.findViewById(R.id.historial_description)
        val featuredImage: ImageView = view.findViewById(R.id.featured_image)


        fun bind(historial: Historial) {
            title.text = historial.title
            description.text = historial.description
            featuredImage.setImageResource(historial.featuredImage)
            featuredImage.scaleType
        }
    }

    override fun getItemCount() = historials.size
}