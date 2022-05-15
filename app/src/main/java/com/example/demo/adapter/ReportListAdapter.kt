package com.example.demo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.model.Report

class ReportListAdapter(
    private val itemClickListener: OnReportClickListener
) : RecyclerView.Adapter<ReportListAdapter.ReportViewHolder>() {

    private var reports = emptyList<Report>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.report_item, parent, false)
        return ReportViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val report = reports[position]
        holder.bind(report)
    }

    internal fun setReports(reports: List<Report>) {
        this.reports = reports
        notifyDataSetChanged()
    }

    inner class ReportViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.report_title)
        val fishingType: TextView = view.findViewById(R.id.report_fishingType)
        val date: TextView = view.findViewById(R.id.report_dateTextView)

        //TODO: ver tema imagen
        val featuredImage: ImageView = view.findViewById(R.id.featured_image)

        fun bind(report: Report) {
            itemView.setOnClickListener { itemClickListener.onItemClick(report) }

            title.text = report.title
            fishingType.text = report.fishing_type
            date.text = report.date
            //  featuredImage.setImageBitmap(report.featured_image)
            //  featuredImage.scaleType

        }

    }

    override fun getItemCount() = reports.size

    interface OnReportClickListener {
        fun onItemClick(report: Report)
    }
}