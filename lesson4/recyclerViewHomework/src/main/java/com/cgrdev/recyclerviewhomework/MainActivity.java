package com.cgrdev.recyclerviewhomework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecipeListAdapter recipeListAdapter;

    // Data set
    private LinkedList<String> recipeTitle = new LinkedList<>();
    private LinkedList<String> recipeDescription = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a handle to the RecyclerView in the layout
        mRecyclerView = findViewById(R.id.recyclerView);

        // Initialize the data set
        initializeDataset();

        // Create an adapter and supply the data
        recipeListAdapter = new RecipeListAdapter(this, recipeTitle, recipeDescription);

        // Connect the adapter with the RecyclerView
        mRecyclerView.setAdapter(recipeListAdapter);

        // Give the RecyclerView a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    private void initializeDataset() {
        recipeTitle.add(getString(R.string.recipe_header_1));
        recipeTitle.add(getString(R.string.recipe_header_2));
        recipeTitle.add(getString(R.string.recipe_header_3));
        recipeTitle.add(getString(R.string.recipe_header_4));
        recipeTitle.add(getString(R.string.recipe_header_5));
        recipeTitle.add(getString(R.string.recipe_header_6));
        recipeTitle.add(getString(R.string.recipe_header_7));
        recipeTitle.add(getString(R.string.recipe_header_8));
        recipeTitle.add(getString(R.string.recipe_header_9));
        recipeTitle.add(getString(R.string.recipe_header_10));

        recipeDescription.add(getString(R.string.recipe_content_1));
        recipeDescription.add(getString(R.string.recipe_content_2));
        recipeDescription.add(getString(R.string.recipe_content_3));
        recipeDescription.add(getString(R.string.recipe_content_4));
        recipeDescription.add(getString(R.string.recipe_content_5));
        recipeDescription.add(getString(R.string.recipe_content_6));
        recipeDescription.add(getString(R.string.recipe_content_7));
        recipeDescription.add(getString(R.string.recipe_content_8));
        recipeDescription.add(getString(R.string.recipe_content_9));
        recipeDescription.add(getString(R.string.recipe_content_10));
    }
}
