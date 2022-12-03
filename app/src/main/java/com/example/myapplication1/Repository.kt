package com.example.myapplication1


import android.content.Context

import androidx.lifecycle.LiveData

class Repository private constructor(context: Context) {


     private val dao = FruitsDataBase.getFruitsDataBase(context).getFruitDao()

    companion object{

        private lateinit var instance: Repository

        fun getInstance(context: Context):Repository{
            if(!::instance.isInitialized){
                   instance = Repository(context)
            }
            return instance
        }
    }


    fun addFruit(fruit: Fruit) {
        dao.insertFruit(fruit)
    }

    fun deleteFruit(fruit: Fruit) {
        dao.deleteFruit(fruit)
    }

    fun getAllFruits(): LiveData<List<Fruit>> {
        // get all fruits from database
        return dao.getAllFruits()
    }
}