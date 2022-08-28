package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.TextureView
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun getEditTextName(): EditText {
        val editText : EditText = findViewById<EditText>(R.id.Edit_Text);
        return editText ;
    }

    fun onClick(view : View){
        val textView : TextView = findViewById<TextView>(R.id.hello_text);
        textView.text = getEditTextName().text ;
    }
}