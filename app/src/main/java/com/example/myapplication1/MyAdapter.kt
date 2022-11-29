package com.example.myapplication1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter (private val dataList : List<Person>): RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val textView:TextView
        val imgView : ImageView

        init {

            textView  = view.findViewById(R.id.text_view)
            imgView = view.findViewById(R.id.image_view)

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataList[position].name
        holder.imgView.setImageResource(dataList[position].imageRes)
    }

    override fun getItemCount(): Int {
        return dataList.size //returns the size of the list
    }
}