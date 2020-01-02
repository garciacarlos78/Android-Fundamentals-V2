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
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private NotificationManager mNotificationManager;

    // Member constants for notification ID and notification channel ID
    private static final int NOTIFICATION_ID = 0;
    public static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ToggleButton
        ToggleButton alarmToggle = findViewById(R.id.alarmToggle);

        // Set up broadcast pending intent for alarm manager
        Intent notifyIntent = new Intent(this, AlarmReceiver.class);
        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        // ------------------ Set the repeating alarm

        // Initialize AlarmManager
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // ------------------ Set the repeating alarm end

        // --------------- Check the state of the alarm when app launched
        boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_NO_CREATE) != null);
        alarmToggle.setChecked(alarmUp);
        // --------------- Check the state of the alarm when app launched end


        // Add listener
        alarmToggle.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        // Message to show to the user
                        String toastMessage;
                        if (isChecked) {

                            // Constant defining 1 minute
                            // long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES/15;
                            // Constant defining 15 minutes
                            long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
                            // The time when the alarm will be triggered (current + 15')
                            long triggerTime = SystemClock.elapsedRealtime() + repeatInterval;

                            // If the toggle is turned on, set the repeating alarm with a 15' interval
                            if (alarmManager != null) {
                                alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, repeatInterval, notifyPendingIntent);
                            }
                            // Set toast message for "on" case
                            toastMessage = getString(R.string.toast_alarm_on);
                        } else {
                            // Cancel the alarm
                            if (alarmManager != null) {
                                alarmManager.cancel(notifyPendingIntent);
                            }
                            // Cancel notification
                            mNotificationManager.cancelAll();

                            // Set toast message for "off" case
                            toastMessage = getString(R.string.toast_alarm_off);
                        }

                        // Show a toast saying if the alarm is turned on or off
                        Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();

                    }
                }
        );

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

    // ------------------- Set the notification content Intent

    private void deliverNotification(Context context) {

        // Intent that will be used for the notification content intent
        Intent contentIntent = new Intent(context, MainActivity.class);

        // Create a PendingIntent from the content intent
        PendingIntent contentPendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Build a notification using notification icon and content intent
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stand_up)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_content_text))
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        // Deliver the notification
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    // ------------------- Set the notification content Intent end
}
