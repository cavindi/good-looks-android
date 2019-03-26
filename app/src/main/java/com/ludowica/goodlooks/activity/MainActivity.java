package com.ludowica.goodlooks.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ludowica.goodlooks.R;
import com.ludowica.goodlooks.services.ProductInterface;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
