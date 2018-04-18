package demo.com.demolibraries;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import demo.com.ui_components.UIActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_open_ui_components).setOnClickListener(this);
    }

    public void openActivity(Class<?> activity) {
        startActivity(new Intent(this, activity));
    }

    //View.OnClickListener_
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_open_ui_components:
                openActivity(UIActivity.class);
                break;
        }
    }
    //_View.OnClickListener
}
