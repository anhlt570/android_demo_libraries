package com.example.bluetooth.online_devices

import android.bluetooth.BluetoothDevice
import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bluetooth.R

class OnlineDevicesAdapter(@NonNull onlineDevices: ArrayList<BluetoothDevice>) : RecyclerView.Adapter<OnlineDevicesViewHolder>() {
    private var devices = arrayListOf<BluetoothDevice>()

    init {
        devices = onlineDevices
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnlineDevicesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_online_devices, parent, false)
        return OnlineDevicesViewHolder(view)
    }

    override fun getItemCount() = devices.size

    override fun onBindViewHolder(holder: OnlineDevicesViewHolder, position: Int) {
        holder.bindView(devices.elementAt(position))
    }
}