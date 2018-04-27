package com.demo.sensors;

import android.arch.lifecycle.ViewModelProviders;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DetailFragment extends BaseFragment implements SensorEventListener {

    public static final String ARG_TYPE_SENSOR = "sensor.type";

    private static final String TAG = "SensorDetail";
    private SharedDataViewModel viewModel;
    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEvent event;
    private int sensorType;

    private TextView tvAccuracy, tvTimestamp, tvValues;

    public static DetailFragment newInstance(int sensorType) {

        Bundle args = new Bundle();
        args.putInt(ARG_TYPE_SENSOR,sensorType);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(SharedDataViewModel.class);
        sensorManager = viewModel.getSensorManager().getValue();
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Log.d(TAG, "onCreate: " + viewModel);
        if(getArguments()!= null){
            sensorType = getArguments().getInt(ARG_TYPE_SENSOR);
            sensor = sensorManager.getDefaultSensor(sensorType);
        }
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvAccuracy = view.findViewById(R.id.tv_accuracy);
        tvTimestamp = view.findViewById(R.id.tv_timestamp);
        tvValues = view.findViewById(R.id.tv_values);
    }

    //BaseFragment_
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_detail;
    }
    //_BaseFragment

    //SensorEventListener_
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        tvAccuracy.setText("Accuracy: " + sensorEvent.accuracy);
        tvTimestamp.setText("Timestamp: " + sensorEvent.timestamp);
        String values = "Value: \n";
        for (float i : sensorEvent.values) {
            values += i + "\n";
        }
        tvValues.setText(values);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        Log.d(TAG, "onAccuracyChanged: " + i);

    }
    //_SensorEventListener
}

