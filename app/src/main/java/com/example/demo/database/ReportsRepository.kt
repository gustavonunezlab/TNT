package com.example.demo.database

import androidx.lifecycle.LiveData

class ReportsRepository(private val reportDao: ReportDAO) {

    val allReports: LiveData<List<Report>> = reportDao.getReports()
    suspend fun insertReport(report: Report) {
        reportDao.insertReport(report)
    }
}