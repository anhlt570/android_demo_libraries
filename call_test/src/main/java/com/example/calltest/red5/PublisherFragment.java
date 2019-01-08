package com.example.calltest.red5;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.calltest.R;
import com.red5pro.streaming.R5Connection;
import com.red5pro.streaming.R5Stream;
import com.red5pro.streaming.R5StreamProtocol;
import com.red5pro.streaming.config.R5Configuration;
import com.red5pro.streaming.event.R5ConnectionEvent;
import com.red5pro.streaming.event.R5ConnectionListener;
import com.red5pro.streaming.source.R5Camera;
import com.red5pro.streaming.source.R5Microphone;

import java.io.IOException;
import java.util.Objects;

public class PublisherFragment extends Fragment implements SurfaceHolder.Callback {
    private R5Configuration configuration;
    private R5Stream stream;
    private Camera camera;
    private boolean isPublishing = true;

    Button btnPublish;

    private SurfaceView cameraPreview;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configuration = new R5Configuration(R5StreamProtocol.RTSP, "192.168.8.72", 8554, "live", 1.0f);
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

        btnPublish = view.findViewById(R.id.btn_publish);
        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publish();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        preview();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(isPublishing){
            publish();
        }
    }

    private void publish() {
        if (isPublishing) stopPublish();
        else
            startPublish();

        isPublishing = !isPublishing;
        btnPublish.setText(isPublishing ? "stop" : "start");
    }

    private void startPublish() {
        camera.stopPreview();
        stream = new R5Stream(new R5Connection(configuration));
        stream.setView(cameraPreview);
        R5Camera r5Camera = new R5Camera(camera, 200, 200);
        R5Microphone r5Microphone = new R5Microphone();

        stream.attachCamera(r5Camera);
        stream.attachMic(r5Microphone);

        stream.publish("R5Stream", R5Stream.RecordType.Live);
        stream.setListener(new R5ConnectionListener() {
            @Override
            public void onConnectionEvent(R5ConnectionEvent r5ConnectionEvent) {
                Log.d("hahaha", "onConnectionEvent: "+ r5ConnectionEvent.message);
            }
        });
        camera.startPreview();
    }

    private void stopPublish() {
        if (stream != null) {
            stream.stop();
            camera.startPreview();
        }
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
