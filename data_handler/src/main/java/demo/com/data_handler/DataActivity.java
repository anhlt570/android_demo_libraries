package demo.com.data_handler;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

import demo.com.data_handler.entities.Club;

public class DataActivity extends AppCompatActivity {
    private MyViewModel mModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        mModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MyViewModel.class);

        mModel.getClubs().observe(this, new Observer<ArrayList<Club>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Club> clubs) {
                if (clubs == null) return;
                for (Club club : clubs) {
                    ((TextView) findViewById(R.id.tv_data)).setText(club.toString());
                }
            }
        });

    }
}
