package com.example.calltest;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.red5pro.streaming.R5Connection;
import com.red5pro.streaming.R5Stream;
import com.red5pro.streaming.R5StreamProtocol;
import com.red5pro.streaming.config.R5Configuration;

import java.io.IOException;
import java.util.Objects;

public class PublisherFragment extends Fragment implements SurfaceHolder.Callback {
    private R5Configuration configuration;
    private R5Stream stream;
    private Camera camera;
    private boolean isPublishing = true;

    private SurfaceView cameraPreview;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configuration = new R5Configuration(R5StreamProtocol.RTSP, "localhost", 8554, "live", 1.0f);
        //TestKey 7CF2-4NUJ-ICRN-YLLT
        configuration.setLicenseKey("key above");
        configuration.setBundleID(Objects.requireNonNull(getActivity()).getPackageName());
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_publisher, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cameraPreview = view.findViewById(R.id.surfaceView);
    }

    @Override
    public void onStart() {
        super.onStart();
        preview();
    }

    private void preview() {
        camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
        cameraPreview.getHolder().addCallback(this);
    }

    /*--------------------------------SurfaceHolder.Callback------------------------------*/
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
