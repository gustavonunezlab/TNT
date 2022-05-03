package com.example.demo.Regulation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.databinding.FragmentRegulationBinding


class RegulationFragment : Fragment() {

    private var _binding: FragmentRegulationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegulationBinding.inflate(inflater, container, false)

        val regulationList: RecyclerView = binding.list
        val regulationAdapter = RegulationAdapter() // (2)
        regulationList.adapter = regulationAdapter // (3)
        regulationAdapter.regulations = Regulation.data // (4)

        val view = binding.root
        return view
    }

}