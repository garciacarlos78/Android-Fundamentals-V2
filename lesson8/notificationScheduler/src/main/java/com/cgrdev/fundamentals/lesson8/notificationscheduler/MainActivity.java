package com.cgrdev.fundamentals.lesson8.notificationscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Member variable for the JobScheduler
    private JobScheduler mScheduler;

    // Member constant for the JOB_ID
    public static final int JOB_ID = 0;

    // Member variables for the switches
    private Switch mDeviceIdleSwitch;
    private Switch mDeviceChargingSwitch;

    // Member variable for the SeekBar
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize switch member variables
        mDeviceIdleSwitch = findViewById(R.id.idleSwitch);
        mDeviceChargingSwitch = findViewById(R.id.chargingSwitch);

        // Initialize SeekBar member variable
        mSeekBar = findViewById(R.id.seekBar);

        // Final variable for the seek bar's progress TextView
        final TextView seekBarProgress = findViewById(R.id.seekBarProgress);

        // Add the listener to the seek bar
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (progress > 0) {
                    seekBarProgress.setText(progress + " s");
                } else {
                    seekBarProgress.setText("Not Set");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void scheduleJob(View view) {

        RadioGroup networkOptions = findViewById(R.id.networkOptions);

        // Get selected network ID
        int selectedNetworkID = networkOptions.getCheckedRadioButtonId();

        // Default network option
        int selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;

        // Check the selected network option
        switch (selectedNetworkID) {
            case R.id.noNetwork:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;
                break;
            case R.id.anyNetwork:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_ANY;
                break;
            case R.id.wifiNetwork:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_UNMETERED;
                break;
        }

        // Create JobInfo.Builder object
        // Here is passed the selected network option as a condition to trigger the job
        ComponentName serviceName = new ComponentName(getPackageName(), NotificationJobService.class.getName());
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName)
                .setRequiredNetworkType(selectedNetworkOption)
                // Set constraints based on the switches selections
                .setRequiresDeviceIdle(mDeviceIdleSwitch.isChecked())
                .setRequiresCharging(mDeviceChargingSwitch.isChecked());

        // Initialize JobScheduler
        mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        // Variables to check the seek bar value
        int seekBarInteger = mSeekBar.getProgress();
        boolean seekBarSet = seekBarInteger > 0;

        // Set deadline value in case it exists (parameter should be passed in ms, that's why multiply by 1000)
        if (seekBarSet) {
            builder.setOverrideDeadline(seekBarInteger * 1000);
        }

        // Boolean to check whether there is a selected constraint
        boolean constraintSet =
                selectedNetworkOption != JobInfo.NETWORK_TYPE_NONE
                        || mDeviceChargingSwitch.isChecked()
                        || mDeviceIdleSwitch.isChecked()
                        || seekBarSet ;

        if (constraintSet) {
            // Create the JobInfo object and pass it to the JobScheduler object
            JobInfo myJobInfo = builder.build();
            mScheduler.schedule(myJobInfo);

            // Let the user know the job has been scheduled
            Toast.makeText(this, R.string.job_scheduld_toast, Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, R.string.set_constraint_toast, Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelJobs(View view) {

        // Check whether JobScheduler is null, and if not, remove all pending jobs and reset JobScheduler to null
        if (mScheduler != null) {
            mScheduler.cancelAll();
            mScheduler = null;
            Toast.makeText(this, R.string.jobs_cancelled_toast, Toast.LENGTH_SHORT).show();
        }
    }
}
