package com.example.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle

class Dialog(context: Context) : Dialog(context,R.style.UI_main_theme) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog)

    }
}