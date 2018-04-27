package com.demo.sensors;

import android.hardware.Sensor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void startSensor(int sensorType) {
        navigationManager.addPage(DetailFragment.newInstance(sensorType));
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
            startSensor(Sensor.TYPE_ACCELEROMETER);
        } else if (i == R.id.btn_temperature) {
            startSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        }
    }
    //_OnClickListener
}
