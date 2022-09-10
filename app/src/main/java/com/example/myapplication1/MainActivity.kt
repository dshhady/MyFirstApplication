package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.*
import androidx.core.text.set
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createFruitsRecyclerView()
    }

    private fun setButtonClickListener() {
        val button = findViewById<Button>(R.id.add_button)
        button.setOnClickListener {

        }
    }
    var fruitsList = mutableListOf<Fruit>()

    private fun createFruitsRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewData)
        val adapter = FruitsAdapter(fruitsList)
        recyclerView.adapter = adapter
        chooseFruitNameAndImage()
    }

     private fun chooseFruitNameAndImage(){
         val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewData)
         val adapter = FruitsAdapter(fruitsList)
         recyclerView.adapter = adapter


         var editText = findViewById<EditText>(R.id.input_text).text
         val imageView = findViewById<ImageView>(R.id.image_view)
         val addButton = findViewById<Button>(R.id.add_button)


         val appleButton = findViewById<ImageView>(R.id.appleButton)
         val bananaButton = findViewById<ImageView>(R.id.bananaButton)
         val kiwiButton = findViewById<ImageView>(R.id.kiwiButton)






         appleButton.setOnClickListener {
             editText = findViewById<EditText>(R.id.input_text).text
                fruitsList.add(Fruit(editText.toString(),R.drawable.apple))



            }
            bananaButton.setOnClickListener {
                editText = findViewById<EditText>(R.id.input_text).text
                fruitsList.add(Fruit(editText.toString(),R.drawable.banana))


            }
         kiwiButton.setOnClickListener {

             editText = findViewById<EditText>(R.id.input_text).text
             fruitsList.add(Fruit(editText.toString(),R.drawable.kiwi))



         }


         addButton.setOnClickListener {
             editText.clear()
             adapter.notifyDataSetChanged()

             Log.d("TAG", "chooseFruitNameAndImage: $fruitsList")
         }









    }








}