package demo.com.demolibraries.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.demo.sensors.SensorMainActivity;
import com.example.calltest.CallTestActivity;
import com.example.facebook.FacebookLoginActivity;
import com.example.mediaplayer.MediaPlayerActivity;
import com.example.ui.UIDemoActivity;

import demo.com.data_handler.DataActivity;
import demo.com.demolibraries.R;
import demo.com.demolibraries.Utility;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        printInfo();
        RecyclerView rvListMenus = findViewById(R.id.rv_list_menu);
        MenuAdapter menuAdapter = new MenuAdapter(Data.entities, this);
        rvListMenus.setAdapter(menuAdapter);
    }


    void printInfo() {
        Utility.hashFromSHA1("D5:00:6F:2E:44:50:95:16:CB:B0:57:45:ED:DF:DA:31:87:0A:29:09");
    }

    public void openActivity(Class<?> activity) {
        startActivity(new Intent(this, activity));
    }

    /*--------------------------OnItemClickListener------------------------*/
    @Override
    public void onItemClick(MenuEntity menu) {
        switch (menu.getId()) {
            case MenuID.DATA_HANDLER:
                openActivity(DataActivity.class);
                break;
            case MenuID.SENSORS:
                openActivity(SensorMainActivity.class);
                break;
            case MenuID.MEDIA_PLAYER:
                openActivity(MediaPlayerActivity.class);
                break;
            case MenuID.FACEBOOK:
                openActivity(FacebookLoginActivity.class);
                break;
            case MenuID.CUSTOM_VIEW:
                openActivity(UIDemoActivity.class);
                break;
            case MenuID.TEST_CALL:
                openActivity(CallTestActivity.class);
                break;
        }
    }
}
