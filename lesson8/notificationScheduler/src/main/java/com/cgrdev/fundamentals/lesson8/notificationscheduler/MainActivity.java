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

    // Member variable for the AsyncTask JobScheduler
    private JobScheduler mTaskScheduler;

    // Member constant for the JOB_ID
    public static final int JOB_ID = 0;

    // Member constant for the async task JOB_ID
    public static final int TASK_JOB_ID = 1;

    // Member variables for the switches
    private Switch mDeviceIdleSwitch;
    private Switch mDeviceChargingSwitch;

    // Member variable for the deadline SeekBar
    private SeekBar mSeekBar;

    // Member variable for the sleep SeekBar
    private SeekBar mSleepSeekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize switch member variables
        mDeviceIdleSwitch = findViewById(R.id.idleSwitch);
        mDeviceChargingSwitch = findViewById(R.id.chargingSwitch);

        // Initialize SeekBar members variables
        mSeekBar = findViewById(R.id.seekBar);
        mSleepSeekBar = findViewById(R.id.seekBarTime);

        // Final variable for the seek bar's progress TextView
        final TextView seekBarProgress = findViewById(R.id.seekBarProgress);

        // Final variable for the sleep seek bar's progress TextView
        final TextView seekBarSleep = findViewById(R.id.seekBarTimeProgress);

        // Add the listener to the seek bar
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (progress > 0) {
                    seekBarProgress.setText(progress + " s");
                } else {
                    seekBarProgress.setText(R.string.not_set);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Add the listener to the sleep time seek bar
        mSleepSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress > 0) {
                    seekBarSleep.setText(progress + " s");
                } else {
                    seekBarSleep.setText(R.string.not_set);
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
            Toast.makeText(this, R.string.job_scheduled_toast, Toast.LENGTH_SHORT).show();

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

    public void scheduleJobAsyncTask(View view) {

        // Time to sleep
        int sleepTime = mSleepSeekBar.getProgress();

        Toast.makeText(this, "AsyncTask called\nSleeping for " + sleepTime + " seconds", Toast.LENGTH_SHORT).show();

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
        ComponentName serviceName = new ComponentName(getPackageName(), AsyncTaskJobService.class.getName());
        JobInfo.Builder builder = new JobInfo.Builder(TASK_JOB_ID, serviceName)
                .setRequiredNetworkType(selectedNetworkOption)
                // Set constraints based on the switches selections
                .setRequiresDeviceIdle(mDeviceIdleSwitch.isChecked())
                .setRequiresCharging(mDeviceChargingSwitch.isChecked());

        // Initialize JobScheduler
        mTaskScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

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
            mTaskScheduler.schedule(myJobInfo);

            // Let the user know the job has been scheduled
            Toast.makeText(this, R.string.async_task_scheduled, Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, R.string.set_constraint_toast, Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelJobAsyncTask(View view) {

        if (mTaskScheduler != null) {
            mTaskScheduler.cancelAll();
            mTaskScheduler = null;
            Toast.makeText(this, R.string.async_task_cancelled, Toast.LENGTH_SHORT).show();
        }
    }
}
