package com.demo.sensors;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;

public class SharedDataViewModel extends AndroidViewModel {
    private MutableLiveData<SensorManager> mSensorManager;

    public MutableLiveData<SensorManager> getSensorManager() {
        if (mSensorManager == null) {
            mSensorManager = new MutableLiveData<>();
            mSensorManager.setValue((SensorManager) getApplication().getSystemService(Context.SENSOR_SERVICE));
        }
        return mSensorManager;
    }

    public SharedDataViewModel(@NonNull Application application) {
        super(application);
    }
}
