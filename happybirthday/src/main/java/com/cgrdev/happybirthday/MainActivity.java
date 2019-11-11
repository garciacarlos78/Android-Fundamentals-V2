package com.cgrdev.happybirthday;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

  private static final String LOG_TAG = MainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Generating an exception
    String emptyString = null;

    try {
      emptyString.charAt(2);
    } catch (Exception e){
      Log.e(LOG_TAG,"Error message: " + e.getMessage());
    }
  }
}
