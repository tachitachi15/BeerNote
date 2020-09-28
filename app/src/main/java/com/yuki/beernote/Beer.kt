package com.yuki.beernote

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "beer")
data class Beer(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name="name")
    val name: String,
    @ColumnInfo(name="body")
    var body: Float, //コク
    @ColumnInfo(name="sharpness")
    var sharpness: Float, //キレ
    @ColumnInfo(name="sweet")
    var sweet: Float, //甘味
    @ColumnInfo(name="bitter")
    var bitter: Float, //苦味
    @ColumnInfo(name="sour")
    var sour: Float, //酸味
):Parcelable