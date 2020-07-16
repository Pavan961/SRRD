package com.example.srrd.Prayers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.srrd.R;

public class PrayersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayers);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Prayers");
    }
}