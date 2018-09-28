package com.example.bluetooth.online_devices

import android.bluetooth.BluetoothDevice
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_paired_device.view.*

public class OnlineDevicesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    public fun bindView(bluetoothDevice: BluetoothDevice) {
        itemView.tv_name.text = bluetoothDevice.name
        itemView.tv_mac_address.text = bluetoothDevice.address
    }
}