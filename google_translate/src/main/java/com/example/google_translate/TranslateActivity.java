package com.example.google_translate;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.common.collect.Lists;

import java.io.FileInputStream;
import java.io.IOException;

import io.grpc.Context;
import io.opencensus.stats.BucketBoundaries;

public class TranslateActivity extends AppCompatActivity {
    private EditText edtSource;
    private TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        edtSource = findViewById(R.id.edt_source_text);
        tvResult = findViewById(R.id.tv_result);
        findViewById(R.id.tv_translate_progress).setVisibility(View.INVISIBLE);

        findViewById(R.id.btn_translate).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TranslateTask translateTask = new TranslateTask();
                translateTask.execute(edtSource.getText().toString());
            }
        });
    }

    public void authen(){
        try {
            AssetFileDescriptor descriptor = getAssets().openFd("authen_info.json");
            GoogleCredentials credential = GoogleCredentials.fromStream(descriptor.createInputStream())
                    .createScoped(Lists.<String>newArrayList("https://www.googleapis.com/auth/cloud-platform"));

            Translate translation = TranslateOptions.newBuilder().setCredentials(credential).build().getService();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class TranslateTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            Translate translator = TranslateOptions.getDefaultInstance().getService();
            Translation translation = translator.translate(strings[0]
                    , Translate.TranslateOption.sourceLanguage("en")
                    , Translate.TranslateOption.targetLanguage("vn"));
            publishProgress();
            return translation.getTranslatedText();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            findViewById(R.id.tv_translate_progress).setVisibility(View.VISIBLE);

        }

        @Override
        protected void onPostExecute(String s) {
            findViewById(R.id.tv_translate_progress).setVisibility(View.INVISIBLE);
            tvResult.setText(s);
        }
    }
}
