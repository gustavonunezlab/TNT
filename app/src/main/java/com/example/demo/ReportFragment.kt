package com.example.demo

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.demo.databinding.FragmentReportBinding

const val REQUEST_IMAGE_CAPTURE = 1

class ReportFragment : Fragment() {

    private var _binding: FragmentReportBinding? = null
    private val binding get() = _binding!!

    private val model: ReportViewModel by navGraphViewModels(R.id.app_navigation)

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
        _binding!!.sendButton.setOnClickListener { sendData() }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fishingInfo() {
        //ir al nuevo fragment FishingInfoFragment, aun no esta creado. Va a una activity
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
            _binding!!.captureImageView.setImageBitmap(imageBitmap)
        }
    }

    private fun sendData() {
        // Comentada la condicion para realizar pruebas.
        //  if(checkData()) {
        //ir al nuevo fragment ReportDisplayFragment. Pasar parámetros a mostrar
        val title = _binding?.titleTextInput?.text.toString()
        Toast.makeText(activity, title, Toast.LENGTH_LONG).show()
        model.setTitle(title)

        val fishingType = _binding?.autoCompleteTextView?.text.toString()
        model.setFishingType(fishingType)
        // val action = ReportFragmentDirections.reportDisplayAction(title, fishingType)

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