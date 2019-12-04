/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.materialme;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

/***
 * Main Activity for the Material Me app, a mock sports news application
 * with poor design choices.
 */
public class MainActivity extends AppCompatActivity {

    private static final String BUNDLE_KEY = "sports_data";
    // Member variables.
    private RecyclerView mRecyclerView;
    private ArrayList<Sport> mSportsData;
    private SportsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView);

        // Get integer from integers.xml, to set the number of columns in the GridLayoutManager
        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);


        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        // Initialize the ArrayList that will contain the data.
        if (savedInstanceState != null) {
            mSportsData = savedInstanceState.getParcelableArrayList(BUNDLE_KEY);
        } else {
            mSportsData = new ArrayList<>();
        }

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new SportsAdapter(this, mSportsData);
        mRecyclerView.setAdapter(mAdapter);

        // Initialize only the first time
        if (savedInstanceState == null) initializeData();

        // If the display is in landscape, that is, with two columns, swipe action will be disabled
        int swipeDirs = (gridColumnCount > 1) ?
                0 : ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

        // Implement swipe to dismiss or move
        ItemTouchHelper helper = new ItemTouchHelper
                (new ItemTouchHelper.SimpleCallback
                        (ItemTouchHelper.LEFT |
                                ItemTouchHelper.RIGHT |
                                ItemTouchHelper.DOWN |
                                ItemTouchHelper.UP,
                                swipeDirs) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        // Get original and target index positions
                        int from = viewHolder.getAdapterPosition();
                        int to = target.getAdapterPosition();
                        // Swap items in the dataset
                        Collections.swap(mSportsData, from, to);
                        // Notify the movement to the adapter
                        mAdapter.notifyItemMoved(from, to);
                        return true;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        // Remove swiped card from dataset
                        mSportsData.remove(viewHolder.getAdapterPosition());
                        // Allow the RecyclerView to animate the deletion
                        mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                    }
                });
        // Add the ItemTouchHelper to the RecyclerView
        helper.attachToRecyclerView(mRecyclerView);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(BUNDLE_KEY, mSportsData);
    }

    /**
     * Initialize the sports data from resources.
     */
    private void initializeData() {
        // Get the resources from the XML file.
        String[] sportsList = getResources()
                .getStringArray(R.array.sports_titles);
        String[] sportsInfo = getResources()
                .getStringArray(R.array.sports_info);
        TypedArray sportsImageResources = getResources().obtainTypedArray(R.array.sports_images);
        String[] sportsDescription = getResources().getStringArray(R.array.sports_descriptions);

        // Clear the existing data (to avoid duplication).
        mSportsData.clear();

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for (int i = 0; i < sportsList.length; i++) {
            mSportsData.add(new Sport(sportsList[i], sportsInfo[i], sportsImageResources.getResourceId(i, 0), sportsDescription[i]));
        }

        // Clean up data in the typed array
        sportsImageResources.recycle();

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }

    public void resetSports(View view) {
        initializeData();
    }
}
