package com.example.myapplication1.model


import android.content.Context

import androidx.lifecycle.LiveData

class Repository private constructor(context: Context) {


     private val fruitsDao = AppDataBase.getAppDataBase(context).getFruitDao()
     private val usersDao = AppDataBase.getAppDataBase(context).getUserDao()

    companion object{

        private lateinit var instance: Repository

        fun getInstance(context: Context): Repository {
            if(!Companion::instance.isInitialized){
                   instance = Repository(context)
            }
            return instance
        }
    }


    fun addFruit(fruit: Fruit) {
        fruitsDao.insertFruit(fruit)
    }

    fun deleteFruit(fruit: Fruit) {
        fruitsDao.deleteFruit(fruit)
    }

    fun updateFruitImageUri(fruit: Fruit, imagePath: String, imageType: IMAGE_TYPE) {
        fruitsDao.updateFruitImageUri(fruit , imagePath ,imageType)
    }

    fun getAllFruits(): LiveData<List<Fruit>> {
        // get all fruits from database
        return fruitsDao.getAllFruits()
    }

    //////////////////////////////////////

    fun addUser(user: User) {
        usersDao.insertUser(user)
    }

    fun deleteUser(user: User) {
        usersDao.deleteUser(user)
    }

    fun getAllUsers(): LiveData<List<User>> {
        // get all fruits from database
        return usersDao.getAllUsers()
    }

    fun getUserByUsernameAndPassword(username: String, password: String): User? {
        return usersDao.getUserByUsernameAndPassword(username, password)
    }


}