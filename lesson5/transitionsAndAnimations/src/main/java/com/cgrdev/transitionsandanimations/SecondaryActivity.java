package com.cgrdev.transitionsandanimations;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import java.util.ArrayList;
import java.util.Collections;

public class SecondaryActivity extends AppCompatActivity {

    private static final String LOG_TAG = "Transitions Secondary: ";

    // Name for the intent extra
    private static final String TRANSITION_TYPE = "transition";

    // Constant for shared element transitions
    private static final String GRID_TRANSITION = "switchGrid";
    private static final String IMAGE_TRANSITION = "switchImage";

    // Name for the intent extra defining the Android icon position
    private static final String CURRENT_POSITIONS = "positions";

    // Value for the intent extra
    private static final int EXPLOSION = 0;
    private static final int FADE = 1;
    private static final int MOVE_ANDROID = 2;

    // Order of the icons in the grid
    private ArrayList<String> icon_order;

    // The grid layout in which we have to re arrange the icons
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        gridLayout = findViewById(R.id.gridlayout);

        if (getIntent().hasExtra(TRANSITION_TYPE)) {
            // Get the intent extra value
            int transition_type = getIntent().getIntExtra(TRANSITION_TYPE, -1);
            switch (transition_type) {
                case EXPLOSION:
                    getWindow().setEnterTransition(new Explode());
                    break;
                case FADE:
                    getWindow().setEnterTransition(new Fade());
                    break;
                default:
                    Log.d(LOG_TAG, "Unhandled transition type: " +
                            transition_type);
            }
        }

        // Set the elements in the current order
        icon_order = getIntent().getStringArrayListExtra(CURRENT_POSITIONS);
        changeUIorder();
    }

    private void changeUIorder() {
        // Get UI elements
        ImageButton ring = findViewById(R.id.ring_secondary);
        ImageButton android = findViewById(R.id.android_secondary);
        ImageButton line = findViewById(R.id.line_secondary);
        ImageButton rectangle = findViewById(R.id.rectangle_secondary);

        // Remove all children from GridLayout to add it again in the new position
        gridLayout.removeAllViews();

        // Modify layout_column and layout_row of each element according to
        // its new position
        for (int i = 0; i < icon_order.size(); i++) {
            switch (icon_order.get(i)) {
                case "square":
                    gridLayout.addView(rectangle, i);
                    break;
                case "android":
                    gridLayout.addView(android, i);
                    break;
                case "line":
                    gridLayout.addView(line, i);
                    break;
                case "ring":
                    gridLayout.addView(ring, i);
                    break;
            }
        }
    }

    public void ringOnClick(View view) {
        // Relaunch activity with transition information
        Intent intent = new Intent(this, this.getClass());
        intent.putExtra(TRANSITION_TYPE, FADE);
        intent.putStringArrayListExtra(CURRENT_POSITIONS, icon_order);
        getWindow().setExitTransition(new Fade());
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) this).toBundle());
    }

    public void lineOnClick(View view) {
        // Create the object animator with desired options
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.ROTATION, 0f, 360f);
        animator.setDuration(200);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(5);
        animator.start();
    }

    public void androidOnClick(View view) {

        // Randonmly change the order of the icons
        Collections.shuffle(icon_order);

        // Create an intent to and launch SecondaryActivity
        Intent intent = new Intent(this, MainActivity.class);

        // Put the new icon_order in the Intent
        intent.putStringArrayListExtra(CURRENT_POSITIONS, icon_order);

        // Set the transition details and start the second activity
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, Pair.create(findViewById(R.id.gridlayout), GRID_TRANSITION), Pair.create(findViewById(R.id.imageView), IMAGE_TRANSITION));

        startActivity(intent, options.toBundle());

/*
        // Create an intent to and launch SecondaryActivity
        Intent intent = new Intent(this, MainActivity.class);
        // Put the kind of transition in the intent
        intent.putExtra(TRANSITION_TYPE, MOVE_ANDROID);
        intent.putStringArrayListExtra(CURRENT_POSITIONS, icon_order);
        startActivity(intent);
*/
    }

    public void rectangleOnClick(View view) {
        // Relaunch activity with transition information
        Intent intent = new Intent(this, this.getClass());
        intent.putExtra(TRANSITION_TYPE, EXPLOSION);
        intent.putStringArrayListExtra(CURRENT_POSITIONS, icon_order);
        getWindow().setExitTransition(new Explode());
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) this).toBundle());
    }
}
