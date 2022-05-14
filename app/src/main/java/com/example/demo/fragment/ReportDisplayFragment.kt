package com.example.demo.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.demo.R
import com.example.demo.viewModel.ReportViewModel
import com.example.demo.databinding.FragmentReportDisplayBinding

class ReportDisplayFragment : Fragment() {

    private var _binding: FragmentReportDisplayBinding? = null
    private val binding get() = _binding!!
    private val model: ReportViewModel by navGraphViewModels(R.id.app_navigation)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportDisplayBinding.inflate(inflater, container, false)

        _binding!!.titleTextView.text = model.title.value
        _binding!!.fishingTypeTextView.text = model.fishingType.value
        _binding!!.dateTextView.text = model.date.value

        val image = model.image.value
        _binding!!.captureImageView.setImageBitmap(image)

        _binding!!.updateButton.setOnClickListener { updateReport() }

        val view = binding.root
        return view
    }

    private fun updateReport() {
        findNavController().navigate(R.id.report_fragment)
    }

}