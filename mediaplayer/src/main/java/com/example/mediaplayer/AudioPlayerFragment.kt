package com.example.mediaplayer

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_audio_player.*

class AudioPlayerFragment : Fragment() {
    private val TAG = "AudioPlayerFragment"
    private var mediaPlayer: MediaPlayer = MediaPlayer()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mediaPlayer.setDataSource(activity, Uri.parse(Constants.MUSIC_LINK))
        mediaPlayer.setOnPreparedListener {
            Log.d("Mediaplayer", "shit")
            progressBar.progress = 0
        }

        mediaPlayer.setOnCompletionListener {
            progressBar.progress = 0
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_audio_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnPlay.setOnClickListener { play() }
        btnStop.setOnClickListener { stop() }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    fun pauseMusic() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            btnPlay.text = "Pause"
        }
    }

    fun play() {
        mediaPlayer.prepare()
        mediaPlayer.start()
        btnPlay.text = "Play"
    }

    fun stop() {
        mediaPlayer.stop()
        progressBar.progress = 0
    }
}
