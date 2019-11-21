package com.cgrdev.pickerfortime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setTime(View view) {
        DialogFragment timeFragment = new TimePickerFragment();
        timeFragment.show(getSupportFragmentManager(), getString(R.string.timepicker));
    }

    public void processTimePickerResult (int hour, int minute) {

        // Create the string with the date
        String hour_string = Integer.toString(hour);
        String minute_string = String.format("%02d", minute);
        String timeMessage = hour_string + ":" + minute_string;

        // Show the toast message
        Toast.makeText(this, getString(R.string.time_message) + timeMessage, Toast.LENGTH_SHORT).show();
    }
}
