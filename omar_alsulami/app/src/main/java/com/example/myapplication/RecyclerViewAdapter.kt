package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item.view.*

class RecyclerViewAdapter(private val message:ArrayList<String>):RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
    class RecyclerViewHolder (itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.row_item,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.RecyclerViewHolder, position: Int) {
        holder.itemView.apply {
            textViewItem.text=message[position]
        }
    }

    override fun getItemCount()= message.size
}