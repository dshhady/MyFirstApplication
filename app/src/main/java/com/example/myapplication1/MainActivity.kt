package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val name = intent.getStringExtra("name")
        findViewById<TextView>(R.id.add_button).text = "Hello $name"
    }

    override fun onStart() {
        super.onStart()


          createFruitsRecyclerView()

    }



   private fun createFruitsRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewData)
        val adapter = FruitsAdapter(this,arrayListOf()){
            displayFruitDetailsFragment(it)
        }
        recyclerView.adapter = adapter
       val fruitsListLiveData = Repository.getInstance(application).getAllFruits()
           fruitsListLiveData.observe(this){
           adapter.heyAdapterPleaseUpdateTheView(it)
       }
        chooseFruitNameAndImage()
    }



     private fun chooseFruitNameAndImage(){
         val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewData)
         val adapter = FruitsAdapter(this,arrayListOf(),){
             displayFruitDetailsFragment(it)
         }

         recyclerView.adapter = adapter

         val fruitListLiveData = Repository.getInstance(this).getAllFruits()
         fruitListLiveData.observe(this){ fruitsList ->
             adapter.heyAdapterPleaseUpdateTheView(fruitsList)
         }

         var editText = findViewById<EditText>(R.id.input_text).text
         val addButton = findViewById<Button>(R.id.add_button)



         val appleButton = findViewById<ImageView>(R.id.appleButton)
         val bananaButton = findViewById<ImageView>(R.id.bananaButton)
         val kiwiButton = findViewById<ImageView>(R.id.kiwiButton)

         var fruitName = "*"
         var fruitImg = 0
         var fruitPrice = "null"
         var fruitAmount = "null"





         appleButton.setOnClickListener {
                 fruitName = editText.toString()
                 fruitImg = R.drawable.apple
                 fruitPrice = "1.0"
                 fruitAmount = "1"

            }
            bananaButton.setOnClickListener {
                 fruitName = editText.toString()
                 fruitImg = R.drawable.banana
                 fruitPrice = "2.0"
                 fruitAmount = "1"
            }
         kiwiButton.setOnClickListener {
              fruitName = editText.toString()
              fruitImg = R.drawable.kiwi
              fruitPrice = "3.0"
              fruitAmount = "1"
         }

         addButton.setOnClickListener {
             thread(start = true) {
                 Repository.getInstance(this).addFruit(Fruit(fruitName, fruitImg, fruitPrice, fruitAmount))
                 fruitImg = 0
                 fruitPrice = "0"
                 fruitAmount = "0"
             }



             adapter.notifyDataSetChanged()
             editText.clear()



         }



     }

   
         fun displayFruitDetailsFragment(fruit: Fruit) {
            val fruitFragment = FruitFragment()
            val fruitBundle = bundleOf("name" to fruit.name,
                "img" to fruit.imagers,
                "price" to fruit.price,
                "amount" to fruit.amount,
                "comment" to fruit.comments)

            fruitFragment.arguments = fruitBundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view,fruitFragment)
                .commit()

    }





}