package com.example.calltest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.red5pro.streaming.R5Connection;
import com.red5pro.streaming.R5Stream;
import com.red5pro.streaming.R5StreamProtocol;
import com.red5pro.streaming.config.R5Configuration;
import com.red5pro.streaming.view.R5VideoView;

import java.util.Objects;

public class SubscriberFragment extends Fragment {
    private Button btnSubscribe;
    public R5VideoView videoView;

    private R5Stream stream;
    private R5Configuration configuration;
    private boolean isSubscribing;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_subscriber, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configuration = new R5Configuration(R5StreamProtocol.RTSP, "192.168.8.72", 8554, "live", 1.0f);
        //TestKey 7CF2-4NUJ-ICRN-YLLT
        configuration.setLicenseKey("above key");
        configuration.setBundleID(Objects.requireNonNull(getActivity()).getPackageName());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        videoView = view.findViewById(R.id.subscribeView);

        btnSubscribe = view.findViewById(R.id.btn_subscribe);
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscribe();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if(isSubscribing){
            subscribe();
        }
    }

    private void subscribe() {
        if (isSubscribing) {
            stopSubscribe();
        } else {
            startSubscribe();
        }

        isSubscribing = !isSubscribing;
        btnSubscribe.setText(isSubscribing ? "stop" : "start");
    }

    private void stopSubscribe() {
        if (stream != null) {
            stream.stop();
        }
    }

    private void startSubscribe() {
        stream = new R5Stream(new R5Connection(configuration));
        videoView.attachStream(stream);
        stream.play("R5Stream");

    }
}
