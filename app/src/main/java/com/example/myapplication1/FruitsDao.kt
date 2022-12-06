package com.example.myapplication1

import android.net.Uri
import android.provider.ContactsContract.CommonDataKinds.Note
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

        fun updateFruitImageUri(fruit: Fruit, uri: Uri){
                fruit.imageUri = uri.toString()
                updateFruit(fruit)
        }


}