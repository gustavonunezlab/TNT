package com.example.demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.databinding.FragmentContestBinding

class ContestFragment : Fragment() {

    private var _binding: FragmentContestBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContestBinding.inflate(inflater, container, false)

        val contestList: RecyclerView = binding.list
        val contestAdapter = ContestAdapter() // (2)
        contestList.adapter = contestAdapter // (3)
        contestAdapter.contests = Contest.data // (4)

        val view = binding.root
        return view
    }


}