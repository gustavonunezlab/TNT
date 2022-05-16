package com.example.demo.fragment.update

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.demo.R
import com.example.demo.databinding.FragmentReportUpdateBinding
import com.example.demo.fragment.add.REQUEST_TAKE_PHOTO
import com.example.demo.model.Report
import com.example.demo.viewModel.ReportViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class ReportUpdateFragment : Fragment() {

    private var _binding: FragmentReportUpdateBinding? = null
    private val binding get() = _binding!!
    private val args: ReportUpdateFragmentArgs by navArgs()
    lateinit var currentPhotoPath: String


    private lateinit var model: ReportViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        model = ViewModelProvider(this)[ReportViewModel::class.java]

        val fishType = resources.getStringArray(R.array.fishType)
        val arrayAdapter = ArrayAdapter(view.context, R.layout.dropdown_item, fishType)

        currentPhotoPath = args.currentReport.photo_path
        _binding!!.spinner.adapter = arrayAdapter
        _binding!!.helpButton.setOnClickListener { fishingInfo() }
        _binding!!.photoButton.setOnClickListener { takePhoto() }
        _binding!!.updateButton.setOnClickListener { updateReport() }
        val arrayPosition = arrayAdapter.getPosition(args.currentReport.fishing_type)
        _binding!!.spinner.setSelection(arrayPosition)

        _binding!!.updateTitleTextInput.setText(args.currentReport.title)

        val imageBitmap: Bitmap? = BitmapFactory.decodeFile(args.currentReport.photo_path)
        _binding!!.updateCaptureImageView.setImageBitmap(imageBitmap)
        return view
    }

    private fun fishingInfo() {
        findNavController().navigate(R.id.fishing_info_activity)
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

            _binding!!.updateCaptureImageView.setImageBitmap(imageBitmap)
        }
    }

    private fun updateReport() {

        val title = _binding?.updateTitleTextInput?.text.toString()
        val fishingType = _binding?.spinner?.selectedItem.toString()
        val date = args.currentReport.date
        val photoPath: String
        // If empty, photo path did not change

        if (currentPhotoPath == "") {
            photoPath = args.currentReport.photo_path
        } else {
            photoPath = currentPhotoPath
        }
        val updatedReport = Report(args.currentReport.id, title, fishingType, date, photoPath)
        model.updateReport(updatedReport)
        Toast.makeText(activity, "Reporte editado correctamente", Toast.LENGTH_LONG).show()
        val action =
            ReportUpdateFragmentDirections.goToReportDetailFromUpdateReportAction(updatedReport)
        findNavController().navigate(action)

    }

}