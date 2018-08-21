package com.example.mediaplayer

import android.os.Bundle
import android.support.v4.view.PagerTabStrip
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity

class MediaPlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)
        initPager()
    }

    private fun initPager() {
        val pager = findViewById<ViewPager>(R.id.pager)
        pager.adapter = PagerAdapter(supportFragmentManager)
    }
}
