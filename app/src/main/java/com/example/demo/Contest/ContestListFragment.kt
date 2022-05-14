package com.example.demo.Contest

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.databinding.FragmentContestListBinding

class ContestListFragment : Fragment(), ContestListAdapter.OnContestClickListener {

    private val model: ContestViewModel by navGraphViewModels(R.id.app_navigation)
    private var _binding: FragmentContestListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContestListBinding.inflate(inflater, container, false)

        val contestList: RecyclerView = binding.list
        val contestListAdapter = ContestListAdapter(this) // (2)
        contestList.adapter = contestListAdapter // (3)
        contestListAdapter.contests = Contest.data // (4)

        contestList.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        val view = binding.root
        return view
    }

    override fun onItemClick(contest: Contest) {
        model.setTitle(contest.title)
        model.setDescription(contest.description)
        model.setDate(contest.date)
        model.setTermsAndConditions(contest.termsAndConditions)

        val bitmap = BitmapFactory.decodeResource(resources, contest.featuredImage)
        model.setImage(bitmap)

        findNavController().navigate(R.id.contest_display_fragment)
    }

}