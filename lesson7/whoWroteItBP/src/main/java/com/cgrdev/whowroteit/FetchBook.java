package com.cgrdev.whowroteit;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class FetchBook extends AsyncTask <String, Void, String> {

    // Member variables
    private WeakReference<TextView> mTitleText;
    private WeakReference<TextView> mAuthorText;

    public FetchBook(TextView mTitleText, TextView mAuthorText) {
        this.mTitleText = new WeakReference<>(mTitleText);
        this.mAuthorText = new WeakReference<>(mAuthorText);
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getBookInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            // Obtain the JSON array of items from the result string
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            // Initialize variables used for the parsing loop
            int i = 0;
            String title = null;
            String authors = null;

            // Iterate through itemsArray array
            while (i < itemsArray.length() &&
                    (authors == null && title == null)) {
                // Get current item information
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                // Try to get the author and title from current item,
                // catch if either field is empty and move on
                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (Exception e) {
                    // If onPostExecute does not receive a proper JSON string,
                    // update UI to show failed results
                    mTitleText.get().setText(R.string.no_results);
                    mAuthorText.get().setText("");
                    e.printStackTrace();
                }

                // Move to the next item
                i++;
            }

            // If a matching response is found, update UI with that response
            if (title != null && authors != null) {
                mTitleText.get().setText(title);
                mAuthorText.get().setText(authors);
            } else {
                // Else, set title with "no results" text and clear author
                mTitleText.get().setText(R.string.no_results);
                mAuthorText.get().setText("");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
