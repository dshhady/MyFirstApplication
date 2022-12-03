package com.example.myapplication1


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fruitsTable")
data class Fruit(@ColumnInfo(name = "title")var name: String,
                 var imagers: Int = 0,
                 var price: String = "0ILS",
                 var amount :String = "0",
                 var comments: String = ""
                 ){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

