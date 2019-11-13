package com.cgrdev.lifecycleCallbacks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  // Constant for Log messages
  private static final String LOG_TAG = MainActivity.class.getSimpleName();

  // Constant for the Intent extra
  public static final String EXTRA_MESSAGE = "com.cgrdev.lesson2.twoActivities.extra.MESSAGE";

  // Constant for the response
  public static final int TEXT_REQUEST = 1;

  // Private variables to hold reply header and TextView
  private TextView mReplyHeadTextView;
  private TextView mReplyTextView;

  private EditText mMessageEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mReplyHeadTextView = findViewById(R.id.text_header_reply);
    mReplyTextView = findViewById(R.id.text_message_reply);

    mMessageEditText = findViewById(R.id.editText_main);

    Log.d(LOG_TAG, "--------");
    Log.d(LOG_TAG, "onCreate");
  }

  @Override
  public void onStart() {
    super.onStart();
    Log.d(LOG_TAG, "onStart");
  }

  @Override
  protected void onStop() {
    super.onStop();
    Log.d(LOG_TAG, "onStop");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(LOG_TAG, "onDestroy");
  }

  @Override
  protected void onPause() {
    super.onPause();
    Log.d(LOG_TAG, "onPause");
  }


  @Override
  protected void onResume() {
    super.onResume();
    Log.d(LOG_TAG, "onResume");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    Log.d(LOG_TAG, "onRestart");
  }

  public void launchSecondActivity(View view) {
    Log.d(LOG_TAG, "Button clicked!");
    Intent intent = new Intent(this, SecondActivity.class);
    String message = mMessageEditText.getText().toString();
    intent.putExtra(EXTRA_MESSAGE, message);
    startActivityForResult(intent, TEXT_REQUEST);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode,
                               Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == TEXT_REQUEST) {
      if (resultCode == RESULT_OK) {
        String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
        mReplyHeadTextView.setVisibility(View.VISIBLE);
        mReplyTextView.setText(reply);
        mReplyTextView.setVisibility(View.VISIBLE);
      }
    }
  }
}
