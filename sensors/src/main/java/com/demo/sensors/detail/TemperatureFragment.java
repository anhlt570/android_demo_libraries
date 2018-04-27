package com.demo.sensors.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.demo.sensors.BaseFragment;
import com.demo.sensors.R;
import com.demo.sensors.SharedDataViewModel;

public class TemperatureFragment extends BaseFragment {
    private static final String TAG = "TemperatureFragment";
    private SharedDataViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(SharedDataViewModel.class);
        Log.d(TAG, "onCreate: " + viewModel);
    }

    //BaseFragment_
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_temperature;
    }
    //_BaseFragment
}
