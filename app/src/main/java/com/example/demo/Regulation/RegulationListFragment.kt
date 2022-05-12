package com.example.demo.Regulation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.databinding.FragmentRegulationListBinding

class RegulationListFragment : Fragment() {

    private var _binding: FragmentRegulationListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegulationListBinding.inflate(inflater, container, false)

        val regulationList: RecyclerView = binding.list
        val regulationListAdapter = RegulationListAdapter() // (2)
        regulationList.adapter = regulationListAdapter // (3)
        regulationListAdapter.regulations = Regulation.data // (4)

        val view = binding.root
        return view
    }

}