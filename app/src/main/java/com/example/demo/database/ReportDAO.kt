package com.example.demo.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Dao
interface ReportDAO {
    @Query("SELECT * from report_table ORDER BY id ASC")
    fun getReports(): LiveData<List<Report>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertReport(report: Report)

    @Query("DELETE FROM report_table")
    fun deleteAll()

    @Query("SELECT COUNT(id) FROM report_table")
    open fun getCount(): Int
}

@Database(entities = arrayOf(Report::class), version = 1, exportSchema = false)
public abstract class ReportRoomDatabase : RoomDatabase() {
    abstract fun reportDao(): ReportDAO

    companion object {
        @Volatile
        private var INSTANCIA: ReportRoomDatabase? = null
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ReportRoomDatabase {
            val temporalInstance = INSTANCIA
            if (temporalInstance != null) {
                return temporalInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReportRoomDatabase::class.java,
                    "report_database"
                )
                    .addCallback(ReportDatabaseCallback(scope))
                    .build()
                INSTANCIA = instance
                return instance
            }
        }
    }

    private class ReportDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCIA?.let { database ->
                scope.launch {
                    populateDatabase(database.reportDao())
                }
            }
        }

        fun populateDatabase(reportDAO: ReportDAO) {
            reportDAO.deleteAll()
            var report = Report(1, "Pesca loca", "Pesca deportiva", "Real Madryn")
            reportDAO.insertReport(report)
            reportDAO.insertReport(Report(2, "El sacrificio", "Pesca científica", "UNPSJB Trelew"))
        }
    }
}