package com.cgrdev.powerreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // Receiver for the plug/unplug battery charger
    private CustomReceiver mReceiver = new CustomReceiver();

    // Intent action
    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the IntentFilter and add the actions to listen to
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);

        // Register the receiver using the activity context
        this.registerReceiver(mReceiver, filter);

        // Dynamically register the local broadcast receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));

    }

    @Override
    protected void onDestroy() {

        // Unregister the receiver
        this.unregisterReceiver(mReceiver);

        super.onDestroy();
    }

    public void sendCustomBroadcast(View view) {

        // Create new intent
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);

        // Add a random integer (0-1000) to the intent
        customBroadcastIntent.putExtra("randomInt", (int)(Math.random()*1000));

        // Send local broadcast
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);

    }
}
