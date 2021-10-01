package com.example.recycler_view_app

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerViewAdapter (private val context:Context,private val message:ArrayList<String>):RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
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
        val item=message[position]

        holder.itemView.apply {
            tvMessage.text=item
            if(item.startsWith("Found")){
                tvMessage.setTextColor(Color.GREEN)
            }else if(item.startsWith("No")||item.startsWith("wrong")){
                tvMessage.setTextColor(Color.RED)
            }else{
                tvMessage.setTextColor(Color.BLACK)
            }
        }
    }

    override fun getItemCount()=message.size
}