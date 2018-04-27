package demo.com.demolibraries;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.demo.sensors.SensorMainActivity;

import demo.com.data_handler.DataActivity;
import demo.com.ui_components.UIActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_ui_components).setOnClickListener(this);
        findViewById(R.id.btn_data_handler).setOnClickListener(this);
        findViewById(R.id.btn_sensors).setOnClickListener(this);
        printInfo();
    }

    void printInfo() {
        Utility.hashFromSHA1("D5:00:6F:2E:44:50:95:16:CB:B0:57:45:ED:DF:DA:31:87:0A:29:09");
    }

    public void openActivity(Class<?> activity) {
        startActivity(new Intent(this, activity));
    }

    //View.OnClickListener_
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_ui_components:
                openActivity(UIActivity.class);
                break;
            case R.id.btn_data_handler:
                openActivity(DataActivity.class);
                break;
            case R.id.btn_sensors:
                openActivity(SensorMainActivity.class);
                break;
        }
    }
    //_View.OnClickListener
}
