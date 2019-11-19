package com.cgrdev.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // We get the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // We check if there is the message
        if (message == null) message = "You have not ordered anything yet.";
        else message = "Order: " + message;

        // We set the message to the textview
        TextView textView = findViewById(R.id.order_textview);
        textView.setText(message);
    }
}
