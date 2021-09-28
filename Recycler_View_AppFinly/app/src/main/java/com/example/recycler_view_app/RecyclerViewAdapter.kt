package com.example.recycler_view_app

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*
import kotlin.random.Random

class RecyclerViewAdapter (private val context:Context,private val list:List<String>):RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
    class RecyclerViewHolder (itemView:View) :RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        return RecyclerViewHolder(LayoutInflater.from(context).inflate(
            R.layout.item_row,
            parent,
            false
        )
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val name=list[position]
        val rnd = Random.Default
        var color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

        holder.itemView.apply {
            tvNames.setBackgroundColor(color)
            tvNames.text=name
        }
    }

    override fun getItemCount()=list.size
}