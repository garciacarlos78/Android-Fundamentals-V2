package com.cgrdev.fundamentals.lesson8.standup;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    // Copied from MainActivity
    private NotificationManager mNotificationManager;

    // Member constants for notification ID and notification channel ID
    private static final int NOTIFICATION_ID = 0;
    public static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";


    @Override
    public void onReceive(Context context, Intent intent) {

        // Initialize mNotificationManager variable
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        deliverNotification(context);
    }

    // Copied from MainActivity
    private void deliverNotification(Context context) {

        // Intent that will be used for the notification content intent
        Intent contentIntent = new Intent(context, MainActivity.class);

        // Create a PendingIntent from the content intent
        PendingIntent contentPendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Build a notification using notification icon and content intent
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stand_up)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(context.getString(R.string.notification_content_text))
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        // Deliver the notification
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
