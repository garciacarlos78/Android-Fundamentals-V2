package com.cgrdev.lesson5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Member variables for holding the score
    private int mScore1;
    private int mScore2;

    // Member variables for holding the TextView elements
    private TextView mScoreText1;
    private TextView mScoreText2;

    // onSaveInstanceState tags
    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the TextViews by ID
        mScoreText1 = findViewById(R.id.score_1);
        mScoreText2 = findViewById(R.id.score_2);

        // Restore scores if there are in the bundle
        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2);
            // Set the score text views
            mScoreText1.setText(String.valueOf(mScore1));
            mScoreText2.setText(String.valueOf(mScore2));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Check if the correct item was clicked
        if (item.getItemId() == R.id.night_mode){
            // Get the night mode state of the app
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            // Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            // Recreate the activity for the theme change to take effect
            recreate();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // Change the label of the menu based on the state of the app
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    /**
     * Method that handles the onClick of both increment buttons
     * @param view The button view that was clicked
     */
    public void increaseScore(View view) {
        // Switch the ID of the button that was clicked
        switch (view.getId()) {
            // Team 1
            case R.id.increaseTeam1:
                // Increment score and update TextView
                mScore1++;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            // Team 2
            case R.id.increaseTeam2:
                // Increment score and update TextView
                mScore2++;
                mScoreText2.setText(String.valueOf(mScore2));
        }
    }

    /**
     * Method that handles the onClick of both the decrement buttons
     * @param view The button view that was clicked
     */
    public void decreaseScore(View view) {
        // Switch the ID of the button that was clicked
        switch (view.getId()){
            // Team 1
            case R.id.decreaseTeam1:
                // Decrement score and update TextView
                mScore1--;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            // Team 2
            case R.id.decreaseTeam2:
                // Decrement score and update TextView
                mScore2--;
                mScoreText2.setText(String.valueOf(mScore2));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        // Save the scores
        outState.putInt(STATE_SCORE_1, mScore1);
        outState.putInt(STATE_SCORE_2, mScore2);

        super.onSaveInstanceState(outState);
    }
}
