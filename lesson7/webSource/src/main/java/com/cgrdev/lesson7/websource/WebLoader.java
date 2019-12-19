package com.cgrdev.lesson7.websource;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class WebLoader extends AsyncTaskLoader <String> {

    private String mWebQuery;

    public WebLoader(@NonNull Context context, String webQuery) {
        super(context);
        mWebQuery = webQuery;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getWebSource(mWebQuery);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        // Start loadInBackground() method
        forceLoad();
    }
}
