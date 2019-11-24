package com.cgrdev.threechildren;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // An image has been clicked
    public void openChild(View view) {

        Intent intent = null;

        // Check which image has been clicked
        switch (view.getId()){
            case R.id.donut_image:
                // Prepare intent to open donut activity
                intent = new Intent(this, DonutActivity.class);
                break;
            case R.id.froyo_image:
                // Prepare intent to open froyo activity
                intent = new Intent (this, FroyoActivity.class);
                break;
            case R.id.icecream_image:
                // Prepare intent to open icecream activity
                intent = new Intent (this, IcecreamActivity.class);
                break;
        }

        // Open selected activity
        if (intent != null) startActivity(intent);
    }
}
