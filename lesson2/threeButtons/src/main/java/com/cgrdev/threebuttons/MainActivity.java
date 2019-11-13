package com.cgrdev.threebuttons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  public static final String EXTRA_MESSAGE = "com.cgrdev.threebuttons.extra.MESSAGE";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void clickedButtonOne(View view) {

    Intent intent = new Intent(this, ScrollingText.class);
    intent.putExtra(EXTRA_MESSAGE, getResources().getString(R.string.passage_one));

    startActivity(intent);
  }

  public void clickedButtonTwo(View view) {

    Intent intent = new Intent(this, ScrollingText.class);
    intent.putExtra(EXTRA_MESSAGE, getResources().getString(R.string.passage_two));

    startActivity(intent);
  }

  public void clickedButtonThree(View view) {

    Intent intent = new Intent(this, ScrollingText.class);
    intent.putExtra(EXTRA_MESSAGE, getResources().getString(R.string.passage_three));

    startActivity(intent);
  }

}
