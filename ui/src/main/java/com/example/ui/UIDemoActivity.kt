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
        val url2 = "https://blogandweb.com/wp-content/uploads/2008/11/png-animado.png"

        val animator = ApngAnimator(this, img_apng)
        animator.isLoop = true
        animator.isAutoPlay = false
        animator.load(url2)
        animator.onLoaded {
            animator.playAndHide()
        }

    }
}
