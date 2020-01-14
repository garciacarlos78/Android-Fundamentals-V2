package com.example.android.sharedPrefsCodingChallenge;

import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SettingsActivity extends AppCompatActivity {

    // Member variables to hold the name of the shared preferences file and a reference to a SharedPreferences object
    private static final String sharedPrefFile = "com.example.android.sharedPrefsCodingChallenge";
    private SharedPreferences mPreferences;

    // Member variable for the spinner
    private Spinner spinner;

    // We need the current count in several methods, we make it member variable
    private int currentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Get the extras from the intent
        currentCount = getIntent().getIntExtra("count", 0);
        int currentColor = getIntent().getIntExtra("color", 0);

        // Initialize the spinner
        spinner = findViewById(R.id.spinner);


        // Put the current color in the TextView and in the spinner
        putCurrentColor(currentColor);

        // Put the current count in the TextView
        TextView tvCurrentCount = findViewById(R.id.currentCount);
        tvCurrentCount.setText(getString(R.string.current_count) + currentCount);

        // Put saved SharedPreferences in corresponding TextViews
        updateUI();

    }

    // Update saved preferences in the UI
    private void updateUI() {

        // Get the TextViews to fill in the saved preferences
        TextView savedCount = findViewById(R.id.tvSavedCount);
        TextView savedColor = findViewById(R.id.tvSavedColor);

        // Get the saved SharedPreferences
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        // Get the SharedPreferences data
        int count = mPreferences.getInt(MainActivity.COUNT_KEY, -1);
        int color = mPreferences.getInt(MainActivity.COLOR_KEY, -1);

        // String to set in the savedCount TextView
        String countString = String.valueOf(count);
        if (count == -1) countString = "Not Set";
        savedCount.setText(getString(R.string.saved_count) + countString);

        // String to set in the savedColor TextView
        String colorString = "Not Set";
        if (color != -1) {
            if (color == getResources().getColor(R.color.default_background)) colorString = "Gray (default)";
            else if (color == getResources().getColor(R.color.green_background)) colorString = "Green";
            else if (color == getResources().getColor(R.color.blue_background)) colorString = "Blue";
            else if (color == getResources().getColor(R.color.red_background)) colorString = "Red";
            else if (color == getResources().getColor(android.R.color.black)) colorString = "Black";
            else colorString = "Unexpected color code";
        }

        // Set the text to the TextView
        savedColor.setText(getString(R.string.saved_color) + colorString);
    }

    private void putCurrentColor(int currentColor) {

        // Get the TextView where the color will be indicated
        TextView tvCurrentColor = findViewById(R.id.currentColor);

        // Get the spinner
        Spinner spinner = findViewById(R.id.spinner);

        // Adapt the string to the current color received and put the corresponding item in the spinner
        String colorString = "Unexpected color";
        if (currentColor == getResources().getColor(R.color.default_background)) {
            colorString = "Gray (default)";
            spinner.setSelection(0);
        }
        else if (currentColor == getResources().getColor(R.color.green_background)) {
            colorString = "Green";
            spinner.setSelection(4);
        }
        else if (currentColor == getResources().getColor(R.color.blue_background)) {
            colorString = "Blue";
            spinner.setSelection(3);
        }
        else if (currentColor == getResources().getColor(R.color.red_background)) {
            colorString = "Red";
            spinner.setSelection(2);
        }
        else if (currentColor == getResources().getColor(android.R.color.black)) {
            colorString = "Black";
            spinner.setSelection(1);
        }

        // Set the text to the TextView
        tvCurrentColor.setText(getString(R.string.current_color) + colorString);
    }

    public void saveSettings(View view) {

        // Check if there is any toggle button switched on
        ToggleButton countButton = findViewById(R.id.countToggle);
        ToggleButton colorButton = findViewById(R.id.colorToggle);

        boolean saveCount = countButton.isChecked();
        boolean saveColor = colorButton.isChecked();

        // At least one button must be checked to save preferences
        if (!saveColor && !saveCount)
            Toast.makeText(this, "Select at least one preference to save", Toast.LENGTH_SHORT).show();

        else {
            // Get a SharedPreferences editor
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            if (saveCount) {
                // Put the current count into the SharedPreferences and update the UI
                preferencesEditor.putInt(MainActivity.COUNT_KEY, currentCount);
            }

            if (saveColor) {
                // Put the selected color into the SharedPreferences
                preferencesEditor.putInt(MainActivity.COLOR_KEY, getColorFromSpinner());
            }
            // Save preferences
            preferencesEditor.apply();
            // Update UI
            updateUI();
        }


    }

    private int getColorFromSpinner() {

        // Resource id of the color
        int resId;

        switch (spinner.getSelectedItemPosition()) {
            case 0:
                resId = R.color.default_background;
                break;
            case 1:
                resId = android.R.color.black;
                break;
            case 2:
                resId = R.color.red_background;
                break;
            case 3:
                resId = R.color.blue_background;
                break;
            default:
                resId = R.color.green_background;
                break;
        }

        return getResources().getColor(resId);
    }

    public void resetPreferences(View view) {

        // Get SharedPreferences editor
        SharedPreferences.Editor editor = mPreferences.edit();
        // Delete and apply
        editor.clear();
        editor.apply();
        // Update UI
        updateUI();
    }
}
































