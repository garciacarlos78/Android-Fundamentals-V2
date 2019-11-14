package com.cgrdev.counterhomework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  private int countValue = 0;
  private TextView text_counter;

  // Key constant for countValue extra
  private static final String COUNT_KEY = "counter_value";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    text_counter = (TextView) findViewById(R.id.text_counter);

    // Check if we have savedInstanceState
    if (savedInstanceState != null) {
      countValue = savedInstanceState.getInt(COUNT_KEY);
    }

    // Set countValue to TextView
    text_counter.setText(Integer.toString(countValue));
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);

    if (countValue > 0) {
      outState.putInt(COUNT_KEY, countValue);
    }
  }

  public void sumCount(View view) {
    countValue++;
    text_counter.setText(Integer.toString(countValue));
  }
}
