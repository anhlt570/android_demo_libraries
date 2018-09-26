package com.example.bluetooth

import android.animation.AnimatorSet
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothProfile
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_bluetooth.*

class BluetoothDemoActivity : AppCompatActivity() {
    companion object {
        private const val REQUEST_CODE_ENABLE_BLUETOOTH = 1001
    }

    private var bluetoothStatusAnimation: AnimatorSet? = null

    private var bluetoothAdapter: BluetoothAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)
        if (bluetoothAdapter != null) {
            v_no_bluetooth.visibility = View.GONE
            initStatusAnimation()
            initBluetooth()
            initBluetoothStatusReceiver()
            initBluetoothClient()
        } else {
            v_no_bluetooth.visibility = View.VISIBLE
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ENABLE_BLUETOOTH) {
            setupBluetoothStatus()
        }
    }
    /*============================================================================================*/

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
        bluetoothStatusAnimation = AnimationManager.INSTANCE.getOnlineAnimation(img_status)
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
            bluetoothStatusAnimation?.cancel()
        } else {
            view_disable.visibility = View.GONE
            img_status.setImageResource(R.drawable.ic_status_dot)
            bluetoothStatusAnimation?.start()
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

        bluetoothAdapter!!.getProfileProxy(this, profileListener, BluetoothProfile.HEADSET)
    }
}