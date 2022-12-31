package com.example.myapplication1.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Fruit::class, User::class), version = 1 )

abstract class AppDataBase: RoomDatabase() {

    abstract fun getFruitDao(): FruitsDao
    abstract fun getUserDao(): UsersDao


    companion object {
        fun getAppDataBase(context: Context): AppDataBase {
            return Room.databaseBuilder(
                context!!.applicationContext,
                AppDataBase::class.java,
                "app_database"
            ).build()

        }
    }






}
