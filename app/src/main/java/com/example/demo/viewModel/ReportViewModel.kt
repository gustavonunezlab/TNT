package com.example.demo.viewModel

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.*
import com.example.demo.model.Report
import com.example.demo.database.ReportRoomDatabase
import com.example.demo.repository.ReportsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReportViewModel(application: Application) : AndroidViewModel(application) {

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

    fun setDate(date: String){
        _date.value = date
    }

    private val _image = MutableLiveData<Bitmap>()
    val image: LiveData<Bitmap>
        get() = _image

    fun setImage(image: Bitmap) {
        _image.value = image
    }

    val repository: ReportsRepository
    val allReports: LiveData<List<Report>>
    init {
        Log.i("ReportViewModel", "cargo?")
        val reportsDao = ReportRoomDatabase
            .getDatabase(application, viewModelScope).reportDao()
        repository = ReportsRepository(reportsDao)
        allReports = repository.allReports
    }
    fun insert(report: Report) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertReport(report)
    }

    fun updateReport(report: Report) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateReport(report)
        }
    }
}