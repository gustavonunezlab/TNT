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
import androidx.navigation.fragment.findNavController
import com.example.demo.databinding.FragmentFormBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

const val REQUEST_IMAGE_CAPTURE = 1
/**
 * A simple [Fragment] subclass.
 * Use the [FormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormFragment : Fragment() {

    private var _binding: FragmentFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFormBinding.inflate(inflater, container, false)
        val view = binding.root

        val fishType = resources.getStringArray(R.array.fishType)
        val arrayAdapter = ArrayAdapter(view.context, R.layout.dropdown_item, fishType)
        _binding!!.autoCompleteTextView.setAdapter(arrayAdapter)


        _binding!!.helpButton.setOnClickListener{ fishingInfo() }
        _binding!!.photoButton.setOnClickListener { takePhoto() }
        _binding!!.sendButton.setOnClickListener { sendData() }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fishingInfo() {
    //ir al nuevo fragment FishingInfoFragment, aun no esta creado
        findNavController().navigate(R.id.fishingInfoActivityAction)
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
        //ir al nuevo fragment FormDisplayFragment, aun no esta creado
        // findNavController().navigate(R.id.formDisplayAction)

        if(checkData()) {
            Toast.makeText(activity, "CASI", Toast.LENGTH_LONG).show()

        }
        
    }

    private fun checkData(): Boolean {
        if(_binding!!.autoCompleteTextView.text.toString() == "Tipo de pesca") {
            Toast.makeText(activity, "Seleccione un tipo de pesca", Toast.LENGTH_LONG).show()
            return false
        }
        else if(_binding!!.titleTextInput.text?.isEmpty() == true) {
            Toast.makeText(activity, "Ingrese un título para la imagen", Toast.LENGTH_LONG).show()
            return false
        }
        else if(_binding!!.captureImageView.getDrawable() == null) {
            Toast.makeText(activity, "Capture una imagen", Toast.LENGTH_LONG).show()
            return false
        }
        else {
            return true
        }
    }


}