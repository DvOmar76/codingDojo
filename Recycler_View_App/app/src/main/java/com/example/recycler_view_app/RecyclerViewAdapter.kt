package com.example.recycler_view_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

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
        holder.itemView.apply {
            tvNames.text=list[position]
        }
    }

    override fun getItemCount()=list.size
}