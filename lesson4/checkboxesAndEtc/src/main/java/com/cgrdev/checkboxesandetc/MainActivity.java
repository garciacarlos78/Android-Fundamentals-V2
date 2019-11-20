package com.cgrdev.checkboxesandetc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.zip.CheckedInputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayToast(View view) {

        // StringBuffer to include all of the checked checkboxes
        StringBuffer toppings = new StringBuffer();

        // Check which checkboxes are checked and add its content to the StringBuffer if checked
        if (((CheckBox) findViewById(R.id.check_chocolate)).isChecked())
            toppings.append(" " + getString(R.string.chocolate_syrup));
        if (((CheckBox) findViewById(R.id.check_sprinkies)).isChecked())
            toppings.append(" " + getString(R.string.sprinkles));
        if (((CheckBox) findViewById(R.id.check_nuts)).isChecked())
            toppings.append(" " + getString(R.string.crushed_nuts));
        if (((CheckBox) findViewById(R.id.check_cherries)).isChecked())
            toppings.append(" " + getString(R.string.cherries));
        if (((CheckBox) findViewById(R.id.check_cookies)).isChecked())
            toppings.append(" " + getString(R.string.oreo_cookie_crumbies));

        // Check if there is any topping
        if (toppings.length() == 0) toppings.append(getString(R.string.no_toppings));
        else {
            // We insert "Toppings: " at the beginning of the string
            toppings.insert(0, "Toppings:");
        }

        // Show the toast
        Toast.makeText(getApplicationContext(), toppings.toString(), Toast.LENGTH_LONG).show();
    }
}
