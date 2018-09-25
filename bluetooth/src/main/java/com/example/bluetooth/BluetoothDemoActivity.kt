package com.example.bluetooth

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.AnimationSet
import kotlinx.android.synthetic.main.activity_bluetooth.*

class BluetoothDemoActivity : AppCompatActivity() {
    companion object {
        private const val REQUEST_CODE_ENABLE_BLUETOOTH = 1001
    }

    val bluetoothStatusAnimation = AnimatorSet()

    private var bluetoothAdapter: BluetoothAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)
        initStatusAnimation()
        initBluetooth()
        initBluetoothStatusReceiver()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ENABLE_BLUETOOTH) {
            setupBluetoothStatus()
        }
    }
    /*_________________________________________________________________________________________________________________*/

    private fun initBluetooth() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        setupBluetoothStatus()
        panel_status.setOnClickListener {
            if (bluetoothAdapter!!.isEnabled) {
                bluetoothAdapter!!.disable()
            } else {
                val enableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableIntent, REQUEST_CODE_ENABLE_BLUETOOTH)
            }
        }
    }

    private fun initStatusAnimation() {
        val scaleAnimation = ObjectAnimator.ofFloat(img_status, "scale", 40.0f)
        scaleAnimation.duration = 500
        scaleAnimation.repeatCount = ObjectAnimator.INFINITE
        scaleAnimation.repeatMode = ObjectAnimator.REVERSE

        val fadedAnimation = ObjectAnimator.ofFloat(img_status, "alpha", 0.0f)
        fadedAnimation.duration = 500
        fadedAnimation.repeatCount = ObjectAnimator.INFINITE
        fadedAnimation.repeatMode = ObjectAnimator.REVERSE
        fadedAnimation.target = img_status

        bluetoothStatusAnimation.playTogether(scaleAnimation, fadedAnimation)
    }

    private fun initBluetoothStatusReceiver() {
        val intentFilter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)

        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val action = intent!!.action
                if (BluetoothAdapter.ACTION_STATE_CHANGED == action) {
                    setupBluetoothStatus()
                }
            }
        }, intentFilter)
    }

    private fun setupBluetoothStatus() {
        if (!bluetoothAdapter!!.isEnabled) {
            view_disable.visibility = View.VISIBLE
            img_status.setImageResource(R.drawable.ic_status_dot_disabled)
            img_status.animation?.cancel()
            bluetoothStatusAnimation.cancel()
        } else {
            view_disable.visibility = View.GONE
            img_status.setImageResource(R.drawable.ic_status_dot)
            bluetoothStatusAnimation.start()
        }
        tv_state.text = "Bluetooth State = ${bluetoothAdapter!!.state}"
    }
}