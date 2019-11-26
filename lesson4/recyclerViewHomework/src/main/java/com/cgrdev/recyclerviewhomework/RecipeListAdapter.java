package com.cgrdev.recyclerviewhomework;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    private LayoutInflater mInflater;
    private LinkedList<String> mRecipeTitleList;
    private LinkedList<String> mRecipeDescriptionList;

    public RecipeListAdapter(Context context, LinkedList<String> recipeTitleList, LinkedList<String> recipeDescriptionList) {
        mInflater = LayoutInflater.from(context);
        mRecipeTitleList = recipeTitleList;
        mRecipeDescriptionList = recipeDescriptionList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate an item view
        View mItemView = mInflater.inflate(R.layout.recipelist_item, parent, false);
        return new RecipeViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {

        // Retrieve the data for that position
        String currentRecipeTitle = mRecipeTitleList.get(position);
        String currentRecipeDescription = mRecipeDescriptionList.get(position);

        // Add the data to the view
        holder.recipeTitleItemView.setText(currentRecipeTitle);
        holder.recipeDescriptionItemView.setText(currentRecipeDescription);

    }

    @Override
    public int getItemCount() {
        return mRecipeTitleList.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView recipeTitleItemView;
        public final TextView recipeDescriptionItemView;
        final RecipeListAdapter recipeListAdapter;

        public RecipeViewHolder(@NonNull View itemView, RecipeListAdapter adapter) {
            super(itemView);
            recipeTitleItemView = itemView.findViewById(R.id.recipe_title);
            recipeDescriptionItemView = itemView.findViewById(R.id.recipe_description);
            recipeListAdapter = adapter;

            // Connect the onClickListener
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            // Create an intent
            Intent intent = new Intent(v.getContext(), RecipeDetail.class);
            // Put the position as extra
            intent.putExtra("position", getAdapterPosition());
            // Start the activity
            v.getContext().startActivity(intent);
        }
    }
}
