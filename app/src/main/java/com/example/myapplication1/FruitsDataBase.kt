package com.example.myapplication1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Fruit::class], version = 1 , exportSchema = false)
abstract class FruitsDataBase: RoomDatabase() {

    abstract fun getFruitDao(): FruitsDao


    companion object {
        fun getFruitsDataBase(context: Context): FruitsDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                FruitsDataBase::class.java,
                "fruits_database"
            ).build()

        }
    }






}
