package com.cgrdev.fundamentals.lesson8.wishapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class ElevenElevenReceiver extends BroadcastReceiver {

    private NotificationManager mNotificationManager;

    // Member constants for the notification ID and the notification channel ID
    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";


    @Override
    public void onReceive(Context context, Intent intent) {

        // Initialize the NotificationManager
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Deliver the notification
        deliverNotification(context);

    }

    // Method to deliver the notification
    private void deliverNotification (Context context) {

        // Intent to be used for the notification content intent
        Intent contentIntent = new Intent(context, MainActivity.class);

        PendingIntent contentPendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Build a notification using the notification icon and content intent
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_wish)
                .setContentTitle(context.getString(R.string.content_title))
                .setContentText(context.getString(R.string.content_text))
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        // Deliver the notification
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }

}
