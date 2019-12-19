package com.cgrdev.headsetreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private HeadsetReceiver mReceiver = new HeadsetReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the intent filter with filter action
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_HEADSET_PLUG);

        // Register the receiver
        this.registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onDestroy() {

        // Unregister the receiver before destroying the activity
        this.unregisterReceiver(mReceiver);

        super.onDestroy();
    }
}
