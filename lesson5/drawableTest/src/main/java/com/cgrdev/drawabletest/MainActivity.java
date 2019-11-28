package com.cgrdev.drawabletest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // Class member to save the current battery level
    private int mLevel = 3;

    private ImageView mBattery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize level list drawable to mLevel
        mBattery = findViewById(R.id.battery_img);
        mBattery.setImageLevel(mLevel);

    }

    public void minusClick(View view) {
        // Only do things if mLevel > 0
        if (mLevel>0) mBattery.setImageLevel(--mLevel);
    }

    public void plusClick(View view) {
        // Only do things if mLevel < 6
        if (mLevel < 6) mBattery.setImageLevel(++mLevel);
    }
}
