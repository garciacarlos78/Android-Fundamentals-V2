package com.cgrdev.navdrawerchallenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        // Close drawer
        drawer.closeDrawer(GravityCompat.START);

        // Handle navigation view item clicks
        switch (menuItem.getItemId()) {
            case R.id.nav_camera:
                Toast.makeText(this, "Import clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_gallery:
                Toast.makeText(this, "Gallery clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_slideshow:
                Toast.makeText(this, "Slideshow clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_tools:
                Toast.makeText(this, "Tools clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_share:
                Toast.makeText(this, "Share clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_send:
                Toast.makeText(this, "Send clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                Toast.makeText(this, "Unhandled event clicked", Toast.LENGTH_SHORT).show();
                return false;
        }
    }
}
