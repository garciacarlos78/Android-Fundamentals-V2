package com.cgrdev.fundamentals.lesson8.notificationscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.widget.Toast;

public class AsyncTaskJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {

        // Call the AsyncTask to sleep
        new SleepAsyncTask().execute(params);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        // We want the job to be rescheduled in case of failure
        return true;
    }

    class SleepAsyncTask extends AsyncTask<JobParameters, Void, Void>{

        @Override
        protected Void doInBackground(JobParameters... params) {

            // Sleep for 5 seconds
            try {
                Thread.sleep(5000);
                jobFinished(params[0], false);
            } catch (InterruptedException e) {
                e.printStackTrace();
                jobFinished(params[0], true);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(AsyncTaskJobService.this, "Snap finished", Toast.LENGTH_SHORT).show();
        }
    }
}

