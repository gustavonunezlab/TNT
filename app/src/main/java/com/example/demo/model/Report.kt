package com.example.demo.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "report_table")
data class Report(

    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "fishing_type")
    var fishing_type: String,
    @ColumnInfo(name = "date")
    var date: String,
    //@ColumnInfo(name = "featured_image")
    //var featured_image: Bitmap,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
