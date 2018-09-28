package com.example.bluetooth

import android.animation.AnimatorSet
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothProfile
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.bluetooth.paired_devices.PairedDevicesFragment
import com.example.ui.AnimationManager
import kotlinx.android.synthetic.main.activity_bluetooth.*

class BluetoothActivity : AppCompatActivity() {
    companion object {
        private const val REQUEST_CODE_ENABLE_BLUETOOTH = 1001
    }

    private var bluetoothStatusAnimation: AnimatorSet? = null
    private var bluetoothAdapter: BluetoothAdapter? = null
    private lateinit var broadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)
        bluetoothStatusAnimation = AnimationManager.INSTANCE.getOnlineAnimation(img_status)
        initBluetooth()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ENABLE_BLUETOOTH) {
            setupBluetoothStatus()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }
    /*============================================================================================*/

    private fun initBluetooth() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        setupBluetoothStatus()
        if (bluetoothAdapter == null) {
            v_no_bluetooth.visibility = View.VISIBLE
        } else {
            v_no_bluetooth.visibility = View.GONE
            panel_status.setOnClickListener {
                if (bluetoothAdapter!!.isEnabled) {
                    bluetoothAdapter!!.disable()
                } else {
                    val enableIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    startActivityForResult(enableIntent, REQUEST_CODE_ENABLE_BLUETOOTH)
                }
            }
            initBluetoothStatusReceiver()
            initBluetoothClient()
            supportFragmentManager.beginTransaction().replace(R.id.container_connected_device, DeviceInfoFragment()).commit()
            bluetoothAdapter?.bondedDevices?.let {
                val listDevices = arrayListOf<BluetoothDevice>()
                listDevices.addAll(it)
                supportFragmentManager.beginTransaction().replace(R.id.container_paired_devices, PairedDevicesFragment.newInstance(listDevices)).commit()
            }
        }
    }

    private fun initBluetoothStatusReceiver() {
        val intentFilter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val action = intent!!.action
                if (BluetoothAdapter.ACTION_STATE_CHANGED == action) {
                    setupBluetoothStatus()
                }
            }
        }
        registerReceiver(broadcastReceiver, intentFilter)
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
                Log.d("anhlee", "onServiceDisconnected: $profile")
            }

            override fun onServiceConnected(profile: Int, proxy: BluetoothProfile?) {
                Log.d("anhlee", "onServiceConnected: $profile")
            }
        }

        bluetoothAdapter!!.getProfileProxy(this, profileListener, BluetoothProfile.HEADSET)
    }
}