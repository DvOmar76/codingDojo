package com.example.youtubeapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapp.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import kotlinx.android.synthetic.main.item_row.view.*


class RecyclerViewAdapter(private val videos:Array<Array<String>>, private val youTubePlayerV: YouTubePlayer):RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
    class RecyclerViewHolder (itemView:View):RecyclerView.ViewHolder(itemView)



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_row,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {


        holder.itemView.apply {
            button.text = videos[position][0]
            button.setOnClickListener {
                youTubePlayerV.loadVideo(videos[position][1],0f)
            }
        }

    }

    override fun getItemCount()=videos.size
}