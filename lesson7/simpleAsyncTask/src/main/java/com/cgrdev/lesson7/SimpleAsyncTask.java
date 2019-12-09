package com.cgrdev.lesson7;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask <Void, Integer, String> {

    private WeakReference<TextView> mTextView;

    SimpleAsyncTask(TextView tv) {
        mTextView = new WeakReference<>(tv);
    }

    @Override
    protected String doInBackground(Void... voids) {

        // Generate a random integer between 0 and 10
        int n = new Random().nextInt(11);

        // Multiply by 200, the resulting number will be between 0 and 2" (2000 ms)
        int s = n * 200;

        // Put the thread to sleep
        try {
            int current = 0;
            // We do the sleep in parts of 100ms, updating the UI each 100ms
            while (current < s) {
                Thread.sleep(100);
                current+=100;
                publishProgress(current);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return "Awake at last after sleeping for " + s + " milliseconds!";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mTextView.get().setText("Sleeping for " + values[0] + " milliseconds...");
    }

    @Override
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}
