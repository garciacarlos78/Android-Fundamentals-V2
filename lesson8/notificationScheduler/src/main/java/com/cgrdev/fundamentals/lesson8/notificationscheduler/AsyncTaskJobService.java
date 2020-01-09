package com.cgrdev.fundamentals.lesson8.notificationscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class AsyncTaskJobService extends JobService {

    // Member variable for the AsyncTask. Necessary to cancel it in case the user wants to
    SleepAsyncTask sleepAsyncTask = new SleepAsyncTask();

    @Override
    public boolean onStartJob(JobParameters params) {

        // Call the AsyncTask to sleep
        sleepAsyncTask.execute(params);
        // Task offloaded to a different thread, we must return true
        return true;
    }

    // TODO: Diferentiate when the task is stopped by the conditions or because the user clicked the cancel button
    @Override
    public boolean onStopJob(JobParameters params) {

        sleepAsyncTask.cancel(true);

        // Inform the user the job failed
        Toast.makeText(this, R.string.job_failed_message, Toast.LENGTH_SHORT).show();

        // We want the job to be rescheduled in case of failure
        return true;
    }

    class SleepAsyncTask extends AsyncTask<JobParameters, Integer, Void>{

        @Override
        protected Void doInBackground(JobParameters... params) {

            // Shows a toast in the moment the conditions are met and therefore this backgroung task is initialized
            publishProgress(0);

            // Get the amount of time the thread must sleep
            int sleepTime = params[0].getExtras().getInt("time")*1000;

            // Sleep for sleepTime seconds
            try {
                int i=0;
                while (sleepTime > 5000) {
                    Thread.sleep(5000);
                    publishProgress(++i);
                    sleepTime-=5000;
                }
                Thread.sleep(sleepTime);
                // Inform the JobScheduler the job has finished and doesn't need to be rescheduled
                jobFinished(params[0], false);
            } catch (InterruptedException e) {
                e.printStackTrace();
                // Inform the JobScheduler the job has finished and needs to be rescheduled
                jobFinished(params[0], true);
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if (values[0] == 0)
                Toast.makeText(getApplicationContext(), "Starting AsyncTask", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Sleeping for " + values[0]*5 + " seconds", Toast.LENGTH_SHORT).show();
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getApplicationContext(), "Snap finished", Toast.LENGTH_SHORT).show();
        }
    }
}

