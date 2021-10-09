package com.example.youtubeapp.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import com.example.youtubeapp.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer


class GridAdapter(private val videos: Array<Array<String>>,
                  private val youTubePlayerV: YouTubePlayer, private val context: Context): BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    override fun getCount() = videos.size

    override fun getItem(position: Int) = videos[position]


    override fun getItemId(position: Int) = position.toLong()


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View ?{
        var convertView = convertView
        var currentTitle = videos[position][0]
        var currentLink = videos[position][1]

        if (layoutInflater == null) layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if (convertView == null) convertView = layoutInflater!!.inflate(R.layout.item_row, null)

        if (convertView != null) {
            var buttonView = convertView.findViewById<Button>(R.id.button)
            buttonView.text = currentTitle
            buttonView.setOnClickListener {
                youTubePlayerV.loadVideo(currentLink, 0f)
            }
        }
        return convertView

    }
}