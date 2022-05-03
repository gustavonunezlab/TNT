package com.example.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContestAdapter: RecyclerView.Adapter<ContestAdapter.ContestViewHolder>() {

    var contests = listOf<Contest>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContestAdapter.ContestViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.contest_item, parent, false)
        return ContestAdapter.ContestViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContestAdapter.ContestViewHolder, position: Int) {
        val contest = contests[position]
        holder.bind(contest)
    }

    class ContestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.contest_title)
        val description: TextView = view.findViewById(R.id.contest_description)
        val featuredImage: ImageView = view.findViewById(R.id.featured_image)

        fun bind(contest: Contest) {
            title.text = contest.title
            description.text = contest.description
            featuredImage.setImageResource(contest.featuredImage)
            featuredImage.scaleType
        }
    }

    override fun getItemCount() = contests.size
}
