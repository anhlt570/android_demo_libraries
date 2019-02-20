package com.example.mediaplayer.video

import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.example.mediaplayer.R
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.hls.HlsDataSourceFactory
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_video_player.*

class VideoPlayerActivity: AppCompatActivity() {
    lateinit var videoPlayer :SimpleExoPlayer

    companion object {
        const val ARG_VIDEO_LINK = "ARG_VIDEO_LINK "
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        videoPlayer = ExoPlayerFactory.newSimpleInstance(this)
        videoPlayer.playWhenReady = true
        v_video_player.player = videoPlayer

        val link = intent.getStringExtra(ARG_VIDEO_LINK)
        val uri = Uri.parse(link)
        val dataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this,getString(R.string.app_name)))
        val videoSource = ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
        videoPlayer.prepare(videoSource)
    }

    override fun onDestroy() {
        super.onDestroy()
        videoPlayer.release()
    }
}