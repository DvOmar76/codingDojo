package com.example.task2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.view.*

class recyclerAdapter(val context:Context,val names: ArrayList<String>) :RecyclerView.Adapter<recyclerAdapter.recyclerViewHolder>() {
    class recyclerViewHolder (itemView: View) :RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): recyclerAdapter.recyclerViewHolder {
        return recyclerViewHolder(
            LayoutInflater.from(context).inflate(
            R.layout.item_view,
            parent,
            false
        )
        )
    }

    override fun onBindViewHolder(holder: recyclerAdapter.recyclerViewHolder, position: Int) {
        val name=names[position]
        holder.itemView.apply {
            textView.text=name
        }
    }

    override fun getItemCount()= names.size
}