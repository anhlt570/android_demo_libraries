package demo.com.demolibraries.main

import com.demo.sensors.SensorMainActivity
import com.example.bluetooth.BluetoothActivity
import com.example.calltest.CallTestActivity
import com.example.chat.ChatActivity
import com.example.facebook.FacebookLoginActivity
import com.example.mediaplayer.MediaPlayerActivity
import com.example.recognizer.SpeechRecognitionActivity
import com.example.ui.UIDemoActivity

import java.util.ArrayList

import demo.com.data_handler.DataActivity
import demo.com.demolibraries.R

val MENU: List<MenuItem> = object : ArrayList<MenuItem>() {
    init {
        add(MenuItem()
                .setId(DATA_HANDLER)
                .setTitle("Data Handler")
                .setIconId(R.drawable.ic_data)
                .setActivityToOpen(DataActivity::class.java))
        add(MenuItem()
                .setId(SENSORS)
                .setTitle("Sensors")
                .setIconId(R.drawable.ic_sensor)
                .setActivityToOpen(SensorMainActivity::class.java))
        add(MenuItem()
                .setId(MEDIA_PLAYER)
                .setTitle("Media Player")
                .setIconId(R.drawable.ic_media)
                .setActivityToOpen(MediaPlayerActivity::class.java))
        add(MenuItem()
                .setId(FACEBOOK)
                .setTitle("Facebook")
                .setIconId(R.drawable.ic_facebook)
                .setActivityToOpen(FacebookLoginActivity::class.java))
        add(MenuItem()
                .setId(CUSTOM_VIEW)
                .setTitle("Custom View")
                .setIconId(R.drawable.ic_custom_view)
                .setActivityToOpen(UIDemoActivity::class.java))
        add(MenuItem()
                .setId(TEST_CALL)
                .setTitle("Test Call")
                .setIconId(R.drawable.ic_call)
                .setActivityToOpen(CallTestActivity::class.java))
        add(MenuItem()
                .setId(SPEECH_RECOGNITION)
                .setTitle("Speech Recognition")
                .setIconId(R.drawable.ic_recorder)
                .setActivityToOpen(SpeechRecognitionActivity::class.java))
        add(MenuItem()
                .setId(BLUETOOTH)
                .setTitle("Bluetooth")
                .setIconId(R.drawable.ic_bluetooth)
                .setActivityToOpen(BluetoothActivity::class.java))
        add(MenuItem()
                .setId(CHAT)
                .setTitle("Chat")
                .setIconId(R.drawable.ic_chat)
                .setActivityToOpen(ChatActivity::class.java))
    }
}
