package com.example.ui

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_custom_view.*
import oupson.apng.ApngAnimator

class UIDemoActivity : AppCompatActivity() {
    var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)
        img_animator.setOnClickListener {
            val src = img_animator.drawable
            if (src is Animatable) {
                src.stop()
                src.start()
            }
        }
        val url3 = "https://css-ig.net/support/messages/apng-img/apng-purple-pony-pingo-apng4-s9-nodither.png"
        val url1 = "https://blog.photopea.com/gifs/nyanCat.png"
        val url2 = "http://210.148.155.30:9117/api=load_img&token=eyJhbGciOiJIUzI1NiJ9.eyJ2YSI6MX0.82CPwFJ6IEUbm-ojE_wVV5g6ezhWYKIc0nSJ6klrKsEANGELeNoyTTZISTFPTDG2MDA2NDRNTDQyNDcwMzVSy89LzcksS4XTxmqGekbmAAAAAP__&img_id=5c0500a33803114e2b512559&img_kind=9"

        val animator = ApngAnimator(this, img_apng)
        animator.isLoop = true
        animator.isAutoPlay = false
        animator.load(url2)
        animator.onLoaded {
            animator.playAndHide()
        }

    }
}
