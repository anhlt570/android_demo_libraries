package com.example.mediaplayer

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.mediaplayer.audio.AudioPlayerFragment
import com.example.mediaplayer.video.VideoListFragment

class PagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    override fun getCount() = 2

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return AudioPlayerFragment()
            1 -> return VideoListFragment()
        }
        return null
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Audio Player"
            1 -> return "Video Player"
        }
        return ""
    }
}