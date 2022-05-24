package com.example.demo.fragment.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.adapter.ReportListAdapter
import com.example.demo.viewModel.ReportViewModel
import com.example.demo.model.Report
import com.example.demo.databinding.FragmentReportListBinding

class ReportListFragment : Fragment(), ReportListAdapter.OnReportClickListener {

    private val reportViewModel: ReportViewModel by navGraphViewModels(R.id.app_navigation)
    private var _binding: FragmentReportListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportListBinding.inflate(inflater, container, false)
        _binding!!.homeActionButton.setOnClickListener{ goHome() }

        val reportList: RecyclerView = binding.list
        val reportAdapter = ReportListAdapter(this)
        reportList.adapter = reportAdapter

        reportViewModel.allReports
            .observe(
                viewLifecycleOwner,
                Observer { reports ->
                    reports?.let { reportAdapter.setReports(it) }
                }
            )

        reportList.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )

        _binding!!.mapActionButton.setOnClickListener { goMaps() }

        val view = binding.root
        return view
    }

    override fun onItemClick(report: Report) {
        val action = ReportListFragmentDirections.goToReportDetailFromMyReportsAction(report)
         findNavController().navigate(action)
    }

    private fun goHome() {
        findNavController().navigate(R.id.home_fragment)
    }

    private fun goMaps() {
        findNavController().navigate(R.id.goToMultipleMapsFragmentFromReportListFragment)
    }

}