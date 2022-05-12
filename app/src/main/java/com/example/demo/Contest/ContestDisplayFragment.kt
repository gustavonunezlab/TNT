package com.example.demo.Contest

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.demo.R
import com.example.demo.databinding.FragmentContestDisplayBinding
import java.util.*

class ContestDisplayFragment : Fragment() {
    private var _binding: FragmentContestDisplayBinding? = null
    private val binding get() = _binding!!
    private val model: ContestViewModel by navGraphViewModels(R.id.app_navigation)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContestDisplayBinding.inflate(inflater, container, false)
        _binding!!.titleTextView.text = model.title.value
        _binding!!.descriptionTextView.text = model.description.value
        _binding!!.dateTextView.text = model.date.value

        val image = model.image.value
        _binding!!.captureImageView.setImageBitmap(image)

        _binding!!.goBackButton.setOnClickListener { goBack() }

        val view = binding.root
        return view
    }

    private fun goBack() {
        findNavController().popBackStack()
    }

}