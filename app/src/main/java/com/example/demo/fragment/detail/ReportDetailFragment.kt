package com.example.demo.fragment.detail

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.example.demo.R
import com.example.demo.databinding.FragmentReportDetailBinding
import com.example.demo.viewModel.ReportViewModel

class ReportDetailFragment : Fragment() {

    private var _binding: FragmentReportDetailBinding? = null
    private val binding get() = _binding!!
    private val args: ReportDetailFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportDetailBinding.inflate(inflater, container, false)

        _binding!!.titleTextView.text = args.currentReport.title    //model.title.value
        _binding!!.fishingTypeTextView.text = args.currentReport.fishing_type //model.fishingType.value
        _binding!!.dateTextView.text = args.currentReport.date //model.date.value

       // val image = model.image.value
       // _binding!!.captureImageView.setImageBitmap(image)

        _binding!!.doneButton.setOnClickListener { done() }
        _binding!!.updateButton.setOnClickListener { updateReport() }

        val view = binding.root
        return view
    }

    private fun done() {
        findNavController().navigate(R.id.options_fragment)
    }
    private fun updateReport() {
        val action = ReportDetailFragmentDirections.goToReportUpdateAction(args.currentReport)
        findNavController().navigate(action)
    }

}