package com.example.myapplication1.model


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FruitsDao {
        @Insert
         fun insertFruit(fruit: Fruit)

        @Delete
        fun deleteFruit(fruit: Fruit)

        @Query("Select * from fruitsTable")
        fun getAllFruits(): LiveData<List<Fruit>>

        @Update
        fun updateFruit(fruit: Fruit)

        fun updateFruitImageUri(fruit: Fruit, imagePath: String, imageType: IMAGE_TYPE) {

                fruit.imagePath = imagePath
                fruit.imageType = imageType
                updateFruit(fruit)
        }


}