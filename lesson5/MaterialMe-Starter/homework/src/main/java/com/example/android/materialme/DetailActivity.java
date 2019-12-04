package com.example.android.materialme;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    // View name of the header image, title and subtitle
    public static final String VIEW_NAME_HEADER_IMAGE = "detail:header:image";
    public static final String VIEW_NAME_HEADER_SPORT_NAME = "detail:header:title";
    public static final String VIEW_NAME_HEADER_NEWS_SUBTITLE = "detail:header:subtitle";
    private static final String LOG_TAG = "homework: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize ImageView and title
        TextView sportsTitle = findViewById(R.id.titleDetail);
        ImageView sportsImage = findViewById(R.id.sportsImageDetail);
        TextView subTitle = findViewById(R.id.subTitleDetail);

        // Pair the views for the transition
        ViewCompat.setTransitionName(sportsImage, VIEW_NAME_HEADER_IMAGE);
        ViewCompat.setTransitionName(sportsTitle, VIEW_NAME_HEADER_SPORT_NAME);
        ViewCompat.setTransitionName(findViewById(R.id.newsTitleDetail), VIEW_NAME_HEADER_NEWS_SUBTITLE);

        // Get title from Intent and set it to sportsTitle
        sportsTitle.setText(getIntent().getStringExtra("title"));
        subTitle.setText(getIntent().getStringExtra("sport_description"));

        // Use Glide to load the image into sportsImage
        Glide.with(this).load(getIntent().getIntExtra("image_resource", 0)).into(sportsImage);

    }

}
