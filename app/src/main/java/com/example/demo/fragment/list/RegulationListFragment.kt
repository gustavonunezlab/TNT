package com.example.demo.fragment.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.model.Regulation
import com.example.demo.adapter.RegulationListAdapter
import com.example.demo.databinding.FragmentRegulationListBinding

class RegulationListFragment : Fragment() {

    private var _binding: FragmentRegulationListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegulationListBinding.inflate(inflater, container, false)
        _binding!!.homeActionButton.setOnClickListener{ goHome() }

        if (findNavController().previousBackStackEntry?.destination?.displayName!! == "com.example.demo:id/main_fragment") {
            _binding!!.homeActionButton.hide()
        }

        val regulationList: RecyclerView = binding.list
        val regulationListAdapter = RegulationListAdapter()
        regulationList.adapter = regulationListAdapter
        regulationListAdapter.regulations = Regulation.data

        val view = binding.root
        return view
    }

    private fun goHome() {
        findNavController().navigate(R.id.home_fragment)
    }

}