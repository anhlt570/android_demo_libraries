package com.demo.sensors;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SensorMainActivity extends AppCompatActivity {
    public NavigationManager navigationManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(SharedDataViewModel.class);
        navigationManager = new NavigationManager(this, R.id.container_fragment);
        setContentView(R.layout.activity_main_sensor);
        navigationManager.addPage(new HomeFragment());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if ( navigationManager.getFragmentManager().getBackStackEntryCount() <= 0) {
            finish();
        }
    }
}
