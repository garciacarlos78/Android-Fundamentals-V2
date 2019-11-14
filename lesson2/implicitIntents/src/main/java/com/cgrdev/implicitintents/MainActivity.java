package com.cgrdev.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

  private static final String LOG_TAG = "ImplicitIntents";

  private EditText mWebsiteEditText;
  private EditText mLocationEditText;
  private EditText mShareTextEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mWebsiteEditText = findViewById(R.id.website_edittext);
    mLocationEditText = findViewById(R.id.location_edittext);
    mShareTextEditText = findViewById(R.id.share_edittext);
  }

  public void openWebsite(View view) {

    String url = mWebsiteEditText.getText().toString();
    Uri webpage = Uri.parse(url);

    // Implicit intent
    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

    // Find an Activity that can handle the implicit Intent
    if (intent.resolveActivity(getPackageManager()) != null) {
      startActivity(intent);
    } else {
      Log.d(LOG_TAG, "Can't handle this!");
    }
  }

  public void openLocation(View view) {

    String loc = mLocationEditText.getText().toString();
    Uri addressUri = Uri.parse("geo:0,0?q=" + loc);

    // Implicit Intent to open a location
    Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

    // Check if there is an Activity to handle the Intent
    if (intent.resolveActivity(getPackageManager()) != null) {
      startActivity(intent);
    } else {
      Log.d(LOG_TAG, "Can't handle this intent!");
    }
  }

  public void shareText(View view) {

    // Getting text from EditText
    String txt = mShareTextEditText.getText().toString();
    String mimeType = "text/plain";

    // Calling ShareCompat.IntentBuilder helper class to share
    ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle(getString(R.string.chooser_title))
            .setText(txt)
            .startChooser();
  }
}
