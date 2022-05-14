package com.example.demo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
}

