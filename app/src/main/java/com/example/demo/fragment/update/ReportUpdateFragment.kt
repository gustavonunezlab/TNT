package com.example.demo.fragment.update

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.demo.R
import com.example.demo.databinding.FragmentReportUpdateBinding
import com.example.demo.fragment.add.REQUEST_IMAGE_CAPTURE
import com.example.demo.model.Report
import com.example.demo.viewModel.ReportViewModel

class ReportUpdateFragment : Fragment() {

    private var _binding: FragmentReportUpdateBinding? = null
    private val binding get() = _binding!!
    private val args: ReportUpdateFragmentArgs by navArgs()

    private lateinit var model: ReportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        model = ViewModelProvider(this)[ReportViewModel::class.java]
        val fishType = resources.getStringArray(R.array.fishType)
        val arrayAdapter = ArrayAdapter(view.context, R.layout.dropdown_item, fishType)

        _binding!!.spinner.adapter = arrayAdapter
        _binding!!.helpButton.setOnClickListener { fishingInfo() }
        _binding!!.photoButton.setOnClickListener { takePhoto() }
        _binding!!.updateButton.setOnClickListener { updateReport() }

        _binding!!.updateTitleTextInput.setText(args.currentReport.title)

        val arrayPosition = arrayAdapter.getPosition(args.currentReport.fishing_type)
        _binding!!.spinner.setSelection(arrayPosition)

        return view
    }

    private fun fishingInfo() {
        findNavController().navigate(R.id.fishing_info_activity)
    }

    private fun takePhoto() {
        val myFragment: Fragment = this
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        myFragment.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
    }

    private fun updateReport() {

        val title = _binding?.updateTitleTextInput?.text.toString()
        val fishingType = _binding?.spinner?.selectedItem.toString()
        val date = args.currentReport.date

        val updatedReport = Report(args.currentReport.id, title, fishingType, date)
        model.updateReport(updatedReport)
        Toast.makeText(activity, "Reporte editado correctamente", Toast.LENGTH_LONG).show()
        val action = ReportUpdateFragmentDirections.goToReportDetailFromUpdateReportAction(updatedReport)
        findNavController().navigate(action)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            //  model.setImage(imageBitmap)
            _binding!!.updateCaptureImageView.setImageBitmap(imageBitmap)
        }
    }

}