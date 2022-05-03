package com.example.demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demo.databinding.FragmentDescriptionFishingTypeBinding

class DescriptionFishingTypeFragment : Fragment() {
    lateinit var arrFishingTypeDesc: Array<String>
    var fishingTypeindex = 0

    private var _binding: FragmentDescriptionFishingTypeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDescriptionFishingTypeBinding.inflate(inflater, container, false)
        val view = binding.root

        arrFishingTypeDesc = resources.getStringArray(R.array.descriptionFishingType)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun changeFishingType(index: Int) {
        fishingTypeindex = index
    binding.descriptionFishingTypeTextView.text = arrFishingTypeDesc[fishingTypeindex]
    }

}