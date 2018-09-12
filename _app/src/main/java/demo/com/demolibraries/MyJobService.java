package demo.com.demolibraries;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Handler;

public class MyJobService extends JobService {
    public static final String TAG = "MyJobService";

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob: " + params);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://wonderfulengineering.com/wp-content/uploads/2016/01/Desktop-Wallpaper-4.jpg");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream inputStream = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    Log.d(TAG, "onStartJob: "+ bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob: " + params);
        return false;
    }
}
