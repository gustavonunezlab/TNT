package com.example.demo.listing

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.database.Report

// TODO: Refactorizar esta clase y la clase Historial.
class ReportListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<ReportListAdapter.ReportViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var reports = emptyList<Report>() // Copia cache de los partidos

    inner class ReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
     //   val reportItemView: TextView = itemView.findViewById(R.id.textView_resultado)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val itemView = inflater.inflate(R.layout.historial_item, parent, false)
        return ReportViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val report = reports[position]
        val text = "${report.title} ${report.fishing_type} - ${report.date} "
       // holder.reportItemView.text = text
    }

    internal fun setReports(reports: List<Report>) {
        this.reports = reports
        notifyDataSetChanged()
    }

    override fun getItemCount() = reports.size
}