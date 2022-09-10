package com.example.myapplication1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class FruitsAdapter(private val dataList: List<Fruit>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class viewHolder(val fruitView: View) : RecyclerView.ViewHolder(fruitView){

        val textView : TextView
        val imgView : ImageView

        init {
            textView = fruitView.findViewById(R.id.text_view)
            imgView = fruitView.findViewById(R.id.image_view)
        }

    }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item_layout, parent, false)
            return viewHolder(view)
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.text_view).text = dataList[position].name
        holder.itemView.findViewById<ImageView>(R.id.image_view).setImageResource(dataList[position].imagers)
    }


}
