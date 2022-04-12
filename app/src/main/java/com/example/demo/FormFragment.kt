package com.example.demo

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.demo.databinding.FragmentFormBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
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
    //ir al nuevo fragment FishingInfoFragment
    // findNavController().navigate(R.id.fishingInfoAction)
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
        //ir al nuevo fragment FishingInfoFragment
        // findNavController().navigate(R.id.fishingInfoAction)
    }


}