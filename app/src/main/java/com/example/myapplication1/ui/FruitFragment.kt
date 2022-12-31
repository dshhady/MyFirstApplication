package com.example.myapplication1.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.myapplication1.R

class FruitFragment: Fragment(R.layout.fruit_fragment) {


    override fun onResume() {
        super.onResume()
        val activity = requireActivity()
        val fruitTextView = activity.findViewById<TextView>(R.id.fruit_fragment_title)
        val fruitImgView = activity.findViewById<ImageView>(R.id.fruit_fragment_image_view)
        val fruitRemove = activity.findViewById<ImageView>(R.id.remove_button)
        val price = activity.findViewById<TextView>(R.id.fruit_price_fragment)



        val img = requireArguments().getInt("img")
        val name = requireArguments().getString("name")
        val price1 = requireArguments().getString("price")

        fruitImgView.setImageResource(img)
        fruitTextView.text = name
        price.text = price1


        fruitRemove.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }


        price.setOnClickListener{
            displayChildFragment()
        }







    }

    fun displayChildFragment() {

        val fruitChildFragment = FruitChildFragment()
        val amount = requireArguments().getString("amount")

        fruitChildFragment.arguments = bundleOf("amount" to amount)
        childFragmentManager.
        beginTransaction().
        add(R.id.child_fragment_container_view, fruitChildFragment)
            .commit()



    }







}
