package com.cgrdev.headsetreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class HeadsetReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // Get Intent action from intent parameter
        String intentAction = intent.getAction();

        if (intentAction!=null) {
            String toastMessage = "Unknown intent action";
            switch (intentAction){
                case Intent.ACTION_HEADSET_PLUG:
                    // Check if it's been plugged (state==1) or unplugged (state==0)
                    if (intent.getIntExtra("state", 0)==0)
                        toastMessage="Headset unplugged!";
                    else toastMessage="Headset plugged!";
            }
            Toast.makeText(context,toastMessage, Toast.LENGTH_SHORT).show();
        }
    }
}
