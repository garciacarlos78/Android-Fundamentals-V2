package com.cgrdev.fundamentals.lesson8.standup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private NotificationManager mNotificationManager;
    private Intent notifyIntent;
    private AlarmManager alarmManager;

    // Member constants for notification ID and notification channel ID
    private static final int NOTIFICATION_ID = 0;
    public static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    // Toggle buttons
    private ToggleButton toggle5min, toggle15min, toggle25min, alarmToggle;
    private PendingIntent notifyPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the Toggle buttons
        alarmToggle = findViewById(R.id.alarmToggle);
        toggle5min = findViewById(R.id.toggle5min);
        toggle15min = findViewById(R.id.toggle15min);
        toggle25min = findViewById(R.id.toggle25min);

        // Set up broadcast pending intent for alarm manager
        notifyIntent = new Intent(this, AlarmReceiver.class);
        notifyPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        // ------------------ Set the repeating alarm

        // Initialize AlarmManager
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // ------------------ Set the repeating alarm end

        // --------------- Check the state of the alarm when app launched
        boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_NO_CREATE) != null);

        alarmToggle.setChecked(alarmUp);
        // --------------- Check the state of the alarm when app launched end

        // Add listeners to toggle buttons
        alarmToggle.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        setAlarm(buttonView, isChecked);
                    }
                }
        );

        toggle5min.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setAlarm(buttonView, isChecked);
            }
        });

        toggle15min.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setAlarm(buttonView, isChecked);
            }
        });

        toggle25min.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setAlarm(buttonView, isChecked);
            }
        });

        // Initialize NotificationManager
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Call createNotificationChannel() to make it available in OREO and higher
        createNotificationChannel();
    }

    /**
     * Creates a Notification channel, for OREO (API 27) and higher
     */
    public void createNotificationChannel() {

        // Create a notification manager object
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Add check on SDK version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Create NotificationChannel with all the parameters
            NotificationChannel notificationChannel = new NotificationChannel(
                    PRIMARY_CHANNEL_ID,
                    "Stand up notification",
                    NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription(
                    "Notifies every 15 minutes to stand up and walk");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

    // Action to perform when clicked the Show Next button
    public void showNext(View view) {

        // Check the state of the alarm
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_NO_CREATE);

        // There is an alarm
        if (pendingIntent != null) {
            // Check if we are running API 21 or higher
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // Get AlarmClockInfo object with information regarding next alarm
                AlarmManager.AlarmClockInfo alarmClockInfo = alarmManager.getNextAlarmClock();

                // Check if there is info (that is, there is next alarm programmed
                if (alarmClockInfo != null) {
                    // Get the time at which the alarm is going to trigger
                    long nextAlarm = alarmManager.getNextAlarmClock().getTriggerTime();
                    // DateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
                    DateFormat df = new SimpleDateFormat("HH:mm");
                    Toast.makeText(this, "Next scheduled alarm: " + df.format(new Date(nextAlarm)), Toast.LENGTH_SHORT).show();
                } else {
                    // Show a toast indicating there is no active alarm
                    Toast.makeText(this, "No active alarm\n(null alarmClockInfo)", Toast.LENGTH_SHORT).show();
                }

            }
        } else {
            // Show a toast indicating there is no active alarm
            Toast.makeText(this, "No active alarm\n(null pendingIntent)", Toast.LENGTH_SHORT).show();
        }
    }

    private void setAlarm (CompoundButton button, boolean isChecked){

        String message;

        switch (button.getId()) {
            case R.id.alarmToggle:
                message = "Alarm cancelled";
                if (isChecked) {
                    toggle5min.setChecked(false);
                    toggle15min.setChecked(false);
                    toggle25min.setChecked(false);
                    activateAlarm(0);
                }
                break;
            case R.id.toggle5min:
                message = "5 minute alarm pressed";
                if (isChecked) {
                    alarmToggle.setChecked(false);
                    toggle15min.setChecked(false);
                    toggle25min.setChecked(false);
                    activateAlarm(5);
                }
                break;
            case R.id.toggle15min:
                message = "15 minute alarm pressed";
                if (isChecked) {
                    toggle5min.setChecked(false);
                    alarmToggle.setChecked(false);
                    toggle25min.setChecked(false);
                    activateAlarm(15);
                }
                break;
            case R.id.toggle25min:
                message = "25 minute alarm pressed";
                if (isChecked) {
                    toggle5min.setChecked(false);
                    toggle15min.setChecked(false);
                    alarmToggle.setChecked(false);
                    activateAlarm(25);
                }
                break;
            default:
                message = "Unknown button pressed";
                Toast.makeText(button.getContext(), message, Toast.LENGTH_SHORT).show();
                break;
        }

        if (isChecked) Toast.makeText(button.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void activateAlarm(int min) {

        // Special case: 0 min = cancel the alarm
        if (min == 0) {
            // Cancel the alarm
            if (alarmManager != null) {
                alarmManager.cancel(notifyPendingIntent);
            }
            // Cancel notification
            mNotificationManager.cancelAll();
        }

        // We are setting the alarm to min minutes
        else {
            if (alarmManager != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(System.currentTimeMillis() + 1000 * 60 * min, null), notifyPendingIntent);
                }
            }
        }
    }
}
