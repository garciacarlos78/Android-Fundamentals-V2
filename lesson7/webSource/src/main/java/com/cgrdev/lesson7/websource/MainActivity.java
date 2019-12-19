package com.cgrdev.lesson7.websource;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, LoaderManager.LoaderCallbacks<String> {

    // Log tag
    private static final String LOG_TAG = "webSource: ";
    
    // Member variables
    Spinner mSpinner;
    EditText mSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if there a loader already exists, to avoid losing information when device rotated
        if(getSupportLoaderManager().getLoader(0)!=null)
            getSupportLoaderManager().initLoader(0, null, this);

        // Get the EditText from the UI
        mSource = findViewById(R.id.source);

        // Spinner creation
        mSpinner = findViewById(R.id.spinner);
        if (mSpinner != null) {
            mSpinner.setOnItemSelectedListener(this);
        }
        // Create ArrayAdapter using the string array and default mSpinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);

        // Specify layout to use when list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the mSpinner
        if (mSpinner != null) {
            mSpinner.setAdapter(adapter);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void getSource(View view) {

        // Get the result TextView in the UI
        TextView resultText = findViewById(R.id.result_text);

        String connection_type = mSpinner.getSelectedItem().toString();
        if (mSource.length() > 0) {
            String url = mSource.getText().toString();

            // Hide the keyboard
            InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

            if (inputManager!=null)
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            // Check network connectivity
            ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = null;
            if (connMgr!=null)
                networkInfo = connMgr.getActiveNetworkInfo();



            // Make query only if there is a connection
            if (networkInfo!=null && networkInfo.isConnected()) {
                // Change the result text view to "loading"
                resultText.setText(R.string.loading);

                // Make the query via TaskLoader
                Bundle queryBundle = new Bundle();
                queryBundle.putString("queryString", connection_type+url);
                getSupportLoaderManager().restartLoader(0, queryBundle, this);

            } else {
                // Inform the user that there is no network connection
                resultText.setText(R.string.no_network);

            }

            Log.d(LOG_TAG, connection_type + url);
        } else {
            // Inform the user must introduce a URL
            resultText.setText(R.string.empty_url);
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {

        String queryString="";

        if (args!=null)
            queryString = args.getString("queryString");

        return new WebLoader(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

        Log.d(LOG_TAG, "onLoadFinished");
        if (data!= null)
            Log.d(LOG_TAG, data);
        else Log.d(LOG_TAG, "Empty data");

        // Put the result in the TextView
        TextView resultText = findViewById(R.id.result_text);
        resultText.setText(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
