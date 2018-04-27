package com.demo.sensors;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.sensors.detail.AccelerometerFragment;
import com.demo.sensors.detail.TemperatureFragment;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //BaseFragment_
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }
    //_BaseFragment

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_accelerometer).setOnClickListener(this);
        view.findViewById(R.id.btn_temperature).setOnClickListener(this);
    }


    //OnClickListener_
    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_accelerometer) {
            navigationManager.addPage(new AccelerometerFragment());
        } else if (i == R.id.btn_temperature) {
            navigationManager.addPage(new TemperatureFragment());
        }
    }
    //_OnClickListener
}
