package com.example.mediaplayer.video

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mediaplayer.R

class VideoListAdapter : RecyclerView.Adapter<VideoViewHolder>() {
    var onPlayVideoListener: OnPlayVideoListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video_list, parent, false)
        val viewHolder = VideoViewHolder(view)
        viewHolder.onPlayVideoListener = onPlayVideoListener
        return viewHolder
    }

    override fun getItemCount() = VIDEO_ITEMS.size

    override fun onBindViewHolder(viewHolder: VideoViewHolder, position: Int) {
        viewHolder.bindView(VIDEO_ITEMS[position])
    }
}