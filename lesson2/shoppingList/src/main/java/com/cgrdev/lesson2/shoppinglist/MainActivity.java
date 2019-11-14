package com.cgrdev.lesson2.shoppinglist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  // Log constant
  private static final String LOG_TAG = MainActivity.class.getSimpleName();

  // Constant for the response
  public static final int TEXT_REQUEST = 1;

  // Constants for keys for saving instance state
  private static final String ARRAY_KEY = "array_key";
  private static final String INDEX_KEY = "index_key";

  // Array of TextViews
  private TextView[] mShoppingList;

  // Index of next element to add
  private int nextProductIndex = 0;

  // Button
  private Button mAddProduct;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Initialize TextView array
    mShoppingList = new TextView[]{
            findViewById(R.id.item_0),
            findViewById(R.id.item_1),
            findViewById(R.id.item_2),
            findViewById(R.id.item_3),
            findViewById(R.id.item_4),
            findViewById(R.id.item_5),
            findViewById(R.id.item_6),
            findViewById(R.id.item_7),
            findViewById(R.id.item_8),
            findViewById(R.id.item_9)};

    if (savedInstanceState != null) {
      String[] shoppingList = savedInstanceState.getStringArray(ARRAY_KEY);
      nextProductIndex = savedInstanceState.getInt(INDEX_KEY);
      for (int i = 0; i < nextProductIndex; i++) {
        mShoppingList[i].setText(shoppingList[i]);
        mShoppingList[i].setVisibility(View.VISIBLE);
      }
    }

    mAddProduct = (Button) findViewById(R.id.add_item);
  }

  public void addItem(View view) {

    Intent intent = new Intent(this, ProductSelectionActivity.class);
    startActivityForResult(intent, TEXT_REQUEST);
  }

  @Override
  // Save the states to avoid losing data on a restart
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);

    // We need to save the strings that are shown
    String[] shoppingList = new String[nextProductIndex];
    for (int i = 0; i < nextProductIndex; i++) {
      shoppingList[i] = mShoppingList[i].getText().toString();
    }
    outState.putStringArray(ARRAY_KEY, shoppingList);
    outState.putInt(INDEX_KEY, nextProductIndex);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    // Check if the result corresponds to our request
    if (requestCode == TEXT_REQUEST) {
      if (resultCode == RESULT_OK) {
        // Get the reply
        String reply = data.getStringExtra(ProductSelectionActivity.PRODUCT_SELECTION_REPLY);
        // Add the product if not already added
        addProduct(reply);
      }
    }
  }

  // Adds the product if not already added and make the TextView visible
  private void addProduct(String reply) {

    Log.d(LOG_TAG, reply);

    // We search in the currently added products
    for (int i = 0; i < nextProductIndex; i++) {
      if (reply.equals(mShoppingList[i].getText().toString()))
        return;
    }

    // The product is not in the array, we add it and make it visible
    mShoppingList[nextProductIndex].setText(reply);
    mShoppingList[nextProductIndex].setVisibility(View.VISIBLE);

    // Update product index
    nextProductIndex++;
  }
}
