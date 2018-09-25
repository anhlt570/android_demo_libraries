package com.example.bluetooth.dialog

import android.os.Bundle
import com.example.bluetooth.R
import kotlinx.android.synthetic.main.dialog_alert.*

class NotificationDialog : BaseDialog() {
    companion object {
        val ARG_CONTENT = "com.example.bluetooth.dialog.content"
        fun newInstance(content: String): NotificationDialog {
            val bundle = Bundle()
            bundle.putString(ARG_CONTENT, content)
            val notificationDialog = NotificationDialog()
            notificationDialog.arguments = bundle
            return notificationDialog
        }
    }

    override fun getLayoutResource() = R.layout.dialog_alert
    override fun init() {
        val content = arguments?.getString(ARG_CONTENT)
        tv_content.text = content

    }

}