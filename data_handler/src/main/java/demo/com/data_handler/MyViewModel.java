package demo.com.data_handler;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.nfc.Tag;
import android.util.Log;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import demo.com.data_handler.entities.Club;
import demo.com.data_handler.entities.Player;


public class MyViewModel extends AndroidViewModel {
    public static final String TAG = "MyViewModel";
    private MutableLiveData<ArrayList<Club>> mClubs;

    public MyViewModel(Application application) {
        super(application);
    }

    public void loadData() {

        try {
            InputStream dataStream = getApplication().getAssets().open("players.json");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataStream));
            StringBuilder outputData = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                outputData.append(line);
            }

            Moshi moshi = new Moshi.Builder().build();
            Type type = Types.newParameterizedType(List.class, Club.class, Player.class);
            JsonAdapter<ArrayList<Club>> jsonAdapter = moshi.adapter(type);
            mClubs.setValue(jsonAdapter.fromJson(outputData.toString()));

            Log.d(TAG, "loadData: " + outputData.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LiveData<ArrayList<Club>> getClubs() {
        if (mClubs == null) {
            mClubs = new MutableLiveData<>();
            loadData();
        }
        return mClubs;
    }
}
