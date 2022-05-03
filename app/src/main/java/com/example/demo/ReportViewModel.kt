package com.example.demo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReportViewModel : ViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    fun setTitle(title: String){
        _title.value = title
    }

    private val _fishingType = MutableLiveData<String>()
    val fishingType: LiveData<String>
        get() = _fishingType

    fun setFishingType(fishingType: String){
        _fishingType.value = fishingType
    }

    private val _date = MutableLiveData<String>()
    val date: LiveData<String>
        get() = _date


}