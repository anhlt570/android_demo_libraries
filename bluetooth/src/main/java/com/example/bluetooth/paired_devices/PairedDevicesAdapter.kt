package com.example.bluetooth.paired_devices

import android.bluetooth.BluetoothDevice
import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bluetooth.R

class PairedDevicesAdapter(@NonNull pairedDevices: ArrayList<BluetoothDevice>) : RecyclerView.Adapter<PairedDeviceViewHolder>() {
    var devices = arrayListOf<BluetoothDevice>()

    init {
        devices = pairedDevices
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PairedDeviceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_paired_device, parent, false)
        return PairedDeviceViewHolder(view)
    }

    override fun getItemCount() = devices.size

    override fun onBindViewHolder(holder: PairedDeviceViewHolder, position: Int) {
        holder.bindView(devices.elementAt(position))
    }
}