package com.cgrdev.fundamentals.notifyme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button_notify;
    private Button button_cancel;
    private Button button_update;

    // Notification ID
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    // Member variable to store the NotificationManager object
    private NotificationManager mNotifyManger;

    // Necessary to associate the notification with a notification ID
    public static final int NOTIFICATION_ID = 0;

    // Constant to represent the update notification action for the broadcast
    public static final String ACTION_UPDATE_NOTIFICATION =
            "com.cgrdev.fundamentals.notifyme.ACTION_UPDATE_NOTIFICATION";

    // Constant to represent the cancel action for the broadcast
    public static final String ACTION_DISMISS_NOTIFICATION =
            "com.cgrdev.fundamentals.notifyme.ACTION_DISMISS_NOTIFICATION";

    // Member variable for the receiver
    private NotificationReceiver mReceiver = new NotificationReceiver();

    // PendingIntent used when notification dismiss
    PendingIntent cancelPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the buttons
        button_notify = findViewById(R.id.notify);
        button_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotificaction();
            }
        });

        button_update = findViewById(R.id.update);
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update notification
                updateNotification();
            }
        });

        button_cancel = findViewById(R.id.cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cancel notification
                cancelNotification();
            }
        });

        createNotificationChannel();

        // Update buttons state
        setNotificationButtonState(true, false, false);

        // Register broadcast receiver to receive ACTION_UPDATE_NOTIFICATION intent
        registerReceiver(mReceiver, new IntentFilter(ACTION_UPDATE_NOTIFICATION));

        // Register broadcast receiver to receive ACTION_DISMISS_NOTIFICATION intent
        registerReceiver(mReceiver, new IntentFilter(ACTION_DISMISS_NOTIFICATION));
    }

    public void sendNotificaction() {

        // Adding the update action to the notification
        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
        // Using FLAG_ONE_SHOT to make sure this pending intent is sent and used only once
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);

        // Get the notification builder
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();

        // Pass in icon, label text and PendingIntent to the builder
        notifyBuilder.addAction(R.drawable.ic_update, "Update Notification", updatePendingIntent);

        // Coding challenge: code to do when user dismiss notification
        // Create an intent with the dismiss action
        Intent cancelIntent = new Intent(ACTION_DISMISS_NOTIFICATION);
        //PendingIntent cancelPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, cancelIntent, PendingIntent.FLAG_ONE_SHOT);
        cancelPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Supply the PendingIntent to the notification builder
        notifyBuilder.setDeleteIntent(cancelPendingIntent);

        // Call notify()
        mNotifyManger.notify(NOTIFICATION_ID, notifyBuilder.build());

        // Update buttons state
        setNotificationButtonState(false, true, true);
    }

    public void updateNotification() {

        // Convert drawable into bitmap
        Bitmap face_icon = BitmapFactory.decodeResource(getResources(), R.drawable.face_icon);


        // Get the NotificationCompat.Builder object
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();

        // Change notification title and content
        notifyBuilder.setSmallIcon(R.drawable.new_mail)
                .setContentTitle("10 New mails from sender@domain.cat")
                .setContentText("Subject of the email")
                .setLargeIcon(face_icon)
                .setStyle(new NotificationCompat.InboxStyle()
                    .addLine("First line, a long line which I expect it should be trimmed")
                    .addLine("Second line")
                    .addLine("Third line")
                    .addLine("Fourth line")
                    .addLine("Fifth line")
                    .addLine("Sixth line")
                    .addLine("Seventh and possibly last visible line")
                    .addLine("Eigth line")
                    .setSummaryText("Summary text"))
                .build();

        // Add PendingIntent for the dismiss behaviour
        notifyBuilder.setDeleteIntent(cancelPendingIntent);

        // Build notification and call notify()
        mNotifyManger.notify(NOTIFICATION_ID, notifyBuilder.build());

        // Update buttons state
        setNotificationButtonState(false, false, true);
    }

    public void cancelNotification() {
        mNotifyManger.cancel(NOTIFICATION_ID);

        // Update buttons state
        setNotificationButtonState(true, false, false);
    }

    public void createNotificationChannel() {
        mNotifyManger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels only available in API 26 and higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create a NotificationChannel
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Mascot Notification",
                    NotificationManager.IMPORTANCE_HIGH);

            // Configure the notification channel
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");

            mNotifyManger.createNotificationChannel(notificationChannel);
        }
    }

    private NotificationCompat.Builder getNotificationBuilder(){

        // Create an explicit intent method to launch MainActivity
        Intent notificationIntent = new Intent(this, MainActivity.class);

        // Get a PendingIntent to communicate with another app
        // In this case, is not another, is this app
        PendingIntent notificationPendintIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Create and instantiate notification builder
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)

        // Add title, text and icon to the notification builder
                // Notice next lines continues notifyBuilder construction
        .setContentTitle("You've been notified!")
                .setContentText("This is your notification text.")
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(notificationPendintIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        return notifyBuilder;
    }

    void setNotificationButtonState (boolean isNotifyEnabled,
                                     boolean isUpdateEnabled,
                                     boolean isCancelEnabled) {
        button_notify.setEnabled(isNotifyEnabled);
        button_update.setEnabled(isUpdateEnabled);
        button_cancel.setEnabled(isCancelEnabled);
    }

    // Broadcast receiver to call updateNotification
    public class NotificationReceiver extends BroadcastReceiver {

        // Empty constructor
        public NotificationReceiver() {}

        @Override
        public void onReceive(Context context, Intent intent) {

            // Check the action of the intent
            switch (intent.getAction()) {
                case ACTION_DISMISS_NOTIFICATION:
                    // Update the buttons
                    setNotificationButtonState(true, false, false);
                    break;
                case ACTION_UPDATE_NOTIFICATION:
                    // Update the notification
                    updateNotification();
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {

        // Unregister the receiver before destroying the activity
        unregisterReceiver(mReceiver);

        super.onDestroy();
    }
}
