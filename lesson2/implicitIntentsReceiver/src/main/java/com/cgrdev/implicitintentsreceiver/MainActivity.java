package com.cgrdev.implicitintentsreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Get the incoming Intent used to activate the Activity
    Intent intent = getIntent();

    // Get Intent data
    Uri uri = intent.getData();

    // Create string if not null
    if (uri != null) {
      String uri_string = getString(R.string.uri_label) + uri.toString();
      TextView textView = findViewById(R.id.text_uri_message);
      textView.setText(uri_string);
    }
  }
}