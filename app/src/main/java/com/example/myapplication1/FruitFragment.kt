package com.example.myapplication1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class FruitFragment: Fragment(R.layout.fruit_fragment) {


    override fun onResume() {
        super.onResume()
        val activity = requireActivity()
        val fruitTextView = activity.findViewById<TextView>(R.id.fruit_fragment_title)
        val fruitImgView = activity.findViewById<ImageView>(R.id.fruit_fragment_image_view)
        val fruitRemove = activity.findViewById<ImageView>(R.id.remove_button)

        val img = requireArguments().getInt("img")
        val name = requireArguments().getString("name")

        fruitImgView.setImageResource(img)
        fruitTextView.text = name


        fruitRemove.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }


    }







}
