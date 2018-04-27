package com.demo.sensors.detail;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.sensors.BaseFragment;
import com.demo.sensors.R;
import com.demo.sensors.SharedDataViewModel;

public class AccelerometerFragment extends BaseFragment {
    private static final String TAG = "AccelerometerFragment";
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
        return R.layout.fragment_accelerometer;
    }
    //_BaseFragment
}
