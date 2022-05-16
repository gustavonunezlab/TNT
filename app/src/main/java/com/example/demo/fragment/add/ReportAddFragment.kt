package com.example.demo.fragment.add

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.demo.R
import com.example.demo.databinding.FragmentReportAddBinding
import com.example.demo.model.Report
import com.example.demo.viewModel.ReportViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


const val REQUEST_TAKE_PHOTO = 1

class ReportAddFragment : Fragment() {

    private var _binding: FragmentReportAddBinding? = null
    private val binding get() = _binding!!
    lateinit var currentPhotoPath: String


    private val model: ReportViewModel by navGraphViewModels(R.id.app_navigation)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportAddBinding.inflate(inflater, container, false)
        val view = binding.root

        val fishType = resources.getStringArray(R.array.fishType)
        val arrayAdapter = ArrayAdapter(view.context, R.layout.dropdown_item, fishType)

        _binding!!.spinner.adapter = arrayAdapter
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

    @RequiresApi(Build.VERSION_CODES.N)
    private fun takePhoto() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(activity?.packageManager!!)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    Toast.makeText(activity, ": error", Toast.LENGTH_LONG).show()
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        requireActivity(),
                        "com.example.demo.fileprovider",
                        photoFile
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)

                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @Throws(IOException::class)
    private fun createImageFile(): File? {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"

        val storageDir: File? = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",  /* suffix */
            storageDir /* directory */
        )
        currentPhotoPath = image.absolutePath

        return image
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == AppCompatActivity.RESULT_OK) {
            val imageBitmap: Bitmap? = BitmapFactory.decodeFile(currentPhotoPath)
            val outputStream: FileOutputStream = FileOutputStream(currentPhotoPath)

            imageBitmap?.compress(
                Bitmap.CompressFormat.JPEG,
                20,
                outputStream
            ); // this line will reduce the size , try changing the second argument to adjust to correct size , it ranges 0-100
            _binding!!.captureImageView.setImageBitmap(imageBitmap)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun sendReport() {
        // Comentada la condicion para realizar pruebas.
        //  if(checkData()) {

        val title = _binding?.titleTextInput?.text.toString()
        val fishingType = _binding?.spinner?.selectedItem.toString()
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(Date())

        val report = Report(0, title, fishingType, currentDate, currentPhotoPath)

        model.insert(report)
        Toast.makeText(activity, "Reporte agregado correctamente", Toast.LENGTH_LONG).show()

        findNavController().navigate(R.id.goToMyReportsFromReportAddAction)
        //  }
    }

    private fun checkData(): Boolean {
        if (_binding!!.titleTextInput.text?.isEmpty() == true) {
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