package com.example.android.materialme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize ImageView and title
        TextView sportsTitle = findViewById(R.id.titleDetail);
        ImageView sportsImage = findViewById(R.id.sportsImageDetail);

        // Get title from Intent and set it to sportsTitle
        sportsTitle.setText(getIntent().getStringExtra("title"));

        // Use Glide to load the image into sportsImage
        Glide.with(this).load(getIntent().getIntExtra("image_resource", 0)).into(sportsImage);
    }
}
