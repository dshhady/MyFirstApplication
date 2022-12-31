package com.example.myapplication1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.example.myapplication1.*
import com.example.myapplication1.model.Fruit
import com.example.myapplication1.viewModel.FruitsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class FruitsActivity : AppCompatActivity() {

    private val fruitsViewModel : FruitsViewModel by viewModels()


    private var chosenFruit : Fruit? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceIntent: Intent = Intent(this, FruitsService::class.java)
        ContextCompat.startForegroundService(this,serviceIntent)

    }

    override fun onStart() {
        super.onStart()
        createFruitsRecyclerView()

    }


    private fun createFruitsRecyclerView() {
        val adapter = FruitsAdapter(this, arrayListOf(), onFruitImgClick()) {
            displayFruitDetailsFragment(it)
        }
        recyclerViewData.adapter = adapter

        fruitsViewModel.fruitsData.observe(this) {
            adapter.heyAdapterPleaseUpdateTheView(it)
        }

        setButtonClickListener()
    }


   private fun createNewFruit(): Fruit {
        val editText = findViewById<EditText>(R.id.input_text).text
        val fruit  = Fruit(editText.toString(),"10ils", "10ILS")
        thread(start = true) {
            fruitsViewModel.addFruit(fruit)

        }
        editText.clear()

       return fruit
    }

    private fun setButtonClickListener() {
        add_button.setOnClickListener {
            val fruit = createNewFruit()
            NotificationManager.displayNewFruitNotification(this, fruit)
        }



    }

    private fun onFruitImgClick(): (fruit: Fruit) -> Unit = { fruit ->
           chosenFruit = fruit
        ImagesManager.displayImagesAlertDialog(fruit, this, getContent)
    }


    val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            ImagesManager.onImageResultFromGallery(result, chosenFruit!!, this)

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





