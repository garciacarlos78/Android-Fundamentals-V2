package com.cgrdev.lesson7.websource;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    static String getWebSource(String mWebQuery) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // The source web code will be stored here
        String webSource = null;

        try {
            // Create URL object from parameter string
            URL requestedURL = new URL(mWebQuery);

            // Open URL connection and make request
            urlConnection = (HttpURLConnection) requestedURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Set up the response

            // Get the InputStream
            InputStream inputStream = urlConnection.getInputStream();

            // Create a buffered reader from that input stream
            reader = new BufferedReader(new InputStreamReader(inputStream));

            // Use a StringBuilder to hold the incoming response
            StringBuilder builder = new StringBuilder();

            //Read input line-by-line into the string while there is still input
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            // Check the string to see if there is existing response content
            if (builder.length() == 0) {
                // Stream empty, no point in parsing
                return null;
            }

            // Convert the StringBuilder object to a String
            webSource = builder.toString();

        } catch (MalformedURLException e) {
            // Catch introduced in new URL(mWebQuery)
            e.printStackTrace();
            return "Error:\nCheck the URL, it seems not be a valid URL.";
        } catch (IOException e) {
            // Catch introduced in URL.openConnection()
            e.printStackTrace();
            return "Error:\n" + e.getMessage();
        } finally {
            // Close network connection and BufferedReader after finish receiving data
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // Log.d(LOG_TAG, webSource);
        return webSource;
    }
}
