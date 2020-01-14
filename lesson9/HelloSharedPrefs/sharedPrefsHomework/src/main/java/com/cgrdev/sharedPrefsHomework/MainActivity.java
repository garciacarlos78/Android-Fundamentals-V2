package com.cgrdev.sharedPrefsHomework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Member varialbes to hold the name of the shared preferences file and a reference to a SharedPreferences object
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.cgrdev.sharedPrefsHomework";

    // Member variables for holding the score
    private int mScore1;
    private int mScore2;

    // Member variables for holding the TextView elements
    private TextView mScoreText1;
    private TextView mScoreText2;

    // SharedPreferences keys
    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the TextViews by ID
        mScoreText1 = findViewById(R.id.score_1);
        mScoreText2 = findViewById(R.id.score_2);

        // Initialize shared preferences
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Restore scores if there are in SharedPreferences
        mScore1 = mPreferences.getInt(STATE_SCORE_1, 0);
        mScore2 = mPreferences.getInt(STATE_SCORE_2, 0);

        // Update UI with the scores in SharedPreferences
        mScoreText1.setText(String.format("%s", mScore1));
        mScoreText2.setText(String.format("%s", mScore2));
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Get an editor for the SharedPreferences object
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();

        // Put current scores into the shared preferences
        preferencesEditor.putInt(STATE_SCORE_1, mScore1);
        preferencesEditor.putInt(STATE_SCORE_2, mScore2);

        // Save preferences
        preferencesEditor.apply();

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

    public void resetScores(View view) {

        // Get an editor for the SharedPreferences
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();

        // Delete shared preferences
        preferencesEditor.clear();

        // Apply changes to the preferences
        preferencesEditor.apply();

        // Call onStart to update UI
        onStart();
    }
}
