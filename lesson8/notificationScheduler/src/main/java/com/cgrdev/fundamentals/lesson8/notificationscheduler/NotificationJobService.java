package com.cgrdev.fundamentals.lesson8.notificationscheduler;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationJobService extends JobService {

    NotificationManager mNotifyManager;
    // Notification channel ID
    public static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    @Override
    public boolean onStartJob(JobParameters params) {

        // Create the notification channel
        createNotificationChannel();

        // Set up the notfication content intent to launch the app when clicked
        PendingIntent contentPendingIntent =
                PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        // Construct a notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_content))
                .setContentIntent(contentPendingIntent)
                .setSmallIcon(R.drawable.ic_job_running)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true);

        // Deliver the notification
        mNotifyManager.notify(0, builder.build());

        // All of the work is completed in onStartJob(), that's why it returns false
        return false;

    }

    @Override
    public boolean onStopJob(JobParameters params) {
        // If the job fails, the job is rescheduled (returning false should be dropped)
        return true;
    }

    // Method to create a notification channel
    /**
     * Creates a Notification channel for OREO and higher
     */
    private void createNotificationChannel() {

        // Define notification manager object
        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Check SDK version, notification channels only available in OREO and higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Create NotificationChannel with its parameters
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Job Service notification",
                    NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notifications from Job Service");

            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

}
