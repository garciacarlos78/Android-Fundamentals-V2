package com.cgrdev.whowroteit;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    // Base URL for Book API
    private static final String BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
    // Parameter for the search string
    private static final String QUERY_PARAM = "q";
    // Parameter that limits search results
    private static final String MAX_RESULTS="maxResults";
    // Parameter to filter by print type
    private static final String PRINT_TYPE = "printType";

    static String getBookInfo(String queryString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;

        try {
            // Build URL and issue the query
            Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build();

            // Convert URI to URL object
            // Remember: URL is a type of URI
            // Uniform Resource (Locator / Identifier)
            URL requestURL = new URL(builtURI.toString());

            // Open URL connection and make request
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Set up the response

            // Get the InputStream
            InputStream inputStream = urlConnection.getInputStream();

            // Create a buffered reader from that input stream
            reader = new BufferedReader(new InputStreamReader(inputStream));

            // Use a StringBuilder to hold the incoming response
            StringBuilder builder = new StringBuilder();

            // Read input line-by-line into the string while there is still input
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing) but it does make debugging a *lot* easier if you print out the completed buffer for debugging
                builder.append("\n");
            }

            // Check the string to see if there is existing response content
            if (builder.length() == 0) {
                // Stream empty, no point in parsing
                return null;
            }

            // Convert the StringBuilder object to a String
            bookJSONString = builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close network connection and BufferedReader after finish receiving JSON
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

        Log.d(LOG_TAG, bookJSONString);
        return bookJSONString;
    }
}
