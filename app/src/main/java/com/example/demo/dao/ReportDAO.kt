package com.example.demo.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.demo.model.Report


@Dao
interface ReportDAO {
    @Query("SELECT * from report_table ORDER BY id ASC")
    fun getReports(): LiveData<List<Report>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertReport(report: Report)

    @Query("DELETE FROM report_table")
    suspend fun deleteAll()

    @Query("SELECT COUNT(id) FROM report_table")
    suspend fun getCount(): Int

    @Update
    suspend fun updateReport(report: Report)
}

