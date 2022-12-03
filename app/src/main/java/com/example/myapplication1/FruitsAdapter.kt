package com.example.myapplication1

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.security.AlgorithmParameterGenerator.getInstance
import java.util.Calendar.getInstance


class FruitsAdapter(
    private val context: Context,
    private val dataList: ArrayList<Any>,
    val onFruitClickFragment:(Fruit)-> Unit


) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mDataList:MutableList<Fruit> = dataList as MutableList<Fruit>




    class viewHolder(val fruitView: View) : RecyclerView.ViewHolder(fruitView){

        val textView : TextView
        val imgView : ImageView
        val deleteButton : ImageView

        init {
            textView = fruitView.findViewById(R.id.text_view)
            imgView = fruitView.findViewById(R.id.image_view)
            deleteButton = fruitView.findViewById(R.id.delete_button)
        }

    }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_item_layout, parent, false)
            return viewHolder(view)
        }

        override fun getItemCount(): Int {
            return mDataList.size
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.text_view).text = mDataList[position].name
        holder.itemView.setOnClickListener {
            onFruitClickFragment(mDataList[position])

        }

        holder.itemView.findViewById<ImageView>(R.id.image_view).setImageResource(mDataList[position].imagers)
        holder.itemView.findViewById<ImageView>(R.id.delete_button).setOnClickListener {
            GlobalScope.launch {
                Repository.getInstance(context).deleteFruit(mDataList[position])
            }
            notifyItemRemoved(position)





        }
        }

    fun heyAdapterPleaseUpdateTheView(fruitsList: List<Fruit>){
        mDataList.clear()
        mDataList.addAll(fruitsList)
        notifyDataSetChanged()
    }








}
