package com.cgrdev.hellocompat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mHelloTextView;

    private String[] mColorArray = {"red", "pink", "purple", "deep_purple",
            "indigo", "blue", "light_blue", "cyan", "teal", "green",
            "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
            "brown", "grey", "blue_grey", "black" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelloTextView = findViewById(R.id.hello_textview);

        // restore saved instance state (text color)
        if (savedInstanceState != null) {
            mHelloTextView.setTextColor(savedInstanceState.getInt("color"));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // save current text color
        outState.putInt("color", mHelloTextView.getCurrentTextColor());
    }

    public void changeColor(View view) {
        Random random = new Random();

        String colorName = mColorArray[random.nextInt(20)];

        int colorResourceName = getResources().getIdentifier(colorName, "color", getApplicationContext().getPackageName());

        // This line of code is only available as for API 23. As we have chosen minimun SDK 15, we must change this line for the next one
//         int colorRes = getResources().getColor(colorResourceName, this.getTheme());

        // Line of code for API independent behaviour
        // int colorRes = ContextCompat.getColor(this, colorResourceName);

        // ************** CODING CHALLENGE **********

        // -------- Initial version --------------
/*
        int colorRes = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            colorRes = getResources().getColor(colorResourceName, this.getTheme());
        } else {
            // TODO Here the code for Android older than API 23
            if (random.nextBoolean()) {
                colorRes = Color.BLUE;
            } else {
                colorRes = Color.CYAN;
            }
        }
        // --------- End Initial version
*/
        // --------- Version after debugging homework
        int colorRes;
        if (Build.VERSION.SDK_INT >= 23) {
            colorRes = getColor(colorResourceName);
        } else {
            colorRes = getResources().getColor(colorResourceName);
        }
        // --------- End Version after debugging homework

// ************** END CODING CHALLENGE **********

        mHelloTextView.setTextColor(colorRes);
    }
}
