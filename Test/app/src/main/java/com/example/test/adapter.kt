package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.new_item.view.*


class adapter(private val names : ArrayList<String>):RecyclerView.Adapter<adapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapter.ItemViewHolder {
        return ItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.new_item,
                    parent,
                    false))
    }

    override fun onBindViewHolder(holder: adapter.ItemViewHolder, position: Int) {
        val name = names[position]

        holder.itemView.apply {
            rc.text = name
        }
    }

    override fun getItemCount()= names.size


}
