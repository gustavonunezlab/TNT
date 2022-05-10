package com.example.demo.database

import android.util.Log
import androidx.lifecycle.LiveData

class ReportsRepository(private val reportDao: ReportDAO) {

    val allReports: LiveData<List<Report>> = reportDao.getReports()
    suspend fun insertReport(report: Report) {
        reportDao.insertReport(report)
        Log.i("PartidoRoomDatabase", "cargo?")
    }
}