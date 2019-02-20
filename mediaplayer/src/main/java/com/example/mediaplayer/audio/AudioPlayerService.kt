package com.example.mediaplayer.audio

import android.app.IntentService
import android.content.Intent
import android.os.IBinder

class AudioPlayerService : IntentService("Music_player_service") {
    override fun onHandleIntent(intent: Intent?) {
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


}