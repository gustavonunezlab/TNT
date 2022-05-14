package com.example.demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.navigation.navOptions
import com.example.demo.Report.ReportListAdapter
import com.example.demo.database.Report

import com.example.demo.databinding.FragmentMainBinding

class MainFragment : Fragment(), ReportListAdapter.OnReportClickListener {
    private val reportViewModel: ReportViewModel by navGraphViewModels(R.id.app_navigation)//by navGraphViewModels(R.id.app_navigation)
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        _binding!!.loginButton.setOnClickListener{ loginApp() }
        _binding!!.registerTextView.setOnClickListener{ registerApp() }

        val reportAdapter = ReportListAdapter(this)
        reportViewModel.allReports
            .observe(
                viewLifecycleOwner,
                Observer { reports ->
                    reports?.let { reportAdapter.setReports(it) }
                }
            )
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loginApp() {
        findNavController().navigate(R.id.loginAction)
    }
    private fun registerApp() {
    //    findNavController().navigate(R.id.registerAction)
        Toast.makeText(activity, "En construcción", Toast.LENGTH_LONG).show()
    }
    override fun onItemClick(report: Report) {

    }
}