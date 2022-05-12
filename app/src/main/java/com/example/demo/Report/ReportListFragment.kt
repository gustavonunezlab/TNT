package com.example.demo.Report

import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore.Images.Media.getBitmap
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.ReportViewModel
import com.example.demo.database.Report
import com.example.demo.databinding.FragmentReportListBinding

class ReportListFragment : Fragment(), ReportListAdapter.OnReportClickListener {

    // private lateinit var reportViewModel: ReportViewModel
    private val reportViewModel: ReportViewModel by navGraphViewModels(R.id.app_navigation)//by navGraphViewModels(R.id.app_navigation)
    private var _binding: FragmentReportListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportListBinding.inflate(inflater, container, false)

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

        val view = binding.root
        return view
    }

    override fun onItemClick(report: Report) {
        reportViewModel.setTitle(report.title)
        reportViewModel.setFishingType(report.fishing_type)
        reportViewModel.setDate(report.date)
        //TODO: ver tema imagen

        //  val bitmap = BitmapFactory.decodeResource(resources, report.featured_image)
        // reportViewModel.setImage(bitmap)

        findNavController().navigate(R.id.report_display_fragment)
    }


}