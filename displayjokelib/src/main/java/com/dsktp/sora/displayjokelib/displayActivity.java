package com.dsktp.sora.displayjokelib;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * This file created by Georgios Kostogloudis
 * and was last modified on 11/6/2018.
 * The name of the project is TellaJoke and it was created as part of
 * UDACITY ND programm.
 */
public class displayActivity extends AppCompatActivity {

    public static final String JOKE_BUNDLE_KEY = "joke";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity_layout);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String joke = bundle.getString(JOKE_BUNDLE_KEY);

        TextView textView = findViewById(R.id.tv_joke);

        textView.setText(joke);


    }
}
