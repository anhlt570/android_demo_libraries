package com.example.bluetooth

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothProfile
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
        initBluetoothClient()
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
        val scaleAnimationX = ObjectAnimator.ofFloat(img_status, "scaleX", 0.50f)
        scaleAnimationX.duration = 500
        scaleAnimationX.repeatCount = ObjectAnimator.INFINITE
        scaleAnimationX.repeatMode = ObjectAnimator.REVERSE


        val scaleAnimationY = ObjectAnimator.ofFloat(img_status, "scaleY", 0.5f)
        scaleAnimationY.duration = 500
        scaleAnimationY.repeatCount = ObjectAnimator.INFINITE
        scaleAnimationY.repeatMode = ObjectAnimator.REVERSE


        val fadedAnimation = ObjectAnimator.ofFloat(img_status, "alpha", 0.0f)
        fadedAnimation.duration = 500
        fadedAnimation.repeatCount = ObjectAnimator.INFINITE
        fadedAnimation.repeatMode = ObjectAnimator.REVERSE
        fadedAnimation.target = img_status

        bluetoothStatusAnimation.playTogether(scaleAnimationX, scaleAnimationY, fadedAnimation)
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

    private fun initBluetoothClient() {
        val profileListener = object : BluetoothProfile.ServiceListener {
            override fun onServiceDisconnected(profile: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onServiceConnected(profile: Int, proxy: BluetoothProfile?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        bluetoothAdapter!!.getProfileProxy(this,profileListener,BluetoothProfile.HEADSET)
    }
}