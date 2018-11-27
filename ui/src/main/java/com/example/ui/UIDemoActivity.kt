package com.example.ui

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_custom_view.*

class UIDemoActivity : AppCompatActivity() {
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
//        val uiOptions = (View.SYSTEM_UI_FLAG_IMMERSIVE
//                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
//        window.decorView.systemUiVisibility = uiOptions
        supportFragmentManager.beginTransaction().replace(R.id.container, Fragment1()).commit()
    }
}
