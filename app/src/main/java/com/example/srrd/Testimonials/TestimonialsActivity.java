package com.example.srrd.Testimonials;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.srrd.R;

public class TestimonialsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testimonials);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Testimonials");
    }
}