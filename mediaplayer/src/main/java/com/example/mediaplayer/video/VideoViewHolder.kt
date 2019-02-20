package com.example.mediaplayer.video

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.mediaplayer.R

class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var onPlayVideoListener: OnPlayVideoListener? = null
    private val tvVideoName: TextView = itemView.findViewById(R.id.tv_video_name)

    init {
        itemView.setOnClickListener {
            onPlayVideoListener?.onPlay(VIDEO_ITEMS[adapterPosition].link)
        }
    }

    fun bindView(videoContentEntity: VideoContentEntity) {
        tvVideoName.text = videoContentEntity.name
    }
}