package com.cgrdev.threebuttons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ScrollingText extends AppCompatActivity {

  TextView mScrollingText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_scrolling_text);

    mScrollingText = (TextView) findViewById(R.id.text_scrolling);

    String message = getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE);

    mScrollingText.setText(message);

  }
}
