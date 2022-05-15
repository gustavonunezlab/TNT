package com.example.demo.model

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "report_table")
data class Report(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "fishing_type")
    var fishing_type: String,
    @ColumnInfo(name = "date")
    var date: String,
  /*  @ColumnInfo(name = "photo")
    var photo: Bitmap,

   */
):Parcelable


