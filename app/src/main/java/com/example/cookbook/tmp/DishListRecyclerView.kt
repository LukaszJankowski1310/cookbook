package com.example.cookbook.tmp


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookbook.R
import com.example.cookbook.api.model.Dish


class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val text: TextView = view.findViewById(R.id.title_text_view)
    val image : ImageView = view.findViewById(R.id.image_view)
}

class MyAdapter(private val itemList: List<Dish>) :
    RecyclerView.Adapter<MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)


        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemList[position]
        Log.i("item", item.toString())
        holder.text.text = item.toString()
    }

    override fun getItemCount(): Int {
        Log.i("size" ,itemList.size.toString())
        return itemList.size
    }
}