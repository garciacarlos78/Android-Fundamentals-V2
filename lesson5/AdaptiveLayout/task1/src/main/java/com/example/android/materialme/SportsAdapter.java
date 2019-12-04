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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/***
 * The adapter class for the RecyclerView, contains the sports data.
 */
class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.ViewHolder> {

    // Member variables.
    private ArrayList<Sport> mSportsData;
    private Context mContext;

    /**
     * Constructor that passes in the sports data and the context.
     *
     * @param sportsData ArrayList containing the sports data.
     * @param context    Context of the application.
     */
    SportsAdapter(Context context, ArrayList<Sport> sportsData) {
        this.mSportsData = sportsData;
        this.mContext = context;
    }


    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent   The ViewGroup into which the new View will be added
     *                 after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */
    @Override
    public SportsAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder   The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(SportsAdapter.ViewHolder holder,
                                 int position) {
        // Get current sport.
        Sport currentSport = mSportsData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentSport);
    }

    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return mSportsData.size();
    }


    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;

        // Member variable for the ImageView
        private ImageView mSportsImage;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mSportsImage = itemView.findViewById(R.id.sportsImage);

            // Set the OnClickListener to the entire view
            itemView.setOnClickListener(this);
        }

        void bindTo(Sport currentSport) {
            // Populate the textviews with data.
            mTitleText.setText(currentSport.getTitle());
            mInfoText.setText(currentSport.getInfo());

            // Populate the imageview with Glide
            Glide.with(mContext).load(currentSport.getImageResource()).into(mSportsImage);

/*
            // Populate the imageview without Glide
            // It does not run out of memory
            Drawable image = itemView.getResources().getDrawable(currentSport.getImageResource());
            mSportsImage.setImageDrawable(image);
*/
        }

        @Override
        public void onClick(View view) {

            // Get Sport clicked
            Sport currentSport = mSportsData.get(getAdapterPosition());

            // Construct transition intent
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("title", currentSport.getTitle());
            intent.putExtra("image_resource", currentSport.getImageResource());
            intent.putExtra("sport_description", currentSport.getDescription());

            // Create the options necessary to show transition

            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext,
//                    new Pair<>(view.findViewById(R.id.sportsImage), DetailActivity.VIEW_NAME_HEADER_IMAGE));
                    new Pair<>(view.findViewById(R.id.sportsImage), DetailActivity.VIEW_NAME_HEADER_IMAGE),
                    new Pair<>(view.findViewById(R.id.title), DetailActivity.VIEW_NAME_HEADER_SPORT_NAME),
                    new Pair<>(view.findViewById(R.id.newsTitle), DetailActivity.VIEW_NAME_HEADER_NEWS_SUBTITLE));
            ActivityCompat.startActivity(mContext, intent, activityOptionsCompat.toBundle());

/*
            // Launch DetailActivity
            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra("title", currentSport.getTitle());
            detailIntent.putExtra("image_resource", currentSport.getImageResource());
            detailIntent.putExtra("sport_description", currentSport.getDescription());
            mContext.startActivity(detailIntent);
*/
        }
    }
}
