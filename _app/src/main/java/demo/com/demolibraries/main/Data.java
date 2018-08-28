package demo.com.demolibraries.main;

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