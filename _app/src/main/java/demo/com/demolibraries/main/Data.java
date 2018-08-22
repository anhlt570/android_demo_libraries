package demo.com.demolibraries.main;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import demo.com.demolibraries.R;

public class Data {
    public static List<MenuEntity> entities = new ArrayList<MenuEntity>() {{
        add(new MenuEntity()
                .setId(MenuID.DATA_HANDLER)
                .setTitle("Data Handler")
                .setDescription("")
                .setIconId(R.drawable.ic_data));
        add(new MenuEntity()
                .setId(MenuID.SENSORS)
                .setTitle("Sensors")
                .setDescription("")
                .setIconId(R.drawable.ic_sensor));
        add(new MenuEntity()
                .setId(MenuID.MEDIA_PLAYER)
                .setTitle("Media Player")
                .setDescription("")
                .setIconId(R.drawable.ic_media));
        add(new MenuEntity()
                .setId(MenuID.FACEBOOK)
                .setTitle("Facebook")
                .setDescription("")
                .setIconId(R.drawable.ic_facebook));
        add(new MenuEntity()
                .setId(MenuID.CUSTOM_VIEW)
                .setTitle("Custom View")
                .setDescription("")
                .setIconId(R.drawable.ic_custom_view));
        add(new MenuEntity()
                .setId(MenuID.TEST_CALL)
                .setTitle("Test Call")
                .setDescription("")
                .setIconId(R.drawable.ic_call));
        add(new MenuEntity()
                .setId(MenuID.SPEECH_RECOGNITION)
                .setTitle("Speech Recognition")
                .setDescription("For detecting whatever user is speaking")
                .setIconId(R.drawable.ic_recorder));
    }};
}

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