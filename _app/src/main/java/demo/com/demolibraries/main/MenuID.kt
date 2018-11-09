package demo.com.demolibraries.main

import android.support.annotation.IntDef

const val DATA_HANDLER = 0
const val SENSORS = 1
const val MEDIA_PLAYER = 2
const val FACEBOOK = 3
const val CUSTOM_VIEW = 4
const val TEST_CALL = 5
const val SPEECH_RECOGNITION = 6
const val BLUETOOTH = 7
const val CHAT = 7

@IntDef(DATA_HANDLER, SENSORS, MEDIA_PLAYER, FACEBOOK, CUSTOM_VIEW, TEST_CALL, SPEECH_RECOGNITION, BLUETOOTH, CHAT)
@Retention(AnnotationRetention.SOURCE)
annotation class MenuID