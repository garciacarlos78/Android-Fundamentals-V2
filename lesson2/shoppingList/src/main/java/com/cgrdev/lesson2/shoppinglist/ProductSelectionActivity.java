package com.cgrdev.lesson2.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ProductSelectionActivity extends AppCompatActivity {

  private static final String LOG_TAG = ProductSelectionActivity.class.getSimpleName();

  public static final String PRODUCT_SELECTION_REPLY = "com.cgrdev.lesson2.shoppinglist.extra.REPLY";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_selection);
  }

  public void sendProduct(View view) {

    // Get the string of the button clicked
    String product = ((Button) view).getText().toString();

    Log.d( LOG_TAG, product);
    //return;

    // Create the intent and add the product string
    Intent replyIntent = new Intent();
    replyIntent.putExtra(PRODUCT_SELECTION_REPLY, product);
    setResult(RESULT_OK, replyIntent);
    finish();
  }
}
