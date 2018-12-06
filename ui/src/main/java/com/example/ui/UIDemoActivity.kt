package com.example.ui

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_custom_view.*
import oupson.apng.ApngAnimator
import java.net.URL

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
        val url1 = "https://pissingonmypistols.files.wordpress.com/2015/06/lions-eye1.png"
        val url2 = "http://210.148.155.30:9117/api=load_img&token=eyJhbGciOiJIUzI1NiJ9.eyJ2YSI6MX0.82CPwFJ6IEUbm-ojE_wVV5g6ezhWYKIc0nSJ6klrKsEANGELeNoyTTYwSU5MtDS2MDA2NDRISUqxMDZLNTNWy89LzcksS4XTxmqGekbmAAAAAP__&img_id=5c0500b63803114e2b51255a&img_kind=9"
        val url3 = "http://210.148.155.30:9117/api=load_img&token=eyJhbGciOiJIUzI1NiJ9.eyJ2YSI6MX0.82CPwFJ6IEUbm-ojE_wVV5g6ezhWYKIc0nSJ6klrKsEANGELeNoyTTYwSU5MtDS2MDA2NDRISUqxMDZLNTNWy89LzcksS4XTxmqGekbmAAAAAP__&img_id=5c0500a33803114e2b512559&img_kind=9"
        val animator = ApngAnimator(this).loadInto(img_apng)
        val abc = ApngAnimator(this).loadUrl(URL(url3), null, null)
//        val drawable1 = ApngDrawable.decode(url1)
//        img_apng.setImageDrawable(drawable1)

    }
}
