package com.cgrdev.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    // Intent action
    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {

        // Get Intent action from intent parameter
        String intentAction = intent.getAction();

        // Check if the action is not null
        if (intentAction != null) {
            // Assign the toast message depending on the intent action
            String toastMessage = "Unknown intent action";

            switch (intentAction) {
                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = context.getString(R.string.power_connected);
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = context.getString(R.string.power_disconnected);
                    break;
                case ACTION_CUSTOM_BROADCAST:
                    toastMessage = context.getString(R.string.custom_broadcast_text);
                    break;
            }

            // Display the toast
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();

        }
    }
}
