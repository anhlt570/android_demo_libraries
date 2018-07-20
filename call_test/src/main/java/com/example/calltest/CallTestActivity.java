package com.example.calltest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class CallTestActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private PageAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        viewPager = findViewById(R.id.pager);
        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };
        if (checkRequiredPermissions(permissions)) {
            initPager();
        } else {
            requestPermissions(permissions, 128);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 128) {
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) return;
            }
            initPager();
        }
    }

    private void initPager() {
        adapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    private boolean checkRequiredPermissions(@NonNull String[] requiredPermissions) {
        if (Build.VERSION.SDK_INT < 23) return true;
        for (String permission : requiredPermissions) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
