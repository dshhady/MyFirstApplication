package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {


    private var chosenFruit : Fruit? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
        createFruitsRecyclerView()
    }


    private fun createFruitsRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewData)
        val adapter = FruitsAdapter(this, arrayListOf(), onFruitImgClick()) {
            displayFruitDetailsFragment(it)
        }
        recyclerView.adapter = adapter
        val fruitsListLiveData = Repository.getInstance(application).getAllFruits()
        fruitsListLiveData.observe(this) {
            adapter.heyAdapterPleaseUpdateTheView(it)
        }
        setButtonClickListener()
    }


   private fun createNewFruit(): Fruit {
        val editText = findViewById<EditText>(R.id.input_text).text
        val fruit  = Fruit(editText.toString(),"10ils", "10ILS")
        thread(start = true) {
            Repository.getInstance(this).addFruit(fruit)
        }
        editText.clear()

       return fruit
    }

    private fun setButtonClickListener() {
        val addButton = findViewById<Button>(R.id.add_button)
        addButton.setOnClickListener {
            val fruit = createNewFruit()
            NotificationManager.display(this, fruit )
        }



    }

    private fun onFruitImgClick(): (fruit: Fruit) -> Unit = { fruit ->
           chosenFruit = fruit
        ImagesManager.displayImagesAlertDialog(fruit,this,getContent)
    }


    val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            ImagesManager.onImageResultFromGallery(result,chosenFruit!!, this)

        }


    fun displayFruitDetailsFragment(fruit: Fruit) {
        val fruitFragment = FruitFragment()
        val fruitBundle = bundleOf(
            "name" to fruit.name,
            "img" to fruit.imagePath,
            "price" to fruit.price,
            "amount" to fruit.amount,
            "comment" to fruit.comments
        )
        fruitFragment.arguments = fruitBundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fruitFragment)
            .commit()
    }
}





