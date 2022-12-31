package com.example.myapplication1.ui

import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.myapplication1.R

class FruitChildFragment : Fragment(R.layout.fruit_child_fragment) {




    override fun onResume() {
        super.onResume()
        val fruitChildRemove = activity?.findViewById<ImageView>(R.id.remove_button_of_child)
        val amountView = activity?.findViewById<TextView>(R.id.amount)
        val commentView = activity?.findViewById<TextView>(R.id.comment)
        val editTextView = activity?.findViewById<EditText>(R.id.comment_input)
        val saveButton = activity?.findViewById<ImageView>(R.id.save_info_button)

        val amount = requireArguments().getString("amount")





        amountView?.text = "Amount:"+amount


        saveButton?.setOnClickListener {
            bundleOf("comment" to commentView?.text.toString())
            commentView?.text = editTextView?.text.toString()
        }









        fruitChildRemove?.setOnClickListener {
            Log.d("TAG", "displayChildFragment: ")
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
    }


}