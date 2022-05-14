package com.example.demo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.demo.R
import com.example.demo.databinding.FragmentOptionsBinding

class OptionsFragment : Fragment() {
    private var _binding: FragmentOptionsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOptionsBinding.inflate(inflater, container, false)

        _binding!!.myReportsButton.setOnClickListener { showMyReports() }
        _binding!!.newReportButton.setOnClickListener { newReport() }
        _binding!!.contestsButton.setOnClickListener { showContests() }
        _binding!!.regulationsButton.setOnClickListener { showRegulations() }
        _binding!!.logOutButton.setOnClickListener { logOut() }

        val view = binding.root
        return view
    }

    private fun showMyReports() {
        findNavController().navigate(R.id.goToMyReportsAction)
    }

    private fun newReport() {
        findNavController().navigate(R.id.goToNewReportAction)
    }

    private fun showContests() {
        findNavController().navigate(R.id.goToContestsAction)
    }

    private fun showRegulations() {
        findNavController().navigate(R.id.goToRegulationsAction)
    }

    private fun logOut() {
        System.exit(0)
    }
}