package demo.com.demolibraries.main;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
        MenuID.DATA_HANDLER,
        MenuID.SENSORS,
        MenuID.MEDIA_PLAYER,
        MenuID.FACEBOOK,
        MenuID.CUSTOM_VIEW,
        MenuID.TEST_CALL,
        MenuID.SPEECH_RECOGNITION
})
@Retention(RetentionPolicy.SOURCE)
@interface MenuID {
    int DATA_HANDLER = 0;
    int SENSORS = 1;
    int MEDIA_PLAYER = 2;
    int FACEBOOK = 3;
    int CUSTOM_VIEW = 4;
    int TEST_CALL = 5;
    int SPEECH_RECOGNITION = 6;
}