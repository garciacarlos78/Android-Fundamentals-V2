package com.cgrdev.recyclerview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private final LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wordListSize = mWordList.size();
                // Add a new word to the wordList
                mWordList.addLast("+ Word " + wordListSize);
                // Notify the adapter that the data has changed
                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
                // Scroll to the bottom
                mRecyclerView.smoothScrollToPosition(wordListSize);
            }
        });

        // Put initial data into the word list
        for (int i = 0; i < 20; i++) mWordList.addLast("Word " + i);

        // Get a handle to the RecyclerView
        mRecyclerView = findViewById(R.id.recyclerview);

        // Create an adapter and supply the data to be displayed and the listener
        mAdapter = new WordListAdapter(this, mWordList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the position clicked
                int position = mRecyclerView.getChildAdapterPosition(v);
                // Change the list element in that position
                mWordList.set(position, "New clicked! " + mWordList.get(position));
                // Notify the change to the adapter
                mRecyclerView.getAdapter().notifyDataSetChanged();
            }
        });

        // Connect the adapter with the RecyclerView
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        if (id == R.id.action_reset) {

            // Empty the words list
            mWordList.clear();

            // Put initial data into the word list
            for (int i = 0; i < 20; i++) mWordList.addLast("Word " + i);

            // Notify the adapter that the data has changed
            mRecyclerView.getAdapter().notifyDataSetChanged();

            // Scroll to the top
            mRecyclerView.smoothScrollToPosition(0);

            // Inform the user that the data has been reset
            Toast.makeText(this, "List of words reset", Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
