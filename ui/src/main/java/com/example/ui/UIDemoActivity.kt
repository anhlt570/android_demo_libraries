package com.example.ui

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_custom_view.*

class UIDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)
        img_animator.setOnClickListener {
            val src =img_animator.drawable
            if(src is Animatable){
                src.stop()
                src.start()
            }
        }
    }
}
