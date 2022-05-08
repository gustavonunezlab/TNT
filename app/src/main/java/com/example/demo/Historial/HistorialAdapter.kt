package com.example.demo.Historial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R

class HistorialAdapter(
    private val itemClickListener: OnReportClickListener
) : RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder>() {


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

    inner class HistorialViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.historial_title)
        val description: TextView = view.findViewById(R.id.historial_description)
        val featuredImage: ImageView = view.findViewById(R.id.featured_image)

        fun bind(historial: Historial) {
            itemView.setOnClickListener { itemClickListener.onItemClick(historial) }
            title.text = historial.title
            description.text = historial.fishingType
            featuredImage.setImageResource(historial.featuredImage)
            featuredImage.scaleType

        }

    }
    override fun getItemCount() = historials.size

    interface OnReportClickListener {
        fun onItemClick(historial: Historial)
    }
}