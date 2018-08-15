package com.example.mediaplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.IOException;

public class MediaPlayerActivity extends AppCompatActivity {
    private MediaPlayer mMusicPlayer;
    private AudioManager mAudioManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        ClickListener clickListener = new ClickListener(this);
        findViewById(R.id.btn_video).setOnClickListener(clickListener);
        findViewById(R.id.btn_audio).setOnClickListener(clickListener);
        initMedia();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMusicPlayer != null)
            mMusicPlayer.release();
    }

    public void initMedia() {
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        assert mAudioManager != null;
        mAudioManager.setMode(AudioManager.MODE_NORMAL);

    }

    public void play() {
        if (mMusicPlayer == null) {
            mMusicPlayer = new MediaPlayer();
            try {
                mMusicPlayer.setDataSource(this, Uri.parse("https://zmp3-mp3-s1-te-vnso-qt-3.zadn.vn/57a44002914678182157/7136304650540504646?authen=exp=1534401383~acl=/57a44002914678182157/*~hmac=f454eb92d992577b1f7f542378edc097"));
                mMusicPlayer.prepare();
                mMusicPlayer.setLooping(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mMusicPlayer.start();
    }

    public void pause() {
        mMusicPlayer.pause();
    }

    public void stop() {
        mMusicPlayer.stop();
    }

    boolean isPlayingMusic() {
        return mMusicPlayer != null && mMusicPlayer.isPlaying();
    }

    public void changePlayMusicState() {
        if (isPlayingMusic()) {
            mMusicPlayer.pause();
        } else {
            play();
        }
    }

    public void playVideo() {
        Uri resource = Uri.parse("https://zmp3-mv-mcloud-bf-s7-te-vnso-qt-3.zadn.vn/jxlJuU0gY_s/1c7b56f5d0b039ee60a1/058b7e344971a02ff960/480/Duyen-Minh-Lo.mp4?authen=exp=1534477721~acl=/jxlJuU0gY_s/*~hmac=38cd5e4731ba2cf4d5de24bbae77e6d0");
        final VideoView videoView = findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(resource);
//        videoView.setZOrderOnTop(true);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });
    }
}
