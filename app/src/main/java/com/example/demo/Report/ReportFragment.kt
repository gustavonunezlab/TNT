package com.example.demo

import android.content.Intent
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.demo.database.Report
import com.example.demo.databinding.FragmentReportBinding
import java.util.*

const val REQUEST_IMAGE_CAPTURE = 1

class ReportFragment : Fragment() {

    private var _binding: FragmentReportBinding? = null
    private val binding get() = _binding!!

    private val model: ReportViewModel by navGraphViewModels(R.id.app_navigation)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        val view = binding.root

        val fishType = resources.getStringArray(R.array.fishType)
        val arrayAdapter = ArrayAdapter(view.context, R.layout.dropdown_item, fishType)
        _binding!!.autoCompleteTextView.setAdapter(arrayAdapter)


        _binding!!.helpButton.setOnClickListener { fishingInfo() }
        _binding!!.photoButton.setOnClickListener { takePhoto() }
        _binding!!.sendButton.setOnClickListener { sendReport() }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fishingInfo() {
        findNavController().navigate(R.id.fishingInfoAction)
    }

    private fun takePhoto() {
        val myFragment: Fragment = this
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        myFragment.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            model.setImage(imageBitmap)
            _binding!!.captureImageView.setImageBitmap(imageBitmap)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun sendReport() {
        // Comentada la condicion para realizar pruebas.
        //  if(checkData()) {
        val title = _binding?.titleTextInput?.text.toString()
        model.setTitle(title)

        val fishingType = _binding?.autoCompleteTextView?.text.toString()
        model.setFishingType(fishingType)

        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(Date())
        model.setDate(currentDate)

        val report = Report(model.title.value!!, model.fishingType.value!!, model.date.value!!)
        model.insert(report)

        findNavController().navigate(R.id.reportDisplayAction)
        //  }
    }

    private fun checkData(): Boolean {
        if (_binding!!.autoCompleteTextView.text.toString() == "Tipo de pesca") {
            Toast.makeText(activity, "Seleccione un tipo de pesca", Toast.LENGTH_LONG).show()
            return false
        } else if (_binding!!.titleTextInput.text?.isEmpty() == true) {
            Toast.makeText(activity, "Ingrese un título para la imagen", Toast.LENGTH_LONG).show()
            return false
        } else if (_binding!!.captureImageView.getDrawable() == null) {
            Toast.makeText(activity, "Capture una imagen", Toast.LENGTH_LONG).show()
            return false
        } else {
            return true
        }
    }
}