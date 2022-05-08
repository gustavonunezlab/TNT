package com.example.demo

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.example.demo.databinding.FragmentReportDisplayBinding
import java.util.*

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

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")

        val currentDate = sdf.format(Date())
        _binding!!.dateTextView.text = currentDate

        model.setDate(currentDate)

        val image = model.image.value
        _binding!!.captureImageView.setImageBitmap(image)

        _binding!!.updateButton.setOnClickListener { updateReport() }

        val view = binding.root
        return view
    }

    private fun updateReport() {
        findNavController().popBackStack()
    }

}