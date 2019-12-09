package com.cgrdev.whowroteit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Member variables
    private EditText mBookInput;
    private TextView mTitleText;
    private TextView mAuthorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize member variables
        mBookInput = findViewById(R.id.bookInput);
        mTitleText = findViewById(R.id.titleText);
        mAuthorText = findViewById(R.id.authorText);
    }

    public void searchBooks(View view) {
        // Get the search strng from the input field
        String queryString = mBookInput.getText().toString();

        // Hide the keyboard when the user taps the button
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

        // Check network connection
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        // Ensure network connection exists, network is connected and a string query is available
        if (networkInfo != null && networkInfo.isConnected() &&
            queryString.length() != 0) {
            new FetchBook(mTitleText, mAuthorText).execute(queryString);

            // Clear author TextView and set loading message
            mAuthorText.setText("");
            mTitleText.setText(R.string.loading);
        } else {
            mAuthorText.setText("");
            // There is no search term
            if (queryString.length() == 0) {
                mTitleText.setText(R.string.no_search_term);
            } else {
                // There is no network connection
                mTitleText.setText(R.string.no_network);
            }
        }
    }
}
