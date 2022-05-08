package com.example.demo.Historial

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.ReportViewModel
import com.example.demo.databinding.FragmentHistorialBinding

class HistorialFragment : Fragment(), HistorialAdapter.OnReportClickListener {

    private val model: ReportViewModel by navGraphViewModels(R.id.app_navigation)
    private var _binding: FragmentHistorialBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistorialBinding.inflate(inflater, container, false)

        val historialList: RecyclerView = binding.list
        val historialAdapter = HistorialAdapter(this) // (2)
        historialList.adapter = historialAdapter // (3)
        historialAdapter.historials = Historial.data // (4)

        historialList.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        val view = binding.root
        return view
    }

    override fun onItemClick(historial: Historial) {
        model.setTitle(historial.title)
        model.setFishingType(historial.fishingType)
        val bitmap = BitmapFactory.decodeResource(resources, historial.featuredImage)

        model.setImage(bitmap)
        findNavController().navigate(R.id.report_display_fragment)
    }
}