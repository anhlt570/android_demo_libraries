package com.example.mediaplayer;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.MulticastSocket;

public class MediaPlayerActivity extends AppCompatActivity implements View.OnClickListener {
    public AssetFileDescriptor hellManHigh, hellManLow;
    private MediaPlayer mMusicPlayer;
    private AudioManager mAudioManager;
    Button btnPlay, btnStop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        initMedia();
    }

    public void initMedia() {
        try {
            hellManHigh = getAssets().openFd("Hell Man [high].m4a");
            hellManLow = getAssets().openFd("Hell Man [low].mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        assert mAudioManager != null;
        mAudioManager.setMode(AudioManager.MODE_NORMAL);
        mMusicPlayer = new MediaPlayer();
        try {

            mMusicPlayer.setDataSource(hellManHigh.getFileDescriptor(), hellManHigh.getStartOffset(), hellManHigh.getLength());
            mMusicPlayer.prepare();
            mMusicPlayer.setLooping(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        mMusicPlayer.start();
    }

    public void pause() {
        mMusicPlayer.pause();
    }

    public void stop() {
        mMusicPlayer.stop();
    }

    //View.OnClickListener_
    @Override
    public void onClick(View v) {
    }
    //_View.OnClickListener
}
