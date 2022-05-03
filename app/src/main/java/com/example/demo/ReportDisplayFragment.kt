package com.example.demo

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.navArgs
import com.example.demo.databinding.FragmentReportDisplayBinding
import java.util.*

class ReportDisplayFragment : Fragment() {

    private var _binding: FragmentReportDisplayBinding? = null
    private val binding get() = _binding!!
    private val args: ReportDisplayFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportDisplayBinding.inflate(inflater, container, false)
        _binding!!.titleTextView.text = args.title
        _binding!!.fishingTypeTextView.text = args.fishingType

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")

        val currentDate = sdf.format(Date())
        _binding!!.dateTextView.text = currentDate
        val view = binding.root
        return view
    }


}