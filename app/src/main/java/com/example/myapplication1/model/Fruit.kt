package com.example.myapplication1.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

enum class IMAGE_TYPE{
    URI,URL,NONE
}

@Entity(tableName = "fruitsTable")
data class Fruit(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "price") var price: String = "0ILS",
    @ColumnInfo(name = "amount") var amount:String = "0",
    @ColumnInfo(name = "comments") var comments: String = "",
    @ColumnInfo(name = "image_path") var imagePath: String? = null,
    @ColumnInfo(name = "image_type") var imageType: IMAGE_TYPE? = null,
    @ColumnInfo(name = "time_stamp") var timeStamp: Long = System.currentTimeMillis(),
                 ){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

