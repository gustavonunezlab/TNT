/*package com.example.demo

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.demo.databinding.ActivityFormBinding

private lateinit var binding: ActivityFormBinding
//const val REQUEST_IMAGE_CAPTURE = 1

class OptionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fishType = resources.getStringArray(R.array.fishType)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, fishType)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)

        binding.helpButton.setOnClickListener{ fishingInfo() }
        binding.photoButton.setOnClickListener { takePhoto() }
        binding.sendButton.setOnClickListener { sendData() }
    }
    private fun fishingInfo() {
        val i = Intent(this, FishingInfoActivity::class.java)
        startActivity(i)
    }

    private fun takePhoto() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.captureImageView.setImageBitmap(imageBitmap)
        }
    }

    private fun sendData() {
        if(checkData()) {
            val fishingType = binding.autoCompleteTextView.text.toString()
            val title = binding.titleTextInput.text.toString()
            val i = Intent(this, FormDisplayActivity::class.java).apply {
                putExtra("fishingType", fishingType)
                putExtra("title", title)
            }
            startActivity(i)
        }

    }

    private fun checkData(): Boolean {
        if(binding.autoCompleteTextView.text.toString() == "Tipo de pesca") {
            Toast.makeText(this, "Seleccione un tipo de pesca", Toast.LENGTH_LONG).show()
            return false
        }
        else if(binding.titleTextInput.text?.isEmpty() == true) {
            Toast.makeText(this, "Ingrese un título para la imagen", Toast.LENGTH_LONG).show()
            return false
        }
        else if(binding.captureImageView.getDrawable() == null) {
            Toast.makeText(this, "Capture una imagen", Toast.LENGTH_LONG).show()
            return false
        }
        else {
            return true
        }
    }
} */