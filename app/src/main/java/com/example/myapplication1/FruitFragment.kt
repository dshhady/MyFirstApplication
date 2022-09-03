package com.example.myapplication1

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class FruitFragment: Fragment(R.layout.fruit_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val fruitTextView = activity?.findViewById<TextView>(R.id.fruit_fragment_title)
        fruitTextView?.text = "Fruit Fragment"

        }

    }
