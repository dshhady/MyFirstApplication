package com.example.myapplication1


import android.text.Editable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fruitsTable")
data class Fruit(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "price") var price: String = "0ILS",
    @ColumnInfo(name = "amount") var amount:String = "0",
    @ColumnInfo(name = "comments") var comments: String = "",
    @ColumnInfo(name = "image_uri") var imageUri: String? = null
                 ){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

