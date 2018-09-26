package com.example.bluetooth.paired_devices

import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bluetooth.R
import kotlinx.android.synthetic.main.fragment_paired_devices.*

class PairedDevicesFragment : Fragment() {
    companion object {
        public val ARG_DEVICES = "com.example.bluetooth.paired_devices.PairedDevicesFragment.devices"
        public fun newInstance(pairedDevices: ArrayList<BluetoothDevice>): PairedDevicesFragment {
            val fragment = PairedDevicesFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_DEVICES, pairedDevices)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_paired_devices, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            rv_devices.adapter = PairedDevicesAdapter(it.getParcelableArrayList<BluetoothDevice>(ARG_DEVICES))
        }
    }
}