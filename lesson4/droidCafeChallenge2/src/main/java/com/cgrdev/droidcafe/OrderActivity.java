package com.cgrdev.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity
        implements AdapterView .OnItemSelectedListener {

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

        // Create the spinner
        Spinner spinner = findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        // Create ArrayAdaptder using the string array and default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.labels_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }

        // ----------------- Coding Challenge 2 ------------------

        // Get the EditText
        final EditText phoneText = findViewById(R.id.phone_text);
        if (phoneText != null)
            phoneText.setOnEditorActionListener(
                    new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                            // If view is found, set the listener for phoneText
                            boolean handled = false;
                            if (actionId == EditorInfo.IME_ACTION_SEND) {
                                dialNumber(phoneText);
                                handled = true;
                            }
                            return handled;
                        }
                    }
            );


    }

    // We get the EditText directly as a parameter, instead of searching it again as proposed in codelab
    private void dialNumber(EditText phoneText) {
        String phoneNum = null;

        // If phoneText is not null, concatenate "tel: " with the phone number string
        if (phoneText != null) phoneNum = "tel: " +
                phoneText.getText().toString();

        // Optional changed: instead of using Log.d, I use Toast
        displayToast("Dial number: " + phoneNum);

        // Specify the intent (implicit)
        Intent intent = new Intent(Intent.ACTION_DIAL);

        // Set the data for the intent as the phone number
        intent.setData(Uri.parse(phoneNum));

        // If the intent resolves to a package (app), start the activity with the intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            displayToast("ImplicitIntents: Can't handle this!");
        }

    }

    // ----------------- Coding Challenge 2 end ------------------

    public void onRadioButtonClicked(View view) {

        // Is the button  now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // As we only do things if it's checked, we ask first button's status
        if (checked){
            // Check which radio button was clicked
            switch (view.getId()){
                case R.id.sameday:
                    displayToast(getString(R.string.same_day_messenger_service));
                    break;
                case R.id.nextday:
                    displayToast(getString(R.string.next_day_ground_delivery));
                    break;
                case R.id.pickup:
                    displayToast(getString(R.string.pick_up));
                    break;
                default:
                    displayToast("Invalid option.");
                    break;
            }
        }

    }

    public void displayToast (String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        String spinnerLabel = adapterView.getItemAtPosition(position).toString();
        displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
