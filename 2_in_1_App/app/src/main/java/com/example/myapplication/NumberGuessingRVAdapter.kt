package com.example.myapplication

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*
import kotlin.random.Random

class NumberGuessingRVAdapter (private val context: Context, private val list:List<String>):
    RecyclerView.Adapter<NumberGuessingRVAdapter.RecyclerViewHolder>() {
    class RecyclerViewHolder (itemView: View) :RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        return RecyclerViewHolder(
            LayoutInflater.from(context).inflate(
            R.layout.item_row,
            parent,
            false
        )
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val rnd = Random.Default
        var color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

        holder.itemView.apply {
            tvMessage.setBackgroundColor(color)
            tvMessage.text=list[position]
        }
    }

    override fun getItemCount()=list.size
}
