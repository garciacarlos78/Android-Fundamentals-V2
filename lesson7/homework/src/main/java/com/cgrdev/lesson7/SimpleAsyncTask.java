package com.cgrdev.lesson7;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask <Void, Void, String> {

    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;

    @Override
    protected void onPreExecute() {
        mProgressBar.get().setProgress(0);
    }

    SimpleAsyncTask(TextView tv, ProgressBar progressBar) {
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(progressBar);
    }

    @Override
    protected String doInBackground(Void... voids) {

        // Generate a random integer between 0 and 10
        int n = new Random().nextInt(11);

        // Multiply by 200, the resulting number will be between 0 and 2" (2000 ms)
        int s = n * 200;

        // Put the thread to sleep
        try {
            int increment = s / 20;
            int current = increment;
            // We do the sleep in parts of 100ms, updating the UI each 100ms
            while (current < s) {
                Thread.sleep(increment);
                publishProgress();
                current+=increment;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    @Override
    protected void onProgressUpdate(Void... voids) {
        // mTextView.get().setText("Sleeping for " + values[0] + " milliseconds...");
        mProgressBar.get().incrementProgressBy(5);
    }

    @Override
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
        mProgressBar.get().setProgress(100);
    }
}
