package com.example.mediaplayer.video

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mediaplayer.R
import kotlinx.android.synthetic.main.fragment_video_list.*

class VideoListFragment : Fragment(), OnPlayVideoListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_video_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_list_video.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val itemDivider = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        rv_list_video.addItemDecoration(itemDivider)
        val adapter = VideoListAdapter()
        adapter.onPlayVideoListener = this
        rv_list_video.adapter = adapter
    }

    /*------------------------------- ------OnPlayVideoListener -----------------------------------*/
    override fun onPlay(link: String) {
//        val intent = Intent(context, VideoPlayerActivity::class.java)
//        intent.putExtra(VideoPlayerActivity.ARG_VIDEO_LINK, link)
//        startActivity(intent)

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(intent)
    }
}
