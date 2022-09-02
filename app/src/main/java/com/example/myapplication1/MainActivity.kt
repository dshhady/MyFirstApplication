package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setButtonClickListener()
    }

    private fun setButtonClickListener() {
        val button = findViewById<Button>(R.id.RecyclerViewButton)
        button.setOnClickListener {
            addAnItem()
        }
    }

    var fruitsList = mutableListOf<Fruit>()


    private fun createFruitsRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewData)
        val adapter = FruitsAdapter(fruitsList)
        recyclerView.adapter = adapter
    }

     private fun addAnItem(){
         val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewData)

         val editText = findViewById<EditText>(R.id.input_text).text
         val imageView = findViewById<ImageView>(R.id.image_view)

         val appleButton = findViewById<ImageButton>(R.id.appleButton).setOnClickListener{
             fruitsList.add(Fruit(editText.toString(),R.drawable.apple))
             val adapter = FruitsAdapter(fruitsList)
             recyclerView.adapter = adapter
         }


         val bananaButton = findViewById<ImageButton>(R.id.bananaButton).setOnClickListener {
             fruitsList.add(Fruit(editText.toString(),R.drawable.banana))
             val adapter = FruitsAdapter(fruitsList)
             recyclerView.adapter = adapter}


         val kiwiButton = findViewById<ImageButton>(R.id.kiwiButton).setOnClickListener {
             fruitsList.add(Fruit(editText.toString(),R.drawable.kiwi))
             val adapter = FruitsAdapter(fruitsList)
             recyclerView.adapter = adapter
         }


    }






}