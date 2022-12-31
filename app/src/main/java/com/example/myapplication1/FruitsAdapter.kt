package com.example.myapplication1

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication1.model.Fruit
import com.example.myapplication1.model.IMAGE_TYPE
import com.example.myapplication1.model.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FruitsAdapter(
    private val context: Context,
    private val dataList: ArrayList<Fruit>,
    val onFruitImgClick:(Fruit)-> Unit,
    val onFruitClickFragment:(Fruit)-> Unit



) : RecyclerView.Adapter<FruitsAdapter.ViewHolder>() {

    private var mDataList:MutableList<Fruit> = dataList as MutableList<Fruit>




    class ViewHolder(fruitView: View) : RecyclerView.ViewHolder(fruitView){

        val textView : TextView
        val imgView : ImageView
        val deleteButton : ImageView

        init {
            textView = fruitView.findViewById(R.id.text_view)
            imgView = fruitView.findViewById(R.id.image_view)
            deleteButton = fruitView.findViewById(R.id.delete_button)
        }

    }



        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val view: View =
             LayoutInflater.from(parent.context).inflate(R.layout.row_item_layout, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return mDataList.size
        }



    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val fruit = mDataList[position]
        holder.textView.text = fruit.name

        if (fruit.imagePath != null)
            if (fruit.imagePath != "") {
                if (fruit.imageType == IMAGE_TYPE.URI)
                    holder.imgView.setImageURI(Uri.parse(fruit.imagePath))
                else if (fruit.imageType == IMAGE_TYPE.URL) {
                    Glide.with(context).load(fruit.imagePath).into(holder.imgView)

                }
                else if (fruit.imageType == IMAGE_TYPE.NONE)
                    holder.imgView.setImageResource(R.drawable.camera_icon)
            }
        else {
            holder.imgView.setImageResource(R.drawable.camera_icon)
        }






        holder.imgView.setOnClickListener {
            onFruitImgClick(fruit)
        }


        holder.textView.setOnClickListener {
            onFruitClickFragment(fruit)
        }
        holder.deleteButton.setOnClickListener {
            GlobalScope.launch {
                Repository.getInstance(context).deleteFruit(fruit)
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
