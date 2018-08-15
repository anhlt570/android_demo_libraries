package com.example.mediaplayer;

import android.view.View;

public class ClickListener implements View.OnClickListener {
    private MediaPlayerActivity activity;

    public ClickListener(MediaPlayerActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_audio) {
            activity.changePlayMusicState();
        } else if (i == R.id.btn_video) {
            activity.playVideo();
        }
    }
}
