package com.cgrdev.sayhello;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SayHello extends AppCompatActivity {

  private TextView hello_count;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_say_hello);

    int mCount = getIntent().getIntExtra(MainActivity.EXTRA_MESSAGE, 0);

    hello_count = (TextView) findViewById(R.id.text_count);
    hello_count.setText(Integer.toString(mCount));

  }
}
