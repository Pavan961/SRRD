package com.example.srrd.TemplePlan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.srrd.R;
import com.github.chrisbanes.photoview.PhotoView;

public class TemplePlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temple_plan);
        ImageView mIcon = findViewById(R.id.ivIcon);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Temple Plan");
        mIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(TemplePlanActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.photoView);
                photoView.setImageResource(R.drawable.temple_plan);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.setCanceledOnTouchOutside(true);
                mDialog.show();
            }
        });
    }
}