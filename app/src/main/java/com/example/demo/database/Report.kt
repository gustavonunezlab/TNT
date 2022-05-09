package com.example.demo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "report_table")
data class Report (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "fishing_type")
    var fishing_type: String,
    @ColumnInfo(name = "date")
    var date: String,
  //  @ColumnInfo(name = "image")
//    var image: Bitmap?
)
