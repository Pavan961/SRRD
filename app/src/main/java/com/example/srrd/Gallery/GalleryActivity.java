package com.example.srrd.Gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.srrd.R;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Gallery");
    }
}