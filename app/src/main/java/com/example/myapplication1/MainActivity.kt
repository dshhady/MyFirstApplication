package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        chooseFruitNameAndImage(adapter)
    }


    private fun chooseFruitNameAndImage(adapter: FruitsAdapter) {

        val fruitListLiveData = Repository.getInstance(this).getAllFruits()
        fruitListLiveData.observe(this) { fruitsList ->
            adapter.heyAdapterPleaseUpdateTheView(fruitsList)
        }
        val editText = findViewById<EditText>(R.id.input_text).text
        val addButton = findViewById<Button>(R.id.add_button)




        addButton.setOnClickListener {
            val fruit  = Fruit(editText.toString(),"10ils", "10ILS")
            thread(start = true) {
                Repository.getInstance(this).addFruit(fruit)
            }
            editText.clear()

        }


    }

    val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val uri = result.data?.data
                if (uri != null) {
                    contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    thread(start = true) {
                        Repository.getInstance(this).updateFruitImageUri(chosenFruit!!,uri)
                        }

                }
            }

        }


    private fun onFruitImgClick(): (fruit: Fruit) -> Unit = { fruit ->
        Log.d("Test1", "onFruitImgClick: ${fruit.name}")
        chosenFruit = fruit
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        getContent.launch(intent)

    }


    fun displayFruitDetailsFragment(fruit: Fruit) {
        val fruitFragment = FruitFragment()
        val fruitBundle = bundleOf(
            "name" to fruit.name,
            "img" to fruit.imageUri,
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





