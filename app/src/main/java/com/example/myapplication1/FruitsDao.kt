package com.example.myapplication1

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FruitsDao {
        @Insert
         fun insertFruit(fruit: Fruit)

        @Delete
        fun deleteFruit(fruit: Fruit)

        @Query("Select * from fruitsTable")
        fun getAllFruits(): LiveData<List<Fruit>>

}