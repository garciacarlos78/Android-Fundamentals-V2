package com.cgrdev.fundamentals.lesson8.wishapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private NotificationManager mNotificationManager;

    // Member constants for the notification ID and the notification channel ID
    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the switch from the UI
        Switch mSwitch = findViewById(R.id.mSwitch);

        // Initialize mNotificationManager
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Create the notification channel (OREO and higher)
        createNotificationChannel();

        // Set up the broadcast pending intent
        Intent notifyIntent = new Intent(this, ElevenElevenReceiver.class);

        // Check if the alarm exists
        boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_NO_CREATE) != null);

        // If exists, set the switch checked. Otherwise unchecked
        mSwitch.setChecked(alarmUp);

        // Create the PendingIntent
        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        // ------------- Set the alarm

        // Initialize AlarmManager
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        // ------------- Set the repeating alarm end


        // Assign a listener
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // Action to do if it's checked
                if (isChecked) checkedSwitch(alarmManager, notifyPendingIntent);

                    // Action to do if it's unchecked
                else uncheckedSwitch(alarmManager, notifyPendingIntent);
            }
        });

    }

    private void uncheckedSwitch(AlarmManager alarmManager, PendingIntent notifyPendingIntent) {
        Toast.makeText(this, "Alarm deactivated", Toast.LENGTH_SHORT).show();
        // Cancel notification
        mNotificationManager.cancelAll();

        // Cancel alarm
        if (alarmManager != null) {
            alarmManager.cancel(notifyPendingIntent);
        }
    }

    private void checkedSwitch(AlarmManager alarmManager, PendingIntent notifyPendingIntent) {
        Toast.makeText(this, "Alarm activated", Toast.LENGTH_SHORT).show();

        // ------------------ Set the repeating alarm

        // Set the alarm to start at 11:11 a.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 11);

        // We have to check current time: if it's past 11:11, we have to
        // add one day, otherwise the notification would be shown inmediately
        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
            calendar.add(Calendar.DATE, 1);
        }

        // Set the repeating alarm
        if (alarmManager != null) {
            // Set exact type of alarm if API >= 19
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), notifyPendingIntent);
            }
            // Set type of alarm if API < 19
            else {
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), notifyPendingIntent);
            }
        }

        // ------------------ Set the repeating alarm end
    }

    /**
     * Creates a Notification channel, for OREO and higher
     */
    public void createNotificationChannel() {

        // Create a notification manager object
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Check version before trying to create a notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, getString(R.string.notification_channel_name), NotificationManager.IMPORTANCE_HIGH);

            // Set the behaviour of the notification channel
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription(
                    getString(R.string.notification_channel_description));

            // Create the notification channel in the NotificationManager
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }



}
