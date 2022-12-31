package com.example.myapplication1

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication1.model.ApiResponseHitsList
import com.example.myapplication1.model.Fruit
import com.example.myapplication1.model.IMAGE_TYPE
import com.example.myapplication1.model.Repository
import retrofit2.Call
import retrofit2.Response
import kotlin.concurrent.thread

object ImagesManager {


    fun getImageFromGallery(fruit: Fruit, getContent: ActivityResultLauncher<Intent>){
        Log.d("Test1", "onFruitImgClick: ${fruit.name}")
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        getContent.launch(intent)
    }

    fun onImageResultFromGallery(result: ActivityResult, chosenFruit: Fruit, context: Context) {
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val uri = result.data?.data
            if (uri != null) {
                context.contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION)
                addImageToFruit(chosenFruit, uri.toString(), IMAGE_TYPE.URI, context)

            }
        }

    }

    private fun addImageToFruit(fruit: Fruit, imagePath: String, imageType: IMAGE_TYPE, context: Context) {
        thread(start = true) {
            Repository.getInstance(context).updateFruitImageUri(fruit!!, imagePath , imageType )

        }
    }

    private fun deleteImageFromFruit(fruit: Fruit, context: Context) {
        thread(start = true) {
            Repository.getInstance(context).updateFruitImageUri(fruit!!,"", IMAGE_TYPE.NONE)
        }
    }






    fun getImageFromApi(fruit: Fruit, context: Context) {
        val retrofit = ApiInterface.create()
        retrofit.getImages(fruit!!.name).enqueue(object : retrofit2.Callback<ApiResponseHitsList>{
            override fun onResponse(call: Call<ApiResponseHitsList>, response: Response<ApiResponseHitsList>) {
                val apiResponse  = response.body()
                val apiImage = apiResponse!!.imagesList[1]
                addImageToFruit(fruit,apiImage.imageUrl, IMAGE_TYPE.URL, context)
            }

            override fun onFailure(call: Call<ApiResponseHitsList>, t: Throwable) {
                Log.e("Wrong api response", "onFailure: ${t.message}")

            }
        })

    }


    fun displayImagesAlertDialog(fruit: Fruit, context: Context, getContent: ActivityResultLauncher<Intent>) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Choose image source")
        builder.setMessage("Choose image source")
        builder.setNeutralButton("Remove") { dialog, which ->
            deleteImageFromFruit(fruit!!, context)

        }
        builder.setPositiveButton("Gallery") { dialog, which ->
           getImageFromGallery(fruit!!, getContent)

        }
        builder.setNegativeButton("Api") { dialog, which ->
          getImageFromApi(fruit!!, context)

        }


        builder.show()
    }




}